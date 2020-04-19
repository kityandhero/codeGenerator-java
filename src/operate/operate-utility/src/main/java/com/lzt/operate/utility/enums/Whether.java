package com.lzt.operate.utility.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
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
	public static final String META_KEY = "whetherList";

	/**
	 * 值
	 */
	private final Integer flag;

	private final String name;

	private final String description;

	Whether(int value, String name, String description) {
		flag = value;
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

	public static List<Whether> valuesToList() {
		return Arrays.asList(Whether.values());
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
