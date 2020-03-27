package com.lzt.operate.code.generator.custommessagequeue.customconfig;

/**
 * @author luzhitao
 */
public class CustomConfigProducerFactory {

	private CustomConfigProducer producer;

	private CustomConfigProducerFactory() {
		this.producer = new CustomConfigProducer();
	}

	public static CustomConfigProducerFactory getInstance() {
		return CustomConfigProducerFactory.InstanceHolder.instance;
	}

	public CustomConfigProducer getProducer() {
		return this.producer;
	}

	static class InstanceHolder {
		private static CustomConfigProducerFactory instance = new CustomConfigProducerFactory();
	}

}
