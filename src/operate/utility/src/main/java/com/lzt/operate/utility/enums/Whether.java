package com.lzt.operate.utility.enums;

import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author luzhitao
 */

public enum Whether {

	/**
	 * 是
	 */
	Yes(1, "是", "是"),

	/**
	 * 否
	 */
	No(0, "否", "否");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "whetherSuperList";

	/**
	 * 值
	 */
	private Integer flag;

	private String name;

	private String description;

	Whether(int value, String name, String description) {
		this.flag = value;
		this.name = name;
		this.description = description;
	}

	public static Optional<Whether> valueOfFlag(@NonNull Integer flag) {
		Whether[] values = Whether.values();

		for (Whether d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public Integer getFlag() {
		return flag;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
