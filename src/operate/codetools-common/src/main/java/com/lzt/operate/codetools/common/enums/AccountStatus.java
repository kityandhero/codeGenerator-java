package com.lzt.operate.codetools.common.enums;

/**
 * 公共角色状态
 *
 * @author lzt
 */
public enum AccountStatus {

	/**
	 * 已启用
	 */
	Enabled(1, "已启用", "已启用"),

	/**
	 * 已禁用
	 */
	Disabled(0, "已禁用", "已禁用"),

	/**
	 * 已删除
	 */
	Remove(-1, "已删除", "已删除");

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

	AccountStatus(int value, String name) {
		this.value = value;
		this.name = name;
		this.description = "";
	}

	AccountStatus(int value, String name, String description) {
		this.value = value;
		this.name = name;
		this.description = description;
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
