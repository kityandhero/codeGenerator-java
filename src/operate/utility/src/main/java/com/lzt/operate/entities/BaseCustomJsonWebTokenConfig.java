package com.lzt.operate.entities;

import java.util.Date;

/**
 * Json Web Token 设置
 *
 * @author lzt
 */
public abstract class BaseCustomJsonWebTokenConfig {
	private String secret;
	private Long expire;
	private String header;

	/**
	 * 校验token是否过期
	 *
	 * @param expirationTime expirationTime
	 * @return boolean
	 */
	public boolean isTokenExpired(Date expirationTime) {
		return expirationTime.before(new Date());
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Long getExpire() {
		return expire;
	}

	public void setExpire(Long expire) {
		this.expire = expire;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}