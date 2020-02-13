package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.entities.RoleUniversal;

/**
 * @author luzhitao
 */
public interface RoleUniversalService extends BaseService<RoleUniversal> {

	/**
	 * 指定渠道制度存在超级管理员角色
	 *
	 * @param channel channel
	 * @return boolean
	 */
	Boolean existSuper(int channel);

}
