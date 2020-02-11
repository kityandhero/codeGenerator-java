package com.lzt.operate.permissions.aspects;

import com.lzt.operate.entities.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.exceptions.AuthenticationException;
import com.lzt.operate.permissions.CustomJsonWebToken;
import org.aspectj.lang.annotation.Before;

/**
 * 检验登录基类
 *
 * @author luzhitao
 */
public abstract class BaseCheckAuthentication {

	protected BaseCustomJsonWebTokenConfig customJsonWebTokenConfig;

	/**
	 * 前置拦截
	 */
	@Before("checkDataPoint()")
	public void checkBefore() throws AuthenticationException {
		CustomJsonWebToken.checkToken(customJsonWebTokenConfig);
	}

}
