package com.lzt.operate.codetools.permissions.aspects;

import com.lzt.operate.permissions.aspects.BaseCheckAuthorization;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 检验权限
 *
 * @author lzt
 */
public class CheckAuthorization extends BaseCheckAuthorization {

	/**
	 * @annotation 声明以注解的方式来定义切点
	 */
	@Pointcut("@annotation(com.lzt.operate.permissions.NeedAuthorization)")
	public void checkDataPoint() {
	}

}
