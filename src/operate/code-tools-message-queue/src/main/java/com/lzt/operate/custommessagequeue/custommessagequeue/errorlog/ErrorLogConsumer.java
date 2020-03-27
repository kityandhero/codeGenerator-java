package com.lzt.operate.custommessagequeue.custommessagequeue.errorlog;

import com.lzt.operate.codetools.entities.ErrorLog;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseConsumerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class ErrorLogConsumer extends BaseConsumerAdapter<ErrorLog, ConcurrentLinkedDeque<ErrorLog>> {

	public ErrorLogConsumer() {
		super(SingletonErrorLogQueue.getInstance().getQueue());
	}

}
