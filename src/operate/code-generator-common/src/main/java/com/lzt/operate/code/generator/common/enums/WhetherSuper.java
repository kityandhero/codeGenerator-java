package com.lzt.operate.code.generator.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 是否超级管理员
 *
 * @author luzhitao
 */
public enum WhetherSuper {

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

	WhetherSuper(int value, String name, String description) {
		this.flag = value;
		this.name = name;
		this.description = description;
	}

	public static Optional<WhetherSuper> valueOfFlag(@NonNull Integer flag) {
		WhetherSuper[] values = WhetherSuper.values();

		for (WhetherSuper d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<WhetherSuper> valuesToList() {
		return Arrays.asList(WhetherSuper.values());
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
