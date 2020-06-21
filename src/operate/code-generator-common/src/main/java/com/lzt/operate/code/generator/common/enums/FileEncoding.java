package com.lzt.operate.code.generator.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhita
 */

public enum FileEncoding {

	/**
	 * utf8
	 */
	UTF8(100, "UTF-8", "UTF-8"),

	/**
	 * GB2312
	 */
	GB2312(101, "GBK", "GBK");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "fileEncodingList";

	private final Integer flag;
	private final String name;
	private final String description;

	FileEncoding(int flag, String name, String description) {
		this.flag = flag;
		this.name = name;
		this.description = description;
	}

	public static Optional<FileEncoding> valueOfFlag(@NonNull Integer flag) {
		FileEncoding[] values = FileEncoding.values();

		for (FileEncoding d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<FileEncoding> valuesToList() {
		return Arrays.asList(FileEncoding.values());
	}

	public static boolean existFlag(@NonNull Integer flag) {
		Optional<FileEncoding> optional = valueOfFlag(flag);

		return optional.isPresent();
	}

	public Integer getFlag() {
		return this.flag;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

}
