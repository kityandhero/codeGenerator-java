package com.lzt.operate.utility.custommessagequeue.interfaces;

/**
 * 消息生产者
 *
 * @author luzhitao
 */
public interface IProducer<T, Q> {

	/**
	 * 推送消息体
	 *
	 * @param message 消息
	 * @param queue   消息队列
	 */
	void push(T message, Q queue);

}
