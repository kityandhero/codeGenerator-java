package com.lzt.operate.utility.permissions;

import com.lzt.operate.utility.assists.ConstantAssist;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要特定权限
 *
 * @author luzhitao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Order(ConstantAssist.AUTHORIZATION_ORDER)
public @interface NeedAuthorization {
	/**
	 * 名称
	 */
	String name() default "";

	/**
	 * 简介描述
	 */
	String description() default "";

	/**
	 * 标识
	 */
	String tag() default "";

	/**
	 * 子权限配置
	 *
	 * @return String[]
	 */
	String[] config() default {};
}
