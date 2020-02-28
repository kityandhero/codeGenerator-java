package com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque;

import com.lzt.operate.utility.custommessagequeue.interfaces.IConsumer;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public abstract class BaseConsumerAdapter<T, Q extends ConcurrentLinkedDeque<T>> implements IConsumer<T, ConcurrentLinkedDeque<T>> {

	private Q queue;

	protected BaseConsumerAdapter(@NonNull Q queue) {
		this.queue = queue;
	}

	public Q getQueue() {
		return queue;
	}

	public Optional<T> pull() {
		return pull(this.queue);
	}

	public Optional<T> pull(Q queue) {
		if (queue.size() == 0) {
			return Optional.empty();
		}

		return Optional.empty();
	}

}
