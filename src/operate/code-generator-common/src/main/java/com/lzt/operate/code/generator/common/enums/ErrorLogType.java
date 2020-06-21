package com.lzt.operate.code.generator.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */

public enum ErrorLogType {

	/**
	 * 数据错误
	 */
	DataError(100, "数据错误", "数据错误"),

	/**
	 * 程序异常
	 */
	Exception(110, "程序异常", "程序异常");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "errorLogTypeList";

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

	ErrorLogType(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
	}

	public static Optional<ErrorLogType> valueOfFlag(@NonNull Integer flag) {
		ErrorLogType[] values = ErrorLogType.values();

		for (ErrorLogType d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<ErrorLogType> valuesToList() {
		return Arrays.asList(ErrorLogType.values());
	}

	public static boolean existFlag(@NonNull Integer flag) {
		Optional<ErrorLogType> optional = valueOfFlag(flag);

		return optional.isPresent();
	}

	public int getFlag() {
		return this.flag;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

}
