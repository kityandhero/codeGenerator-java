package com.lzt.operate.codetools.app.permissions.aspects;

import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.permissions.aspects.BaseCheckAuthorization;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 检验权限
 *
 * @author luzhitao
 */
@Aspect
@Component
public class CheckAuthorization extends BaseCheckAuthorization {

	@Autowired
	public CheckAuthorization(CustomJsonWebTokenConfig customJsonWebTokenConfig) {
		this.setBaseCustomJsonWebTokenConfig(customJsonWebTokenConfig);
	}

	@Override
	protected boolean checkAuthorization(CustomJsonWebToken customJsonWebToken) {
		return false;
	}
}
