package com.lzt.operate.code.generator.dao.service;

import com.lzt.operate.code.generator.dao.repositories.RoleUniversalRepository;
import com.lzt.operate.code.generator.dao.service.bases.BaseRoleService;
import com.lzt.operate.code.generator.entities.RoleUniversal;

import java.util.Optional;

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

	/**
	 * findSuper
	 *
	 * @param channel channel
	 * @return RoleUniversal
	 */
	Optional<RoleUniversal> findSuper(int channel);
}
