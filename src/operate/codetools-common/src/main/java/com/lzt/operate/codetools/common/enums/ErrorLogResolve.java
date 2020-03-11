package com.lzt.operate.codetools.common.enums;

import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author luzhitao
 */

public enum ErrorLogResolve {

	/**
	 * 未解决
	 */
	Unresolved(0, "未解决", "未解决"),

	/**
	 * 已解决
	 */
	Resolved(1, "已解决", "已解决");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "errorLogResolveList";

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

	ErrorLogResolve(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
	}

	public static Optional<ErrorLogResolve> valueOfFlag(@NonNull Integer flag) {
		ErrorLogResolve[] values = ErrorLogResolve.values();

		for (ErrorLogResolve d : values) {
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
