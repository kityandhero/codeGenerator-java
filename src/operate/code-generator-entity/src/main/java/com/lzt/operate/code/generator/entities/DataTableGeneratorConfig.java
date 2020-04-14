package com.lzt.operate.code.generator.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzt.operate.code.generator.entities.bases.BaseEntity;
import com.lzt.operate.utility.enums.Whether;
import com.lzt.operate.utility.general.ConstantCollection;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * GeneratorConfig is the Config of mybatis generator config exclude database
 * config
 * <p>
 *
 * @author luzhitao
 * @date 2020/03/17
 */
@Entity
@Table(name = "data_table_generator_config")
@EqualsAndHashCode(callSuper = true)
public class DataTableGeneratorConfig extends BaseEntity {

	private static final long serialVersionUID = -6940964534931128004L;

	@Column(name = "connection_config_id", nullable = false)
	private long connectionConfigId;

	@Column(name = "database_generator_config_id", nullable = false)
	private long databaseGeneratorConfigId;

	@Column(name = "table_name", nullable = false)
	private String tableName;

	@Column(name = "use_generate_key", nullable = false)
	private int useGenerateKey;

	@Column(name = "generate_keys", nullable = false)
	private String generateKeys;

	@Column(name = "domain_object_name", nullable = false)
	private String domainObjectName;

	@Column(name = "mapper_name", nullable = false)
	private String mapperName;

	@Column(name = "use_actual_column_names", nullable = false)
	private int useActualColumnNames;

	@Column(name = "use_table_name_alias", nullable = false)
	private int useTableNameAlias;

	@Column(name = "alias_name", nullable = false)
	private String aliasName;

	@Lob
	@Column(name = "model_content", nullable = false)
	private String modelContent;

	@Lob
	@Column(name = "mapper_content", nullable = false)
	private String mapperContent;

	@Lob
	@Column(name = "mapping_xml_content", nullable = false)
	private String mappingXmlContent;

	@Lob
	@Column(name = "example_content", nullable = false)
	private String exampleContent;

	@Lob
	@Column(name = "service_content", nullable = false)
	private String serviceContent;

	@Lob
	@Column(name = "service_impl_content", nullable = false)
	private String serviceImplContent;

	/**
	 * 最后一次的生成时间
	 */
	@Column(name = "last_generate_time", nullable = false)
	@DateTimeFormat
	@JsonFormat
	private LocalDateTime lastGenerateTime;

	public DataTableGeneratorConfig() {
		super();

		this.connectionConfigId = 0;
		this.databaseGeneratorConfigId = 0;
		this.tableName = "";
		this.useGenerateKey = ConstantCollection.NO_INT;
		this.generateKeys = "";
		this.domainObjectName = "";
		this.mapperName = "";
		this.useActualColumnNames = Whether.No.getFlag();
		this.useTableNameAlias = Whether.No.getFlag();
		this.aliasName = "";
		this.modelContent = "";
		this.mapperContent = "";
		this.mappingXmlContent = "";
		this.exampleContent = "";
		this.serviceContent = "";
		this.serviceImplContent = "";
		this.lastGenerateTime = ConstantCollection.DEFAULT_DATE_TIME;
	}

	public long getConnectionConfigId() {
		return connectionConfigId;
	}

	public void setConnectionConfigId(long connectionConfigId) {
		this.connectionConfigId = connectionConfigId;
	}

	public long getDatabaseGeneratorConfigId() {
		return databaseGeneratorConfigId;
	}

	public void setDatabaseGeneratorConfigId(long databaseGeneratorConfigId) {
		this.databaseGeneratorConfigId = databaseGeneratorConfigId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getGenerateKeys() {
		return generateKeys;
	}

	public void setGenerateKeys(String generateKeys) {
		this.generateKeys = generateKeys;
	}

	public int getUseGenerateKey() {
		return useGenerateKey;
	}

	public void setUseGenerateKey(Whether whether) {
		this.useGenerateKey = whether.getFlag();
	}

	public String getDomainObjectName() {
		return domainObjectName;
	}

	public void setDomainObjectName(String domainObjectName) {
		this.domainObjectName = domainObjectName;
	}

	public String getMapperName() {
		return mapperName;
	}

	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}

	public int getUseActualColumnNames() {
		return useActualColumnNames;
	}

	public void setUseActualColumnNames(Whether whether) {
		this.useActualColumnNames = whether.getFlag();
	}

	public int getUseTableNameAlias() {
		return useTableNameAlias;
	}

	public void setUseTableNameAlias(Whether whether) {
		this.useTableNameAlias = whether.getFlag();
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getModelContent() {
		return modelContent;
	}

	public void setModelContent(String modelContent) {
		this.modelContent = modelContent;
	}

	public String getMapperContent() {
		return mapperContent;
	}

	public void setMapperContent(String mapperContent) {
		this.mapperContent = mapperContent;
	}

	public String getMappingXmlContent() {
		return mappingXmlContent;
	}

	public void setMappingXmlContent(String mappingXmlContent) {
		this.mappingXmlContent = mappingXmlContent;
	}

	public String getExampleContent() {
		return exampleContent;
	}

	public void setExampleContent(String exampleContent) {
		this.exampleContent = exampleContent;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public String getServiceImplContent() {
		return serviceImplContent;
	}

	public void setServiceImplContent(String serviceImplContent) {
		this.serviceImplContent = serviceImplContent;
	}

	public LocalDateTime getLastGenerateTime() {
		return lastGenerateTime;
	}

	public void setLastGenerateTime(LocalDateTime lastGenerateTime) {
		this.lastGenerateTime = lastGenerateTime;
	}

}
