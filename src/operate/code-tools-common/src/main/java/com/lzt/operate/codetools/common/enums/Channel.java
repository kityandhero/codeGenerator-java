package com.lzt.operate.codetools.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 渠道枚举集合
 *
 * @author luzhitao
 */
public enum Channel {

	/**
	 * 未知
	 */
	Unknown(0, "未知", "未知渠道"),

	/**
	 * 代码工具
	 */
	CodeTools(10010, "代码工具", "代码工具");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "channelList";

	/**
	 * 标志
	 */
	private int flag;
	/**
	 * 标志
	 */
	private String name;
	/**
	 * 简介描述
	 */
	private String description;

	Channel(int flag, String name, String description) {
		this.flag = flag;
		this.name = name;
		this.description = description;
	}

	public static Optional<Channel> valueOfFlag(@NonNull Integer flag) {
		Channel[] values = Channel.values();

		for (Channel d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<Channel> valuesToList() {
		return Arrays.asList(Channel.values());
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
