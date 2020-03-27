package com.lzt.operate.codetools.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author lzt
 */
public enum DatabaseEncoding {

	/**
	 * utf8
	 */
	UTF8(100, "utf8", "utf8"),

	/**
	 * GB2312
	 */
	GB2312(101, "gb2312", "gb2312"),

	/**
	 * GBK
	 */
	GBK(103, "gbk", "gbk");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "databaseEncodingList";

	private final Integer flag;
	private final String name;
	private final String description;

	DatabaseEncoding(int flag, String name, String description) {
		this.flag = flag;
		this.name = name;
		this.description = description;
	}

	public static Optional<DatabaseEncoding> valueOfFlag(@NonNull Integer flag) {
		DatabaseEncoding[] values = DatabaseEncoding.values();

		for (DatabaseEncoding d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<DatabaseEncoding> valuesToList() {
		return Arrays.asList(DatabaseEncoding.values());
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
