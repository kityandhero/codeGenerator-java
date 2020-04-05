package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.entities.bases.BaseEntity;
import com.lzt.operate.utility.general.ConstantCollection;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

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

	@Column(name = "database_generator_config_id", nullable = false, unique = true)
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

	@Lob
	@Column(name = "comment", nullable = false)
	private String comment;

	public DataTableGeneratorConfig() {
		super();

		connectionConfigId = 0;
		databaseGeneratorConfigId = 0;
		tableName = "";
		useGenerateKey = ConstantCollection.NO_INT;
		this.generateKeys = "";
		domainObjectName = "";
		mapperName = "";
		comment = "";
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

	public void setUseGenerateKey(int useGenerateKey) {
		this.useGenerateKey = useGenerateKey;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
