package com.lzt.operate.permissions.aspects;

import com.lzt.operate.entities.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.exceptions.AuthenticationException;
import com.lzt.operate.permissions.CustomJsonWebToken;
import org.aspectj.lang.annotation.Before;

import java.util.Optional;

/**
 * 检验登录基类
 *
 * @author lzt
 */
public abstract class BaseCheckAuthentication {

	protected BaseCustomJsonWebTokenConfig customJsonWebTokenConfig;

	/**
	 * 前置拦截
	 */
	@Before("checkDataPoint()")
	public void checkBefore() throws AuthenticationException {
		Optional<CustomJsonWebToken> optional = CustomJsonWebToken.getFromCurrentHttpToken(customJsonWebTokenConfig);

		if (!optional.isPresent()) {
			throw new AuthenticationException("请登录后访问");
		}

		CustomJsonWebToken customJsonWebToken = optional.get();

		if (customJsonWebToken.isTokenExpired()) {
			throw new AuthenticationException("登录超时，请重新登录");
		}
	}

}
