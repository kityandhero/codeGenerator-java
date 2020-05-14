package com.lzt.operate.code.generator.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */

public enum DataColumnStatus {

	/**
	 * 正常
	 */
	NoCustom(0, "未定制", "未定制"),

	/**
	 * 正常
	 */
	AlreadyCustom(1, "已定制", "已定制");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "dataColumnStatusList";

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

	DataColumnStatus(int value, String name, String descriptor) {
		flag = value;
		this.name = name;
		description = descriptor;
	}

	public static Optional<DataColumnStatus> valueOfFlag(@NonNull Integer flag) {
		DataColumnStatus[] values = DataColumnStatus.values();

		for (DataColumnStatus d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<DataColumnStatus> valuesToList() {
		return Arrays.asList(DataColumnStatus.values());
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
