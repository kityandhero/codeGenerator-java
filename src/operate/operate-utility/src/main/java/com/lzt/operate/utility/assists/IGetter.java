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
	 * @param source source
	 * @return Object
	 */
	Object apply(T source);

	/**
	 * apply
	 *
	 * @param source source
	 * @return S
	 */
	default <S> S applyConvert(T source) {

		return ObjectAssist.cast(apply(source));
	}
}