package com.lzt.operate.codetools.common.enums;

import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author luzhitao
 */

public enum ErrorLogStatus {

	/**
	 * 正常
	 */
	Normal(1, "正常", "正常");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "errorLogStatusList";

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

	ErrorLogStatus(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
	}

	public static Optional<ErrorLogStatus> valueOfFlag(@NonNull Integer flag) {
		ErrorLogStatus[] values = ErrorLogStatus.values();

		for (ErrorLogStatus d : values) {
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
