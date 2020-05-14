package com.lzt.operate.utility.extensions;

/**
 * 整型扩展类
 * CreteTime:2019-04-28 23:14
 * UpdateTIme:2019-04-28 23:14
 *
 * @author luzhitao
 */
public class IntegerEx {
	private final Integer source;

	IntegerEx(Integer source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return source.toString();
	}

	public StringEx toStringEx() {
		return new StringEx(toString());
	}
}
