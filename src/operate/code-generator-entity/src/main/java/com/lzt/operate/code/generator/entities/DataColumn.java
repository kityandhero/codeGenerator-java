package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.entities.bases.BaseEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 数据表列设置
 *
 * @author luzhitao
 */
@Entity
@Table(name = "data_column", uniqueConstraints = @UniqueConstraint(columnNames = {"column_name"}))
@EqualsAndHashCode(callSuper = true)
public class DataColumn extends BaseEntity {

	private static final long serialVersionUID = 828097585595882930L;

	@Column(name = "connection_config_id", nullable = false)
	private long connectionConfigId;

	@Column(name = "database_generator_config_id", nullable = false, unique = true)
	private long databaseGeneratorConfigId;

	@Column(name = "data_table_generator_config_id", nullable = false, unique = true)
	private long dataTableGeneratorConfigId;

	@Column(name = "table_name", nullable = false)
	private String tableName;

	@Column(name = "column_name", nullable = false)
	private String columnName;

	@Column(name = "column_type", nullable = false)
	private String columnType;

	@Column(name = "alias_name", nullable = false)
	private String aliasName;

	@Column(name = "java_type", nullable = false)
	private String javaType;

	@Column(name = "type_handler", nullable = false)
	private String typeHandler;

	public DataColumn() {
		super();

		this.connectionConfigId = 0;
		this.tableName = "";
		this.columnName = "";
		this.columnType = "";
		this.aliasName = "";
		this.javaType = "";
		this.typeHandler = "";
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

	public long getDataTableGeneratorConfigId() {
		return dataTableGeneratorConfigId;
	}

	public void setDataTableGeneratorConfigId(long dataTableGeneratorConfigId) {
		this.dataTableGeneratorConfigId = dataTableGeneratorConfigId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String name) {
		this.columnName = name;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String type) {
		this.columnType = type;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getTypeHandler() {
		return typeHandler;
	}

	public void setTypeHandler(String typeHandler) {
		this.typeHandler = typeHandler;
	}

}
