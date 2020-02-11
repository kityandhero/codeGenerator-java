package com.lzt.operate.codetools.app.components.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author luzhitao
 */
@Aspect
@Component
public class LogAspect {

	/**
	 * 功能描述: 拦截对这个包下所有方法的访问
	 *
	 * @param:[]
	 * @return:void
	 **/
	@Pointcut("execution(* com.lzt.operate.codetools.app.controllers.*.*(..))")
	public void loginLog() {
	}

	/**
	 * 前置通知
	 *
	 * @param joinPoint joinPoint
	 */
	@Before("loginLog()")
	public void loginBefore(JoinPoint joinPoint) {

		//们从请求的上下文中获取request，记录请求的内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		assert attributes != null;
		HttpServletRequest request = attributes.getRequest();

		System.out.println("请求路径 : " + request.getRequestURL());
		System.out.println("请求方式 : " + request.getMethod());
		System.out.println("方法名 : " + joinPoint.getSignature().getName());
		System.out.println("类路径 : " + joinPoint.getSignature().getDeclaringTypeName());
		System.out.println("参数 : " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(returning = "object", pointcut = "loginLog()")
	public void doAfterReturning(Object object) {

		System.out.println("方法的返回值 : " + object);
	}

	/**
	 * 方法发生异常时执行该方法
	 *
	 * @param joinPoint joinPoint
	 * @param e         Exception
	 */
	@AfterThrowing(throwing = "e", pointcut = "loginLog()")
	public void throwsExecute(JoinPoint joinPoint, Exception e) {

		System.err.println("方法执行异常 : " + e.getMessage());
	}

	/**
	 * 后置通知
	 */
	@After("loginLog()")
	public void afterInform() {

		System.out.println("后置通知结束");
	}

	/**
	 * 环绕通知
	 *
	 * @param proceedingJoinPoint proceedingJoinPoint
	 * @return Object
	 */
	@Around("loginLog()")
	public Object surroundInform(ProceedingJoinPoint proceedingJoinPoint) {

		System.out.println("环绕通知开始...");

		try {
			Object o = proceedingJoinPoint.proceed();
			System.out.println("方法环绕proceed，结果是 :" + o);
			return o;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
}
