package com.lzt.operate.code.generator.app.controllers.business;

import com.lzt.operate.code.generator.app.assists.DatabaseAssist;
import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.DataColumnStatus;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.ConnectionConfigService;
import com.lzt.operate.code.generator.dao.service.DataColumnService;
import com.lzt.operate.code.generator.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.DataColumnServiceImpl;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataColumn;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.SerializableData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/dataColumn")
@Api(tags = {"数据库表列管理"})
public class DataColumnController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "数据表列配置管理/";

	private ConnectionConfigService connectionConfigService;

	private DataColumnService dataColumnService;

	@Autowired
	public DataColumnController(CustomJsonWebTokenConfig customJsonWebTokenConfig, ConnectionConfigServiceImpl connectionConfigServiceImpl, DataColumnServiceImpl dataColumnService) {
		super(customJsonWebTokenConfig);

		this.connectionConfigService = connectionConfigServiceImpl;
		this.dataColumnService = dataColumnService;
	}

	public ConnectionConfigService getConnectionConfigService() {
		Optional<ConnectionConfigService> optional = Optional.ofNullable(this.connectionConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("ConnectionConfigService获取失败");
	}

	public DataColumnService getDataColumnService() {
		Optional<DataColumnService> optional = Optional.ofNullable(this.dataColumnService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("DataColumnService获取失败");
	}

	@ApiOperation(value = "数据库表列列表", notes = "数据库表列列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_COLUMN_LIST, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_COLUMN_TABLE_NAME),
			@ApiJsonProperty(name = GlobalString.DATA_COLUMN_COLUMN_NAME)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_COLUMN_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据库表列列表", description = "数据库表列列表", tag = "75bcfe2e-849b-4d26-83ab-fba103e3b0a9")
	public ResultListData list(@RequestBody Map<String, Serializable> json) throws Exception {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.pageDataEmpty();
		}

		String tableName = paramJson.getStringByKey(GlobalString.DATA_COLUMN_TABLE_NAME);

		if (StringAssist.isNullOrEmpty(tableName)) {
			return this.listDataEmpty();
		}

		String name = paramJson.getStringByKey(GlobalString.DATA_COLUMN_COLUMN_NAME);

		Optional<ConnectionConfig> optional = this.getConnectionConfigService()
												  .get(connectionConfigId);

		if (optional.isPresent()) {
			ConnectionConfig connectionConfig = optional.get();

			List<DataColumn> listDataColumn = DatabaseAssist.listTableColumn(connectionConfig, tableName);

			if (!StringAssist.isNullOrEmpty(name)) {
				listDataColumn = listDataColumn.stream()
											   .filter(o -> StringAssist.contains(o.getColumnName(), name, true))
											   .collect(Collectors.toList());
			}

			List<DataColumn> listData = this.listDataColumn(connectionConfigId, tableName, listDataColumn.stream()
																										 .map(DataColumn::getColumnName)
																										 .collect(Collectors
																												 .toList()));

			List<SerializableData> list = listDataColumn
					.stream()
					.map(o -> {
						List<IGetter<DataColumn>> getterList = new ArrayList<>();

						getterList.add(DataColumn::getConnectionConfigId);
						getterList.add(DataColumn::getTableName);
						getterList.add(DataColumn::getColumnName);
						getterList.add(DataColumn::getColumnType);
						getterList.add(DataColumn::getJavaType);
						getterList.add(DataColumn::getAliasName);
						getterList.add(DataColumn::getTypeHandler);
						getterList.add(DataColumn::getChannel);
						getterList.add(DataColumn::getChannelNote);
						getterList.add(DataColumn::getStatus);
						getterList.add(DataColumn::getStatusNote);
						getterList.add(DataColumn::getCreateTime);
						getterList.add(DataColumn::getUpdateTime);

						DataColumn dataColumn = o;

						Optional<DataColumn> optionalDataColumn = listData.stream()
																		  .filter(item -> item.getColumnName()
																							  .equals(o.getColumnName()))
																		  .findFirst();

						if (optionalDataColumn.isPresent()) {
							dataColumn = optionalDataColumn.get();
						}

						dataColumn.setConnectionConfigId(connectionConfigId);
						dataColumn.setTableName(tableName);
						dataColumn.setChannel(Channel.CodeGenerator);

						SerializableData data = SerializableData.toSerializableData(dataColumn, getterList);

						data.append(ReflectAssist.getFriendlyIdName(DataColumn.class), o
								.getId());

						return data;
					})
					.collect(Collectors.toList());

			return this.listData(list);
		}

		return this.listDataEmpty();
	}

	@ApiOperation(value = "获取定时列信息", notes = "获取定时列信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_COLUMN_SET, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_COLUMN_TABLE_NAME),
			@ApiJsonProperty(name = GlobalString.DATA_COLUMN_COLUMN_NAME)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_COLUMN_SET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "获取定时列信息", description = "获取定时列信息", tag = "303175ed-6c19-42ab-8690-f52a9a23120b")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) throws Exception {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID).toLong();
		String tableName = paramJson.getStringByKey(GlobalString.DATA_COLUMN_TABLE_NAME);
		String name = paramJson.getStringByKey(GlobalString.DATA_COLUMN_COLUMN_NAME);

		ExecutiveResult<DataColumn> result = getDataColumn(connectionConfigId, tableName, name);

		if (!result.getSuccess()) {
			return this.fail(result.getCode());
		}

		DataColumn dataColumn = result.getData();

		List<IGetter<DataColumn>> getterList = new ArrayList<>();

		getterList.add(DataColumn::getConnectionConfigId);
		getterList.add(DataColumn::getTableName);
		getterList.add(DataColumn::getColumnName);
		getterList.add(DataColumn::getColumnType);
		getterList.add(DataColumn::getJavaType);
		getterList.add(DataColumn::getAliasName);
		getterList.add(DataColumn::getTypeHandler);
		getterList.add(DataColumn::getChannel);
		getterList.add(DataColumn::getChannelNote);
		getterList.add(DataColumn::getStatus);
		getterList.add(DataColumn::getStatusNote);
		getterList.add(DataColumn::getCreateTime);
		getterList.add(DataColumn::getUpdateTime);

		dataColumn = this.getDataColumnService().fillFromDataColumnConfig(dataColumn);

		SerializableData data = SerializableData.toSerializableData(dataColumn, getterList);

		data.append(ReflectAssist.getFriendlyIdName(DataColumn.class), dataColumn.getId());

		return this.singleData(data);
	}

	@ApiOperation(value = "设置定时列信息", notes = "设置定时列信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_COLUMN_SET, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_COLUMN_TABLE_NAME),
			@ApiJsonProperty(name = GlobalString.DATA_COLUMN_COLUMN_NAME),
			@ApiJsonProperty(name = GlobalString.DATA_COLUMN_ALIAS_NAME),
			@ApiJsonProperty(name = GlobalString.DATA_COLUMN_JAVA_TYPE),
			@ApiJsonProperty(name = GlobalString.DATA_COLUMN_TYPE_HANDLER)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_COLUMN_SET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/set", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "设置定时列信息", description = "设置定时列信息", tag = "d455d085-d60a-4c4a-b3f7-5e6c7a9bf3fc")
	public BaseResultData set(@RequestBody Map<String, Serializable> json) throws Exception {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID).toLong();
		String tableName = paramJson.getStringByKey(GlobalString.DATA_COLUMN_TABLE_NAME);
		String name = paramJson.getStringByKey(GlobalString.DATA_COLUMN_COLUMN_NAME);
		String aliasName = paramJson.getStringByKey(GlobalString.DATA_COLUMN_ALIAS_NAME);
		String javaType = paramJson.getStringByKey(GlobalString.DATA_COLUMN_JAVA_TYPE);
		String typeHandler = paramJson.getStringByKey(GlobalString.DATA_COLUMN_TYPE_HANDLER);

		ExecutiveResult<DataColumn> result = getDataColumn(connectionConfigId, tableName, name);

		if (!result.getSuccess()) {
			return this.fail(result.getCode());
		}

		long operatorId = getOperatorId();

		DataColumn dataColumn = result.getData();

		Integer status = dataColumn.getStatus();

		if (status.equals(DataColumnStatus.AlreadyCustom.getFlag())) {

			dataColumn.setAliasName(aliasName);
			dataColumn.setJavaType(javaType);
			dataColumn.setTypeHandler(typeHandler);
			dataColumn.setStatus(DataColumnStatus.AlreadyCustom, DataColumnStatus::getFlag, DataColumnStatus::getName);
			dataColumn.setUpdateTime(LocalDateTime.now());
			dataColumn.setUpdateOperatorId(operatorId);

			this.getDataColumnService().save(dataColumn);
		} else {
			dataColumn.setConnectionConfigId(connectionConfigId);
			dataColumn.setTableName(tableName);
			dataColumn.setAliasName(aliasName);
			dataColumn.setJavaType(javaType);
			dataColumn.setTypeHandler(typeHandler);
			dataColumn.setStatus(DataColumnStatus.AlreadyCustom, DataColumnStatus::getFlag, DataColumnStatus::getName);
			dataColumn.setCreateOperatorId(operatorId);
			dataColumn.setUpdateOperatorId(operatorId);

			this.getDataColumnService().save(dataColumn);
		}

		SerializableData serializableData = new SerializableData();

		serializableData.append(GlobalString.DATA_COLUMN_ID, ConvertAssist.longToString(dataColumn
				.getId()));

		return this.singleData(serializableData);
	}

	private ExecutiveResult<ConnectionConfig> checkConnection(Long connectionConfigId, String tableName) throws Exception {
		if (StringAssist.isNullOrEmpty(tableName)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.toMessage(StringAssist
					.merge(GlobalString.CONNECTION_CONFIG_ID, "请提交数据库表名")));
		}

		if (connectionConfigId.equals(ConstantCollection.ZERO_LONG)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.toMessage(StringAssist
					.merge(GlobalString.CONNECTION_CONFIG_ID, "请提交数据连接标识")));
		}

		Optional<ConnectionConfig> optionalConnectionConfig = this.getConnectionConfigService().get(connectionConfigId);

		if (!optionalConnectionConfig.isPresent()) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.toMessage(StringAssist
					.merge(GlobalString.CONNECTION_CONFIG_ID, "数据连接校验失败")));
		}

		ConnectionConfig connectionConfig = optionalConnectionConfig.get();

		boolean tableExist = DatabaseAssist.checkTableNameExist(connectionConfig, tableName);

		if (!tableExist) {
			return new ExecutiveResult<>(ReturnDataCode.DataError.toMessage("数据库表不存在"));
		}

		return new ExecutiveResult<>(ReturnDataCode.Ok, connectionConfig);
	}

	private List<DataColumn> listDataColumn(Long connectionConfigId, String tableName, Collection<String> nameCollection) throws Exception {
		ExecutiveResult<ConnectionConfig> resultCheckConnection = this.checkConnection(connectionConfigId, tableName);

		if (!resultCheckConnection.getSuccess()) {
			return new ArrayList<>();
		}

		ConnectionConfig connectionConfig = resultCheckConnection.getData();

		List<DataColumn> list = this.getDataColumnService()
									.findByConnectionConfigIdAndTableNameAndNames(connectionConfigId, tableName, nameCollection);

		List<DataColumn> listDataColumn = DatabaseAssist.listTableColumn(connectionConfig, tableName);

		List<DataColumn> listResult = new ArrayList<>();

		for (DataColumn d : listDataColumn) {
			Optional<DataColumn> optionalDataColumn = list.stream()
														  .filter(o -> o.getColumnName().equals(d.getColumnName()))
														  .findFirst();

			if (optionalDataColumn.isPresent()) {
				DataColumn data = optionalDataColumn.get();

				data.setColumnName(data.getColumnName());
				data.setColumnType(data.getColumnType());
				data.setStatus(DataColumnStatus.AlreadyCustom, DataColumnStatus::getFlag, DataColumnStatus::getName);

				listResult.add(data);
			} else {
				d.setStatus(DataColumnStatus.NoCustom, DataColumnStatus::getFlag, DataColumnStatus::getName);

				listResult.add(d);
			}
		}

		return listResult;
	}

	private ExecutiveResult<DataColumn> getDataColumn(Long connectionConfigId, String tableName, String name) throws Exception {
		ExecutiveResult<ConnectionConfig> resultCheckConnection = this.checkConnection(connectionConfigId, tableName);

		if (!resultCheckConnection.getSuccess()) {
			return new ExecutiveResult<>(resultCheckConnection.getCode());
		}

		ConnectionConfig connectionConfig = resultCheckConnection.getData();

		Optional<DataColumn> optional = this.getDataColumnService()
											.findByConnectionConfigIdAndTableNameAndName(connectionConfigId, tableName, name);

		DataColumn dataColumn;

		if (optional.isPresent()) {
			dataColumn = optional.get();

			dataColumn.setStatus(DataColumnStatus.AlreadyCustom, DataColumnStatus::getFlag, DataColumnStatus::getName);
		} else {
			dataColumn = new DataColumn();

			dataColumn.setStatus(DataColumnStatus.NoCustom, DataColumnStatus::getFlag, DataColumnStatus::getName);
		}

		List<DataColumn> listDataColumn = DatabaseAssist.listTableColumn(connectionConfig, tableName);

		Optional<DataColumn> optionalDataColumn = listDataColumn.stream()
																.filter(o -> o.getColumnName().equals(name))
																.findFirst();

		if (optionalDataColumn.isPresent()) {
			DataColumn data = optionalDataColumn.get();

			dataColumn.setColumnName(data.getColumnName());
			dataColumn.setColumnType(data.getColumnType());

			return new ExecutiveResult<>(ReturnDataCode.Ok, dataColumn);
		} else {
			return new ExecutiveResult<>(ReturnDataCode.DataError.toMessage("数据库表列不存在"));
		}
	}

}
