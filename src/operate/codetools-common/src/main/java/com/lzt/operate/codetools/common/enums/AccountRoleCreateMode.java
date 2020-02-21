package com.lzt.operate.codetools.common.enums;

import lombok.Getter;

/**
 * 创建模式
 *
 * @author lzt
 */
@Getter
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

}
