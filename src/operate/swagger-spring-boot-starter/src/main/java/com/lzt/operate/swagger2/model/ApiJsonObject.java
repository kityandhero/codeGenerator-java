package com.lzt.operate.swagger2.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author luzhitao
 * @date 2019-05-08 16:45
 */
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiJsonObject {

	ApiJsonProperty[] value(); //对象属性值

	ApiJsonResult result() default @ApiJsonResult({});

	String name() default "";

}
