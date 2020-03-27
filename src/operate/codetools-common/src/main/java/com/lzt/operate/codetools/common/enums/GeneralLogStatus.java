package com.lzt.operate.codetools.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public enum GeneralLogStatus {

	/**
	 * 正常
	 */
	Normal(1, "正常", "正常");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "generalLogStatusList";

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

	GeneralLogStatus(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
	}

	public static Optional<GeneralLogStatus> valueOfFlag(@NonNull Integer flag) {
		GeneralLogStatus[] values = GeneralLogStatus.values();

		for (GeneralLogStatus d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<GeneralLogStatus> valuesToList() {
		return Arrays.asList(GeneralLogStatus.values());
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
