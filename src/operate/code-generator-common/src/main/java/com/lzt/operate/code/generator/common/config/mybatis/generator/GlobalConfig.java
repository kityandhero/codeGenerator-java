package com.lzt.operate.code.generator.common.config.mybatis.generator;

import com.lzt.operate.code.generator.common.enums.FileEncoding;
import com.lzt.operate.code.generator.common.enums.mybatis.DaoType;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.enums.Whether;

import java.io.Serializable;
import java.util.Optional;

/**
 * GlobalConfig
 *
 * @author luzhitao
 */
public class GlobalConfig implements Serializable {

	private static final long serialVersionUID = -1208551375087585488L;

	private String connectorJarFile;

	private String projectFolder;

	private String modelPackage;

	private String modelTargetFolder;

	private int modelTargetFolderRelativeMode;

	private String daoPackage;

	private String daoTargetFolder;

	private int daoTargetFolderRelativeMode;

	private String mappingXmlPackage;

	private String mappingXmlTargetFolder;

	private int mappingXmlTargetFolderRelativeMode;

	private String servicePackage;

	private String serviceTargetFolder;

	private int serviceTargetFolderRelativeMode;

	private int offsetLimit;

	private int needToStringHashCodeEquals;

	private int needForUpdate;

	private int annotationDAO;

	private int annotation;

	private int encoding;

	private int useDAOExtendStyle;

	private int useSchemaPrefix;

	private int jsr310Support;

	private int overrideXML;

	private int autoDelimitKeywords;

	private int comment;

	private String mapperExtensionName;

	private int daoType;

	public GlobalConfig() {
		connectorJarFile = "";
		projectFolder = "";
		modelPackage = "model";
		modelTargetFolder = "";
		modelTargetFolderRelativeMode = Whether.Yes.getFlag();
		daoPackage = "dao";
		daoTargetFolder = "";
		daoTargetFolderRelativeMode = Whether.Yes.getFlag();
		mappingXmlPackage = "mapping";
		mappingXmlTargetFolder = "";
		mappingXmlTargetFolderRelativeMode = Whether.Yes.getFlag();
		servicePackage = "service";
		serviceTargetFolder = "";
		serviceTargetFolderRelativeMode = Whether.Yes.getFlag();
		offsetLimit = Whether.No.getFlag();
		needToStringHashCodeEquals = Whether.No.getFlag();
		needForUpdate = Whether.No.getFlag();
		annotationDAO = Whether.No.getFlag();
		annotation = Whether.No.getFlag();
		encoding = FileEncoding.UTF8.getFlag();
		useDAOExtendStyle = Whether.No.getFlag();
		useSchemaPrefix = Whether.No.getFlag();
		jsr310Support = Whether.Yes.getFlag();
		overrideXML = Whether.No.getFlag();
		autoDelimitKeywords = Whether.Yes.getFlag();
		comment = Whether.No.getFlag();
		mapperExtensionName = "";
		daoType = DaoType.XmlMapper.getFlag();
	}

	public String getConnectorJarFile() {
		return connectorJarFile;
	}

	public void setConnectorJarFile(String connectorJarPath) {
		connectorJarFile = connectorJarPath;
	}

	public String getProjectFolder() {
		return projectFolder;
	}

	public void setProjectFolder(String projectFolder) {
		this.projectFolder = projectFolder;
	}

	public String getModelPackage() {
		return modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public String getModelTargetFolder() {
		return modelTargetFolder;
	}

	public void setModelTargetFolder(String modelPackageTargetFolder) {
		modelTargetFolder = modelPackageTargetFolder;
	}

	public int getModelTargetFolderRelativeMode() {
		return modelTargetFolderRelativeMode;
	}

	public void setModelTargetFolderRelativeMode(Whether whether) {
		modelTargetFolderRelativeMode = whether.getFlag();
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getDaoTargetFolder() {
		return daoTargetFolder;
	}

	public void setDaoTargetFolder(String daoTargetFolder) {
		this.daoTargetFolder = daoTargetFolder;
	}

	public int getDaoTargetFolderRelativeMode() {
		return daoTargetFolderRelativeMode;
	}

	public void setDaoTargetFolderRelativeMode(Whether whether) {
		daoTargetFolderRelativeMode = whether.getFlag();
	}

	public String getMappingXmlPackage() {
		return mappingXmlPackage;
	}

	public void setMappingXmlPackage(String mappingXMLPackage) {
		mappingXmlPackage = mappingXMLPackage;
	}

	public String getMappingXmlTargetFolder() {
		return mappingXmlTargetFolder;
	}

	public void setMappingXmlTargetFolder(String mappingXMLTargetFolder) {
		mappingXmlTargetFolder = mappingXMLTargetFolder;
	}

	public int getMappingXmlTargetFolderRelativeMode() {
		return mappingXmlTargetFolderRelativeMode;
	}

	public void setMappingXmlTargetFolderRelativeMode(Whether whether) {
		mappingXmlTargetFolderRelativeMode = whether.getFlag();
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServiceTargetFolder() {
		return serviceTargetFolder;
	}

	public void setServiceTargetFolder(String serviceTargetFolder) {
		this.serviceTargetFolder = serviceTargetFolder;
	}

	public int getServiceTargetFolderRelativeMode() {
		return serviceTargetFolderRelativeMode;
	}

	public void setServiceTargetFolderRelativeMode(Whether whether) {
		serviceTargetFolderRelativeMode = whether.getFlag();
	}

	public int getOffsetLimit() {
		return offsetLimit;
	}

	public void setOffsetLimit(Whether whether) {
		offsetLimit = whether.getFlag();
	}

	public int getNeedToStringHashCodeEquals() {
		return needToStringHashCodeEquals;
	}

	public void setNeedToStringHashCodeEquals(Whether whether) {
		needToStringHashCodeEquals = whether.getFlag();
	}

	public int getNeedForUpdate() {
		return needForUpdate;
	}

	public void setNeedForUpdate(Whether whether) {
		needForUpdate = whether.getFlag();
	}

	public int getAnnotationDAO() {
		return annotationDAO;
	}

	public void setAnnotationDAO(Whether whether) {
		annotationDAO = whether.getFlag();
	}

	public int getAnnotation() {
		return annotation;
	}

	public void setAnnotation(Whether whether) {
		annotation = whether.getFlag();
	}

	public int getEncoding() {
		return encoding;
	}

	public void setEncoding(FileEncoding fileEncoding) {
		encoding = fileEncoding.getFlag();
	}

	public int getUseDAOExtendStyle() {
		return useDAOExtendStyle;
	}

	public void setUseDAOExtendStyle(Whether whether) {
		useDAOExtendStyle = whether.getFlag();
	}

	public int getUseSchemaPrefix() {
		return useSchemaPrefix;
	}

	public void setUseSchemaPrefix(Whether whether) {
		useSchemaPrefix = whether.getFlag();
	}

	public int getJsr310Support() {
		return jsr310Support;
	}

	public void setJsr310Support(Whether whether) {
		jsr310Support = whether.getFlag();
	}

	public int getOverrideXML() {
		return overrideXML;
	}

	public void setOverrideXML(Whether whether) {
		overrideXML = whether.getFlag();
	}

	public int getAutoDelimitKeywords() {
		return autoDelimitKeywords;
	}

	public void setAutoDelimitKeywords(Whether whether) {
		autoDelimitKeywords = whether.getFlag();
	}

	public int getComment() {
		return comment;
	}

	public void setComment(Whether whether) {
		comment = whether.getFlag();
	}

	public String getMapperExtensionName() {
		return mapperExtensionName;
	}

	public void setMapperExtensionName(String mapperName) {
		mapperExtensionName = mapperName;
	}

	public int getDaoType() {
		return daoType;
	}

	public void setDaoType(DaoType daoType) {
		this.daoType = daoType.getFlag();
	}

	public static GlobalConfig Deserialize(String json) {
		try {
			GlobalConfig result = ConvertAssist.deserialize(json, GlobalConfig.class);

			if (Optional.ofNullable(result).isPresent()) {
				return result;
			}

			return new GlobalConfig();
		} catch (Exception e) {
			e.printStackTrace();

			return new GlobalConfig();
		}
	}
}
