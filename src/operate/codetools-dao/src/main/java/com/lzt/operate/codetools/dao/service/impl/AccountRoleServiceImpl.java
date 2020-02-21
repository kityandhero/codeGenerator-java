package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.dao.repositories.AccountRoleRepository;
import com.lzt.operate.codetools.dao.service.AccountRoleService;
import com.lzt.operate.codetools.entities.AccountRole;
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
public class AccountRoleServiceImpl implements AccountRoleService {

	private final UidGenService uidGenService;

	private final AccountRoleRepository repository;

	public AccountRoleServiceImpl(UidGenService uidGenService, AccountRoleRepository repository) {
		this.uidGenService = uidGenService;
		this.repository = repository;
	}

	@Override
	public UidGenService getUidGenService() {
		return this.uidGenService;
	}

	@Override
	public AccountRoleRepository getRepository() {
		if (Optional.ofNullable(this.repository).isPresent()) {
			return this.repository;
		}

		throw new RuntimeException("Repository不存在");
	}

	@Override
	public Optional<AccountRole> findByAccountId(long operatorId) {
		AccountRoleRepository resultRepository = getRepository();

		if (operatorId <= 0) {
			return Optional.empty();
		}

		Specification<AccountRole> spec = new Specification<AccountRole>() {
			private static final long serialVersionUID = -2260955832137429106L;

			@Override
			public Predicate toPredicate(Root<AccountRole> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Path<Object> path = root.get(ReflectAssist.getFieldName(AccountRole::getAccountId));

				return criteriaBuilder.equal(path, operatorId);
			}
		};

		return resultRepository.findOne(spec);
	}

}
