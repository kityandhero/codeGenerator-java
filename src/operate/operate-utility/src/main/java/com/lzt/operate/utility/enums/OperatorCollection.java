package com.lzt.operate.utility.enums;

/**
 * 操作返回码
 *
 * @author luzhitao
 */
public enum OperatorCollection {

	/**
	 * 系统操作
	 */
	System(-1, "系统操作");

	private long id;

	private String userName;

	OperatorCollection(long id, String userName) {
		this.id = id;
		this.userName = userName;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

}
