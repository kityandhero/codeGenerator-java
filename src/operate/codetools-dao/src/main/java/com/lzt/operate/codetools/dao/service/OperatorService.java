package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.entities.Operator;

import java.util.Optional;

/**
 * @author luzhitao
 */
public interface OperatorService extends BaseService<Operator> {

	/**
	 * 通过登录名获取信息
	 *
	 * @param userName 登录名
	 * @return Optional<Operator>
	 */
	Optional<Operator> findByUserName(String userName);

	/**
	 * 是否存在任意用户
	 *
	 * @param channel channel
	 * @return boolean
	 */
	boolean existAny(int channel);

	/**
	 * 是否有效
	 *
	 * @param id id
	 * @return boolean
	 */
	boolean existEffective(long id);

}
