package com.lzt.operate.codetools.common.enums;

import lombok.Getter;

/**
 * 公共角色状态
 *
 * @author lzt
 */
@Getter
public enum OperatorStatus {

	/**
	 * 已启用
	 */
	Enabled(1, "已启用"),

	/**
	 * 已禁用
	 */
	Disabled(0, "已禁用"),

	/**
	 * 已删除
	 */
	Remove(-1, "已删除");

	/**
	 * 值
	 */
	private int value;

	/**
	 * 描述
	 */
	private String description;

	OperatorStatus(int value, String descriptor) {
		this.value = value;
		this.description = descriptor;
	}

}
