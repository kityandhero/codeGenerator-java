package com.lzt.operate.custommessagequeue.custommessagequeue.accessway;

import com.lzt.operate.codetools.entities.AccessWay;
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
