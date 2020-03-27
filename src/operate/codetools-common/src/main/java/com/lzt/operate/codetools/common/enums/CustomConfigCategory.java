package com.lzt.operate.codetools.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 自定义配置分类
 *
 * @author luzhitao
 */
public enum CustomConfigCategory {

	/**
	 * 日志记录
	 */
	Common(1, "一般设置", "一般设置"),

	/**
	 * 日志记录
	 */
	Log(10, "日志记录", "日志记录相关");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "customConfigCategoryList";

	private int flag;

	private String name;

	private String description;

	CustomConfigCategory(int flag, String name, String description) {
		this.flag = flag;
		this.name = name;
		this.description = description;
	}

	public static Optional<CustomConfigCategory> valueOfFlag(@NonNull Integer flag) {
		CustomConfigCategory[] values = CustomConfigCategory.values();

		for (CustomConfigCategory d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<CustomConfigCategory> valuesToList() {
		return Arrays.asList(CustomConfigCategory.values());
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
