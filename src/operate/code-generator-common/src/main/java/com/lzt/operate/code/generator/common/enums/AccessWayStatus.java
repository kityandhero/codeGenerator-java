package com.lzt.operate.code.generator.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public enum AccessWayStatus {

	/**
	 * 已启用
	 */
	Enabled(1, "已启用", "已启用"),

	/**
	 * 已禁用
	 */
	Disabled(0, "已禁用", "已禁用");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "accessWayStatusList";

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

	AccessWayStatus(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
	}

	public static Optional<AccessWayStatus> valueOfFlag(@NonNull Integer flag) {
		AccessWayStatus[] values = AccessWayStatus.values();

		for (AccessWayStatus d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<AccessWayStatus> valuesToList() {
		return Arrays.asList(AccessWayStatus.values());
	}

	public static boolean existFlag(@NonNull Integer flag) {
		Optional<AccessWayStatus> optional = valueOfFlag(flag);

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
