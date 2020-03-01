package com.lzt.operate.codetools.common.enums;

/**
 * @author luzhitao
 */

public enum ErrorLogDegree {

	/**
	 * 一般记录
	 */
	Info(100, "一般记录", "一般记录"),

	/**
	 * 警告日志
	 */
	Warning(200, "警告日志", "警告日志"),

	/**
	 * 一般错误
	 */
	Error(300, "一般错误", "一般错误"),

	/**
	 * 严重错误
	 */
	Urgency(400, "严重错误", "严重错误");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "errorLogDegreeList";

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

	ErrorLogDegree(int value, String name, String descriptor) {
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
