package com.lzt.operate.utility.secret;

import lombok.Data;

/**
 * 揭秘结果
 *
 * @author luzhitao
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
