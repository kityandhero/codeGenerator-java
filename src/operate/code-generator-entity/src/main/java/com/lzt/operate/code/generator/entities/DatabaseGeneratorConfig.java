package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.common.enums.FileEncoding;
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

	@Column(name = "dao_package", nullable = false)
	private String daoPackage;

	@Column(name = "dao_target_folder", nullable = false)
	private String daoTargetFolder;

	@Column(name = "mapping_xml_package", nullable = false)
	private String mappingXmlPackage;

	@Column(name = "mapping_xml_target_folder", nullable = false)
	private String mappingXmlTargetFolder;

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

	public DatabaseGeneratorConfig() {
		super();

		this.connectorJarFile = "";
		this.projectFolder = "";
		this.modelPackage = "model";
		this.modelTargetFolder = "model";
		this.daoPackage = "dao";
		this.daoTargetFolder = "dao";
		this.mappingXmlPackage = "mapping";
		this.mappingXmlTargetFolder = "mapping";
		this.offsetLimit = Whether.No.getFlag();
		this.needToStringHashCodeEquals = Whether.No.getFlag();
		this.needForUpdate = Whether.No.getFlag();
		this.annotationDAO = Whether.No.getFlag();
		this.annotation = Whether.No.getFlag();
		this.encoding = FileEncoding.UTF8.getFlag();
		this.useDAOExtendStyle = Whether.No.getFlag();
		this.useSchemaPrefix = Whether.No.getFlag();
		this.jsr310Support = Whether.No.getFlag();
		this.overrideXML = Whether.No.getFlag();
		this.autoDelimitKeywords = Whether.Yes.getFlag();
	}

	public long getConnectionConfigId() {
		return connectionConfigId;
	}

	public void setConnectionConfigId(long connectionConfigId) {
		this.connectionConfigId = connectionConfigId;
	}

	public String getConnectorJarFile() {
		return connectorJarFile;
	}

	public void setConnectorJarFile(String connectorJarPath) {
		this.connectorJarFile = connectorJarPath;
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
		this.modelTargetFolder = modelPackageTargetFolder;
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

	public String getMappingXmlPackage() {
		return mappingXmlPackage;
	}

	public void setMappingXmlPackage(String mappingXMLPackage) {
		this.mappingXmlPackage = mappingXMLPackage;
	}

	public String getMappingXmlTargetFolder() {
		return mappingXmlTargetFolder;
	}

	public void setMappingXmlTargetFolder(String mappingXMLTargetFolder) {
		this.mappingXmlTargetFolder = mappingXMLTargetFolder;
	}

	public int getOffsetLimit() {
		return offsetLimit;
	}

	public void setOffsetLimit(int offsetLimit) {
		this.offsetLimit = offsetLimit;
	}

	public int getNeedToStringHashCodeEquals() {
		return needToStringHashCodeEquals;
	}

	public void setNeedToStringHashCodeEquals(int needToStringHashcodeEquals) {
		this.needToStringHashCodeEquals = needToStringHashcodeEquals;
	}

	public int getNeedForUpdate() {
		return needForUpdate;
	}

	public void setNeedForUpdate(int needForUpdate) {
		this.needForUpdate = needForUpdate;
	}

	public int getAnnotationDAO() {
		return annotationDAO;
	}

	public void setAnnotationDAO(int annotationDAO) {
		this.annotationDAO = annotationDAO;
	}

	public int getAnnotation() {
		return annotation;
	}

	public void setAnnotation(int annotation) {
		this.annotation = annotation;
	}

	public int getEncoding() {
		return encoding;
	}

	public void setEncoding(FileEncoding fileEncoding) {
		this.encoding = fileEncoding.getFlag();
	}

	public int getUseDAOExtendStyle() {
		return useDAOExtendStyle;
	}

	public void setUseDAOExtendStyle(int useDAOExtendStyle) {
		this.useDAOExtendStyle = useDAOExtendStyle;
	}

	public int getUseSchemaPrefix() {
		return useSchemaPrefix;
	}

	public void setUseSchemaPrefix(int useSchemaPrefix) {
		this.useSchemaPrefix = useSchemaPrefix;
	}

	public int getJsr310Support() {
		return jsr310Support;
	}

	public void setJsr310Support(int jsr310Support) {
		this.jsr310Support = jsr310Support;
	}

	public int getOverrideXML() {
		return overrideXML;
	}

	public void setOverrideXML(int overrideXML) {
		this.overrideXML = overrideXML;
	}

	public int getAutoDelimitKeywords() {
		return autoDelimitKeywords;
	}

	public void setAutoDelimitKeywords(Whether whether) {
		this.autoDelimitKeywords = whether.getFlag();
	}
}
