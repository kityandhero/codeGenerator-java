package com.lzt.operate.code.generator.app.controllers.business;

import com.lzt.operate.code.generator.app.assists.ConnectionConfigAssist;
import com.lzt.operate.code.generator.app.assists.DatabaseAssist;
import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.ConnectionConfigStatus;
import com.lzt.operate.code.generator.common.enums.ConnectionType;
import com.lzt.operate.code.generator.common.enums.DataBaseGeneratorConfigStatus;
import com.lzt.operate.code.generator.common.enums.DatabaseEncoding;
import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.DataColumnService;
import com.lzt.operate.code.generator.dao.service.DataTableGeneratorConfigService;
import com.lzt.operate.code.generator.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.DataBaseGeneratorConfigServiceImpl;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DatabaseGeneratorConfig;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.SerializableData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/connectionConfig")
@Api(tags = {"数据库连接"})
public class ConnectionConfigController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "数据库连接/";

	private final ConnectionConfigAssist connectionConfigAssist;

	@Autowired
	public ConnectionConfigController(
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			ConnectionConfigServiceImpl connectionConfigService,
			DataBaseGeneratorConfigServiceImpl databaseGeneratorConfigService,
			DataTableGeneratorConfigService dataTableGeneratorConfigService,
			DataColumnService dataColumnService) {
		super(customJsonWebTokenConfig);

		this.connectionConfigAssist = new ConnectionConfigAssist(
				connectionConfigService,
				databaseGeneratorConfigService,
				dataTableGeneratorConfigService,
				dataColumnService);
	}

	@ApiOperation(value = "连接分页列表", notes = "数据库连接分页列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_PAGE_LIST, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_NAME),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_PAGE_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/pageList", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "连接分页列表", description = "数据库连接分页列表", tag = "f201e035-bfcc-4eee-a263-70fdc2968e64", config = {"显示路径", "显示子权限"})
	public ResultListData pageList(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		String name = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_NAME);

		Specification<ConnectionConfig> specification = new Specification<ConnectionConfig>() {

			private static final long serialVersionUID = 8024589834916855002L;

			@Override
			public Predicate toPredicate(@NonNull Root<ConnectionConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!StringAssist.isNullOrEmpty(name)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(ConnectionConfig::getName)), StringAssist
							.merge("%", name, "%")));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(ConnectionConfig::getCreateTime));

		Page<ConnectionConfig> result = this.connectionConfigAssist.getConnectionConfigService()
																   .page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<ConnectionConfig>> getterList = new ArrayList<>();

												getterList.add(ConnectionConfig::getName);
												getterList.add(ConnectionConfig::getDescription);
												getterList.add(ConnectionConfig::getConnectionType);
												getterList.add(ConnectionConfig::getDatabaseType);
												getterList.add(ConnectionConfig::getHost);
												getterList.add(ConnectionConfig::getPort);
												getterList.add(ConnectionConfig::getSchema);
												getterList.add(ConnectionConfig::getEncoding);
												getterList.add(ConnectionConfig::getChannel);
												getterList.add(ConnectionConfig::getChannelNote);
												getterList.add(ConnectionConfig::getStatus);
												getterList.add(ConnectionConfig::getStatusNote);
												getterList.add(ConnectionConfig::getCreateTime);
												getterList.add(ConnectionConfig::getUpdateTime);

												SerializableData data = SerializableData.toSerializableData(o, getterList);

												data.append(ReflectAssist.getFriendlyIdName(ConnectionConfig.class), o.getId());

												return data;
											})
											.collect(Collectors.toList());

		int pageIndex = result.getNumber();
		long totalPages = result.getTotalPages();

		return this.pageData(list, pageIndex, pageSize, totalPages);
	}

	@ApiOperation(value = "获取连接", notes = "获取数据库连接", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_GET, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "连接详情", description = "获取数据库连接", tag = "6b0d1fbe-9e31-48ce-86ab-5dc1ebe387db")
	public ResultSingleData get(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		Optional<ConnectionConfig> result = this.connectionConfigAssist.getConnectionConfig(connectionConfigId);

		if (result.isPresent()) {
			ConnectionConfig connectionConfig = result.get();

			return decorateSingleData(connectionConfig);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

	@ApiOperation(value = "创建连接", notes = "创建数据库连接", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_ADD_BASIC_INFO, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_NAME),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_DESCRIPTION),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_DATABASE_TYPE),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_HOST),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SCHEMA),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_USERNAME),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_PASSWORD),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ENCODING),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_LOCAL_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_REMOTE_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_HOST),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_USER),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_PASSWORD)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_ADD_BASIC_INFO)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/addBasicInfo", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "新增连接", description = "创建数据库连接", tag = "94520b18-bcb8-499c-90fd-afb82f45f3f0")
	public ResultSingleData addBasicInfo(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		ExecutiveResult<ConnectionConfig> result = this.connectionConfigAssist.createConnectionConfig(paramJson);

		if (result.getSuccess()) {
			ConnectionConfig data = result.getData();

			data.setStatus(ConnectionConfigStatus.Enabled, ConnectionConfigStatus::getFlag, ConnectionConfigStatus::getName);
			data.setChannel(Channel.CodeGenerator);

			long operatorId = getOperatorId();

			data.setCreateOperatorId(operatorId);
			data.setUpdateOperatorId(operatorId);

			data = this.connectionConfigAssist.saveConnectionConfig(data);

			DatabaseGeneratorConfig databaseGeneratorConfig = new DatabaseGeneratorConfig();

			databaseGeneratorConfig.setConnectionConfigId(data.getId());
			databaseGeneratorConfig.setChannel(Channel.CodeGenerator);
			databaseGeneratorConfig.setCreateOperatorId(operatorId);
			databaseGeneratorConfig.setStatus(DataBaseGeneratorConfigStatus.EFFECTIVE, DataBaseGeneratorConfigStatus::getFlag, DataBaseGeneratorConfigStatus::getName);
			databaseGeneratorConfig.setUpdateOperatorId(operatorId);

			this.connectionConfigAssist.getDatabaseGeneratorConfigService().save(databaseGeneratorConfig);

			this.connectionConfigAssist.getDatabaseGeneratorConfigService()
									   .changeConnectorJarPathByConnectionConfig(data);

			return decorateSingleData(data);
		}

		return this.fail(result);
	}

	@SuppressWarnings("DuplicatedCode")
	@ApiOperation(value = "更新连接", notes = "更新数据库连接", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_UPDATE_BASIC_INFO, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_NAME),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_DATABASE_TYPE),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_HOST),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SCHEMA),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_USERNAME),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_PASSWORD),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ENCODING),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_LOCAL_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_REMOTE_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_HOST),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_USER),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_PASSWORD)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_UPDATE_BASIC_INFO)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/updateBasicInfo", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "更新基本信息", description = "更新数据库连接", tag = "3fab7782-4641-4e8b-832c-3996ddc61b3f")
	public ResultSingleData updateBasicInfo(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_ID, "数据无效");
		}

		String name = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_NAME);

		if (StringAssist.isNullOrEmpty(name)) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_NAME, "不能为空值");
		}

		int connectionType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, ConnectionType.TCP_IP
				.getFlag().toString()).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(ConnectionType.values()), ConnectionType::getFlag, connectionType)) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值");
		}

		int databaseType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DATABASE_TYPE).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(DatabaseType.values()), DatabaseType::getFlag, databaseType)) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值");
		}

		int encoding = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ENCODING).toInt();

		if (!EnumAssist.existTargetValue(DatabaseEncoding.valuesToList(), DatabaseEncoding::getFlag, encoding)) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_ENCODING, "允许范围之外的值");
		}

		Optional<ConnectionConfig> result = this.connectionConfigAssist.getConnectionConfig(connectionConfigId);

		if (result.isPresent()) {
			ConnectionConfig data = result.get();

			Integer databaseTypePre = data.getDatabaseType();

			String description = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_DESCRIPTION);
			String host = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_HOST);
			String port = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_PORT);
			String schema = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_SCHEMA);
			String username = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_USERNAME);
			String password = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_PASSWORD);
			String localPort = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_LOCAL_PORT);
			String remotePort = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_REMOTE_PORT);
			String sshPort = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_SSH_PORT);
			String sshHost = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_SSH_HOST);
			String sshUser = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_SSH_USER);
			String sshPassword = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_SSH_PASSWORD);

			data.setName(name);
			data.setDescription(description);
			data.setConnectionType(connectionType);
			data.setDatabaseType(databaseType);
			data.setHost(host);
			data.setPort(port);
			data.setSchema(schema);
			data.setUserName(username);
			data.setPassword(password);
			data.setEncoding(DatabaseEncoding.valueOfFlag(encoding).orElse(DatabaseEncoding.UTF8));
			data.setLocalPort(localPort);
			data.setRemotePort(remotePort);
			data.setSshPort(sshPort);
			data.setSshHost(sshHost);
			data.setSshUser(sshUser);
			data.setSshPassword(sshPassword);

			long operatorId = getOperatorId();

			data.setUpdateOperatorId(operatorId);

			ConnectionConfig saveResult = connectionConfigAssist.saveConnectionConfig(data);

			if (!databaseTypePre.equals(databaseType)) {
				this.connectionConfigAssist.getDatabaseGeneratorConfigService()
										   .changeConnectorJarPathByConnectionConfig(data);
			}

			return this.singleData(saveResult);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

	@ApiOperation(value = "移除连接", notes = "移除数据库连接", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_REMOVE, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_REMOVE)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/remove", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "移除连接", description = "移除数据库连接", tag = "17e57607-d519-4289-9b8a-949bbcff603e")
	public ResultSingleData remove(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_ID, "数据无效");
		}

		this.connectionConfigAssist.deleteById(connectionConfigId);

		return this.success();
	}

	@ApiOperation(value = "打开数据库连接", notes = "打开数据库连接", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_OPEN_CONNECTION, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_OPEN_CONNECTION)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/tryConnection", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "打开数据库连接", description = "打开数据库连接", tag = "042ae8f2-a6c5-4c81-ba17-9acbb7e7b41f")
	public ResultSingleData tryConnection(@RequestBody Map<String, Object> json) throws Exception {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_ID, "数据无效");
		}

		Optional<ConnectionConfig> optional = this.connectionConfigAssist.getConnectionConfig(connectionConfigId);

		if (optional.isPresent()) {
			ConnectionConfig connectionConfig = optional.get();

			ExecutiveSimpleResult result = DatabaseAssist.tryConnection(connectionConfig);

			if (result.getSuccess()) {
				return this.success();
			} else {
				return this.fail(result.getCode());
			}
		}

		return this.fail(ReturnDataCode.Exception.toMessage("测试连接数据库失败"));
	}

	/**
	 * 修饰get返回数据
	 *
	 * @param connectionConfig connectionConfig
	 * @return BaseResultData
	 */
	private ResultSingleData decorateSingleData(@NotNull ConnectionConfig connectionConfig) {
		List<IGetter<ConnectionConfig>> getterList = new ArrayList<>();

		getterList.add(ConnectionConfig::getName);
		getterList.add(ConnectionConfig::getDescription);
		getterList.add(ConnectionConfig::getConnectionType);
		getterList.add(ConnectionConfig::getDatabaseType);
		getterList.add(ConnectionConfig::getHost);
		getterList.add(ConnectionConfig::getPort);
		getterList.add(ConnectionConfig::getSchema);
		getterList.add(ConnectionConfig::getUserName);
		getterList.add(ConnectionConfig::getPassword);
		getterList.add(ConnectionConfig::getEncoding);
		getterList.add(ConnectionConfig::getLocalPort);
		getterList.add(ConnectionConfig::getRemotePort);
		getterList.add(ConnectionConfig::getSshHost);
		getterList.add(ConnectionConfig::getSshPort);
		getterList.add(ConnectionConfig::getSshUser);
		getterList.add(ConnectionConfig::getSshPassword);
		getterList.add(ConnectionConfig::getChannel);
		getterList.add(ConnectionConfig::getChannelNote);
		getterList.add(ConnectionConfig::getStatus);
		getterList.add(ConnectionConfig::getStatusNote);
		getterList.add(ConnectionConfig::getCreateTime);
		getterList.add(ConnectionConfig::getUpdateTime);

		SerializableData data = SerializableData.toSerializableData(connectionConfig, getterList);

		data.append(ReflectAssist.getFriendlyIdName(ConnectionConfig.class), connectionConfig.getId());

		return this.singleData(data);
	}

}
