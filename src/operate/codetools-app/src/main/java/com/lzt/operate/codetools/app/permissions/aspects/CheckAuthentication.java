package com.lzt.operate.codetools.app.permissions.aspects;

import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
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

	@Autowired
	public CheckAuthentication(CustomJsonWebTokenConfig customJsonWebTokenConfig) {
		this.setBaseCustomJsonWebTokenConfig(customJsonWebTokenConfig);
	}

	/**
	 * @annotation 声明以注解的方式来定义切点
	 */
	@Pointcut("execution(public * com.lzt.operate.codetools.app.controllers.business.*.*(..))")
	public void checkDataPoint() {
	}

}
