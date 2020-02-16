package com.lzt.operate.utility.permissions.aspects;

import com.lzt.operate.utility.components.bases.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.utility.exceptions.AuthenticationException;
import com.lzt.operate.utility.exceptions.AuthorizationException;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Optional;

/**
 * 检验权限基类
 *
 * @author luzhitao
 */
public abstract class BaseCheckAuthorization {

	/**
	 * getCustomJsonWebTokenConfig
	 *
	 * @return BaseCustomJsonWebTokenConfig
	 */
	protected abstract BaseCustomJsonWebTokenConfig getCustomJsonWebTokenConfig();

	/**
	 * 声明以注解的方式来定义切点
	 */
	@Pointcut("@annotation(com.lzt.operate.utility.permissions.NeedAuthorization)")
	public void checkDataPoint() {
	}

	/**
	 * 前置拦截
	 */
	@Before("checkDataPoint() && @annotation(needAuthorization)")
	public void checkBefore(JoinPoint joinPoint, NeedAuthorization needAuthorization) throws AuthenticationException {
		Optional<CustomJsonWebToken> optional = CustomJsonWebToken.getEffectiveCurrent(getCustomJsonWebTokenConfig());

		if (!optional.isPresent()) {
			throw new AuthorizationException("无有效登录凭证，禁止访问");
		}

		CustomJsonWebToken customJsonWebToken = optional.get();

		if (!checkAuthorization(joinPoint, needAuthorization, customJsonWebToken)) {
			throw new AuthorizationException("无权限访问");
		}
	}

	/**
	 * 校验是否具有指定权限
	 *
	 * @param joinPoint          拦截点
	 * @param needAuthorization  权限信息
	 * @param customJsonWebToken 登录凭证
	 * @return boolean 是否具有权限
	 */
	protected abstract boolean checkAuthorization(JoinPoint joinPoint, NeedAuthorization needAuthorization, CustomJsonWebToken customJsonWebToken);

}
