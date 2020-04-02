package com.lzt.operate.code.generator.custommessagequeue.errorlog;

import com.lzt.operate.code.generator.entities.ErrorLog;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.RequestAssist;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseProducerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class ErrorLogProducer extends BaseProducerAdapter<ErrorLog, ConcurrentLinkedDeque<ErrorLog>> {

	protected ErrorLogProducer() {
		super(SingletonErrorLogQueue.getInstance().getQueue());
	}

	public void pushException(Exception ex) {
		ErrorLog errorLog = new ErrorLog();

		errorLog.setMessage(ex.getMessage());

		try {
			errorLog.setStackTrace(ConvertAssist.serialize(ex.getStackTrace()));
		} catch (Exception e) {
			errorLog.setStackTrace("");

			ErrorLog errorLogCatch = new ErrorLog();

			errorLogCatch.setMessage(ex.getMessage());
			errorLogCatch.setHeader(RequestAssist.getCurrentRequestHeaderJson());

			this.push(errorLogCatch);
		}

		errorLog.setHeader(RequestAssist.getCurrentRequestHeaderJson());

		this.push(errorLog);
	}

}
