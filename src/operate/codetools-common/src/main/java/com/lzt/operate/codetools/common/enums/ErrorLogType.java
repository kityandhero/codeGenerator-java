package com.lzt.operate.codetools.common.enums;

/**
 * @author luzhitao
 */

public enum ErrorLogType {

	/**
	 * 数据错误
	 */
	DataError(100, "数据错误", "数据错误"),

	/**
	 * 程序异常
	 */
	Exception(110, "程序异常", "程序异常");

	/**
	 * 值
	 */
	private int value;

	/**
	 * 描述
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	ErrorLogType(int value, String name, String descriptor) {
		this.value = value;
		this.name = name;
		this.description = descriptor;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
