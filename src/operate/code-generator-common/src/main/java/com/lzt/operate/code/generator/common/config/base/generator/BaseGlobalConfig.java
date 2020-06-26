package com.lzt.operate.code.generator.common.config.base.generator;

import com.lzt.operate.code.generator.common.enums.FileEncoding;
import com.lzt.operate.code.generator.common.enums.mybatis.DaoType;
import com.lzt.operate.utility.enums.Whether;

import java.io.Serializable;

public abstract class BaseGlobalConfig implements Serializable {

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

	public BaseGlobalConfig() {
		this.connectorJarFile = "";
		this.projectFolder = "";
		this.modelPackage = "model";
		this.modelTargetFolder = "";
		this.modelTargetFolderRelativeMode = Whether.Yes.getFlag();
		this.daoPackage = "dao";
		this.daoTargetFolder = "";
		this.daoTargetFolderRelativeMode = Whether.Yes.getFlag();
		this.mappingXmlPackage = "mapping";
		this.mappingXmlTargetFolder = "";
		this.mappingXmlTargetFolderRelativeMode = Whether.Yes.getFlag();
		this.servicePackage = "service";
		this.serviceTargetFolder = "";
		this.serviceTargetFolderRelativeMode = Whether.Yes.getFlag();
		this.offsetLimit = Whether.No.getFlag();
		this.needToStringHashCodeEquals = Whether.No.getFlag();
		this.needForUpdate = Whether.No.getFlag();
		this.annotationDAO = Whether.No.getFlag();
		this.annotation = Whether.No.getFlag();
		this.encoding = FileEncoding.UTF8.getFlag();
		this.useDAOExtendStyle = Whether.No.getFlag();
		this.useSchemaPrefix = Whether.No.getFlag();
		this.jsr310Support = Whether.Yes.getFlag();
		this.overrideXML = Whether.No.getFlag();
		this.autoDelimitKeywords = Whether.Yes.getFlag();
		this.comment = Whether.No.getFlag();
		this.mapperExtensionName = "";
		this.daoType = DaoType.XmlMapper.getFlag();
	}

	public String getConnectorJarFile() {
		return this.connectorJarFile;
	}

	public void setConnectorJarFile(String connectorJarPath) {
		this.connectorJarFile = connectorJarPath;
	}

	public String getProjectFolder() {
		return this.projectFolder;
	}

	public void setProjectFolder(String projectFolder) {
		this.projectFolder = projectFolder;
	}

	public String getModelPackage() {
		return this.modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public String getModelTargetFolder() {
		return this.modelTargetFolder;
	}

	public void setModelTargetFolder(String modelPackageTargetFolder) {
		this.modelTargetFolder = modelPackageTargetFolder;
	}

	public int getModelTargetFolderRelativeMode() {
		return this.modelTargetFolderRelativeMode;
	}

	public void setModelTargetFolderRelativeMode(int modelTargetFolderRelativeMode) {
		if (Whether.existFlag(modelTargetFolderRelativeMode)) {
			this.modelTargetFolderRelativeMode = modelTargetFolderRelativeMode;
		} else {
			this.modelTargetFolderRelativeMode = Whether.No.getFlag();
		}
	}

	public String getDaoPackage() {
		return this.daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getDaoTargetFolder() {
		return this.daoTargetFolder;
	}

	public void setDaoTargetFolder(String daoTargetFolder) {
		this.daoTargetFolder = daoTargetFolder;
	}

	public int getDaoTargetFolderRelativeMode() {
		return this.daoTargetFolderRelativeMode;
	}

	public void setDaoTargetFolderRelativeMode(int daoTargetFolderRelativeMode) {
		if (Whether.existFlag(this.daoTargetFolderRelativeMode)) {
			this.daoTargetFolderRelativeMode = daoTargetFolderRelativeMode;
		} else {
			this.daoTargetFolderRelativeMode = Whether.No.getFlag();
		}
	}

	public String getMappingXmlPackage() {
		return this.mappingXmlPackage;
	}

	public void setMappingXmlPackage(String mappingXMLPackage) {
		this.mappingXmlPackage = mappingXMLPackage;
	}

	public String getMappingXmlTargetFolder() {
		return this.mappingXmlTargetFolder;
	}

	public void setMappingXmlTargetFolder(String mappingXMLTargetFolder) {
		this.mappingXmlTargetFolder = mappingXMLTargetFolder;
	}

	public int getMappingXmlTargetFolderRelativeMode() {
		return this.mappingXmlTargetFolderRelativeMode;
	}

	public void setMappingXmlTargetFolderRelativeMode(int mappingXmlTargetFolderRelativeMode) {
		if (Whether.existFlag(this.mappingXmlTargetFolderRelativeMode)) {
			this.mappingXmlTargetFolderRelativeMode = mappingXmlTargetFolderRelativeMode;
		} else {
			this.mappingXmlTargetFolderRelativeMode = Whether.No.getFlag();
		}
	}

	public String getServicePackage() {
		return this.servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServiceTargetFolder() {
		return this.serviceTargetFolder;
	}

	public void setServiceTargetFolder(String serviceTargetFolder) {
		this.serviceTargetFolder = serviceTargetFolder;
	}

	public int getServiceTargetFolderRelativeMode() {
		return this.serviceTargetFolderRelativeMode;
	}

	public void setServiceTargetFolderRelativeMode(int serviceTargetFolderRelativeMode) {
		if (Whether.existFlag(serviceTargetFolderRelativeMode)) {
			this.serviceTargetFolderRelativeMode = serviceTargetFolderRelativeMode;
		} else {
			this.serviceTargetFolderRelativeMode = Whether.No.getFlag();
		}
	}

	public int getOffsetLimit() {
		return this.offsetLimit;
	}

	public void setOffsetLimit(int offsetLimit) {
		if (Whether.existFlag(offsetLimit)) {
			this.offsetLimit = offsetLimit;
		} else {
			this.offsetLimit = Whether.No.getFlag();
		}
	}

	public int getNeedToStringHashCodeEquals() {
		return this.needToStringHashCodeEquals;
	}

	public void setNeedToStringHashCodeEquals(int needToStringHashCodeEquals) {
		if (Whether.existFlag(needToStringHashCodeEquals)) {
			this.needToStringHashCodeEquals = needToStringHashCodeEquals;
		} else {
			this.needToStringHashCodeEquals = Whether.No.getFlag();
		}
	}

	public int getNeedForUpdate() {
		return this.needForUpdate;
	}

	public void setNeedForUpdate(int needForUpdate) {
		if (Whether.existFlag(needForUpdate)) {
			this.needForUpdate = needForUpdate;
		} else {
			this.needForUpdate = Whether.No.getFlag();
		}
	}

	public int getAnnotationDAO() {
		return this.annotationDAO;
	}

	public void setAnnotationDAO(int annotationDAO) {
		if (Whether.existFlag(annotationDAO)) {
			this.annotationDAO = annotationDAO;
		} else {
			this.annotationDAO = Whether.No.getFlag();
		}
	}

	public int getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(int annotation) {
		if (Whether.existFlag(annotation)) {
			this.annotation = annotation;
		} else {
			this.annotation = Whether.No.getFlag();
		}
	}

	public int getEncoding() {
		return this.encoding;
	}

	public void setEncoding(int encoding) {
		if (FileEncoding.existFlag(encoding)) {
			this.encoding = encoding;
		} else {
			this.encoding = FileEncoding.UTF8.getFlag();
		}
	}

	public int getUseDAOExtendStyle() {
		return this.useDAOExtendStyle;
	}

	public void setUseDAOExtendStyle(int useDAOExtendStyle) {
		if (Whether.existFlag(useDAOExtendStyle)) {
			this.useDAOExtendStyle = useDAOExtendStyle;
		} else {
			this.useDAOExtendStyle = Whether.No.getFlag();
		}
	}

	public int getUseSchemaPrefix() {
		return this.useSchemaPrefix;
	}

	public void setUseSchemaPrefix(int useSchemaPrefix) {
		if (Whether.existFlag(useSchemaPrefix)) {
			this.useSchemaPrefix = useSchemaPrefix;
		} else {
			this.useSchemaPrefix = Whether.No.getFlag();
		}
	}

	public int getJsr310Support() {
		return this.jsr310Support;
	}

	public void setJsr310Support(int jsr310Support) {
		if (Whether.existFlag(jsr310Support)) {
			this.jsr310Support = jsr310Support;
		} else {
			this.jsr310Support = Whether.No.getFlag();
		}
	}

	public int getOverrideXML() {
		return this.overrideXML;
	}

	public void setOverrideXML(int overrideXML) {
		if (Whether.existFlag(overrideXML)) {
			this.overrideXML = overrideXML;
		} else {
			this.overrideXML = Whether.No.getFlag();
		}
	}

	public int getAutoDelimitKeywords() {
		return this.autoDelimitKeywords;
	}

	public void setAutoDelimitKeywords(int autoDelimitKeywords) {
		if (Whether.existFlag(autoDelimitKeywords)) {
			this.autoDelimitKeywords = autoDelimitKeywords;
		} else {
			this.autoDelimitKeywords = Whether.No.getFlag();
		}
	}

	public int getComment() {
		return this.comment;
	}

	public void setComment(int comment) {
		if (Whether.existFlag(comment)) {
			this.comment = comment;
		} else {
			this.comment = Whether.No.getFlag();
		}
	}

	public String getMapperExtensionName() {
		return this.mapperExtensionName;
	}

	public void setMapperExtensionName(String mapperName) {
		this.mapperExtensionName = mapperName;
	}

	public int getDaoType() {
		return this.daoType;
	}

	public void setDaoType(int daoType) {
		if (DaoType.existFlag(this.autoDelimitKeywords)) {
			this.daoType = daoType;
		} else {
			this.daoType = DaoType.XmlMapper.getFlag();
		}

	}

}
