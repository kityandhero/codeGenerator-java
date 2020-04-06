package com.lzt.operate.utility.exceptions;

/**
 * 鉴权异常
 *
 * @author luzhitao
 */
public class AuthorizationException extends RuntimeException {

	public AuthorizationException() {
	}

	public AuthorizationException(String message) {
		super(message);
	}

}
