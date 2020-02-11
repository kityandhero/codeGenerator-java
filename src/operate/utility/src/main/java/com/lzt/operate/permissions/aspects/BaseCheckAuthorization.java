package com.lzt.operate.permissions.aspects;

import com.lzt.operate.entities.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.exceptions.AuthenticationException;
import com.lzt.operate.exceptions.AuthorizationException;
import com.lzt.operate.permissions.CustomJsonWebToken;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Optional;

/**
 * 检验权限基类
 *
 * @author lzt
 */
public abstract class BaseCheckAuthorization {
	protected BaseCustomJsonWebTokenConfig customJsonWebTokenConfig;

	/**
	 * @annotation 声明以注解的方式来定义切点
	 */
	@Pointcut("@annotation(com.lzt.operate.permissions.NeedAuthorization)")
	public void checkDataPoint() {
	}

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

		if (!checkAuthorization()) {
			throw new AuthorizationException("无权限访问");
		}
	}

	/**
	 * 校验是否具有指定权限
	 *
	 * @return boolean
	 */
	protected abstract boolean checkAuthorization();
}
