package com.lzt.operate.utility.permissions.aspects;

import com.lzt.operate.utility.pojo.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.utility.exceptions.AuthenticationException;
import com.lzt.operate.utility.exceptions.AuthorizationException;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Optional;

/**
 * 检验权限基类
 *
 * @author luzhitao
 */
public abstract class BaseCheckAuthorization {

	private BaseCustomJsonWebTokenConfig customJsonWebTokenConfig;

	protected BaseCustomJsonWebTokenConfig getCustomJsonWebTokenConfig() {
		return this.customJsonWebTokenConfig;
	}

	protected void setBaseCustomJsonWebTokenConfig(BaseCustomJsonWebTokenConfig customJsonWebTokenConfig) {
		this.customJsonWebTokenConfig = customJsonWebTokenConfig;
	}

	/**
	 * @annotation 声明以注解的方式来定义切点
	 */
	@Pointcut("@annotation(com.lzt.operate.utility.permissions.NeedAuthorization)")
	public void checkDataPoint() {
	}

	/**
	 * 前置拦截
	 */
	@Before("checkDataPoint()")
	public void checkBefore() throws AuthenticationException {
		Optional<CustomJsonWebToken> optional = CustomJsonWebToken.getEffectiveCurrent(customJsonWebTokenConfig);

		if (!optional.isPresent()) {
			throw new AuthorizationException("无有效登录凭证，禁止访问");
		}

		CustomJsonWebToken customJsonWebToken = optional.get();

		if (!checkAuthorization(customJsonWebToken)) {
			throw new AuthorizationException("无权限访问");
		}
	}

	/**
	 * 校验是否具有指定权限
	 *
	 * @param customJsonWebToken 登录凭证
	 * @return boolean 是否具有权限
	 */
	protected abstract boolean checkAuthorization(CustomJsonWebToken customJsonWebToken);

}
