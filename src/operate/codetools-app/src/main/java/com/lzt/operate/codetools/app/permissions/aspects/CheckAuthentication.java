package com.lzt.operate.codetools.app.permissions.aspects;

import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.dao.service.OperatorService;
import com.lzt.operate.codetools.dao.service.impl.OperatorServiceImpl;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.permissions.aspects.BaseCheckAuthentication;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 检验登录Aop
 *
 * @author luzhitao
 */
@Aspect
@Component
public class CheckAuthentication extends BaseCheckAuthentication {

	private final OperatorService operatorService;

	@Autowired
	public CheckAuthentication(CustomJsonWebTokenConfig customJsonWebTokenConfig, OperatorServiceImpl operatorService) {
		this.setBaseCustomJsonWebTokenConfig(customJsonWebTokenConfig);
		this.operatorService = operatorService;
	}

	/**
	 * 声明以注解的方式来定义切点
	 */
	@Pointcut("execution(public * com.lzt.operate.codetools.app.controllers.business.*.*(..))")
	public void checkDataPoint() {
	}

	@Override
	protected boolean checkAccount(CustomJsonWebToken customJsonWebToken) {
		long operatorId = customJsonWebToken.getId();

		return this.operatorService.existEffective(operatorId);
	}
}
