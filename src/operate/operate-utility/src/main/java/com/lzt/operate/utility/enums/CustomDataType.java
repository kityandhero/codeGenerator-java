package com.lzt.operate.utility.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public enum CustomDataType {

	/**
	 * Json Object
	 */
	Unknown(0, "Unknown", "Unknown"),

	/**
	 * Json Object
	 */
	JsonObject(100, "JsonObject", "JsonObject"),

	/**
	 * Json Object List
	 */
	JsonObjectList(200, "JsonObject数组", "JsonObject数组"),

	/**
	 * Plain Value
	 */
	PlainValue(300, "一般值类型", "一般值类型");

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

	CustomDataType(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
	}

	public static Optional<CustomDataType> valueOfFlag(@NonNull Integer flag) {
		CustomDataType[] values = CustomDataType.values();

		for (CustomDataType d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<CustomDataType> valuesToList() {
		return Arrays.asList(CustomDataType.values());
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