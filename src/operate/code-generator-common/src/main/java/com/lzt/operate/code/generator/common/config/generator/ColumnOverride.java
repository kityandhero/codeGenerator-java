package com.lzt.operate.code.generator.common.config.generator;

import java.io.Serializable;

/**
 * @author luzhitao
 */
public class ColumnOverride implements Serializable {

	private static final long serialVersionUID = -3516146626949121482L;

	private String columnName;

	private String columnType;

	private String aliasName;

	private String javaType;

	private String typeHandler;

	private String delimitedColumnName;

	private int ignore;

	public ColumnOverride() {
		this.columnName = "";
		this.columnType = "";
		this.aliasName = "";
		this.javaType = "";
		this.typeHandler = "";
		this.delimitedColumnName = "";
		this.ignore = 0;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return this.columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getAliasName() {
		return this.aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getJavaType() {
		return this.javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getTypeHandler() {
		return this.typeHandler;
	}

	public void setTypeHandler(String typeHandler) {
		this.typeHandler = typeHandler;
	}

	public String getDelimitedColumnName() {
		return this.delimitedColumnName;
	}

	public void setDelimitedColumnName(String delimitedColumnName) {
		this.delimitedColumnName = delimitedColumnName;
	}

	public int getIgnore() {
		return this.ignore;
	}

	public void setIgnore(int ignore) {
		this.ignore = ignore;
	}
}
