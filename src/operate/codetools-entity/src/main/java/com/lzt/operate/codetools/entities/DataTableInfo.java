package com.lzt.operate.codetools.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luzhitao
 */
@Data
public class DataTableInfo implements Serializable {

	private static final long serialVersionUID = -6269891887246380955L;

	public String name;

	public DataTableInfo() {
	}

	public DataTableInfo(String name) {
		this.name = name;
	}

}
