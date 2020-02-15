package com.lzt.operate.utility.assists;

import java.io.Serializable;

/**
 * getter方法接口定义
 *
 * @author luzhitao
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