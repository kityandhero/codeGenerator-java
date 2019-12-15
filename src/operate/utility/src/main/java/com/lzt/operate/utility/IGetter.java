package com.lzt.operate.utility;

import java.io.Serializable;

/**
 * getter方法接口定义
 *
 * @author lzt
 */
@FunctionalInterface
public interface IGetter<T> extends Serializable {
	/**
	 * apply
	 *
	 * @param source
	 * @return
	 */
	Object apply(T source);
}