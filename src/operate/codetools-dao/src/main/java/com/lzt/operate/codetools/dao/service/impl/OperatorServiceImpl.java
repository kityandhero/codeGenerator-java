package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.common.enums.OperatorStatus;
import com.lzt.operate.codetools.dao.repositories.OperatorRepository;
import com.lzt.operate.codetools.dao.service.OperatorService;
import com.lzt.operate.codetools.entities.Operator;
import com.lzt.operate.utility.assists.ReflectAssist;
import lombok.var;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
public class OperatorServiceImpl implements OperatorService {

	private final UidGenService uidGenService;

	private final OperatorRepository repository;

	public OperatorServiceImpl(UidGenService uidGenService, OperatorRepository repository) {
		this.uidGenService = uidGenService;
		this.repository = repository;
	}

	@Override
	public long generateId() {
		return uidGenService.getUid();
	}

	@Override
	public Page<Operator> page(Example<Operator> filter, Pageable pageable) {
		return repository.findAll(filter, pageable);
	}

	@Override
	public Optional<Operator> get(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Operator> findOne(Example<Operator> filter) {
		return repository.findOne(filter);
	}

	@Override
	public Optional<Operator> findByUserName(String userName) {
		Specification<Operator> spec = new Specification<Operator>() {
			private static final long serialVersionUID = -2260955832137429106L;

			@Override
			public Predicate toPredicate(Root<Operator> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Path<Object> path = root.get(ReflectAssist.getFieldName(Operator::getUserName));

				return criteriaBuilder.equal(path, userName);
			}
		};

		return repository.findOne(spec);
	}

	@Override
	public boolean existAny(int channel) {
		Specification<Operator> spec = new Specification<Operator>() {

			private static final long serialVersionUID = 7150353365384278625L;

			@Override
			public Predicate toPredicate(Root<Operator> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(Operator::getChannel)), channel));

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		var optional = repository.findOne(spec);

		return optional.isPresent();
	}

	@Override
	public boolean existEffective(long id) {
		var optional = repository.findById(id);

		if (optional.isPresent()) {
			Operator operator = optional.get();

			Integer status = operator.getStatus();

			return status.equals(OperatorStatus.Enabled.getValue());
		}

		return false;
	}

	@Override
	public Operator save(Operator entity) {
		beforeSave(entity);

		return repository.save(entity);
	}

	@Override
	public void fixDataBeforeSave(Operator entity) {
	}

}
