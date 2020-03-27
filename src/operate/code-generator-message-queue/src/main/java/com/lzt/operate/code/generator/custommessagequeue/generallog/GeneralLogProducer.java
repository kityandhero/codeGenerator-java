package com.lzt.operate.code.generator.custommessagequeue.generallog;

import com.lzt.operate.code.generator.entities.GeneralLog;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseProducerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class GeneralLogProducer extends BaseProducerAdapter<GeneralLog, ConcurrentLinkedDeque<GeneralLog>> {

	protected GeneralLogProducer() {
		super(SingletonGeneralLogQueue.getInstance().getQueue());
	}

}
