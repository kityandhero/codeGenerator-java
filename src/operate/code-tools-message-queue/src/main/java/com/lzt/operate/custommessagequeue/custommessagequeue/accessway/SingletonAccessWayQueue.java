package com.lzt.operate.custommessagequeue.custommessagequeue.accessway;

import com.lzt.operate.codetools.entities.AccessWay;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class SingletonAccessWayQueue {

	private ConcurrentLinkedDeque<AccessWay> queue;

	private SingletonAccessWayQueue() {
		queue = new ConcurrentLinkedDeque<>();
	}

	public static SingletonAccessWayQueue getInstance() {
		return InstanceHolder.instance;
	}

	public ConcurrentLinkedDeque<AccessWay> getQueue() {
		return queue;
	}

	static class InstanceHolder {
		private static SingletonAccessWayQueue instance = new SingletonAccessWayQueue();
	}
}
