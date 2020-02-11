package com.lzt.operate.utility.assists;

import java.io.Serializable;

/**
 * setter方法接口定义
 *
 * @author luzhitao
 */
@FunctionalInterface
public interface ISetter<T, U> extends Serializable {
	void accept(T t, U u);
}
