package com.lzt.operate.utility.exceptions;

import org.springframework.http.HttpStatus;

/**
 * TokenException
 *
 * @author luzhitao
 */
public class TokenException extends Exception {

	private HttpStatus status;

	public TokenException(String message, HttpStatus status) {
		this.status = status;
	}

}
