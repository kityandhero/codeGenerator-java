package com.lzt.operate.codetools.common.enums;

import org.springframework.lang.NonNull;

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
	private int flag;

	/**
	 * 描述
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	DataColumnStatus(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
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
