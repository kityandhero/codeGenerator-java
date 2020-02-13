package com.lzt.operate.codetools.common.enums;

import lombok.Getter;

/**
 * 渠道枚举集合
 *
 * @author luzhitao
 */
@Getter
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
	private int flag;

	/**
	 * 标志
	 */
	private String note;

	Channel(int flag, String note) {
		this.flag = flag;
		this.note = note;
	}

}
