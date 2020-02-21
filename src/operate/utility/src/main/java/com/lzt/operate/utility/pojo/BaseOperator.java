package com.lzt.operate.utility.pojo;

/**
 * @author lzt
 */
public class BaseOperator {

	/**
	 * 操作者标识，来自各种账户赋值
	 */
	private long operatorId;

	/**
	 * 操作者登录名
	 */
	private String userName;

	public BaseOperator() {
		this.operatorId = 0;
		this.userName = "";
	}

	public long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
