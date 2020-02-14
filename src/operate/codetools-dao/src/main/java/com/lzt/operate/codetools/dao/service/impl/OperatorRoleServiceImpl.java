package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.dao.repositories.OperatorRoleRepository;
import com.lzt.operate.codetools.dao.service.OperatorRoleService;
import com.lzt.operate.codetools.entities.OperatorRole;
import com.lzt.operate.utility.assists.ReflectAssist;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

/**
 * @author luzhitao
 */
@Service
public class OperatorRoleServiceImpl implements OperatorRoleService {

	private final UidGenService uidGenService;

	private final OperatorRoleRepository repository;

	public OperatorRoleServiceImpl(UidGenService uidGenService, OperatorRoleRepository repository) {
		this.uidGenService = uidGenService;
		this.repository = repository;
	}

	@Override
	public UidGenService getUidGenService() {
		return this.uidGenService;
	}

	@Override
	public OperatorRoleRepository getRepository() {
		return this.repository;
	}

	@Override
	public Optional<OperatorRole> findByOperatorId(long operatorId) {
		if (operatorId <= 0) {
			return Optional.empty();
		}

		Specification<OperatorRole> spec = new Specification<OperatorRole>() {
			private static final long serialVersionUID = -2260955832137429106L;

			@Override
			public Predicate toPredicate(Root<OperatorRole> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Path<Object> path = root.get(ReflectAssist.getFieldName(OperatorRole::getOperatorId));

				return criteriaBuilder.equal(path, operatorId);
			}
		};

		return repository.findOne(spec);
	}

}
