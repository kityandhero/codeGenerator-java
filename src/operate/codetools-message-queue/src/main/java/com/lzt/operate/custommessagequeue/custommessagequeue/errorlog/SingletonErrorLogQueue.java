package com.lzt.operate.custommessagequeue.custommessagequeue.errorlog;

import com.lzt.operate.codetools.entities.ErrorLog;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class SingletonErrorLogQueue {

	private ConcurrentLinkedDeque<ErrorLog> queue;

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
		private static SingletonErrorLogQueue instance = new SingletonErrorLogQueue();
	}
}