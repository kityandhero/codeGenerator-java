package com.lzt.operate.code.generator.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
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
	private final int flag;

	/**
	 * 描述
	 */
	private final String name;

	/**
	 * 描述
	 */
	private final String description;

	ErrorLogResolve(int value, String name, String descriptor) {
		flag = value;
		this.name = name;
		description = descriptor;
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

	public static List<ErrorLogResolve> valuesToList() {
		return Arrays.asList(ErrorLogResolve.values());
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
