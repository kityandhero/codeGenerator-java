package com.lzt.operate.codetools.entities;

import java.io.Serializable;

/**
 * @author luzhitao
 */
public class DataTableInfo implements Serializable {

	private static final long serialVersionUID = -6269891887246380955L;

	public String name;

	public DataTableInfo() {
	}

	public DataTableInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
