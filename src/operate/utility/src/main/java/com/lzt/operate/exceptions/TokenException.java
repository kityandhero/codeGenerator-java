package com.lzt.operate.exceptions;

import org.springframework.http.HttpStatus;

/**
 * TokenException
 *
 * @author lzt
 */
public class TokenException extends Exception {

	private HttpStatus status;

	public TokenException(String message, HttpStatus status) {
		this.status = status;
	}

}
