package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.dao.repositories.RoleUniversalRepository;
import com.lzt.operate.codetools.dao.service.bases.BaseRoleService;
import com.lzt.operate.codetools.entities.RoleUniversal;

/**
 * @author luzhitao
 */
public interface RoleUniversalService extends BaseRoleService<RoleUniversalRepository, RoleUniversal> {

	/**
	 * existSuper
	 *
	 * @param channel channel
	 * @return Boolean
	 */
	Boolean existSuper(int channel);

}
