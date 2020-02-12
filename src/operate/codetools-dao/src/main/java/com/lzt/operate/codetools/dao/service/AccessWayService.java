package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.entities.AccessWay;

import java.util.Optional;

/**
 * @author luzhitao
 */
public interface AccessWayService extends BaseService<AccessWay> {

	/**
	 * 通过登录名获取信息
	 *
	 * @param tag 通道标识
	 * @return Optional<AccessWay>
	 */
	Optional<AccessWay> findByTag(String tag);

}
