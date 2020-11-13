package com.lzt.operate.swagger2.model;

import com.lzt.operate.swagger2.CommonData;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author luzhitao
 * @date 2019-05-08 16:45
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiJsonResult {

    String[] value();

    String name() default "";

    String type() default CommonData.RESULT_TYPE_NORMAL_FINAL;

}