package com.lzt.operate.code.generator.custommessagequeue.customconfig;

import com.lzt.operate.code.generator.entities.CustomConfig;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class SingletonCustomConfigQueue {

	private ConcurrentLinkedDeque<CustomConfig> queue;

	private SingletonCustomConfigQueue() {
		this.queue = new ConcurrentLinkedDeque<>();
	}

	public static SingletonCustomConfigQueue getInstance() {
		return InstanceHolder.instance;
	}

	public ConcurrentLinkedDeque<CustomConfig> getQueue() {
		return this.queue;
	}

	static class InstanceHolder {
		private static SingletonCustomConfigQueue instance = new SingletonCustomConfigQueue();
	}
}
