package com.lzt.operate.codetools.common.enums;

/**
 * @author luzhitao
 */
public enum GeneralLogStatus {

	/**
	 * 正常
	 */
	Normal(1, "正常", "正常");

	/**
	 * 值
	 */
	private int value;

	/**
	 * 描述
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	GeneralLogStatus(int value, String name, String descriptor) {
		this.value = value;
		this.name = name;
		this.description = descriptor;
	}

	public int getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

}
