package com.lzt.operate.custommessagequeue.custommessagequeue.errorlog;

/**
 * @author luzhitao
 */
public class ErrorLogProducerFactory {

	private ErrorLogProducer producer;

	private ErrorLogProducerFactory() {
		producer = new ErrorLogProducer();
	}

	public static ErrorLogProducerFactory getInstance() {
		return ErrorLogProducerFactory.InstanceHolder.instance;
	}

	public ErrorLogProducer getProducer() {
		return producer;
	}

	static class InstanceHolder {
		private static ErrorLogProducerFactory instance = new ErrorLogProducerFactory();
	}

}
