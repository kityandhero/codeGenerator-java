package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
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

	@Column(name = "connection_config_id")
	private long connectionConfigId;

	@Column(name = "connector_jar_path")
	private String connectorJarPath;

	@Column(name = "project_folder")
	private String projectFolder;

	@Column(name = "model_package")
	private String modelPackage;

	@Column(name = "model_package_target_folder")
	private String modelPackageTargetFolder;

	@Column(name = "dao_package")
	private String daoPackage;

	@Column(name = "dao_target_folder")
	private String daoTargetFolder;

	@Column(name = "mapping_xml_package")
	private String mappingXMLPackage;

	@Column(name = "mapping_xml_target_folder")
	private String mappingXMLTargetFolder;

	@Column(name = "offset_limit")
	private boolean offsetLimit;

	@Column(name = "need_to_string_hashcode_equals")
	private boolean needToStringHashcodeEquals;

	@Column(name = "need_for_update")
	private boolean needForUpdate;

	@Column(name = "annotation_dao")
	private boolean annotationDAO;

	@Column(name = "annotation")
	private boolean annotation;

	@Column(name = "use_actual_column_names")
	private boolean useActualColumnNames;

	@Column(name = "use_example")
	private boolean useExample;

	@Column(name = "generate_keys")
	private String generateKeys;

	@Column(name = "encoding")
	private String encoding;

	@Column(name = "use_table_name_alias")
	private boolean useTableNameAlias;

	@Column(name = "use_dao_extend_style")
	private boolean useDAOExtendStyle;

	@Column(name = "use_schema_prefix")
	private boolean useSchemaPrefix;

	@Column(name = "jsr_310_support")
	private boolean jsr310Support;

	@Column(name = "override_xml")
	private boolean overrideXML;

	public DataBaseGeneratorConfig() {
		super();
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

	public String getModelPackageTargetFolder() {
		return modelPackageTargetFolder;
	}

	public void setModelPackageTargetFolder(String modelPackageTargetFolder) {
		this.modelPackageTargetFolder = modelPackageTargetFolder;
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

	public boolean isOffsetLimit() {
		return offsetLimit;
	}

	public void setOffsetLimit(boolean offsetLimit) {
		this.offsetLimit = offsetLimit;
	}

	public boolean isNeedToStringHashcodeEquals() {
		return needToStringHashcodeEquals;
	}

	public void setNeedToStringHashcodeEquals(boolean needToStringHashcodeEquals) {
		this.needToStringHashcodeEquals = needToStringHashcodeEquals;
	}

	public boolean isNeedForUpdate() {
		return needForUpdate;
	}

	public void setNeedForUpdate(boolean needForUpdate) {
		this.needForUpdate = needForUpdate;
	}

	public boolean isAnnotationDAO() {
		return annotationDAO;
	}

	public void setAnnotationDAO(boolean annotationDAO) {
		this.annotationDAO = annotationDAO;
	}

	public boolean isAnnotation() {
		return annotation;
	}

	public void setAnnotation(boolean annotation) {
		this.annotation = annotation;
	}

	public boolean isUseActualColumnNames() {
		return useActualColumnNames;
	}

	public void setUseActualColumnNames(boolean useActualColumnNames) {
		this.useActualColumnNames = useActualColumnNames;
	}

	public boolean isUseExample() {
		return useExample;
	}

	public void setUseExample(boolean useExample) {
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

	public boolean isUseTableNameAlias() {
		return useTableNameAlias;
	}

	public void setUseTableNameAlias(boolean useTableNameAlias) {
		this.useTableNameAlias = useTableNameAlias;
	}

	public boolean isUseDAOExtendStyle() {
		return useDAOExtendStyle;
	}

	public void setUseDAOExtendStyle(boolean useDAOExtendStyle) {
		this.useDAOExtendStyle = useDAOExtendStyle;
	}

	public boolean isUseSchemaPrefix() {
		return useSchemaPrefix;
	}

	public void setUseSchemaPrefix(boolean useSchemaPrefix) {
		this.useSchemaPrefix = useSchemaPrefix;
	}

	public boolean isJsr310Support() {
		return jsr310Support;
	}

	public void setJsr310Support(boolean jsr310Support) {
		this.jsr310Support = jsr310Support;
	}

	public boolean isOverrideXML() {
		return overrideXML;
	}

	public void setOverrideXML(boolean overrideXML) {
		this.overrideXML = overrideXML;
	}
}
