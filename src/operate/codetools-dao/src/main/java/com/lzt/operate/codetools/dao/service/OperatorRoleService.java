package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.dao.repositories.OperatorRoleRepository;
import com.lzt.operate.codetools.dao.service.bases.BaseService;
import com.lzt.operate.codetools.entities.OperatorRole;

import java.util.Optional;

/**
 * @author luzhitao
 */
public interface OperatorRoleService extends BaseService<OperatorRoleRepository, OperatorRole> {

	/**
	 * 通过OperatorId获取信息
	 *
	 * @param operatorId operatorId
	 * @return Optional<Operator>
	 */
	Optional<OperatorRole> findByOperatorId(long operatorId);

}
