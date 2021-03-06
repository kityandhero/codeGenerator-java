package com.lzt.operate.code.generator.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.code.generator.common.enums.AccountStatus;
import com.lzt.operate.code.generator.dao.repositories.AccountRepository;
import com.lzt.operate.code.generator.dao.service.AccountService;
import com.lzt.operate.code.generator.entities.Account;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.general.ConstantCollection;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
@Service
public class AccountServiceImpl implements AccountService {

	private final UidGenService uidGenService;

	private final AccountRepository repository;

	public AccountServiceImpl(UidGenService uidGenService, AccountRepository repository) {
		this.uidGenService = uidGenService;
		this.repository = repository;
	}

	@Override
	public UidGenService getUidGenService() {
		if (Optional.ofNullable(this.uidGenService).isPresent()) {
			return this.uidGenService;
		}

		throw new RuntimeException("UidGenService不存在");
	}

	@Override
	public AccountRepository getRepository() {
		if (Optional.ofNullable(this.repository).isPresent()) {
			return this.repository;
		}

		throw new RuntimeException("Repository不存在");
	}

	@Override
	public Optional<Account> findByUserName(String userName) {
		Specification<Account> spec = new Specification<Account>() {
			private static final long serialVersionUID = -2260955832137429106L;

			@Override
			public Predicate toPredicate(Root<Account> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Path<Object> path = root.get(ReflectAssist.getFieldName(Account::getUserName));

				return criteriaBuilder.equal(path, userName);
			}
		};

		return repository.findOne(spec);
	}

	@Override
	public boolean existAny(int channel) {
		Specification<Account> spec = new Specification<Account>() {

			private static final long serialVersionUID = 7150353365384278625L;

			@Override
			public Predicate toPredicate(Root<Account> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(Account::getChannel)), channel));

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		long totalCount = repository.count(spec);

		return totalCount > ConstantCollection.ZERO_LONG;
	}

	@Override
	public boolean existEffective(long id) {
		Optional<Account> optional = repository.findById(id);

		if (optional.isPresent()) {
			Account operator = optional.get();

			Integer status = operator.getStatus();

			return status.equals(AccountStatus.Enabled.getFlag());
		}

		return false;
	}

}
