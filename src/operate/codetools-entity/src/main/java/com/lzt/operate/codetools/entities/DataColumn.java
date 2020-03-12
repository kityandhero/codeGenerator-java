package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据表列设置
 *
 * @author luzhitao
 */
@Entity
@Table(name = "data_column")
@EqualsAndHashCode(callSuper = true)
public class DataColumn extends BaseEntity {

	private static final long serialVersionUID = 828097585595882930L;

	@Column(name = "connection_config_id")
	private long connectionConfigId;

	@Column(name = "table_name")
	private String tableName;

	@Column
	private String name;

	@Column
	private String type;

	@Column(name = "alias_name")
	private String aliasName;

	@Column(name = "java_type")
	private String javaType;

	@Column(name = "type_handler")
	private String typeHandler;

	public DataColumn() {
		super();

		this.connectionConfigId = 0;
		this.tableName = "";
		this.name = "";
		this.type = "";
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
