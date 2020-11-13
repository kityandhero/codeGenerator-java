package com.lzt.operate.code.generator.custommessagequeue.accessway;

import com.lzt.operate.code.generator.entities.AccessWay;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseProducerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class AccessWayProducer extends BaseProducerAdapter<AccessWay, ConcurrentLinkedDeque<AccessWay>> {

	protected AccessWayProducer() {
		super(SingletonAccessWayQueue.getInstance().getQueue());
	}

}
