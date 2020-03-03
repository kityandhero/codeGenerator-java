package com.lzt.operate.codetools.common.enums;

/**
 * 功能配置型键值对
 *
 * @author luzhitao
 */
public enum CustomConfigCollection {
	/**
	 * 记录启用日志
	 */
	RecordStartLog("b4ed81b7-5dbd-49e1-919e-55006f5d79d4", "记录启用日志", CustomConfigCategory.Log, "0", boolean.class.getTypeName(), "是否记录启动日志");

	private String uuid;

	private String name;

	private CustomConfigCategory category;

	private String valueType;

	private String defaultValue;

	private String description;

	CustomConfigCollection(String guid, String name, CustomConfigCategory category, String valueType, String defaultValue, String description) {
		this.uuid = guid;
		this.name = name;
		this.category = category;
		this.valueType = valueType;
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

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getDescription() {
		return description;
	}
}
