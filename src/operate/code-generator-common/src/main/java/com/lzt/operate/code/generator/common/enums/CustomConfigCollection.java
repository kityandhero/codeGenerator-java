package com.lzt.operate.code.generator.common.enums;

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
	 * 自动打开Web操作面板
	 */
	AutoOpenOperatePanel(
			"e7cdc419-6217-4ae0-a813-4daaba5ce245",
			"自动打开Web操作面板",
			CustomConfigCategory.Common,
			boolean.class.getTypeName(),
			Arrays.asList(ConstantCollection.NO_STRING, ConstantCollection.YES_STRING),
			ConstantCollection.YES_STRING,
			"启动后是否自动打开Web操作面板"),

	/**
	 * 需要登录使用
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

	private final String uuid;

	private final String name;

	private final CustomConfigCategory category;

	private final String valueType;

	private final List<String> availableValue;

	private final String defaultValue;

	private final String description;

	CustomConfigCollection(String guid, String name, CustomConfigCategory category, String valueType, List<String> availableValue, String defaultValue, String description) {
		uuid = guid;
		this.name = name;
		this.category = category;
		this.valueType = valueType;
		this.availableValue = availableValue;
		this.defaultValue = defaultValue;
		this.description = description;
	}

	public static List<CustomConfigCollection> valuesToList() {
		return Arrays.asList(CustomConfigCollection.values());
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
