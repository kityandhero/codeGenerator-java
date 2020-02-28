package com.lzt.operate.utility.custommessagequeue.interfaces;

import java.util.Optional;

/**
 * 消息消费方
 *
 * @author luzhitao
 */
public interface IConsumer<T, Q> {

	/**
	 * 获取一条消息
	 *
	 * @param queue 消息队列
	 * @return T 消息体
	 */
	Optional<T> pull(Q queue);

}
