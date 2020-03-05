package com.lzt.operate.custommessagequeue.custommessagequeue.customconfig;

import com.lzt.operate.codetools.entities.CustomConfig;
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
