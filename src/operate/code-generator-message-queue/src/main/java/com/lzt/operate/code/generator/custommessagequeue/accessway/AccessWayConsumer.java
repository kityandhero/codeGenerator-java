package com.lzt.operate.code.generator.custommessagequeue.accessway;

import com.lzt.operate.code.generator.entities.AccessWay;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseConsumerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class AccessWayConsumer extends BaseConsumerAdapter<AccessWay, ConcurrentLinkedDeque<AccessWay>> {

	public AccessWayConsumer() {
		super(SingletonAccessWayQueue.getInstance().getQueue());
	}

}
