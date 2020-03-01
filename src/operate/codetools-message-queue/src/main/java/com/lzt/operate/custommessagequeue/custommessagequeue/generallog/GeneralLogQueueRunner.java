package com.lzt.operate.custommessagequeue.custommessagequeue.generallog;

import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.enums.GeneralLogStatus;
import com.lzt.operate.codetools.dao.service.GeneralLogService;
import com.lzt.operate.codetools.entities.GeneralLog;
import com.lzt.operate.utility.enums.OperatorCollection;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class GeneralLogQueueRunner implements Runnable {

	private GeneralLogService generalLogService;

	private GeneralLogConsumer consumer;

	public GeneralLogQueueRunner(GeneralLogService generalLogService, GeneralLogConsumer consumer) {
		this.generalLogService = generalLogService;

		this.consumer = consumer;
	}

	@SneakyThrows
	@Override
	public void run() {
		while (true) {
			try {
				Optional<GeneralLog> optional = this.consumer.pull();

				if (optional.isPresent()) {
					GeneralLog generalLog = optional.get();

					generalLog.setChannel(Channel.CodeTools);
					generalLog.setStatus(GeneralLogStatus.Normal, GeneralLogStatus::getValue, GeneralLogStatus::getName);
					generalLog.setCreateOperatorId(OperatorCollection.System.getId());
					generalLog.setCreateTime(LocalDateTime.now());
					generalLog.setUpdateOperatorId(OperatorCollection.System.getId());
					generalLog.setUpdateTime(LocalDateTime.now());

					this.generalLogService.save(generalLog);

					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.fillInStackTrace();

				break;
			}
		}
	}

}
