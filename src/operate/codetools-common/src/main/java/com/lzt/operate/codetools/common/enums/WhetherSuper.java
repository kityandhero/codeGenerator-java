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
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "whetherSuperList";

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
