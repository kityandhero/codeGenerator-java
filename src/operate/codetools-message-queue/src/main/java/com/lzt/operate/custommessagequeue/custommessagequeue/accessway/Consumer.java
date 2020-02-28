package com.lzt.operate.custommessagequeue.custommessagequeue.accessway;

import com.lzt.operate.codetools.interfaces.IAccessWay;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseConsumerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class Consumer extends BaseConsumerAdapter<IAccessWay, ConcurrentLinkedDeque<IAccessWay>> {

	protected Consumer() {
		super(SingletonQueue.getInstance().getQueue());
	}

}
