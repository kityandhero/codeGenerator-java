package com.lzt.operate.codetools.app.enums;

import lombok.Getter;

/**
 * @author lzt
 */
@Getter
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

	private final Integer flag;
	private final String name;
	private final String description;

	DatabaseEncoding(int flag, String name, String description) {
		this.flag = flag;
		this.name = name;
		this.description = description;
	}

}
