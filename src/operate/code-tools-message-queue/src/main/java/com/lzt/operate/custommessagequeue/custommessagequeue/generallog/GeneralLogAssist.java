package com.lzt.operate.custommessagequeue.custommessagequeue.generallog;

import com.lzt.operate.codetools.entities.GeneralLog;

/**
 * @author luzhitao
 */
public interface GeneralLogAssist {

	/**
	 * quickRecord
	 *
	 * @param message message
	 */
	static void quickRecord(String message) {
		GeneralLogProducer producer = GeneralLogProducerFactory.getInstance().getProducer();

		GeneralLog generalLog = new GeneralLog();

		generalLog.setMessage(message);

		producer.push(generalLog);
	}

}
