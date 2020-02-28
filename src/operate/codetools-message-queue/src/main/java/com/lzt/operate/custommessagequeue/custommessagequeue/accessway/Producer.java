package com.lzt.operate.custommessagequeue.custommessagequeue.accessway;

import com.lzt.operate.codetools.interfaces.IAccessWay;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseProducerAdapter;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class Producer extends BaseProducerAdapter<IAccessWay, ConcurrentLinkedDeque<IAccessWay>> {

	protected Producer() {
		super(SingletonQueue.getInstance().getQueue());
	}

}
