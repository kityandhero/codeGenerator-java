package com.lzt.operate.custommessagequeue.custommessagequeue.generallog;

/**
 * @author luzhitao
 */
public class GeneralLogProducerFactory {

	private GeneralLogProducer producer;

	private GeneralLogProducerFactory() {
		this.producer = new GeneralLogProducer();
	}

	public static GeneralLogProducerFactory getInstance() {
		return GeneralLogProducerFactory.InstanceHolder.instance;
	}

	public GeneralLogProducer getProducer() {
		return this.producer;
	}

	static class InstanceHolder {
		private static GeneralLogProducerFactory instance = new GeneralLogProducerFactory();
	}

}
