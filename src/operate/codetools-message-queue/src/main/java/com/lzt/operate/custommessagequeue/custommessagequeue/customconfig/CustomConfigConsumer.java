package com.lzt.operate.custommessagequeue.custommessagequeue.customconfig;

import com.lzt.operate.codetools.entities.CustomConfig;
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
