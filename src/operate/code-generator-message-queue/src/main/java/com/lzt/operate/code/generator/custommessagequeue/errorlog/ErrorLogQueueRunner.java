package com.lzt.operate.code.generator.custommessagequeue.errorlog;

import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.ErrorLogStatus;
import com.lzt.operate.code.generator.common.jackson.ObjectMapperAssist;
import com.lzt.operate.code.generator.dao.service.ErrorLogService;
import com.lzt.operate.code.generator.entities.ErrorLog;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.enums.OperatorCollection;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author luzhitao
 */
@Slf4j
public class ErrorLogQueueRunner implements Runnable {

	private final ErrorLogService errorLogService;

	private final ErrorLogConsumer consumer;

	public ErrorLogQueueRunner(ErrorLogService accessWayService, ErrorLogConsumer consumer) {
		errorLogService = accessWayService;

		this.consumer = consumer;
	}

	@SneakyThrows
	@Override
	public void run() {
		while (true) {
			ErrorLog errorLog = null;

			try {
				Optional<ErrorLog> optional = consumer.pull();

				if (optional.isPresent()) {
					errorLog = optional.get();

					errorLog.setChannel(Channel.CodeGenerator);
					errorLog.setStatus(ErrorLogStatus.Normal, ErrorLogStatus::getFlag, ErrorLogStatus::getName);
					errorLog.setCreateOperatorId(OperatorCollection.System.getFlag());
					errorLog.setUpdateOperatorId(OperatorCollection.System.getFlag());

					errorLogService.save(errorLog);
				} else {
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.fillInStackTrace();

				if (Optional.ofNullable(errorLog).isPresent()) {
					log.error(ConvertAssist.serialize(ObjectMapperAssist.createObjectMapper(), errorLog));
				}

				break;
			}
		}
	}

}
