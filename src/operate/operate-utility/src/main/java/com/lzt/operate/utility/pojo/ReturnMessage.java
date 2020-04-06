package com.lzt.operate.utility.pojo;

/**
 * 返回消息模型
 *
 * @author lzt
 */
public class ReturnMessage {

	private int code;
	private String message;
	private boolean success;

	protected ReturnMessage(int code, String message, boolean success) {
		this.code = code;
		this.message = message;
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public boolean getSuccess() {
		return success;
	}

	public ReturnMessage toMessage(boolean success) {
		this.success = success;

		return this;
	}

	public ReturnMessage toMessage(String message) {
		this.message = message;

		return this;
	}

	public ReturnMessage toMessage(int code) {
		this.code = code;

		return this;
	}

	public static ReturnMessage create(int code, String message, boolean success) {
		return new ReturnMessage(code, message, success);
	}
}
