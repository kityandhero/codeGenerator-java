package com.lzt.operate.exceptions;

/**
 * 认证异常
 *
 * @author lzt
 */
public class AuthenticationException extends RuntimeException {

	public AuthenticationException() {
	}

	public AuthenticationException(String message) {
		super(message);
	}

}
