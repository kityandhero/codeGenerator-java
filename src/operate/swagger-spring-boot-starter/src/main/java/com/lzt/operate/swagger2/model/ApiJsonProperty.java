package com.lzt.operate.swagger2.model;

import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lzt
 * @date 2019-05-08 16:45
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiJsonProperty {

    String name();

    String defaultValue() default "";

    String description() default "";

    String allowableValues() default "";

    boolean required() default false;

    String access() default "";

    boolean allowMultiple() default false;

    Class<?> type() default String.class;

    String paramType() default "";

    String example() default "";

    Example examples() default @Example(value = @ExampleProperty(value = ""));

    String format() default "";

    boolean readOnly() default false;

    String collectionFormat() default "";

}