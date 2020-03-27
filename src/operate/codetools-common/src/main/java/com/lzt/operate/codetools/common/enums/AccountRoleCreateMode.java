package com.lzt.operate.codetools.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 创建模式
 *
 * @author lzt
 */
public enum AccountRoleCreateMode {

	/**
	 * 系统角色
	 */
	FromUniversal(0, "系统角色", "系统角色"),

	/**
	 * 自主建立
	 */
	IndependentEstablishment(1, "自主建立", "自主建立");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "accountRoleCreateModeList";

	/**
	 * 值
	 */
	private int flag;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	AccountRoleCreateMode(int value, String name, String descriptor) {
		this.flag = value;
		this.name = name;
		this.description = descriptor;
	}

	public static Optional<AccountRoleCreateMode> valueOfFlag(@NonNull Integer flag) {
		AccountRoleCreateMode[] values = AccountRoleCreateMode.values();

		for (AccountRoleCreateMode d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<AccountRoleCreateMode> valuesToList() {
		return Arrays.asList(AccountRoleCreateMode.values());
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
