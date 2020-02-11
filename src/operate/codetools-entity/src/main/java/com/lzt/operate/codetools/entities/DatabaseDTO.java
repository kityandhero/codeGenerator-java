package com.lzt.operate.codetools.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * @author luzhitao
 */
@Getter
@Setter
public class DatabaseDTO {

	private String name;
	private int value;
	private String driverClass;

	@Override
	public String toString() {
		return this.name;
	}

}
