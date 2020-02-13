package com.lzt.operate.utility.permissions.aspects;

import com.lzt.operate.utility.components.bases.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.utility.exceptions.AuthenticationException;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
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
		CustomJsonWebToken customJsonWebToken = CustomJsonWebToken.checkToken(customJsonWebTokenConfig);

		boolean checkResult = checkAccount(customJsonWebToken);

		if (!checkResult) {
			throw new AuthenticationException("登录信息异常");
		}
	}

	/**
	 * 检测登录账户数据，比如状态发生变更等等
	 *
	 * @param customJsonWebToken customJsonWebToken
	 * @return Boolean
	 */
	protected abstract boolean checkAccount(CustomJsonWebToken customJsonWebToken);
}
