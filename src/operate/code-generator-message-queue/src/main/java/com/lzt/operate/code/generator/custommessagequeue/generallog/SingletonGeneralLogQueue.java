package com.lzt.operate.code.generator.custommessagequeue.generallog;

import com.lzt.operate.code.generator.entities.GeneralLog;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class SingletonGeneralLogQueue {

	private final ConcurrentLinkedDeque<GeneralLog> queue;

	private SingletonGeneralLogQueue() {
		queue = new ConcurrentLinkedDeque<>();
	}

	public static SingletonGeneralLogQueue getInstance() {
		return InstanceHolder.instance;
	}

	public ConcurrentLinkedDeque<GeneralLog> getQueue() {
		return queue;
	}

	static class InstanceHolder {
		private static final SingletonGeneralLogQueue instance = new SingletonGeneralLogQueue();
	}
}
