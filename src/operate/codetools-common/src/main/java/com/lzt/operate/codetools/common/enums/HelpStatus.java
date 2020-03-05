package com.lzt.operate.codetools.common.enums;

public enum HelpStatus {

	/**
	 * 已启用
	 */
	Enabled(1, "已启用", "已启用"),

	/**
	 * 已禁用
	 */
	Disabled(0, "已禁用", "已禁用");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "accessWayStatusList";

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

	HelpStatus(int value, String name, String descriptor) {
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
