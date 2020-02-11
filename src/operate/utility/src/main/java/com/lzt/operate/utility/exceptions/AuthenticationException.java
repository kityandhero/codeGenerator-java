package com.lzt.operate.utility.exceptions;

/**
 * 认证异常
 *
 * @author luzhitao
 * @date 2020/02/11 16:13
 */
public class AuthenticationException extends RuntimeException {

	public AuthenticationException() {
	}

	public AuthenticationException(String message) {
		super(message);
	}

}
