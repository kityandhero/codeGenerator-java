package com.lzt.operate.code.generator.custommessagequeue.generallog;

import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.GeneralLogStatus;
import com.lzt.operate.code.generator.dao.service.GeneralLogService;
import com.lzt.operate.code.generator.entities.GeneralLog;
import com.lzt.operate.utility.enums.OperatorCollection;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class GeneralLogQueueRunner implements Runnable {

	private final GeneralLogService generalLogService;

	private final GeneralLogConsumer consumer;

	public GeneralLogQueueRunner(GeneralLogService generalLogService, GeneralLogConsumer consumer) {
		this.generalLogService = generalLogService;

		this.consumer = consumer;
	}

	@SneakyThrows
	@Override
	public void run() {
		while (true) {
			try {
				Optional<GeneralLog> optional = consumer.pull();

				if (optional.isPresent()) {
					GeneralLog generalLog = optional.get();

					generalLog.setChannel(Channel.CodeGenerator);
					generalLog.setStatus(GeneralLogStatus.Normal, GeneralLogStatus::getFlag, GeneralLogStatus::getName);
					generalLog.setCreateOperatorId(OperatorCollection.System.getFlag());
					generalLog.setCreateTime(LocalDateTime.now());
					generalLog.setUpdateOperatorId(OperatorCollection.System.getFlag());

					generalLogService.save(generalLog);
				} else {
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.fillInStackTrace();

				break;
			}
		}
	}

}
