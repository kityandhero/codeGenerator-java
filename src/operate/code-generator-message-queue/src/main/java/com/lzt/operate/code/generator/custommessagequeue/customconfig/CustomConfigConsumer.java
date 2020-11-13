package com.lzt.operate.code.generator.custommessagequeue.customconfig;

import com.lzt.operate.code.generator.entities.CustomConfig;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseConsumerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class CustomConfigConsumer extends BaseConsumerAdapter<CustomConfig, ConcurrentLinkedDeque<CustomConfig>> {

	public CustomConfigConsumer() {
		super(SingletonCustomConfigQueue.getInstance().getQueue());
	}

}
