package com.lzt.operate.exceptions;

/**
 * 鉴权异常
 *
 * @author lzt
 */
public class AuthorizationException extends RuntimeException {

	public AuthorizationException() {
	}

	public AuthorizationException(String message) {
		super(message);
	}

}
