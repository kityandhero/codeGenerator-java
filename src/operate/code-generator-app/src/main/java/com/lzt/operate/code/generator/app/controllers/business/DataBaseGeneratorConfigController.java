package com.lzt.operate.code.generator.app.controllers.business;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.lzt.operate.code.generator.app.assists.ConnectionConfigAssist;
import com.lzt.operate.code.generator.app.bridge.MybatisGeneratorBridge;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.app.controllers.business.base.BaseBusinessController;
import com.lzt.operate.code.generator.common.config.mybatis.generator.GlobalConfig;
import com.lzt.operate.code.generator.common.enums.mybatis.DaoType;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.DataBaseGeneratorConfigServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.DataColumnServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.DataTableGeneratorConfigServiceImpl;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DatabaseGeneratorConfig;
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
public class DataBaseGeneratorConfigController extends BaseBusinessController {

	private static final String CONTROLLER_DESCRIPTION = "数据库生成配置/";

	private final ConnectionConfigAssist connectionConfigAssist;

	@Autowired
	public DataBaseGeneratorConfigController(
			final LoadingCache<String, Object> loadingCache,
			final CustomJsonWebTokenConfig customJsonWebTokenConfig,
			final ConnectionConfigServiceImpl connectionConfigService,
			final DataBaseGeneratorConfigServiceImpl databaseGeneratorConfigService,
			final DataTableGeneratorConfigServiceImpl dataTableGeneratorConfigService,
			final DataColumnServiceImpl dataColumnService) {
		super(loadingCache, customJsonWebTokenConfig);

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
	public ResultListData pageList(@RequestBody final Map<String, Object> json) {
		final ParamData paramJson = this.getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		final long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)
												 .toLong();

		final Specification<DatabaseGeneratorConfig> specification = new Specification<DatabaseGeneratorConfig>() {

			private static final long serialVersionUID = 9114015918184208846L;

			@Override
			public Predicate toPredicate(@NonNull final Root<DatabaseGeneratorConfig> root, @NonNull final CriteriaQuery<?> query, @NonNull final CriteriaBuilder criteriaBuilder) {
				final List<Predicate> list = new ArrayList<>();

				if (!ConstantCollection.ZERO_LONG.equals(connectionConfigId)) {
					list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DatabaseGeneratorConfig::getConnectionConfigId)), connectionConfigId));
				}

				final Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		final Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(DatabaseGeneratorConfig::getCreateTime));

		final Page<DatabaseGeneratorConfig> result = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
																				.page(specification, pageable);

		final List<SerializableData> list = result.getContent()
												  .stream()
												  .map(o -> {
													  final List<IGetter<DatabaseGeneratorConfig>> getterList = new ArrayList<>();

													  getterList.add(DatabaseGeneratorConfig::getConnectionConfigId);
													  getterList.add(DatabaseGeneratorConfig::getChannel);
													  getterList.add(DatabaseGeneratorConfig::getChannelNote);
													  getterList.add(DatabaseGeneratorConfig::getStatus);
													  getterList.add(DatabaseGeneratorConfig::getStatusNote);
													  getterList.add(DatabaseGeneratorConfig::getCreateTime);
													  getterList.add(DatabaseGeneratorConfig::getUpdateTime);

													  final SerializableData data = SerializableData.toSerializableData(o, getterList);

													  data.append(ReflectAssist.getFriendlyIdName(DatabaseGeneratorConfig.class), o
															  .getId());

													  return data;
												  })
												  .collect(Collectors.toList());

		final int pageIndex = result.getNumber();
		final long totalPages = result.getTotalPages();

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
	public ResultSingleData get(@RequestBody final Map<String, Object> json) {
		final ParamData paramJson = this.getParamData(json);

		final long databaseGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ID, "0")
														.toLong();

		final Optional<DatabaseGeneratorConfig> result = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
																					.get(databaseGeneratorConfigId);

		if (result.isPresent()) {
			final DatabaseGeneratorConfig databaseGeneratorConfig = result.get();

			return this.decorateSingleData(databaseGeneratorConfig);
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
	public ResultSingleData getByConnectionId(@RequestBody final Map<String, Object> json) {
		ParamData paramJson = this.getParamData(json);

		long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "0")
										   .toLong();

		Optional<ConnectionConfig> optionalConnectionConfig = this.connectionConfigAssist.getConnectionConfig(connectionConfigId);

		if (optionalConnectionConfig.isPresent()) {
			ConnectionConfig connectionConfig = optionalConnectionConfig.get();

			Optional<DatabaseGeneratorConfig> result = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
																				  .findByConnectionConfigId(connectionConfigId);

			DatabaseGeneratorConfig databaseGeneratorConfig;

			databaseGeneratorConfig = result.orElseGet(() -> this.initDatabaseGeneratorConfigByConnectionConfig(this.connectionConfigAssist
					.getDatabaseGeneratorConfigService(), connectionConfig, new ParamData()));

			return this.decorateSingleData(databaseGeneratorConfig);
		}

		return this.fail(ReturnDataCode.NoData.toMessage("无效的数据链接配置"));
	}

	@ApiOperation(value = "创建数据库生成配置", notes = "创建数据库生成配置", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATABASE_GENERATOR_CONFIG_SET, value = {
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTOR_JAR_FILE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_PROJECT_FOLDER),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER_RELATIVE_MODE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_DAO_PACKAGE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER_RELATIVE_MODE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_PACKAGE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER_RELATIVE_MODE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_SERVICE_PACKAGE),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_SERVICE_TARGET_FOLDER),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_SERVICE_TARGET_FOLDER_RELATIVE_MODE),
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
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_AUTO_DELIMIT_KEYWORDS),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_COMMENT),
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_MAPPER_Extension_NAME),},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATABASE_GENERATOR_CONFIG_SET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/set", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "设置数据库生成配置", description = "创建数据库生成配置", tag = "94520b18-bcb8-499c-90fd-afb82f45f3f0")
	public ResultSingleData set(@RequestBody final Map<String, Object> json) {
		final ParamData paramJson = this.getParamData(json);

		final long databaseGeneratorConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ID, "0")
														.toLong();
		final long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "0")
												 .toLong();

		final String modelTargetFolder = paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER);
		final int modelTargetFolderRelativeMode = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER_RELATIVE_MODE)
														   .toInt();

		if (Whether.Yes.getFlag().equals(modelTargetFolderRelativeMode)) {
			if (StringAssist.contains(modelTargetFolder, ConstantCollection.EMPTY_STRING)) {
				return this.paramError(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER, "model文件夹不能含有空白");
			}
		}

		final String daoTargetFolder = paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER);
		final int daoTargetFolderRelativeMode = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER_RELATIVE_MODE)
														 .toInt();

		if (Whether.Yes.getFlag().equals(daoTargetFolderRelativeMode)) {
			if (StringAssist.contains(daoTargetFolder, ConstantCollection.EMPTY_STRING)) {
				return this.paramError(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER, "dao文件夹不能含有空白");
			}
		}

		final String mappingXmlTargetFolder = paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER);
		final int mappingXmlTargetFolderRelativeMode = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER_RELATIVE_MODE)
																.toInt();

		if (Whether.Yes.getFlag().equals(mappingXmlTargetFolderRelativeMode)) {
			if (StringAssist.contains(mappingXmlTargetFolder, ConstantCollection.EMPTY_STRING)) {
				return this.paramError(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER, "mappingXml文件夹不能含有空白");
			}
		}

		final String serviceTargetFolder = paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_SERVICE_TARGET_FOLDER);
		final int serviceTargetFolderRelativeMode = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_SERVICE_TARGET_FOLDER_RELATIVE_MODE)
															 .toInt();

		if (Whether.Yes.getFlag().equals(serviceTargetFolderRelativeMode)) {
			if (StringAssist.contains(serviceTargetFolder, ConstantCollection.EMPTY_STRING)) {
				return this.paramError(GlobalString.DATABASE_GENERATOR_CONFIG_SERVICE_TARGET_FOLDER, "service文件夹不能含有空白");
			}
		}

		final Integer daoTypeValue = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TYPE, DaoType.XmlMapper
				.getFlag()
				.toString())
											  .toInt();

		final Optional<DaoType> optionalDaoType = DaoType.valueOfFlag(daoTypeValue);

		if (!optionalDaoType.isPresent()) {
			return this.noDataError("无效的daoType");
		}

		final DaoType daoType = optionalDaoType.get();

		final Optional<ConnectionConfig> optionalConnectionConfig = this.connectionConfigAssist.getConnectionConfig(connectionConfigId);

		if (!optionalConnectionConfig.isPresent()) {
			return this.noDataError("数据库连接不存在");
		}

		DatabaseGeneratorConfig databaseGeneratorConfig = null;

		if (databaseGeneratorConfigId > 0) {
			final Optional<DatabaseGeneratorConfig> optionalFindByDataBaseGeneratorConfigId = this.connectionConfigAssist
					.getDatabaseGeneratorConfigService()
					.get(databaseGeneratorConfigId);

			if (optionalFindByDataBaseGeneratorConfigId.isPresent()) {
				databaseGeneratorConfig = optionalFindByDataBaseGeneratorConfigId.get();
			}
		}

		if (!Optional.ofNullable(databaseGeneratorConfig).isPresent()) {
			if (connectionConfigId > 0) {
				final Optional<DatabaseGeneratorConfig> optionalFindByConnectionConfigId = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
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

		final GlobalConfig mybatisGeneratorGlobalConfig = databaseGeneratorConfig.BuildMyybatisGeneratorGlobalConfig();

		mybatisGeneratorGlobalConfig.setDaoType(daoType.getFlag());

		return this.setCore(databaseGeneratorConfig, mybatisGeneratorGlobalConfig, paramJson);
	}

	@ApiOperation(value = "构建库对应代码", notes = "构建库对应代码", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATABASE_GENERATOR_CONFIG_GENERATE, value = {
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATABASE_GENERATOR_CONFIG_GENERATE)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/generate", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "构建库对应代码", description = "构建库对应代码", tag = "83ddf6ea-1f01-4cb9-b67d-0d2689184412")
	public ResultSingleData generate(@RequestBody final Map<String, Object> json) throws Exception {
		final ParamData paramJson = this.getParamData(json);

		final Long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)
												 .toLong();

		if (ConstantCollection.ZERO_LONG.equals(connectionConfigId)) {
			this.fail(ReturnDataCode.ParamError.appendMessage(GlobalString.DATA_TABLE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "不能为空值"));
		}

		final Optional<ConnectionConfig> optionalConnectionConfig = this.connectionConfigAssist.getConnectionConfig(connectionConfigId);

		if (!optionalConnectionConfig.isPresent()) {
			return this.fail(ReturnDataCode.NoData.toMessage("指定的数据连接不存在"));
		}

		final MybatisGeneratorBridge mybatisGeneratorBridge = new MybatisGeneratorBridge(
				optionalConnectionConfig.get(),
				this.connectionConfigAssist.getDatabaseGeneratorConfigService(),
				this.connectionConfigAssist.getDataTableGeneratorConfigService(),
				this.connectionConfigAssist.getDataColumnService());

		final ExecutiveSimpleResult result = mybatisGeneratorBridge.generateAll();

		if (result.getSuccess()) {
			return this.successWithTimestamp();
		}

		return this.fail(result.getCode());
	}

	@ApiOperation(value = "打开项目文件夹", notes = "打开项目文件夹", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATABASE_GENERATOR_CONFIG_OPEN_PROJECT_FOLDER, value = {
			@ApiJsonProperty(name = GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATABASE_GENERATOR_CONFIG_OPEN_PROJECT_FOLDER)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/openProjectFolder", produces = "application/json")
	public ResultSingleData openProjectFolder(@RequestBody final Map<String, Object> json) {
		final ParamData paramJson = this.getParamData(json);

		final Long connectionConfigId = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID)
												 .toLong();

		if (ConstantCollection.ZERO_LONG.equals(connectionConfigId)) {
			this.fail(ReturnDataCode.ParamError.appendMessage(GlobalString.DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID, "不能为空值"));
		}

		final Optional<DatabaseGeneratorConfig> optionalDatabaseGeneratorConfig = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
																											 .findByConnectionConfigId(connectionConfigId);

		if (!optionalDatabaseGeneratorConfig.isPresent()) {
			return this.fail(ReturnDataCode.DataError.appendMessage("数据库生成配置不存在"));
		}

		final GlobalConfig mybatisGeneratorGlobalConfig = optionalDatabaseGeneratorConfig.get()
																						 .BuildMyybatisGeneratorGlobalConfig();

		final String folder = mybatisGeneratorGlobalConfig.getProjectFolder();

		final ExecutiveSimpleResult result = this.openFolder(folder);

		if (result.getSuccess()) {
			return this.successWithTimestamp();
		}

		return this.fail(result.getCode());
	}

	/**
	 * 修饰get返回数据
	 *
	 * @param databaseGeneratorConfig databaseGeneratorConfig
	 * @return BaseResultData
	 */
	private ResultSingleData decorateSingleData(@NotNull final DatabaseGeneratorConfig databaseGeneratorConfig) {
		final List<IGetter<DatabaseGeneratorConfig>> getterList = new ArrayList<>();

		getterList.add(DatabaseGeneratorConfig::getConnectionConfigId);

		getterList.add(DatabaseGeneratorConfig::getChannel);
		getterList.add(DatabaseGeneratorConfig::getChannelNote);
		getterList.add(DatabaseGeneratorConfig::getStatus);
		getterList.add(DatabaseGeneratorConfig::getStatusNote);
		getterList.add(DatabaseGeneratorConfig::getCreateTime);
		getterList.add(DatabaseGeneratorConfig::getUpdateTime);

		final SerializableData data = SerializableData.toSerializableData(databaseGeneratorConfig, getterList);

		data.append(ReflectAssist.getFriendlyIdName(DatabaseGeneratorConfig.class), databaseGeneratorConfig.getId());

		final GlobalConfig mybatisGeneratorGlobalConfig = databaseGeneratorConfig.BuildMyybatisGeneratorGlobalConfig();

		data.append(com.lzt.operate.code.generator.common.config.mybatis.generator.ConstantCollection.GLOBALCONFIG, mybatisGeneratorGlobalConfig);

		return this.singleData(data);
	}

	/**
	 * 修饰get返回数据
	 *
	 * @param databaseGeneratorConfig databaseGeneratorConfig
	 * @return BaseResultData
	 */
	private ResultSingleData setCore(@NotNull final DatabaseGeneratorConfig databaseGeneratorConfig, @NotNull final GlobalConfig mybatisGeneratorGlobalConfig, @NotNull final ParamData paramJson) {
		DatabaseGeneratorConfig v = this.fill(databaseGeneratorConfig, mybatisGeneratorGlobalConfig, paramJson);

		v = this.connectionConfigAssist.getDatabaseGeneratorConfigService().save(v);

		return this.decorateSingleData(v);
	}

}
