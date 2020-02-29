package com.lzt.operate.codetools.common.enums;

/**
 * @author luzhitao
 */

public enum ErrorLogResolve {

	/**
	 * 未解决
	 */
	Unresolved(0, "未解决", "未解决"),

	/**
	 * 已解决
	 */
	Resolved(1, "已解决", "已解决");

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

	ErrorLogResolve(int value, String name, String descriptor) {
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
