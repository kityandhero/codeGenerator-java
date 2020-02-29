package com.lzt.operate.custommessagequeue.custommessagequeue.errorlog;

import com.lzt.operate.codetools.entities.ErrorLog;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseProducerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class ErrorLogProducer extends BaseProducerAdapter<ErrorLog, ConcurrentLinkedDeque<ErrorLog>> {

	protected ErrorLogProducer() {
		super(SingletonErrorLogQueue.getInstance().getQueue());
	}

}
