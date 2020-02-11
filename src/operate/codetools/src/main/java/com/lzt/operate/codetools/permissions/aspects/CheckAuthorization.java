package com.lzt.operate.codetools.permissions.aspects;

import com.lzt.operate.codetools.components.CustomJsonWebTokenConfig;
import com.lzt.operate.permissions.aspects.BaseCheckAuthorization;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 检验权限
 *
 * @author lzt
 */
@Aspect
@Component
public class CheckAuthorization extends BaseCheckAuthorization {

	@Autowired
	public CheckAuthorization(CustomJsonWebTokenConfig jwtConfig) {
		this.customJsonWebTokenConfig = jwtConfig;
	}

	@Override
	protected boolean checkAuthorization() {
		return false;
	}
}
