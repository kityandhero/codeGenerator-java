package com.lzt.operate.code.generator.custommessagequeue.generallog;

import com.lzt.operate.code.generator.entities.GeneralLog;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class SingletonGeneralLogQueue {

	private ConcurrentLinkedDeque<GeneralLog> queue;

	private SingletonGeneralLogQueue() {
		this.queue = new ConcurrentLinkedDeque<>();
	}

	public static SingletonGeneralLogQueue getInstance() {
		return InstanceHolder.instance;
	}

	public ConcurrentLinkedDeque<GeneralLog> getQueue() {
		return this.queue;
	}

	static class InstanceHolder {
		private static SingletonGeneralLogQueue instance = new SingletonGeneralLogQueue();
	}
}