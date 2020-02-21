package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.dao.repositories.AccountRepository;
import com.lzt.operate.codetools.dao.service.bases.BaseService;
import com.lzt.operate.codetools.entities.Account;

import java.util.Optional;

/**
 * @author luzhitao
 */
public interface AccountService extends BaseService<AccountRepository, Account> {

	/**
	 * 通过登录名获取信息
	 *
	 * @param userName 登录名
	 * @return Optional<Operator>
	 */
	Optional<Account> findByUserName(String userName);

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
