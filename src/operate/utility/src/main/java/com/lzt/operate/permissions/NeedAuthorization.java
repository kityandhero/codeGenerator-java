package com.lzt.operate.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 需要特定权限
 *
 * @author lzt
 */
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NeedAuthorization {
}
