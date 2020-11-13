package com.lzt.operate.code.generator.app.permissions.aspects;

import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.dao.service.AccountService;
import com.lzt.operate.code.generator.dao.service.impl.AccountServiceImpl;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.permissions.aspects.BaseCheckAuthentication;
import com.lzt.operate.utility.pojo.BaseOperator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 检验登录Aop
 *
 * @author luzhitao
 */
@Aspect
@Component
public class CheckAuthentication extends BaseCheckAuthentication {

	private final AccountService operatorService;

	@Autowired
	public CheckAuthentication(CustomJsonWebTokenConfig customJsonWebTokenConfig, AccountServiceImpl operatorService) {
		this.setBaseCustomJsonWebTokenConfig(customJsonWebTokenConfig);
		this.operatorService = operatorService;
	}

	/**
	 * 声明以注解的方式来定义切点
	 */
	@Pointcut("execution(public * com.lzt.operate.code.generator.app.controllers.business.*.*(..))")
	public void checkDataPoint() {
	}

	@Override
	protected boolean checkAccount(CustomJsonWebToken customJsonWebToken) {
		Optional<BaseOperator> result = customJsonWebToken.getOperator();

		return result.filter(baseOperator -> this.operatorService.existEffective(baseOperator.getOperatorId()))
					 .isPresent();
	}
}
