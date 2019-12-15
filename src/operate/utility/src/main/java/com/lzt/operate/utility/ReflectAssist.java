package com.lzt.operate.utility;

import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * @author lzt
 */
public class ReflectAssist {

	private static final Logger log = LoggerFactory.getLogger(ValidatorAssist.class);

	/***
	 * 转换方法引用为属性名
	 * @param fn 方法名
	 * @return
	 */
	public static <T> String getFieldName(IGetter<T> fn) {
		SerializedLambda lambda = getSerializedLambda(fn);
		String methodName = lambda.getImplMethodName();
		String prefix = null;
		if (methodName.startsWith("get")) {
			prefix = "get";
		} else if (methodName.startsWith("is")) {
			prefix = "is";
		}
		if (prefix == null) {
			log.warn("无效的getter方法: " + methodName);
		}

		// 截取get/is之后的字符串并转换首字母为小写
		var result = StringAssist.toFirstLower(StringAssist.substringAfter(methodName, prefix));

		return result;
	}

	/***
	 * 转换setter方法引用为属性名
	 * @param fn
	 * @return
	 */
	public static <T, R> String getFieldName(ISetter<T, R> fn) {
		SerializedLambda lambda = getSerializedLambda(fn);
		String methodName = lambda.getImplMethodName();

		if (!methodName.startsWith("set")) {
			log.warn("无效的setter方法: " + methodName);
		}

		var result = StringAssist.toFirstLower(StringAssist.substringAfter(methodName, "set"));

		return result;
	}

	/***
	 * 获取类对应的Lambda
	 * @param fn
	 * @return
	 */
	private static SerializedLambda getSerializedLambda(Serializable fn) {
		//先检查缓存中是否已存在
		SerializedLambda lambda = null;
		try {//提取SerializedLambda并缓存
			Method method = fn.getClass().getDeclaredMethod("writeReplace");
			method.setAccessible(Boolean.TRUE);
			lambda = (SerializedLambda) method.invoke(fn);
		} catch (Exception e) {
			log.error("获取SerializedLambda异常, class=" + fn.getClass().getSimpleName(), e);
		}

		return lambda;
	}
}
