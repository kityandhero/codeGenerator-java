package com.lzt.operate.code.generator.app.controllers.business;

import com.lzt.operate.code.generator.app.assists.DatabaseAssist;
import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.pojos.DataTable;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.ConnectionConfigService;
import com.lzt.operate.code.generator.dao.service.DataTableGeneratorConfigService;
import com.lzt.operate.code.generator.dao.service.DatabaseGeneratorConfigService;
import com.lzt.operate.code.generator.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.DataBaseGeneratorConfigServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.DataTableGeneratorConfigServiceImpl;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataTableGeneratorConfig;
import com.lzt.operate.code.generator.entities.DatabaseGeneratorConfig;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.SerializableData;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/dataTableGeneratorConfig")
@Api(tags = {"数据表生成配置"})
public class DataTableGeneratorConfigController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "数据表列配置管理/";

	private ConnectionConfigService connectionConfigService;

	private DatabaseGeneratorConfigService databaseGeneratorConfigService;

	private DataTableGeneratorConfigService dataTableGeneratorConfigService;

	@Autowired
	public DataTableGeneratorConfigController(CustomJsonWebTokenConfig customJsonWebTokenConfig, ConnectionConfigServiceImpl connectionConfigServiceImpl, DataBaseGeneratorConfigServiceImpl databaseGeneratorConfigService, DataTableGeneratorConfigServiceImpl dataTableGeneratorConfigService) {
		super(customJsonWebTokenConfig);

		this.connectionConfigService = connectionConfigServiceImpl;
		this.databaseGeneratorConfigService = databaseGeneratorConfigService;
		this.dataTableGeneratorConfigService = dataTableGeneratorConfigService;
	}

	public ConnectionConfigService getConnectionConfigService() {
		Optional<ConnectionConfigService> optional = Optional.ofNullable(this.connectionConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("ConnectionConfigService获取失败");
	}

	public DatabaseGeneratorConfigService getDatabaseGeneratorConfigService() {
		Optional<DatabaseGeneratorConfigService> optional = Optional.ofNullable(this.databaseGeneratorConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("DataBaseGeneratorConfigService获取失败");
	}

	public DataTableGeneratorConfigService getDataTableGeneratorConfigService() {
		Optional<DataTableGeneratorConfigService> optional = Optional.ofNullable(this.dataTableGeneratorConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("DataTableGeneratorConfigService获取失败");
	}

	@ApiOperation(value = "数据库表生成配置列表", notes = "数据库表生成配置列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_PAGE_LIST, value = {
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_DATABASE_GENERATOR_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_TABLE_NAME)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_PAGE_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据库表生成配置列表", description = "数据库表生成配置列表", tag = "3884225e-99b2-4c63-a29d-d901a426fddf")
	public ResultListData list(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "0")
										   .toLong();

		if (connectionConfigId <= 0) {
			return this.pageDataEmpty();
		}

		Long databaseGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_DATABASE_GENERATOR_CONFIG_ID, "0")
												  .toLong();

		if (databaseGeneratorConfigId <= 0) {
			return this.pageDataEmpty();
		}

		String tableName = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_TABLE_NAME);

		Specification<DataTableGeneratorConfig> specification = new Specification<DataTableGeneratorConfig>() {

			private static final long serialVersionUID = 5826322526864777111L;

			@Override
			public Predicate toPredicate(@NonNull Root<DataTableGeneratorConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!ConstantCollection.ZERO_LONG.equals(connectionConfigId)) {
					list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getConnectionConfigId)), connectionConfigId));
				}

				if (!ConstantCollection.ZERO_LONG.equals(databaseGeneratorConfigId)) {
					list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getDatabaseGeneratorConfigId)), databaseGeneratorConfigId));
				}

				if (!StringAssist.isNullOrEmpty(tableName)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getTableName)), StringAssist
							.merge("%", tableName, "%")));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(DataTableGeneratorConfig::getCreateTime));

		Page<DataTableGeneratorConfig> result = this.getDataTableGeneratorConfigService()
													.page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<DataTableGeneratorConfig>> getterList = new ArrayList<>();

												getterList.add(DataTableGeneratorConfig::getConnectionConfigId);
												getterList.add(DataTableGeneratorConfig::getDatabaseGeneratorConfigId);
												getterList.add(DataTableGeneratorConfig::getTableName);
												getterList.add(DataTableGeneratorConfig::getDomainObjectName);
												getterList.add(DataTableGeneratorConfig::getMapperName);
												getterList.add(DataTableGeneratorConfig::getComment);
												getterList.add(DataTableGeneratorConfig::getChannel);
												getterList.add(DataTableGeneratorConfig::getChannelNote);
												getterList.add(DataTableGeneratorConfig::getStatus);
												getterList.add(DataTableGeneratorConfig::getStatusNote);
												getterList.add(DataTableGeneratorConfig::getCreateTime);
												getterList.add(DataTableGeneratorConfig::getUpdateTime);

												SerializableData data = SerializableData.toSerializableData(o, getterList);

												data.append(ReflectAssist.getFriendlyIdName(DataTableGeneratorConfig.class), o
														.getId());

												return data;
											})
											.collect(Collectors.toList());

		return this.listData(list);

	}

	@ApiOperation(value = "获取数据库表生成配置信息", notes = "获取数据库表生成配置信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_GET, value = {
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "获取数据库表生成配置信息", description = "获取数据库表生成配置信息", tag = "d5e1f2ab-67bb-49f9-a02e-9429d763d915")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		Long dataTableGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID)
												   .toLong();

		Optional<DataTableGeneratorConfig> optional = this.getDataTableGeneratorConfigService()
														  .get(dataTableGeneratorConfigId);

		if (optional.isPresent()) {
			DataTableGeneratorConfig dataTableGeneratorConfig = optional.get();

			List<IGetter<DataTableGeneratorConfig>> getterList = new ArrayList<>();

			getterList.add(DataTableGeneratorConfig::getConnectionConfigId);
			getterList.add(DataTableGeneratorConfig::getDatabaseGeneratorConfigId);
			getterList.add(DataTableGeneratorConfig::getTableName);
			getterList.add(DataTableGeneratorConfig::getDomainObjectName);
			getterList.add(DataTableGeneratorConfig::getMapperName);
			getterList.add(DataTableGeneratorConfig::getComment);
			getterList.add(DataTableGeneratorConfig::getChannel);
			getterList.add(DataTableGeneratorConfig::getChannelNote);
			getterList.add(DataTableGeneratorConfig::getStatus);
			getterList.add(DataTableGeneratorConfig::getStatusNote);
			getterList.add(DataTableGeneratorConfig::getCreateTime);
			getterList.add(DataTableGeneratorConfig::getUpdateTime);

			SerializableData data = SerializableData.toSerializableData(dataTableGeneratorConfig, getterList);

			data.append(ReflectAssist.getFriendlyIdName(DataTableGeneratorConfig.class), dataTableGeneratorConfig.getId());

			return this.singleData(data);
		}

		return this.noDataError("获取数据库表生成配置信息不存在");
	}

	@ApiOperation(value = "设置定时列信息", notes = "设置定时列信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_SET, value = {
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_DOMAIN_OBJECT_NAME),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_MAPPER_NAME),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_COMMENT)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_SET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/set", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "设置定时列信息", description = "设置定时列信息", tag = "f4994bd3-bba7-4b97-8224-4b6fbfc5a8d0")
	public BaseResultData set(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		Long dataTableGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID)
												   .toLong();
		String domainObjectName = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_DOMAIN_OBJECT_NAME);
		String mapperName = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_MAPPER_NAME);
		String comment = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_COMMENT);

		Optional<DataTableGeneratorConfig> optional = this.getDataTableGeneratorConfigService()
														  .get(dataTableGeneratorConfigId);

		if (optional.isPresent()) {
			DataTableGeneratorConfig dataTableGeneratorConfig = optional.get();

			dataTableGeneratorConfig.setDomainObjectName(domainObjectName);
			dataTableGeneratorConfig.setMapperName(mapperName);
			dataTableGeneratorConfig.setComment(comment);

			dataTableGeneratorConfig = this.getDataTableGeneratorConfigService().save(dataTableGeneratorConfig);

			return this.singleData(new SerializableData().append(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID, dataTableGeneratorConfig
					.getId()));
		}

		return this.noDataError("获取数据库表生成配置信息不存在");
	}

	@ApiOperation(value = "设置定时列信息", notes = "设置定时列信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_SET, value = {
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_DATABASE_GENERATOR_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_TABLE_NAME)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_SET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/initialize", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "设置定时列信息", description = "设置定时列信息", tag = "f4994bd3-bba7-4b97-8224-4b6fbfc5a8d0")
	public BaseResultData initialize(@RequestBody Map<String, Serializable> json) throws Exception {
		ParamData paramJson = getParamData(json);

		ConnectionConfig connectionConfig;

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)
										   .toLong();

		Optional<ConnectionConfig> optionalConnectionConfig = this.getConnectionConfigService().get(connectionConfigId);

		if (!optionalConnectionConfig.isPresent()) {
			return this.noDataError("指定的数据连接不存在");
		} else {
			connectionConfig = optionalConnectionConfig.get();
		}

		Long databaseGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_DATABASE_GENERATOR_CONFIG_ID)
												  .toLong();

		Optional<DatabaseGeneratorConfig> optionalDatabaseGeneratorConfig = this.getDatabaseGeneratorConfigService()
																				.get(databaseGeneratorConfigId);

		if (!optionalDatabaseGeneratorConfig.isPresent()) {
			return this.noDataError("指定的数据库生成配置不存在");
		}

		String tableName = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_TABLE_NAME);

		if (StringAssist.isNullOrEmpty(tableName)) {
			return this.noDataError("数据库表名称参数不能为空");
		}

		List<DataTable> list = DatabaseAssist.listDataTable(connectionConfig);

		if (ConstantCollection.ZERO_LONG.equals(list.stream().filter(o -> o.getName().contains(tableName)).count())) {
			return this.noDataError("数据库表不存在");
		}

		Specification<DataTableGeneratorConfig> specification = new Specification<DataTableGeneratorConfig>() {

			private static final long serialVersionUID = 5826322526864777111L;

			@Override
			public Predicate toPredicate(@NonNull Root<DataTableGeneratorConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getConnectionConfigId)), connectionConfigId));

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getDatabaseGeneratorConfigId)), databaseGeneratorConfigId));

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getTableName)), tableName));

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Optional<DataTableGeneratorConfig> optionalDataTableGeneratorConfig = this.getDataTableGeneratorConfigService()
																				  .findFirst(specification);

		if (optionalDataTableGeneratorConfig.isPresent()) {
			DataTableGeneratorConfig dataTableGeneratorConfig = optionalDataTableGeneratorConfig.get();

			return this.singleData(new SerializableData().append(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID, dataTableGeneratorConfig
					.getId()));
		} else {
			DataTableGeneratorConfig dataTableGeneratorConfig = new DataTableGeneratorConfig();

			dataTableGeneratorConfig.setConnectionConfigId(connectionConfigId);
			dataTableGeneratorConfig.setConnectionConfigId(databaseGeneratorConfigId);
			dataTableGeneratorConfig.setTableName(tableName);

			dataTableGeneratorConfig = this.getDataTableGeneratorConfigService().save(dataTableGeneratorConfig);

			return this.singleData(new SerializableData().append(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID, dataTableGeneratorConfig
					.getId()));
		}
	}

}
