package com.lzt.operate.code.generator.app.controllers.business;

import com.lzt.operate.code.generator.app.assists.ConnectionConfigAssist;
import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.DataBaseGeneratorConfigStatus;
import com.lzt.operate.code.generator.common.enums.FileEncoding;
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
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.enums.Whether;
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
@RequestMapping("/business/databaseGeneratorConfig")
@Api(tags = {"数据库生成配置"})
public class DataBaseGeneratorConfigController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "数据库生成配置/";

	private final ConnectionConfigAssist connectionConfigAssist;

	@Autowired
	public DataBaseGeneratorConfigController(CustomJsonWebTokenConfig customJsonWebTokenConfig,
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
	public ResultListData pageList(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)
										   .toLong();

		Specification<DatabaseGeneratorConfig> specification = new Specification<DatabaseGeneratorConfig>() {

			private static final long serialVersionUID = 9114015918184208846L;

			@Override
			public Predicate toPredicate(@NonNull Root<DatabaseGeneratorConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!ConstantCollection.ZERO_LONG.equals(connectionConfigId)) {
					list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DatabaseGeneratorConfig::getConnectionConfigId)), connectionConfigId));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(DatabaseGeneratorConfig::getCreateTime));

		Page<DatabaseGeneratorConfig> result = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
																		  .page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<DatabaseGeneratorConfig>> getterList = new ArrayList<>();

												getterList.add(DatabaseGeneratorConfig::getConnectionConfigId);
												getterList.add(DatabaseGeneratorConfig::getEncoding);
												getterList.add(DatabaseGeneratorConfig::getChannel);
												getterList.add(DatabaseGeneratorConfig::getChannelNote);
												getterList.add(DatabaseGeneratorConfig::getStatus);
												getterList.add(DatabaseGeneratorConfig::getStatusNote);
												getterList.add(DatabaseGeneratorConfig::getCreateTime);
												getterList.add(DatabaseGeneratorConfig::getUpdateTime);

												SerializableData data = SerializableData.toSerializableData(o, getterList);

												data.append(ReflectAssist.getFriendlyIdName(DatabaseGeneratorConfig.class), o
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
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据库生成配置详情", description = "获取数据库生成配置", tag = "6552cd29-5cdb-48b3-9a74-7541e6879839")
	public ResultSingleData get(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		long databaseGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ID, "0")
												  .toLong();

		Optional<DatabaseGeneratorConfig> result = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
																			  .get(databaseGeneratorConfigId);

		if (result.isPresent()) {
			DatabaseGeneratorConfig databaseGeneratorConfig = result.get();

			return decorateSingleData(databaseGeneratorConfig);
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
	@PostMapping(path = "/getByConnectionId", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据库生成配置详情", description = "获取数据库生成配置", tag = "6b0d1fbe-9e31-48ce-86ab-5dc1ebe387db")
	public ResultSingleData getByConnectionId(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "0")
										   .toLong();

		Optional<DatabaseGeneratorConfig> result = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
																			  .findByConnectionConfigId(connectionConfigId);

		DatabaseGeneratorConfig databaseGeneratorConfig;

		if (result.isPresent()) {
			databaseGeneratorConfig = result.get();
		} else {
			DatabaseGeneratorConfig v = new DatabaseGeneratorConfig();

			v.setConnectionConfigId(connectionConfigId);

			databaseGeneratorConfig = this.fill(v, paramJson);
		}

		return decorateSingleData(databaseGeneratorConfig);
	}

	@ApiOperation(value = "创建数据库生成配置", notes = "创建数据库生成配置", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATABASE_GENERATOR_CONFIG_SET, value = {
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTOR_JAR_FILE),
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
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_ENCODING),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_USE_DAO_EXTEND_STYLE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_USE_SCHEMA_PREFIX),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_JSR_310_SUPPORT),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_OVERRIDE_XML),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_AUTO_DELIMIT_KEYWORDS)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATABASE_GENERATOR_CONFIG_SET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/set", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "设置数据库生成配置", description = "创建数据库生成配置", tag = "94520b18-bcb8-499c-90fd-afb82f45f3f0")
	public ResultSingleData set(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		long databaseGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ID, "0")
												  .toLong();
		long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "0")
										   .toLong();

		Optional<ConnectionConfig> optionalConnectionConfig = this.connectionConfigAssist.getConnectionConfig(connectionConfigId);

		if (!optionalConnectionConfig.isPresent()) {
			return this.noDataError("数据库连接不存在");
		}

		DatabaseGeneratorConfig databaseGeneratorConfig = null;

		if (databaseGeneratorConfigId > 0) {
			Optional<DatabaseGeneratorConfig> optionalFindByDataBaseGeneratorConfigId = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
																												   .get(databaseGeneratorConfigId);

			if (optionalFindByDataBaseGeneratorConfigId.isPresent()) {
				databaseGeneratorConfig = optionalFindByDataBaseGeneratorConfigId.get();
			}
		}

		if (!Optional.ofNullable(databaseGeneratorConfig).isPresent()) {
			if (connectionConfigId > 0) {
				Optional<DatabaseGeneratorConfig> optionalFindByConnectionConfigId = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
																												.findByConnectionConfigId(connectionConfigId);

				if (optionalFindByConnectionConfigId.isPresent()) {
					databaseGeneratorConfig = optionalFindByConnectionConfigId.get();
				}
			}
		}

		if (!Optional.ofNullable(databaseGeneratorConfig).isPresent()) {
			databaseGeneratorConfig = new DatabaseGeneratorConfig();

			databaseGeneratorConfig.setConnectionConfigId(connectionConfigId);
		}

		return setCore(databaseGeneratorConfig, paramJson);
	}

	/**
	 * 修饰get返回数据
	 *
	 * @param databaseGeneratorConfig databaseGeneratorConfig
	 * @return BaseResultData
	 */
	private ResultSingleData decorateSingleData(@NotNull DatabaseGeneratorConfig databaseGeneratorConfig) {
		List<IGetter<DatabaseGeneratorConfig>> getterList = new ArrayList<>();

		getterList.add(DatabaseGeneratorConfig::getConnectionConfigId);
		getterList.add(DatabaseGeneratorConfig::getConnectorJarFile);
		getterList.add(DatabaseGeneratorConfig::getProjectFolder);
		getterList.add(DatabaseGeneratorConfig::getModelPackage);
		getterList.add(DatabaseGeneratorConfig::getModelTargetFolder);
		getterList.add(DatabaseGeneratorConfig::getDaoPackage);
		getterList.add(DatabaseGeneratorConfig::getDaoTargetFolder);
		getterList.add(DatabaseGeneratorConfig::getMappingXmlPackage);
		getterList.add(DatabaseGeneratorConfig::getMappingXmlTargetFolder);
		getterList.add(DatabaseGeneratorConfig::getOffsetLimit);
		getterList.add(DatabaseGeneratorConfig::getNeedToStringHashCodeEquals);
		getterList.add(DatabaseGeneratorConfig::getNeedForUpdate);
		getterList.add(DatabaseGeneratorConfig::getAnnotationDAO);
		getterList.add(DatabaseGeneratorConfig::getAnnotation);
		getterList.add(DatabaseGeneratorConfig::getEncoding);
		getterList.add(DatabaseGeneratorConfig::getUseDAOExtendStyle);
		getterList.add(DatabaseGeneratorConfig::getUseSchemaPrefix);
		getterList.add(DatabaseGeneratorConfig::getJsr310Support);
		getterList.add(DatabaseGeneratorConfig::getOverrideXML);
		getterList.add(DatabaseGeneratorConfig::getAutoDelimitKeywords);
		getterList.add(DatabaseGeneratorConfig::getChannel);
		getterList.add(DatabaseGeneratorConfig::getChannelNote);
		getterList.add(DatabaseGeneratorConfig::getStatus);
		getterList.add(DatabaseGeneratorConfig::getStatusNote);
		getterList.add(DatabaseGeneratorConfig::getCreateTime);
		getterList.add(DatabaseGeneratorConfig::getUpdateTime);

		SerializableData data = SerializableData.toSerializableData(databaseGeneratorConfig, getterList);

		data.append(ReflectAssist.getFriendlyIdName(DatabaseGeneratorConfig.class), databaseGeneratorConfig.getId());

		return this.singleData(data);
	}

	private DatabaseGeneratorConfig fill(@NotNull DatabaseGeneratorConfig databaseGeneratorConfig, @NotNull ParamData paramJson) {
		databaseGeneratorConfig.setAnnotation(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ANNOTATION)
													   .toInt());
		databaseGeneratorConfig.setAnnotationDAO(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ANNOTATION_DAO)
														  .toInt());
		databaseGeneratorConfig.setDaoPackage(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_PACKAGE));
		databaseGeneratorConfig.setDaoTargetFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER));

		Integer encoding = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ENCODING).toInt();
		Optional<FileEncoding> optionalFileEncoding = EnumAssist.getTargetValue(FileEncoding.valuesToList(), FileEncoding::getFlag, encoding);

		if (optionalFileEncoding.isPresent()) {
			databaseGeneratorConfig.setEncoding(optionalFileEncoding.get());
		} else {
			databaseGeneratorConfig.setEncoding(FileEncoding.UTF8);
		}

		databaseGeneratorConfig.setJsr310Support(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_JSR_310_SUPPORT)
														  .toInt());
		databaseGeneratorConfig.setMappingXmlPackage(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_PACKAGE));
		databaseGeneratorConfig.setMappingXmlTargetFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER));
		databaseGeneratorConfig.setModelPackage(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE));
		databaseGeneratorConfig.setModelTargetFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER));
		databaseGeneratorConfig.setNeedForUpdate(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_NEED_FOR_UPDATE)
														  .toInt());
		databaseGeneratorConfig.setNeedToStringHashCodeEquals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_NEED_TO_STRING_HASHCODE_EQUALS)
																	   .toInt());
		databaseGeneratorConfig.setOffsetLimit(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_OFFSET_LIMIT)
														.toInt());
		databaseGeneratorConfig.setOverrideXML(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_OVERRIDE_XML)
														.toInt());
		databaseGeneratorConfig.setProjectFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_PROJECT_FOLDER));

		databaseGeneratorConfig.setUseDAOExtendStyle(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_USE_DAO_EXTEND_STYLE)
															  .toInt());
		databaseGeneratorConfig.setUseSchemaPrefix(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_USE_SCHEMA_PREFIX)
															.toInt());

		Integer autoDelimitKeywords = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_AUTO_DELIMIT_KEYWORDS)
											   .toInt();
		databaseGeneratorConfig.setAutoDelimitKeywords(Whether.No.getFlag()
																 .equals(autoDelimitKeywords) ? Whether.No : Whether.Yes);

		long operatorId = getOperatorId();

		if (ConstantCollection.ZERO_LONG.equals(databaseGeneratorConfig.getId())) {
			databaseGeneratorConfig.setChannel(Channel.CodeGenerator);

			databaseGeneratorConfig.setCreateOperatorId(operatorId);
			databaseGeneratorConfig.setStatus(DataBaseGeneratorConfigStatus.EFFECTIVE, DataBaseGeneratorConfigStatus::getFlag, DataBaseGeneratorConfigStatus::getName);
		}

		databaseGeneratorConfig.setUpdateOperatorId(operatorId);

		return databaseGeneratorConfig;
	}

	/**
	 * 修饰get返回数据
	 *
	 * @param databaseGeneratorConfig databaseGeneratorConfig
	 * @return BaseResultData
	 */
	private ResultSingleData setCore(@NotNull DatabaseGeneratorConfig databaseGeneratorConfig, @NotNull ParamData paramJson) {
		DatabaseGeneratorConfig v = this.fill(databaseGeneratorConfig, paramJson);

		v = this.connectionConfigAssist.getDatabaseGeneratorConfigService().save(v);

		return decorateSingleData(v);
	}

}
