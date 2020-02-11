package com.lzt.operate.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 需要登录
 *
 * @author lzt
 */
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NeedAuthentication {
}
