package com.lzt.operate.permissions;

import com.lzt.operate.utility.ConstantAssist;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 需要登录
 *
 * @author luzhitao
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Order(ConstantAssist.AUTHENTICATION_ORDER)
public @interface NeedAuthentication {
}
