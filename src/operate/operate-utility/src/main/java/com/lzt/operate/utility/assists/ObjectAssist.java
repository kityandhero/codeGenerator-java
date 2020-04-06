package com.lzt.operate.utility.assists;

/**
 * @author luzhitao
 */
public class ObjectAssist {

	/**
	 * 转换Object为指定类型，不当调用将产生异常
	 *
	 * @param value Object
	 * @param <T>   目标类型
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cast(Object value) {
		return (T) value;
	}
}
