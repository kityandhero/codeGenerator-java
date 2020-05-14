package com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque;

import com.lzt.operate.utility.custommessagequeue.interfaces.IProducer;
import org.springframework.lang.NonNull;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * ConcurrentLinkedDeque Producer
 *
 * @author luzhitao
 */
public abstract class BaseProducerAdapter<T, Q extends ConcurrentLinkedDeque<T>> implements IProducer<T, ConcurrentLinkedDeque<T>> {

	private final Q queue;

	protected BaseProducerAdapter(@NonNull Q queue) {
		this.queue = queue;
	}

	public Q getQueue() {
		return queue;
	}

	public void push(T message) {
		queue.offer(message);
	}

	public void push(T message, Q queue) {
		queue.offer(message);
	}

}
