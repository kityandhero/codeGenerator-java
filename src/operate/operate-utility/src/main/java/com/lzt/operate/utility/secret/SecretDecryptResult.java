package com.lzt.operate.utility.secret;

import lombok.Data;

/**
 * 揭秘结果
 *
 * @author luzhitao
 */
public class SecretDecryptResult {

	private Boolean success;

	private String message;

	private String decryptResult;

	private Boolean expired;

	public SecretDecryptResult() {
		success = false;
		message = "";
		decryptResult = "";
		expired = false;

	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDecryptResult() {
		return decryptResult;
	}

	public void setDecryptResult(String decryptResult) {
		this.decryptResult = decryptResult;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
}
