package com.lzt.operate.code.generator.app.controllers.business;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.lzt.operate.code.generator.app.assists.ConnectionConfigAssist;
import com.lzt.operate.code.generator.app.bridge.MybatisGeneratorBridge;
import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.DataColumnService;
import com.lzt.operate.code.generator.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.DataBaseGeneratorConfigServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.DataTableGeneratorConfigServiceImpl;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataTableGeneratorConfig;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.enums.Whether;
import com.lzt.operate.utility.general.ConstantCollection;
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

	private static final String CONTROLLER_DESCRIPTION = "数据表生成配置管理/";

	private ConnectionConfigAssist connectionConfigAssist;

	@Autowired
	public DataTableGeneratorConfigController(
			LoadingCache<String, Object> loadingCache,
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			ConnectionConfigServiceImpl connectionConfigService,
			DataBaseGeneratorConfigServiceImpl databaseGeneratorConfigService,
			DataTableGeneratorConfigServiceImpl dataTableGeneratorConfigService,
			DataColumnService dataColumnService) {
		super(loadingCache, customJsonWebTokenConfig);

		this.connectionConfigAssist = new ConnectionConfigAssist(
				connectionConfigService,
				databaseGeneratorConfigService,
				dataTableGeneratorConfigService,
				dataColumnService);
	}

	@ApiOperation(value = "数据表生成配置列表", notes = "数据表生成配置列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_PAGE_LIST, value = {
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_DATABASE_GENERATOR_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_TABLE_NAME)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_PAGE_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/pageList", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据表生成配置列表", description = "数据表生成配置列表", tag = "3884225e-99b2-4c63-a29d-d901a426fddf")
	public ResultListData pageList(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "0")
										   .toLong();

		String tableName = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_TABLE_NAME);

		Specification<DataTableGeneratorConfig> specification = new Specification<DataTableGeneratorConfig>() {

			private static final long serialVersionUID = 5826322526864777111L;

			@Override
			public Predicate toPredicate(@NonNull Root<DataTableGeneratorConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!ConstantCollection.SEARCH_UNLIMITED_LONG.equals(connectionConfigId)) {
					list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getConnectionConfigId)), connectionConfigId));
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

		Page<DataTableGeneratorConfig> result = this.connectionConfigAssist.getDataTableGeneratorConfigService()
																		   .page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<DataTableGeneratorConfig>> getterList = new ArrayList<>();

												getterList.add(DataTableGeneratorConfig::getConnectionConfigId);
												getterList.add(DataTableGeneratorConfig::getDatabaseGeneratorConfigId);
												getterList.add(DataTableGeneratorConfig::getTableName);
												getterList.add(DataTableGeneratorConfig::getUseGenerateKey);
												getterList.add(DataTableGeneratorConfig::getGenerateKeys);
												getterList.add(DataTableGeneratorConfig::getDomainObjectName);
												getterList.add(DataTableGeneratorConfig::getMapperName);
												getterList.add(DataTableGeneratorConfig::getUseExample);
												getterList.add(DataTableGeneratorConfig::getUseActualColumnNames);
												getterList.add(DataTableGeneratorConfig::getUseTableNameAlias);
												getterList.add(DataTableGeneratorConfig::getAliasName);
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

		int pageIndex = result.getNumber();
		long totalPages = result.getTotalPages();

		return this.pageData(list, pageIndex, pageSize, totalPages);
	}

	@ApiOperation(value = "获取数据表生成配置信息", notes = "获取数据表生成配置信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_GET, value = {
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "获取数据表生成配置信息", description = "获取数据表生成配置信息", tag = "d5e1f2ab-67bb-49f9-a02e-9429d763d915")
	public ResultSingleData get(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		Long dataTableGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID)
												   .toLong();

		Optional<DataTableGeneratorConfig> optional = this.connectionConfigAssist.getDataTableGeneratorConfigService()
																				 .get(dataTableGeneratorConfigId);

		if (optional.isPresent()) {
			DataTableGeneratorConfig dataTableGeneratorConfig = optional.get();

			List<IGetter<DataTableGeneratorConfig>> getterList = new ArrayList<>();

			getterList.add(DataTableGeneratorConfig::getConnectionConfigId);
			getterList.add(DataTableGeneratorConfig::getDatabaseGeneratorConfigId);
			getterList.add(DataTableGeneratorConfig::getTableName);
			getterList.add(DataTableGeneratorConfig::getUseGenerateKey);
			getterList.add(DataTableGeneratorConfig::getGenerateKeys);
			getterList.add(DataTableGeneratorConfig::getDomainObjectName);
			getterList.add(DataTableGeneratorConfig::getMapperName);
			getterList.add(DataTableGeneratorConfig::getUseExample);
			getterList.add(DataTableGeneratorConfig::getUseActualColumnNames);
			getterList.add(DataTableGeneratorConfig::getUseTableNameAlias);
			getterList.add(DataTableGeneratorConfig::getAliasName);
			getterList.add(DataTableGeneratorConfig::getModelContent);
			getterList.add(DataTableGeneratorConfig::getMapperContent);
			getterList.add(DataTableGeneratorConfig::getMappingXmlContent);
			getterList.add(DataTableGeneratorConfig::getExampleContent);
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

	@ApiOperation(value = "设置数据表生成配置信息", notes = "设置数据表生成配置信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_SET, value = {
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_USE_GENERATE_KEY),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_GENERATE_KEYS),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_DOMAIN_OBJECT_NAME),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_MAPPER_NAME),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_USE_EXAMPLE),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_USE_ACTUAL_COLUMN_NAMES),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_USE_TABLE_NAME_ALIAS),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_ALIAS_NAME)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_SET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/set", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "设置数据表生成配置信息", description = "设置数据表生成配置信息", tag = "f4994bd3-bba7-4b97-8224-4b6fbfc5a8d0")
	public ResultSingleData set(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		Long dataTableGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID)
												   .toLong();
		Integer useGenerateKey = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_USE_GENERATE_KEY)
										  .toInt();
		String generateKeys = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_GENERATE_KEYS);
		String domainObjectName = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_DOMAIN_OBJECT_NAME);
		String mapperName = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_MAPPER_NAME);
		Integer useExample = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_USE_EXAMPLE)
									  .toInt();
		Integer useActualColumnNames = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_USE_ACTUAL_COLUMN_NAMES)
												.toInt();
		Integer useTableNameAlias = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_USE_TABLE_NAME_ALIAS)
											 .toInt();
		String aliasName = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ALIAS_NAME);

		if (Whether.Yes.getFlag().equals(useGenerateKey)) {
			if (StringAssist.isNullOrEmpty(generateKeys)) {
				return this.paramError(GlobalString.DATA_TABLE_GENERATOR_CONFIG_GENERATE_KEYS, "启用自增键，需要提交自增列");
			}
		} else {
			generateKeys = "";
		}

		if (Whether.Yes.getFlag().equals(useTableNameAlias)) {
			if (StringAssist.isNullOrEmpty(aliasName)) {
				return this.paramError(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ALIAS_NAME, "启用表别名，需要提交别名");
			}
		} else {
			aliasName = "";
		}

		Optional<DataTableGeneratorConfig> optional = this.connectionConfigAssist.getDataTableGeneratorConfigService()
																				 .get(dataTableGeneratorConfigId);

		if (optional.isPresent()) {
			DataTableGeneratorConfig dataTableGeneratorConfig = optional.get();

			dataTableGeneratorConfig.setUseGenerateKey(Whether.No.getFlag()
																 .equals(useGenerateKey) ? Whether.No : Whether.Yes);
			dataTableGeneratorConfig.setGenerateKeys(generateKeys);
			dataTableGeneratorConfig.setDomainObjectName(domainObjectName);
			dataTableGeneratorConfig.setMapperName(mapperName);
			dataTableGeneratorConfig.setUseExample(Whether.No.getFlag().equals(useExample) ? Whether.No : Whether.Yes);
			dataTableGeneratorConfig.setUseActualColumnNames(Whether.No.getFlag()
																	   .equals(useActualColumnNames) ? Whether.No : Whether.Yes);
			dataTableGeneratorConfig.setUseTableNameAlias(Whether.No.getFlag()
																	.equals(useTableNameAlias) ? Whether.No : Whether.Yes);
			dataTableGeneratorConfig.setAliasName(aliasName);

			dataTableGeneratorConfig = this.connectionConfigAssist.getDataTableGeneratorConfigService()
																  .save(dataTableGeneratorConfig);

			return this.singleData(new SerializableData().append(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID, dataTableGeneratorConfig
					.getId()));
		}

		return this.noDataError("获取数据表生成配置信息不存在");
	}

	@ApiOperation(value = "初始化数据表生成配置信息", notes = "初始化数据表生成配置信息", httpMethod = "POST")
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
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "初始化数据表生成配置信息", description = "初始化数据表生成配置信息", tag = "85a8c025-dc17-4ed8-9138-9cfb212fe458")
	public ResultSingleData initialize(@RequestBody Map<String, Object> json) throws Exception {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)
										   .toLong();

		String tableName = paramJson.getStringByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_TABLE_NAME);

		ExecutiveResult<DataTableGeneratorConfig> result = this.connectionConfigAssist.initializeDataTableGeneratorConfig(connectionConfigId, tableName);

		if (result.getSuccess()) {
			return this.singleData(new SerializableData().append(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID, result.getData()
																													.getId()));
		}

		return this.fail(result.getCode());
	}

	@ApiOperation(value = "构建表对应代码", notes = "构建表对应代码", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_GENERATE, value = {
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_TABLE_GENERATOR_CONFIG_GENERATE)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/generate", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "构建表对应代码", description = "构建表对应代码", tag = "83ddf6ea-1f01-4cb9-b67d-0d2689184412")
	public ResultSingleData generate(@RequestBody Map<String, Object> json) throws Exception {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)
										   .toLong();

		if (ConstantCollection.ZERO_LONG.equals(connectionConfigId)) {
			this.fail(ReturnDataCode.ParamError.appendMessage(GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "不能为空值"));
		}

		Long dataTableGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID)
												   .toLong();

		if (ConstantCollection.ZERO_LONG.equals(dataTableGeneratorConfigId)) {
			this.fail(ReturnDataCode.ParamError.appendMessage(GlobalString.DATA_TABLE_GENERATOR_CONFIG_ID, "不能为空值"));
		}

		Optional<ConnectionConfig> optionalConnectionConfig = this.connectionConfigAssist.getConnectionConfig(connectionConfigId);

		if (!optionalConnectionConfig.isPresent()) {
			return this.fail(ReturnDataCode.NoData.toMessage("指定的数据连接不存在"));
		}

		Optional<DataTableGeneratorConfig> optionalDataTableGeneratorConfig = this.connectionConfigAssist.getDataTableGeneratorConfigService()
																										 .get(dataTableGeneratorConfigId);

		if (!optionalDataTableGeneratorConfig.isPresent()) {
			return this.fail(ReturnDataCode.NoData.toMessage("指定的数据表生成配置不存在"));
		}

		MybatisGeneratorBridge mybatisGeneratorBridge = new MybatisGeneratorBridge(
				optionalConnectionConfig.get(),
				this.connectionConfigAssist.getDatabaseGeneratorConfigService(),
				this.connectionConfigAssist.getDataTableGeneratorConfigService());

		ExecutiveSimpleResult result = mybatisGeneratorBridge.generate(optionalDataTableGeneratorConfig.get());

		if (result.getSuccess()) {
			return this.successWithTimestamp();
		}

		return this.fail(result.getCode());
	}

}


