package com.lzt.operate.codetools.app.enums;

import lombok.Getter;

/**
 * @author luzhitao
 */
@Getter
public enum ConnectionType {

	/**
	 * GB2312
	 */
	TCP_IP(100, "TCP_IP", "TCP_IP"),

	/**
	 * GBK
	 */
	SSH(101, "SSH", "SSH");

	private final Integer flag;
	private final String name;
	private final String description;

	ConnectionType(int flag, String name, String description) {
		this.flag = flag;
		this.name = name;
		this.description = description;
	}

}