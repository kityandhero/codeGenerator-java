package com.lzt.operate.code.generator.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 公共角色状态
 *
 * @author lzt
 */
public enum AccountStatus {

	/**
	 * 已启用
	 */
	Enabled(1, "已启用", "已启用"),

	/**
	 * 已禁用
	 */
	Disabled(0, "已禁用", "已禁用"),

	/**
	 * 已删除
	 */
	Remove(-1, "已删除", "已删除");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "accountStatusList";

	/**
	 * 值
	 */
	private final int flag;

	/**
	 * 描述
	 */
	private final String name;

	/**
	 * 描述
	 */
	private final String description;

	AccountStatus(int value, String name) {
		flag = value;
		this.name = name;
		description = "";
	}

	AccountStatus(int value, String name, String description) {
		flag = value;
		this.name = name;
		this.description = description;
	}

	public static Optional<AccountStatus> valueOfFlag(@NonNull Integer flag) {
		AccountStatus[] values = AccountStatus.values();

		for (AccountStatus d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<AccountStatus> valuesToList() {
		return Arrays.asList(AccountStatus.values());
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
