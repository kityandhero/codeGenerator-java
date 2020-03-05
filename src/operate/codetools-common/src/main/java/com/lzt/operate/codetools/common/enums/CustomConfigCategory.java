package com.lzt.operate.codetools.common.enums;

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
