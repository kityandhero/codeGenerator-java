package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.assists.ConnectionConfigAssist;
import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.enums.DataBaseGeneratorConfigStatus;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.ConnectionConfigService;
import com.lzt.operate.codetools.dao.service.DataBaseGeneratorConfigService;
import com.lzt.operate.codetools.dao.service.impl.DataBaseGeneratorConfigServiceImpl;
import com.lzt.operate.codetools.entities.ConnectionConfig;
import com.lzt.operate.codetools.entities.DataBaseGeneratorConfig;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
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
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@RequestMapping("/business/dataBaseGeneratorConfig")
@Api(tags = {"数据库生成配置"})
public class DataBaseGeneratorConfigController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "数据库生成配置/";

	private ConnectionConfigService connectionConfigService;

	private DataBaseGeneratorConfigService dataBaseGeneratorConfigService;

	@Autowired
	public DataBaseGeneratorConfigController(CustomJsonWebTokenConfig customJsonWebTokenConfig, ConnectionConfigService connectionConfigServiceImpl, DataBaseGeneratorConfigServiceImpl dataBaseGeneratorConfigService) {
		super(customJsonWebTokenConfig);

		this.connectionConfigService = connectionConfigServiceImpl;
		this.dataBaseGeneratorConfigService = dataBaseGeneratorConfigService;
	}

	public ConnectionConfigService getConnectionConfigService() {
		Optional<ConnectionConfigService> optional = Optional.ofNullable(this.connectionConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("ConnectionConfigService获取失败");
	}

	public DataBaseGeneratorConfigService getDataBaseGeneratorConfigService() {
		Optional<DataBaseGeneratorConfigService> optional = Optional.ofNullable(this.dataBaseGeneratorConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("DataBaseGeneratorConfigService获取失败");
	}

	private ConnectionConfigAssist getConnectionConfigAssist() {
		return new ConnectionConfigAssist(this.getConnectionConfigService());
	}

	@ApiOperation(value = "数据库生成配置分页列表", notes = "数据库生成配置分页列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATABASE_GENERATOR_CONFIG_PAGE_LIST, value = {
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATABASE_GENERATOR_CONFIG_PAGE_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/pageList", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据库生成配置分页列表", description = "数据库生成配置分页列表", tag = "f201e035-bfcc-4eee-a263-70fdc2968e64", config = {"显示路径", "显示子权限"})
	public ResultListData pageList(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)
										   .toLong();

		Specification<DataBaseGeneratorConfig> specification = new Specification<DataBaseGeneratorConfig>() {

			private static final long serialVersionUID = 9114015918184208846L;

			@Override
			public Predicate toPredicate(@NonNull Root<DataBaseGeneratorConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!ConstantCollection.ZERO_LONG.equals(connectionConfigId)) {
					list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataBaseGeneratorConfig::getConnectionConfigId)), connectionConfigId));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(DataBaseGeneratorConfig::getCreateTime));

		Page<DataBaseGeneratorConfig> result = this.getDataBaseGeneratorConfigService().page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<DataBaseGeneratorConfig>> getterList = new ArrayList<>();

												getterList.add(DataBaseGeneratorConfig::getConnectionConfigId);
												// getterList.add(DataBaseGeneratorConfig::getDescription);
												// getterList.add(DataBaseGeneratorConfig::getConnectionType);
												// getterList.add(DataBaseGeneratorConfig::getDatabaseType);
												// getterList.add(DataBaseGeneratorConfig::getHost);
												// getterList.add(DataBaseGeneratorConfig::getPort);
												// getterList.add(DataBaseGeneratorConfig::getSchema);
												getterList.add(DataBaseGeneratorConfig::getEncoding);
												getterList.add(DataBaseGeneratorConfig::getChannel);
												getterList.add(DataBaseGeneratorConfig::getChannelNote);
												getterList.add(DataBaseGeneratorConfig::getStatus);
												getterList.add(DataBaseGeneratorConfig::getStatusNote);
												getterList.add(DataBaseGeneratorConfig::getCreateTime);
												getterList.add(DataBaseGeneratorConfig::getUpdateTime);

												SerializableData data = SerializableData.toSerializableData(o, getterList);

												data.append(ReflectAssist.getFriendlyIdName(DataBaseGeneratorConfig.class), o
														.getId());

												return data;
											})
											.collect(Collectors.toList());

		int pageIndex = result.getNumber();
		long totalPages = result.getTotalPages();

		return this.pageData(list, pageIndex, pageSize, totalPages);
	}

	@ApiOperation(value = "获取数据库生成配置", notes = "获取数据库生成配置", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATABASE_GENERATOR_CONFIG_GET, value = {
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATABASE_GENERATOR_CONFIG_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据库生成配置详情", description = "获取数据库生成配置", tag = "6b0d1fbe-9e31-48ce-86ab-5dc1ebe387db")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long dataBaseGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ID, "0")
												  .toLong();

		Optional<DataBaseGeneratorConfig> result = this.getDataBaseGeneratorConfigService()
													   .get(dataBaseGeneratorConfigId);

		if (result.isPresent()) {
			DataBaseGeneratorConfig dataBaseGeneratorConfig = result.get();

			return decorate(dataBaseGeneratorConfig);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

	@ApiOperation(value = "获取数据库生成配置", notes = "获取数据库生成配置", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATABASE_GENERATOR_CONFIG_GET, value = {
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATABASE_GENERATOR_CONFIG_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据库生成配置详情", description = "获取数据库生成配置", tag = "6b0d1fbe-9e31-48ce-86ab-5dc1ebe387db")
	public BaseResultData getByConnectionId(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "0")
										   .toLong();

		Optional<DataBaseGeneratorConfig> result = this.getDataBaseGeneratorConfigService()
													   .findByConnectionConfigId(connectionConfigId);

		if (result.isPresent()) {
			DataBaseGeneratorConfig dataBaseGeneratorConfig = result.get();

			return decorate(dataBaseGeneratorConfig);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

	@ApiOperation(value = "创建数据库生成配置", notes = "创建数据库生成配置", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATABASE_GENERATOR_CONFIG_SET, value = {
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTOR_JAR_PATH),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_PROJECT_FOLDER),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_DAO_PACKAGE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_PACKAGE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_OFFSET_LIMIT),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_NEED_TO_STRING_HASHCODE_EQUALS),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_NEED_FOR_UPDATE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_ANNOTATION_DAO),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_ANNOTATION),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_USE_ACTUAL_COLUMN_NAMES),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_USE_EXAMPLE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_GENERATE_KEYS),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_ENCODING),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_USE_TABLE_NAME_ALIAS),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_USE_DAO_EXTEND_STYLE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_USE_SCHEMA_PREFIX),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_JSR_310_SUPPORT),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_OVERRIDE_XML)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATABASE_GENERATOR_CONFIG_SET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/set", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "设置数据库生成配置", description = "创建数据库生成配置", tag = "94520b18-bcb8-499c-90fd-afb82f45f3f0")
	public BaseResultData set(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long dataBaseGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ID, "0")
												  .toLong();
		long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "0")
										   .toLong();

		Optional<ConnectionConfig> optionalConnectionConfig = this.getConnectionConfigAssist()
																  .getConnectionConfig(connectionConfigId);

		if (!optionalConnectionConfig.isPresent()) {
			return this.noDataError("数据库连接不存在");
		}

		DataBaseGeneratorConfig dataBaseGeneratorConfig = null;

		if (dataBaseGeneratorConfigId > 0) {
			Optional<DataBaseGeneratorConfig> optionalFindByDataBaseGeneratorConfigId = getDataBaseGeneratorConfigService()
					.get(dataBaseGeneratorConfigId);

			if (optionalFindByDataBaseGeneratorConfigId.isPresent()) {
				dataBaseGeneratorConfig = optionalFindByDataBaseGeneratorConfigId.get();
			}
		}

		if (!Optional.ofNullable(dataBaseGeneratorConfig).isPresent()) {
			if (connectionConfigId > 0) {
				Optional<DataBaseGeneratorConfig> optionalFindByConnectionConfigId = getDataBaseGeneratorConfigService()
						.get(dataBaseGeneratorConfigId);

				if (optionalFindByConnectionConfigId.isPresent()) {
					dataBaseGeneratorConfig = optionalFindByConnectionConfigId.get();
				}
			}
		}

		if (!Optional.ofNullable(dataBaseGeneratorConfig).isPresent()) {
			dataBaseGeneratorConfig = new DataBaseGeneratorConfig();
		}

		return setCore(dataBaseGeneratorConfig, paramJson);
	}

	/**
	 * 修饰get返回数据
	 *
	 * @param dataBaseGeneratorConfig dataBaseGeneratorConfig
	 * @return BaseResultData
	 */
	private BaseResultData decorate(@NotNull DataBaseGeneratorConfig dataBaseGeneratorConfig) {
		List<IGetter<DataBaseGeneratorConfig>> getterList = new ArrayList<>();

		// getterList.add(DataBaseGeneratorConfig::getName);
		// getterList.add(DataBaseGeneratorConfig::getDescription);
		// getterList.add(DataBaseGeneratorConfig::getConnectionType);
		// getterList.add(DataBaseGeneratorConfig::getDatabaseType);
		// getterList.add(DataBaseGeneratorConfig::getHost);
		// getterList.add(DataBaseGeneratorConfig::getPort);
		// getterList.add(DataBaseGeneratorConfig::getSchema);
		// getterList.add(DataBaseGeneratorConfig::getUserName);
		// getterList.add(DataBaseGeneratorConfig::getPassword);
		getterList.add(DataBaseGeneratorConfig::getEncoding);
		// getterList.add(DataBaseGeneratorConfig::getLocalPort);
		// getterList.add(DataBaseGeneratorConfig::getRemotePort);
		// getterList.add(DataBaseGeneratorConfig::getSshHost);
		// getterList.add(DataBaseGeneratorConfig::getSshPort);
		// getterList.add(DataBaseGeneratorConfig::getSshUser);
		// getterList.add(DataBaseGeneratorConfig::getSshPassword);
		getterList.add(DataBaseGeneratorConfig::getChannel);
		getterList.add(DataBaseGeneratorConfig::getChannelNote);
		getterList.add(DataBaseGeneratorConfig::getStatus);
		getterList.add(DataBaseGeneratorConfig::getStatusNote);
		getterList.add(DataBaseGeneratorConfig::getCreateTime);
		getterList.add(DataBaseGeneratorConfig::getUpdateTime);

		SerializableData data = SerializableData.toSerializableData(dataBaseGeneratorConfig, getterList);

		data.append(ReflectAssist.getFriendlyIdName(DataBaseGeneratorConfig.class), dataBaseGeneratorConfig.getId());

		return this.singleData(data);
	}

	/**
	 * 修饰get返回数据
	 *
	 * @param dataBaseGeneratorConfig dataBaseGeneratorConfig
	 * @return BaseResultData
	 */
	private BaseResultData setCore(@NotNull DataBaseGeneratorConfig dataBaseGeneratorConfig, @NotNull ParamData paramJson) {

		dataBaseGeneratorConfig.setAnnotation(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ANNOTATION)
													   .toInt());
		dataBaseGeneratorConfig.setAnnotationDAO(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ANNOTATION_DAO)
														  .toInt());
		dataBaseGeneratorConfig.setConnectorJarPath(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTOR_JAR_PATH));
		dataBaseGeneratorConfig.setDaoPackage(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_PACKAGE));
		dataBaseGeneratorConfig.setDaoTargetFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER));
		dataBaseGeneratorConfig.setEncoding(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ENCODING));
		dataBaseGeneratorConfig.setGenerateKeys(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_GENERATE_KEYS));
		dataBaseGeneratorConfig.setJsr310Support(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_JSR_310_SUPPORT)
														  .toInt());
		dataBaseGeneratorConfig.setMappingXMLPackage(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_PACKAGE));
		dataBaseGeneratorConfig.setMappingXMLTargetFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER));
		dataBaseGeneratorConfig.setModelPackage(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE));
		dataBaseGeneratorConfig.setModelPackageTargetFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER));
		dataBaseGeneratorConfig.setNeedForUpdate(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_NEED_FOR_UPDATE)
														  .toInt());
		dataBaseGeneratorConfig.setNeedToStringHashcodeEquals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_NEED_TO_STRING_HASHCODE_EQUALS)
																	   .toInt());
		dataBaseGeneratorConfig.setOffsetLimit(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_OFFSET_LIMIT)
														.toInt());
		dataBaseGeneratorConfig.setOverrideXML(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_OVERRIDE_XML)
														.toInt());
		dataBaseGeneratorConfig.setProjectFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_PROJECT_FOLDER));
		dataBaseGeneratorConfig.setUseActualColumnNames(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_USE_ACTUAL_COLUMN_NAMES)
																 .toInt());
		dataBaseGeneratorConfig.setUseDAOExtendStyle(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_USE_DAO_EXTEND_STYLE)
															  .toInt());
		dataBaseGeneratorConfig.setUseExample(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_USE_EXAMPLE)
													   .toInt());
		dataBaseGeneratorConfig.setUseSchemaPrefix(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_USE_SCHEMA_PREFIX)
															.toInt());
		dataBaseGeneratorConfig.setUseTableNameAlias(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_USE_TABLE_NAME_ALIAS)

															  .toInt());

		long operatorId = getOperatorId();

		if (ConstantCollection.ZERO_LONG.equals(dataBaseGeneratorConfig.getId())) {
			dataBaseGeneratorConfig.setChannel(Channel.CodeTools);

			dataBaseGeneratorConfig.setCreateOperatorId(operatorId);
			dataBaseGeneratorConfig.setCreateTime(LocalDateTime.now());
			dataBaseGeneratorConfig.setStatus(DataBaseGeneratorConfigStatus.EFFECTIVE, DataBaseGeneratorConfigStatus::getFlag, DataBaseGeneratorConfigStatus::getName);
		}

		dataBaseGeneratorConfig.setUpdateOperatorId(operatorId);
		dataBaseGeneratorConfig.setUpdateTime(LocalDateTime.now());

		dataBaseGeneratorConfig = this.getDataBaseGeneratorConfigService().save(dataBaseGeneratorConfig);

		return decorate(dataBaseGeneratorConfig);
	}

}
