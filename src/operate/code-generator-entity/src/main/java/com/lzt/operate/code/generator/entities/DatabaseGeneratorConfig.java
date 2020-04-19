package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.common.enums.FileEncoding;
import com.lzt.operate.code.generator.common.enums.mybatis.DaoType;
import com.lzt.operate.code.generator.entities.bases.BaseEntity;
import com.lzt.operate.utility.enums.Whether;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author luzhitao
 */
@Entity
@Table(name = "database_generator_config")
@EqualsAndHashCode(callSuper = true)
public class DatabaseGeneratorConfig extends BaseEntity {
	private static final long serialVersionUID = 4482665423484753190L;

	@Column(name = "connection_config_id", nullable = false, unique = true)
	private long connectionConfigId;

	@Column(name = "connector_jar_file", nullable = false)
	private String connectorJarFile;

	@Column(name = "project_folder", nullable = false)
	private String projectFolder;

	@Column(name = "model_package", nullable = false)
	private String modelPackage;

	@Column(name = "model_package_target_folder", nullable = false)
	private String modelTargetFolder;

	@Column(name = "model_package_target_folder_relative_mode", nullable = false)
	private int modelTargetFolderRelativeMode;

	@Column(name = "dao_package", nullable = false)
	private String daoPackage;

	@Column(name = "dao_target_folder", nullable = false)
	private String daoTargetFolder;

	@Column(name = "dao_target_folder_relative_mode", nullable = false)
	private int daoTargetFolderRelativeMode;

	@Column(name = "dao_type", nullable = false)
	private int daoType;

	@Column(name = "mapping_xml_package", nullable = false)
	private String mappingXmlPackage;

	@Column(name = "mapping_xml_target_folder", nullable = false)
	private String mappingXmlTargetFolder;

	@Column(name = "mapping_xml_target_folder_relative_mode", nullable = false)
	private int mappingXmlTargetFolderRelativeMode;

	@Column(name = "service_package", nullable = false)
	private String servicePackage;

	@Column(name = "service_target_folder", nullable = false)
	private String serviceTargetFolder;

	@Column(name = "service_target_folder_relative_mode", nullable = false)
	private int serviceTargetFolderRelativeMode;

	@Column(name = "offset_limit", nullable = false)
	private int offsetLimit;

	@Column(name = "need_to_string_hashcode_equals", nullable = false)
	private int needToStringHashCodeEquals;

	@Column(name = "need_for_update", nullable = false)
	private int needForUpdate;

	@Column(name = "annotation_dao", nullable = false)
	private int annotationDAO;

	@Column(name = "annotation", nullable = false)
	private int annotation;

	@Column(name = "encoding", nullable = false)
	private int encoding;

	@Column(name = "use_dao_extend_style", nullable = false)
	private int useDAOExtendStyle;

	@Column(name = "use_schema_prefix", nullable = false)
	private int useSchemaPrefix;

	@Column(name = "jsr_310_support", nullable = false)
	private int jsr310Support;

	@Column(name = "override_xml", nullable = false)
	private int overrideXML;

	@Column(name = "auto_delimit_keywords", nullable = false)
	private int autoDelimitKeywords;

	@Column(name = "comment", nullable = false)
	private int comment;

	@Column(name = "mapper_extension_name", nullable = false)
	private String mapperExtensionName;

	public DatabaseGeneratorConfig() {
		super();

		this.connectorJarFile = "";
		this.projectFolder = "";
		this.modelPackage = "model";
		this.modelTargetFolder = "";
		this.modelTargetFolderRelativeMode = Whether.Yes.getFlag();
		this.daoPackage = "dao";
		this.daoTargetFolder = "";
		this.daoTargetFolderRelativeMode = Whether.Yes.getFlag();
		this.daoType = DaoType.XmlMapper.getFlag();
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

	}

	public long getConnectionConfigId() {
		return this.connectionConfigId;
	}

	public void setConnectionConfigId(long connectionConfigId) {
		this.connectionConfigId = connectionConfigId;
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
		return modelTargetFolderRelativeMode;
	}

	public void setModelTargetFolderRelativeMode(Whether whether) {
		this.modelTargetFolderRelativeMode = whether.getFlag();
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
		return daoTargetFolderRelativeMode;
	}

	public void setDaoTargetFolderRelativeMode(Whether whether) {
		this.daoTargetFolderRelativeMode = whether.getFlag();
	}

	public int getDaoType() {
		return daoType;
	}

	public void setDaoType(DaoType daoType) {
		this.daoType = daoType.getFlag();
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
		return mappingXmlTargetFolderRelativeMode;
	}

	public void setMappingXmlTargetFolderRelativeMode(Whether whether) {
		this.mappingXmlTargetFolderRelativeMode = whether.getFlag();
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
		this.serviceTargetFolderRelativeMode = whether.getFlag();
	}

	public int getOffsetLimit() {
		return this.offsetLimit;
	}

	public void setOffsetLimit(Whether whether) {
		this.offsetLimit = whether.getFlag();
	}

	public int getNeedToStringHashCodeEquals() {
		return this.needToStringHashCodeEquals;
	}

	public void setNeedToStringHashCodeEquals(Whether whether) {
		this.needToStringHashCodeEquals = whether.getFlag();
	}

	public int getNeedForUpdate() {
		return this.needForUpdate;
	}

	public void setNeedForUpdate(Whether whether) {
		this.needForUpdate = whether.getFlag();
	}

	public int getAnnotationDAO() {
		return this.annotationDAO;
	}

	public void setAnnotationDAO(Whether whether) {
		this.annotationDAO = whether.getFlag();
	}

	public int getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(Whether whether) {
		this.annotation = whether.getFlag();
	}

	public int getEncoding() {
		return this.encoding;
	}

	public void setEncoding(FileEncoding fileEncoding) {
		this.encoding = fileEncoding.getFlag();
	}

	public int getUseDAOExtendStyle() {
		return this.useDAOExtendStyle;
	}

	public void setUseDAOExtendStyle(Whether whether) {
		this.useDAOExtendStyle = whether.getFlag();
	}

	public int getUseSchemaPrefix() {
		return this.useSchemaPrefix;
	}

	public void setUseSchemaPrefix(Whether whether) {
		this.useSchemaPrefix = whether.getFlag();
	}

	public int getJsr310Support() {
		return this.jsr310Support;
	}

	public void setJsr310Support(Whether whether) {
		this.jsr310Support = whether.getFlag();
	}

	public int getOverrideXML() {
		return this.overrideXML;
	}

	public void setOverrideXML(Whether whether) {
		this.overrideXML = whether.getFlag();
	}

	public int getAutoDelimitKeywords() {
		return this.autoDelimitKeywords;
	}

	public void setAutoDelimitKeywords(Whether whether) {
		this.autoDelimitKeywords = whether.getFlag();
	}

	public int getComment() {
		return this.comment;
	}

	public void setComment(Whether whether) {
		this.comment = whether.getFlag();
	}

	public String getMapperExtensionName() {
		return this.mapperExtensionName;
	}

	public void setMapperExtensionName(String mapperName) {
		this.mapperExtensionName = mapperName;
	}

}
