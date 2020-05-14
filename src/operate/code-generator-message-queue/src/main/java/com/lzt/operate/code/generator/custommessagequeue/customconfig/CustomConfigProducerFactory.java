package com.lzt.operate.code.generator.custommessagequeue.customconfig;

/**
 * @author luzhitao
 */
public class CustomConfigProducerFactory {

	private final CustomConfigProducer producer;

	private CustomConfigProducerFactory() {
		producer = new CustomConfigProducer();
	}

	public static CustomConfigProducerFactory getInstance() {
		return CustomConfigProducerFactory.InstanceHolder.instance;
	}

	public CustomConfigProducer getProducer() {
		return producer;
	}

	static class InstanceHolder {
		private static final CustomConfigProducerFactory instance = new CustomConfigProducerFactory();
	}

}
