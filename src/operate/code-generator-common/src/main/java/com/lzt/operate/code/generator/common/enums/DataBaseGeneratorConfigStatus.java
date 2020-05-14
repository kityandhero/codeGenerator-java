package com.lzt.operate.code.generator.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */

public enum DataBaseGeneratorConfigStatus {

	/**
	 * 已启用
	 */
	EFFECTIVE(1, "已启用", "已启用");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "databaseGeneratorConfigStatusList";

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

	DataBaseGeneratorConfigStatus(int value, String name, String descriptor) {
		flag = value;
		this.name = name;
		description = descriptor;
	}

	public static Optional<DataBaseGeneratorConfigStatus> valueOfFlag(@NonNull Integer flag) {
		DataBaseGeneratorConfigStatus[] values = DataBaseGeneratorConfigStatus.values();

		for (DataBaseGeneratorConfigStatus d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<DataBaseGeneratorConfigStatus> valuesToList() {
		return Arrays.asList(DataBaseGeneratorConfigStatus.values());
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
