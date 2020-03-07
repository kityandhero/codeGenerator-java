package com.lzt.operate.codetools.app.enums;

/**
 * @author luzhitao
 */
public enum ConnectionType {

	/**
	 * GB2312
	 */
	TCP_IP(100, "TCP_IP", "TCP_IP"),

	/**
	 * GBK
	 */
	SSH(101, "SSH", "SSH");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "databaseConnectionTypeList";

	private final Integer flag;
	private final String name;
	private final String description;

	ConnectionType(int flag, String name, String description) {
		this.flag = flag;
		this.name = name;
		this.description = description;
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