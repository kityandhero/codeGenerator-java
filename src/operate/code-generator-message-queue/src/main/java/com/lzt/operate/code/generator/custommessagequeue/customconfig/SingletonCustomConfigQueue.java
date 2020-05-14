package com.lzt.operate.code.generator.custommessagequeue.customconfig;

import com.lzt.operate.code.generator.entities.CustomConfig;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class SingletonCustomConfigQueue {

	private final ConcurrentLinkedDeque<CustomConfig> queue;

	private SingletonCustomConfigQueue() {
		queue = new ConcurrentLinkedDeque<>();
	}

	public static SingletonCustomConfigQueue getInstance() {
		return InstanceHolder.instance;
	}

	public ConcurrentLinkedDeque<CustomConfig> getQueue() {
		return queue;
	}

	static class InstanceHolder {
		private static final SingletonCustomConfigQueue instance = new SingletonCustomConfigQueue();
	}
}
