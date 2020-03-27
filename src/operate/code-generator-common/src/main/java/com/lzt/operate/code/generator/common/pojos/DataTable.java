package com.lzt.operate.code.generator.common.pojos;

import java.io.Serializable;

/**
 * @author luzhitao
 */
public class DataTable implements Serializable {

	private static final long serialVersionUID = -2355144520541767072L;

	private String name;

	public DataTable() {
		this.name = "";
	}

	public DataTable(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
