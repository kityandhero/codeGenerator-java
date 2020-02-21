package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.dao.repositories.AccountRoleRepository;
import com.lzt.operate.codetools.dao.service.bases.BaseService;
import com.lzt.operate.codetools.entities.AccountRole;

import java.util.Optional;

/**
 * @author luzhitao
 */
public interface AccountRoleService extends BaseService<AccountRoleRepository, AccountRole> {

	/**
	 * 通过accountId获取信息
	 *
	 * @param accountId accountId
	 * @return Optional<AccountRole>
	 */
	Optional<AccountRole> findByAccountId(long accountId);

}
