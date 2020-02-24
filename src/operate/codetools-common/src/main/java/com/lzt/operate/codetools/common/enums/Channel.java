package com.lzt.operate.codetools.common.enums;

/**
 * 渠道枚举集合
 *
 * @author luzhitao
 */
public enum Channel {

	/**
	 * 未知
	 */
	Unknown(0, "未知"),

	/**
	 * 代码工具
	 */
	CodeTools(10010, "代码工具");

	/**
	 * 标志
	 */
	private int value;

	/**
	 * 标志
	 */
	private String note;

	Channel(int value, String note) {
		this.value = value;
		this.note = note;
	}

	public int getValue() {
		return value;
	}

	public String getNote() {
		return note;
	}
}
