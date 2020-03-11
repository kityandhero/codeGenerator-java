package com.lzt.operate.codetools.entities;

import java.io.Serializable;

/**
 * @author luzhitao
 */

public class DataColumnInfo implements Serializable {

	private static final long serialVersionUID = 4318585142129658518L;

	/**
	 * 表标示
	 */
	public String tableId;

	/**
	 * 列明
	 */
	public String name;

	/**
	 * 列数据类型
	 */
	public String type;

	/**
	 * 列别名
	 */
	public String alias;

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
