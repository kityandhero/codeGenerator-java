package com.lzt.operate.utility.pojo.results;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lzt
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExecutiveResult<T> extends ExecutiveSimpleResult {

	/**
	 * 消息
	 */
	private T data;

	public ExecutiveResult(T resultData) {
		super();

		data = resultData;
	}
}
