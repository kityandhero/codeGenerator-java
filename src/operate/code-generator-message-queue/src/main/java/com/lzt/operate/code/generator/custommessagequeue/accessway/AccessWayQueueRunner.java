package com.lzt.operate.code.generator.custommessagequeue.accessway;

import com.lzt.operate.code.generator.common.enums.AccessWayStatus;
import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.dao.service.AccessWayService;
import com.lzt.operate.code.generator.entities.AccessWay;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.OperatorCollection;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class AccessWayQueueRunner implements Runnable {

	private AccessWayService accessWayService;

	private AccessWayConsumer consumer;

	public AccessWayQueueRunner(AccessWayService accessWayService, AccessWayConsumer consumer) {
		this.accessWayService = accessWayService;

		this.consumer = consumer;
	}

	@SneakyThrows
	@Override
	public void run() {
		while (true) {
			try {
				Optional<AccessWay> optional = this.consumer.pull();

				if (optional.isPresent()) {
					AccessWay accessWayFromQueue = optional.get();

					String tag = accessWayFromQueue.getTag();

					if (!StringAssist.isNullOrEmpty(tag)) {
						String name = accessWayFromQueue.getName();
						String description = accessWayFromQueue.getDescription();
						String relativePath = accessWayFromQueue.getRelativePath();
						String expand = accessWayFromQueue.getExpand();

						Optional<AccessWay> optionalSearch = this.accessWayService.findByTag(tag);

						if (optionalSearch.isPresent()) {
							AccessWay accessWay = optional.get();

							String namePrev = accessWay.getName();
							String descriptionPrev = accessWay.getDescription();
							String relativePathPrev = accessWay.getRelativePath();
							String expandPrev = accessWay.getExpand();

							if (!name.equals(namePrev) || !description.equals(descriptionPrev) || !relativePath.equals(relativePathPrev) || !expand
									.equals(expandPrev)) {

								accessWay.setName(name);
								accessWay.setDescription(description);
								accessWay.setRelativePath(relativePath);
								accessWay.setExpand(expand);
								accessWay.setChannel(Channel.CodeGenerator);
								accessWay.setStatus(AccessWayStatus.Enabled, AccessWayStatus::getFlag, AccessWayStatus::getName);
								accessWay.setUpdateOperatorId(OperatorCollection.System.getId());

								this.accessWayService.save(accessWay);
							}
						} else {
							AccessWay accessWay = new AccessWay();

							accessWay.setName(name);
							accessWay.setDescription(description);
							accessWay.setTag(tag);
							accessWay.setRelativePath(relativePath);
							accessWay.setExpand(expand);
							accessWay.setChannel(Channel.CodeGenerator);
							accessWay.setStatus(AccessWayStatus.Enabled, AccessWayStatus::getFlag, AccessWayStatus::getName);
							accessWay.setCreateOperatorId(OperatorCollection.System.getId());
							accessWay.setUpdateOperatorId(OperatorCollection.System.getId());

							this.accessWayService.save(accessWay);
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
