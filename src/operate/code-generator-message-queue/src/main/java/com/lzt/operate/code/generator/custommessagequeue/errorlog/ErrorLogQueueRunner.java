package com.lzt.operate.code.generator.custommessagequeue.errorlog;

import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.ErrorLogStatus;
import com.lzt.operate.code.generator.dao.service.ErrorLogService;
import com.lzt.operate.code.generator.entities.ErrorLog;
import com.lzt.operate.utility.enums.OperatorCollection;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class ErrorLogQueueRunner implements Runnable {

	private ErrorLogService errorLogService;

	private ErrorLogConsumer consumer;

	public ErrorLogQueueRunner(ErrorLogService accessWayService, ErrorLogConsumer consumer) {
		this.errorLogService = accessWayService;

		this.consumer = consumer;
	}

	@SneakyThrows
	@Override
	public void run() {
		while (true) {
			try {
				Optional<ErrorLog> optional = this.consumer.pull();

				if (optional.isPresent()) {
					ErrorLog errorLog = optional.get();

					errorLog.setChannel(Channel.CodeGenerator);
					errorLog.setStatus(ErrorLogStatus.Normal, ErrorLogStatus::getFlag, ErrorLogStatus::getName);
					errorLog.setCreateOperatorId(OperatorCollection.System.getId());
					errorLog.setCreateTime(LocalDateTime.now());
					errorLog.setUpdateOperatorId(OperatorCollection.System.getId());
					errorLog.setUpdateTime(LocalDateTime.now());

					this.errorLogService.save(errorLog);
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
