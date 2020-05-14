package com.lzt.operate.code.generator.custommessagequeue.accessway;

/**
 * @author luzhitao
 */
public class AccessWayProducerFactory {

	private final AccessWayProducer producer;

	private AccessWayProducerFactory() {
		producer = new AccessWayProducer();
	}

	public static AccessWayProducerFactory getInstance() {
		return AccessWayProducerFactory.InstanceHolder.instance;
	}

	public AccessWayProducer getProducer() {
		return producer;
	}

	static class InstanceHolder {
		private static final AccessWayProducerFactory instance = new AccessWayProducerFactory();
	}

}
