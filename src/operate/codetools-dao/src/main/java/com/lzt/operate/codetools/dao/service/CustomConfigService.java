package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.dao.repositories.CustomConfigRepository;
import com.lzt.operate.codetools.dao.service.bases.BaseService;
import com.lzt.operate.codetools.entities.CustomConfig;

import java.util.Optional;

/**
 * @author luzhitao
 */
public interface CustomConfigService extends BaseService<CustomConfigRepository, CustomConfig> {

	/**
	 * 通过uuid取信息
	 *
	 * @param uuid uuid
	 * @return Optional<CustomConfig>
	 */
	Optional<CustomConfig> findByUuid(String uuid);

}
