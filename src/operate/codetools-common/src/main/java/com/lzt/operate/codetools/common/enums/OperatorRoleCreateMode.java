package com.lzt.operate.codetools.common.enums;

import lombok.Getter;

/**
 * 创建模式
 *
 * @author lzt
 */
@Getter
public enum OperatorRoleCreateMode {

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

	OperatorRoleCreateMode(int value, String descriptor) {
		this.value = value;
		this.description = descriptor;
	}

}
