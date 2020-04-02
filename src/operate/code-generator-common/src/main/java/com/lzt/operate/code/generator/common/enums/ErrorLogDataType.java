package com.lzt.operate.code.generator.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public enum ErrorLogDataType {

	/**
	 * 一般记录
	 */
	JsonObject(100, "JsonObject", "JsonObject"),

	/**
	 * 一般记录
	 */
	JsonObjectList(200, "JsonObject数组", "JsonObject数组"),

	/**
	 * 警告日志
	 */
	CommonValue(300, "一般值类型", "一般值类型");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "errorLogDataTypeList";

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

	ErrorLogDataType(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
	}

	public static Optional<ErrorLogDataType> valueOfFlag(@NonNull Integer flag) {
		ErrorLogDataType[] values = ErrorLogDataType.values();

		for (ErrorLogDataType d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<ErrorLogDataType> valuesToList() {
		return Arrays.asList(ErrorLogDataType.values());
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
