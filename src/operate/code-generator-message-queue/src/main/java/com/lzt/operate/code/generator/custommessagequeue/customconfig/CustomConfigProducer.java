package com.lzt.operate.code.generator.custommessagequeue.customconfig;

import com.lzt.operate.code.generator.entities.CustomConfig;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseProducerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class CustomConfigProducer extends BaseProducerAdapter<CustomConfig, ConcurrentLinkedDeque<CustomConfig>> {

	protected CustomConfigProducer() {
		super(SingletonCustomConfigQueue.getInstance().getQueue());
	}

}
