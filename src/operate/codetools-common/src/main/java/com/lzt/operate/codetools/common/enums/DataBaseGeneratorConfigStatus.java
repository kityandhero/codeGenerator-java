package com.lzt.operate.codetools.common.enums;

import org.springframework.lang.NonNull;

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
	public static final String META_KEY = "dataBaseGeneratorConfigStatusList";

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

	DataBaseGeneratorConfigStatus(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
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
