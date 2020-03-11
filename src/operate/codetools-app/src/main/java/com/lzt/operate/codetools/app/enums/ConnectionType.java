package com.lzt.operate.codetools.app.enums;

import org.springframework.lang.NonNull;

import java.util.Optional;

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

	public static Optional<ConnectionType> valueOfFlag(@NonNull Integer flag) {
		ConnectionType[] values = ConnectionType.values();

		for (ConnectionType d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
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