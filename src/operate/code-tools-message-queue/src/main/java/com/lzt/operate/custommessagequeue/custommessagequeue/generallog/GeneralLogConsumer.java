package com.lzt.operate.custommessagequeue.custommessagequeue.generallog;

import com.lzt.operate.codetools.entities.GeneralLog;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseConsumerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class GeneralLogConsumer extends BaseConsumerAdapter<GeneralLog, ConcurrentLinkedDeque<GeneralLog>> {

	public GeneralLogConsumer() {
		super(SingletonGeneralLogQueue.getInstance().getQueue());
	}

}
