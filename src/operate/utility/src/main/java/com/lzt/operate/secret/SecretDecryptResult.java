package com.lzt.operate.secret;

import lombok.Data;

/**
 * 揭秘结果
 *
 * @author lzt
 */
@Data
public class SecretDecryptResult {

	public Boolean success;

	public String message;

	public String decryptResult;

	public Boolean expired;

	public SecretDecryptResult() {
		success = false;
		message = "";
		decryptResult = "";
		expired = false;

	}

}
