package com.lzt.operate.code.generator.custommessagequeue.accessway;

import com.lzt.operate.code.generator.entities.AccessWay;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class SingletonAccessWayQueue {

	private final ConcurrentLinkedDeque<AccessWay> queue;

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
		private static final SingletonAccessWayQueue instance = new SingletonAccessWayQueue();
	}
}
