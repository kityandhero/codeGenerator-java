package com.lzt.operate.codetools.common.enums;

/**
 * 创建模式
 *
 * @author lzt
 */
public enum AccountRoleCreateMode {

	/**
	 * 系统角色
	 */
	FromUniversal(0, "系统角色"),

	/**
	 * 自主建立
	 */
	IndependentEstablishment(1, "自主建立");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "accountRoleCreateModeList";

	/**
	 * 值
	 */
	private int value;

	/**
	 * 描述
	 */
	private String description;

	AccountRoleCreateMode(int value, String descriptor) {
		this.value = value;
		this.description = descriptor;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
}
