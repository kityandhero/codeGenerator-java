package com.lzt.operate.code.generator.custommessagequeue.errorlog;

import com.lzt.operate.code.generator.entities.ErrorLog;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class SingletonErrorLogQueue {

	private final ConcurrentLinkedDeque<ErrorLog> queue;

	private SingletonErrorLogQueue() {
		queue = new ConcurrentLinkedDeque<>();
	}

	public static SingletonErrorLogQueue getInstance() {
		return InstanceHolder.instance;
	}

	public ConcurrentLinkedDeque<ErrorLog> getQueue() {
		return queue;
	}

	static class InstanceHolder {
		private static final SingletonErrorLogQueue instance = new SingletonErrorLogQueue();
	}
}
