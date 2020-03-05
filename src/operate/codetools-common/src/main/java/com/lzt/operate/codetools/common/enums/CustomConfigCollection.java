package com.lzt.operate.codetools.common.enums;

import com.lzt.operate.utility.general.ConstantCollection;

import java.util.Arrays;
import java.util.List;

/**
 * 功能配置型键值对
 *
 * @author luzhitao
 */
public enum CustomConfigCollection {

	/**
	 * 记录启用日志
	 */
	NeedLogin(
			"d4f21d37-c35d-48cb-ac1c-843bc0dacc87",
			"需要登录使用",
			CustomConfigCategory.Common,
			boolean.class.getTypeName(),
			Arrays.asList(ConstantCollection.NO_STRING, ConstantCollection.YES_STRING),
			ConstantCollection.YES_STRING,
			"需要登录才能使用工具使用"),

	/**
	 * 记录启用日志
	 */
	RecordStartLog(
			"b4ed81b7-5dbd-49e1-919e-55006f5d79d4",
			"记录启用日志",
			CustomConfigCategory.Log,
			boolean.class.getTypeName(),
			Arrays.asList(ConstantCollection.NO_STRING, ConstantCollection.YES_STRING),
			ConstantCollection.YES_STRING,
			"是否记录启动日志");

	private String uuid;

	private String name;

	private CustomConfigCategory category;

	private String valueType;

	private List<String> availableValue;

	private String defaultValue;

	private String description;

	CustomConfigCollection(String guid, String name, CustomConfigCategory category, String valueType, List<String> availableValue, String defaultValue, String description) {
		this.uuid = guid;
		this.name = name;
		this.category = category;
		this.valueType = valueType;
		this.availableValue = availableValue;
		this.defaultValue = defaultValue;
		this.description = description;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public CustomConfigCategory getCategory() {
		return category;
	}

	public String getValueType() {
		return valueType;
	}

	public List<String> getAvailableValue() {
		return availableValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getDescription() {
		return description;
	}
}
