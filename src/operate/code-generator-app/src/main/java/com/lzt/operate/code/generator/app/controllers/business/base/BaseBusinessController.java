package com.lzt.operate.code.generator.app.controllers.business.base;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.config.mybatis.generator.GlobalConfig;
import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.DataBaseGeneratorConfigStatus;
import com.lzt.operate.code.generator.common.enums.FileEncoding;
import com.lzt.operate.code.generator.common.enums.mybatis.GeneratorType;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.custommessagequeue.errorlog.ErrorLogProducerFactory;
import com.lzt.operate.code.generator.dao.service.DatabaseGeneratorConfigService;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DatabaseGeneratorConfig;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.Whether;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author lzt
 */
public class BaseBusinessController extends BaseOperateAuthController {

	protected BaseBusinessController(LoadingCache<String, Object> loadingCache, CustomJsonWebTokenConfig customJsonWebTokenConfig) {
		super(loadingCache, customJsonWebTokenConfig);
	}

	protected DatabaseGeneratorConfig initDatabaseGeneratorConfigByConnectionConfig(@NotNull DatabaseGeneratorConfigService databaseGeneratorConfigService, @NotNull ConnectionConfig connectionConfig, @NotNull ParamData paramData) {
		long operatorId = this.getOperatorId();

		DatabaseGeneratorConfig databaseGeneratorConfig = new DatabaseGeneratorConfig();

		databaseGeneratorConfig.setConnectionConfigId(connectionConfig.getId());
		databaseGeneratorConfig.setGeneratorType(GeneratorType.MybatisGenerator.getFlag());
		databaseGeneratorConfig.setChannel(Channel.CodeGenerator);
		databaseGeneratorConfig.setCreateOperatorId(operatorId);
		databaseGeneratorConfig.setStatus(DataBaseGeneratorConfigStatus.EFFECTIVE, DataBaseGeneratorConfigStatus::getFlag, DataBaseGeneratorConfigStatus::getName);
		databaseGeneratorConfig.setUpdateOperatorId(operatorId);

		GlobalConfig mybatisGeneratorGlobalConfig = new GlobalConfig();

		//创建生成文件夹
		if (this.checkDefaultMainGenerateFolderPathEnable()) {
			ExecutiveResult<String> createFolderResult = this.createGenerateResultFolder(connectionConfig.getName());

			if (createFolderResult.getSuccess()) {
				mybatisGeneratorGlobalConfig.setProjectFolder(createFolderResult.getData());
			}
		}

		databaseGeneratorConfig = this.fill(databaseGeneratorConfig, mybatisGeneratorGlobalConfig, paramData);

		databaseGeneratorConfig = databaseGeneratorConfigService.save(databaseGeneratorConfig);

		return databaseGeneratorConfig;
	}

	protected DatabaseGeneratorConfig fill(@NotNull DatabaseGeneratorConfig databaseGeneratorConfig, @NotNull GlobalConfig mybatisGeneratorGlobalConfig, @NotNull final ParamData paramJson) {
		mybatisGeneratorGlobalConfig.setAnnotation(Whether.No.getFlag()
															 .equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ANNOTATION)
																			  .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());
		mybatisGeneratorGlobalConfig.setAnnotationDAO(Whether.No.getFlag()
																.equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ANNOTATION_DAO)
																				 .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());

		if (paramJson.existKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_PACKAGE)) {
			String daoPackage = paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_PACKAGE);

			mybatisGeneratorGlobalConfig.setDaoPackage(StringAssist.isNullOrEmpty(daoPackage) ? "dao" : daoPackage);
		} else {
			mybatisGeneratorGlobalConfig.setDaoPackage(StringAssist.isNullOrEmpty(mybatisGeneratorGlobalConfig.getDaoPackage()) ? "dao" : mybatisGeneratorGlobalConfig
					.getDaoPackage());
		}

		mybatisGeneratorGlobalConfig.setDaoTargetFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER));
		mybatisGeneratorGlobalConfig.setDaoTargetFolderRelativeMode(Whether.No.getFlag()
																			  .equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER_RELATIVE_MODE)
																							   .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());

		final Integer encoding = paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_ENCODING).toInt();
		final Optional<FileEncoding> optionalFileEncoding = EnumAssist.getTargetValue(FileEncoding.valuesToList(), FileEncoding::getFlag, encoding);

		if (optionalFileEncoding.isPresent()) {
			mybatisGeneratorGlobalConfig.setEncoding(optionalFileEncoding.get().getFlag());
		} else {
			mybatisGeneratorGlobalConfig.setEncoding(FileEncoding.UTF8.getFlag());
		}

		mybatisGeneratorGlobalConfig.setJsr310Support(Whether.No.getFlag()
																.equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_JSR_310_SUPPORT)
																				 .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());

		if (paramJson.existKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_PACKAGE)) {
			String mappingXmlPackage = paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_PACKAGE);

			mybatisGeneratorGlobalConfig.setMappingXmlPackage(StringAssist.isNullOrEmpty(mappingXmlPackage) ? "mapping" : mappingXmlPackage);
		} else {
			mybatisGeneratorGlobalConfig.setMappingXmlPackage(StringAssist.isNullOrEmpty(mybatisGeneratorGlobalConfig.getMappingXmlPackage()) ? "mapping" : mybatisGeneratorGlobalConfig
					.getMappingXmlPackage());
		}

		mybatisGeneratorGlobalConfig.setMappingXmlTargetFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER));
		mybatisGeneratorGlobalConfig.setMappingXmlTargetFolderRelativeMode(Whether.No.getFlag()
																					 .equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER_RELATIVE_MODE)
																									  .toInt()) ? Whether.No
				.getFlag() : Whether.Yes.getFlag());

		if (paramJson.existKey(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE)) {
			String modelPackage = paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE);

			mybatisGeneratorGlobalConfig.setModelPackage(StringAssist.isNullOrEmpty(modelPackage) ? "model" : modelPackage);
		} else {
			mybatisGeneratorGlobalConfig.setModelPackage(StringAssist.isNullOrEmpty(mybatisGeneratorGlobalConfig.getModelPackage()) ? "model" : mybatisGeneratorGlobalConfig
					.getModelPackage());
		}

		mybatisGeneratorGlobalConfig.setModelTargetFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER));
		mybatisGeneratorGlobalConfig.setModelTargetFolderRelativeMode(Whether.No.getFlag()
																				.equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER_RELATIVE_MODE)
																								 .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());

		if (paramJson.existKey(GlobalString.DATABASE_GENERATOR_CONFIG_SERVICE_PACKAGE)) {
			String servicePackage = paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_SERVICE_PACKAGE);

			mybatisGeneratorGlobalConfig.setServicePackage(StringAssist.isNullOrEmpty(servicePackage) ? "service" : servicePackage);
		} else {
			mybatisGeneratorGlobalConfig.setServicePackage(StringAssist.isNullOrEmpty(mybatisGeneratorGlobalConfig.getServicePackage()) ? "service" : mybatisGeneratorGlobalConfig
					.getServicePackage());
		}

		mybatisGeneratorGlobalConfig.setServiceTargetFolder(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_SERVICE_TARGET_FOLDER));
		mybatisGeneratorGlobalConfig.setServiceTargetFolderRelativeMode(Whether.No.getFlag()
																				  .equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_SERVICE_TARGET_FOLDER_RELATIVE_MODE)
																								   .toInt()) ? Whether.No
				.getFlag() : Whether.Yes.getFlag());
		mybatisGeneratorGlobalConfig.setMapperExtensionName(paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_MAPPER_Extension_NAME));
		mybatisGeneratorGlobalConfig.setNeedForUpdate(Whether.No.getFlag()
																.equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_NEED_FOR_UPDATE)
																				 .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());
		mybatisGeneratorGlobalConfig.setNeedToStringHashCodeEquals(Whether.No.getFlag()
																			 .equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_NEED_TO_STRING_HASHCODE_EQUALS)
																							  .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());
		mybatisGeneratorGlobalConfig.setOffsetLimit(Whether.No.getFlag()
															  .equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_OFFSET_LIMIT)
																			   .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());
		mybatisGeneratorGlobalConfig.setOverrideXML(Whether.No.getFlag()
															  .equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_OVERRIDE_XML)
																			   .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());

		if (paramJson.existKey(GlobalString.DATABASE_GENERATOR_CONFIG_PROJECT_FOLDER)) {
			String projectFolder = paramJson.getStringByKey(GlobalString.DATABASE_GENERATOR_CONFIG_PROJECT_FOLDER);

			mybatisGeneratorGlobalConfig.setProjectFolder(StringAssist.isNullOrEmpty(projectFolder) ? "" : projectFolder);
		} else {
			mybatisGeneratorGlobalConfig.setProjectFolder(StringAssist.isNullOrEmpty(mybatisGeneratorGlobalConfig.getProjectFolder()) ? "" : mybatisGeneratorGlobalConfig
					.getProjectFolder());
		}

		mybatisGeneratorGlobalConfig.setUseDAOExtendStyle(Whether.No.getFlag()
																	.equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_USE_DAO_EXTEND_STYLE)
																					 .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());
		mybatisGeneratorGlobalConfig.setUseSchemaPrefix(Whether.No.getFlag()
																  .equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_USE_SCHEMA_PREFIX)
																				   .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());
		mybatisGeneratorGlobalConfig.setAutoDelimitKeywords(Whether.No.getFlag()
																	  .equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_AUTO_DELIMIT_KEYWORDS)
																					   .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());
		mybatisGeneratorGlobalConfig.setComment(Whether.No.getFlag()
														  .equals(paramJson.getStringExByKey(GlobalString.DATABASE_GENERATOR_CONFIG_COMMENT)
																		   .toInt()) ? Whether.No.getFlag() : Whether.Yes
				.getFlag());

		String mybatisGeneratorGlobalConfigSerializeString = "";

		try {
			mybatisGeneratorGlobalConfigSerializeString = ConvertAssist.serialize(mybatisGeneratorGlobalConfig);
		} catch (Exception e) {
			ErrorLogProducerFactory.getInstance().getProducer().pushException(e, "");

			e.printStackTrace();
		}

		databaseGeneratorConfig.setMybatisGeneratorGlobalConfig(mybatisGeneratorGlobalConfigSerializeString);

		final long operatorId = this.getOperatorId();

		if (ConstantCollection.ZERO_LONG.equals(databaseGeneratorConfig.getId())) {
			databaseGeneratorConfig.setChannel(Channel.CodeGenerator);

			databaseGeneratorConfig.setCreateOperatorId(operatorId);
			databaseGeneratorConfig.setStatus(DataBaseGeneratorConfigStatus.EFFECTIVE, DataBaseGeneratorConfigStatus::getFlag, DataBaseGeneratorConfigStatus::getName);
		}

		databaseGeneratorConfig.setUpdateOperatorId(operatorId);

		return databaseGeneratorConfig;
	}

}
