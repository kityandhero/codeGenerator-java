package com.lzt.operate.code.generator.dao.service;

import com.lzt.operate.code.generator.dao.repositories.AccessWayRepository;
import com.lzt.operate.code.generator.dao.service.bases.BaseService;
import com.lzt.operate.code.generator.entities.AccessWay;

import java.util.Optional;

/**
 * @author luzhitao
 */
public interface AccessWayService extends BaseService<AccessWayRepository, AccessWay> {

	/**
	 * 通过登录名获取信息
	 *
	 * @param tag 通道标识
	 * @return Optional<AccessWay>
	 */
	Optional<AccessWay> findByTag(String tag);

}
