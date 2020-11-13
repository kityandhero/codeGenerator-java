package com.lzt.operate.code.generator.custommessagequeue.errorlog;

/**
 * @author luzhitao
 */
public class ErrorLogProducerFactory {

	private final ErrorLogProducer producer;

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
		private static final ErrorLogProducerFactory instance = new ErrorLogProducerFactory();
	}

}
