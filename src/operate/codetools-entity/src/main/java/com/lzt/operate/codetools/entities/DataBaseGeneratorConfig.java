package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import com.lzt.operate.utility.enums.Whether;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author luzhitao
 */
@Entity
@Table(name = "data_base_generator_config")
@EqualsAndHashCode(callSuper = true)
public class DataBaseGeneratorConfig extends BaseEntity {
	private static final long serialVersionUID = 4482665423484753190L;

	@Column(name = "connection_config_id", nullable = false)
	private long connectionConfigId;

	@Column(name = "connector_jar_path", nullable = false)
	private String connectorJarPath;

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
	private String mappingXMLPackage;

	@Column(name = "mapping_xml_target_folder", nullable = false)
	private String mappingXMLTargetFolder;

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

	@Column(name = "use_actual_column_names", nullable = false)
	private int useActualColumnNames;

	@Column(name = "use_example", nullable = false)
	private int useExample;

	@Column(name = "generate_keys", nullable = false)
	private String generateKeys;

	@Column(name = "encoding", nullable = false)
	private String encoding;

	@Column(name = "use_table_name_alias", nullable = false)
	private int useTableNameAlias;

	@Column(name = "use_dao_extend_style", nullable = false)
	private int useDAOExtendStyle;

	@Column(name = "use_schema_prefix", nullable = false)
	private int useSchemaPrefix;

	@Column(name = "jsr_310_support", nullable = false)
	private int jsr310Support;

	@Column(name = "override_xml", nullable = false)
	private int overrideXML;

	public DataBaseGeneratorConfig() {
		super();

		this.connectorJarPath = "";
		this.projectFolder = "";
		this.modelPackage = "";
		this.modelTargetFolder = "";
		this.daoPackage = "";
		this.daoTargetFolder = "";
		this.mappingXMLPackage = "";
		this.mappingXMLTargetFolder = "";
		this.offsetLimit = Whether.No.getFlag();
		this.needToStringHashCodeEquals = Whether.No.getFlag();
		this.needForUpdate = Whether.No.getFlag();
		this.annotationDAO = Whether.No.getFlag();
		this.annotation = Whether.No.getFlag();
		this.useActualColumnNames = Whether.No.getFlag();
		this.useExample = Whether.No.getFlag();
		this.generateKeys = "";
		this.encoding = "";
		this.useTableNameAlias = Whether.No.getFlag();
		this.useDAOExtendStyle = Whether.No.getFlag();
		this.useSchemaPrefix = Whether.No.getFlag();
		this.jsr310Support = Whether.No.getFlag();
		this.overrideXML = Whether.No.getFlag();
	}

	public long getConnectionConfigId() {
		return connectionConfigId;
	}

	public void setConnectionConfigId(long connectionConfigId) {
		this.connectionConfigId = connectionConfigId;
	}

	public String getConnectorJarPath() {
		return connectorJarPath;
	}

	public void setConnectorJarPath(String connectorJarPath) {
		this.connectorJarPath = connectorJarPath;
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

	public String getMappingXMLPackage() {
		return mappingXMLPackage;
	}

	public void setMappingXMLPackage(String mappingXMLPackage) {
		this.mappingXMLPackage = mappingXMLPackage;
	}

	public String getMappingXMLTargetFolder() {
		return mappingXMLTargetFolder;
	}

	public void setMappingXMLTargetFolder(String mappingXMLTargetFolder) {
		this.mappingXMLTargetFolder = mappingXMLTargetFolder;
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

	public int getUseActualColumnNames() {
		return useActualColumnNames;
	}

	public void setUseActualColumnNames(int useActualColumnNames) {
		this.useActualColumnNames = useActualColumnNames;
	}

	public int getUseExample() {
		return useExample;
	}

	public void setUseExample(int useExample) {
		this.useExample = useExample;
	}

	public String getGenerateKeys() {
		return generateKeys;
	}

	public void setGenerateKeys(String generateKeys) {
		this.generateKeys = generateKeys;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public int getUseTableNameAlias() {
		return useTableNameAlias;
	}

	public void setUseTableNameAlias(int useTableNameAlias) {
		this.useTableNameAlias = useTableNameAlias;
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
}
