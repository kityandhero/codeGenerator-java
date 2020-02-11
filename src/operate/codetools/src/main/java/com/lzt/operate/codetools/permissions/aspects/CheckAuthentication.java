package com.lzt.operate.codetools.permissions.aspects;

import com.lzt.operate.codetools.components.CustomJsonWebTokenConfig;
import com.lzt.operate.permissions.aspects.BaseCheckAuthentication;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 检验登录Aop
 *
 * @author lzt
 */
@Aspect
@Component
public class CheckAuthentication extends BaseCheckAuthentication {

	@Autowired
	public CheckAuthentication(CustomJsonWebTokenConfig jwtConfig) {
		this.customJsonWebTokenConfig = jwtConfig;
	}

	/**
	 * @annotation 声明以注解的方式来定义切点
	 */
	@Pointcut("execution(public * com.lzt.operate.codetools.controllers.business.*.*(..))")
	public void checkDataPoint() {
	}

}
