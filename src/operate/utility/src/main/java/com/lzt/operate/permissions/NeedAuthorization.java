package com.lzt.operate.permissions;

import com.lzt.operate.utility.ConstantAssist;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 需要特定权限
 *
 * @author luzhitao
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Order(ConstantAssist.AUTHORIZATION_ORDER)
public @interface NeedAuthorization {
	/**
	 * 名称
	 */
	String name() default "";

	/**
	 * 标识
	 */
	String identification() default "";
}
