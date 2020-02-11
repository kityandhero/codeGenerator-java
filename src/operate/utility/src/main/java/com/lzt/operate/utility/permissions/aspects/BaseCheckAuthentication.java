package com.lzt.operate.utility.permissions.aspects;

import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.pojo.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.utility.exceptions.AuthenticationException;
import org.aspectj.lang.annotation.Before;

/**
 * 检验登录基类
 *
 * @author luzhitao
 */
public abstract class BaseCheckAuthentication {

	private BaseCustomJsonWebTokenConfig customJsonWebTokenConfig;

	protected BaseCustomJsonWebTokenConfig getCustomJsonWebTokenConfig() {
		return this.customJsonWebTokenConfig;
	}

	protected void setBaseCustomJsonWebTokenConfig(BaseCustomJsonWebTokenConfig customJsonWebTokenConfig) {
		this.customJsonWebTokenConfig = customJsonWebTokenConfig;
	}

	/**
	 * 前置拦截
	 */
	@Before("checkDataPoint()")
	public void checkBefore() throws AuthenticationException {
		CustomJsonWebToken.checkToken(customJsonWebTokenConfig);
	}

}
