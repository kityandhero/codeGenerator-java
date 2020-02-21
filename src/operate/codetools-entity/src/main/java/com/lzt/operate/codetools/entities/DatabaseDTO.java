package com.lzt.operate.codetools.entities;

/**
 * @author luzhitao
 */
public class DatabaseDTO {

	private String name;
	private int value;
	private String driverClass;

	@Override
	public String toString() {
		return this.name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
}
