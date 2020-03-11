package com.lzt.operate.codetools.common.enums;

import org.springframework.lang.NonNull;

import java.util.Optional;

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
	private int flag;

	/**
	 * 描述
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	ErrorLogDegree(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
	}

	public static Optional<ErrorLogDegree> valueOfFlag(@NonNull Integer flag) {
		ErrorLogDegree[] values = ErrorLogDegree.values();

		for (ErrorLogDegree d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public int getFlag() {
		return flag;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
