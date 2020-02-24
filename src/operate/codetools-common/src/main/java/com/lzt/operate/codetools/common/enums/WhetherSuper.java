package com.lzt.operate.codetools.common.enums;

/**
 * 是否超级管理员
 *
 * @author luzhitao
 */
public enum WhetherSuper {

	/**
	 * 是
	 */
	Yes(1),

	/**
	 * 否
	 */
	No(0);

	/**
	 * 值
	 */
	private Integer value;

	WhetherSuper(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
