package com.lzt.operate.code.generator.custommessagequeue.generallog;

/**
 * @author luzhitao
 */
public class GeneralLogProducerFactory {

	private final GeneralLogProducer producer;

	private GeneralLogProducerFactory() {
		producer = new GeneralLogProducer();
	}

	public static GeneralLogProducerFactory getInstance() {
		return GeneralLogProducerFactory.InstanceHolder.instance;
	}

	public GeneralLogProducer getProducer() {
		return producer;
	}

	static class InstanceHolder {
		private static final GeneralLogProducerFactory instance = new GeneralLogProducerFactory();
	}

}
