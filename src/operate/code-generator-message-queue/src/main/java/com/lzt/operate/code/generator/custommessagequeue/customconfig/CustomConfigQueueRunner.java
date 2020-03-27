package com.lzt.operate.code.generator.custommessagequeue.customconfig;

import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.CustomConfigCollection;
import com.lzt.operate.code.generator.dao.service.CustomConfigService;
import com.lzt.operate.code.generator.entities.CustomConfig;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.assists.StringAssist;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class CustomConfigQueueRunner implements Runnable {

	private CustomConfigService customConfigService;

	private CustomConfigConsumer consumer;

	public CustomConfigQueueRunner(CustomConfigService generalLogService, CustomConfigConsumer consumer) {
		this.customConfigService = generalLogService;

		this.consumer = consumer;
	}

	@SneakyThrows
	@Override
	public void run() {
		while (true) {
			try {
				Optional<CustomConfig> optional = this.consumer.pull();

				if (optional.isPresent()) {
					CustomConfig customConfigFromQuery = optional.get();

					Optional<CustomConfigCollection> optionalCustomConfigCollection = EnumAssist.getTargetValue(Arrays
							.asList(CustomConfigCollection.values()), CustomConfigCollection::getUuid, customConfigFromQuery
							.getUuid());

					if (optionalCustomConfigCollection.isPresent()) {
						CustomConfigCollection customConfigCollection = optionalCustomConfigCollection.get();

						if (!StringAssist.isNullOrEmpty(customConfigFromQuery.getUuid())) {
							if (customConfigCollection.getAvailableValue().contains(customConfigFromQuery.getValue())) {
								Optional<CustomConfig> optionalCustomConfig = this.customConfigService.findByUuid(customConfigFromQuery
										.getUuid());

								CustomConfig customConfig;

								if (optionalCustomConfig.isPresent()) {
									customConfig = optionalCustomConfig.get();
								} else {
									customConfig = new CustomConfig();

									customConfig.setUuid(customConfigFromQuery.getUuid());
								}

								customConfig.setName(customConfigCollection.getName());
								customConfig.setValue(customConfigFromQuery.getValue());
								customConfig.setDescription(customConfigCollection.getDescription());

								Optional<Channel> optionalChannel = EnumAssist.getTargetValue(Arrays
										.asList(Channel.values()), Channel::getFlag, customConfigFromQuery
										.getChannel());

								optionalChannel.ifPresent(customConfig::setChannel);

								this.customConfigService.save(customConfig);
							}
						}
					}
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
