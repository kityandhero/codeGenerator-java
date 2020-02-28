package com.lzt.operate.custommessagequeue.custommessagequeue.accessway;

import com.lzt.operate.codetools.interfaces.IAccessWay;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class SingletonQueue {

	private ConcurrentLinkedDeque<IAccessWay> queue;

	private SingletonQueue() {
		queue = new ConcurrentLinkedDeque<>();
	}

	public static SingletonQueue getInstance() {
		return InstanceHolder.instance;
	}

	public ConcurrentLinkedDeque<IAccessWay> getQueue() {
		return queue;
	}

	static class InstanceHolder {
		private static SingletonQueue instance = new SingletonQueue();
	}
}
