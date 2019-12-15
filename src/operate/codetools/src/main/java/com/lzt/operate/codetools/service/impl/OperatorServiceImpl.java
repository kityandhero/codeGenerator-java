package com.lzt.operate.codetools.service.impl;

import com.lzt.operate.codetools.entity.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.codetools.service.OperatorService;
import com.lzt.operate.utility.ReflectAssist;
import com.lzt.operate.utility.StringAssist;
import lombok.var;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;
import java.util.UUID;

/**
 * @author lzt
 */
@Service
public class OperatorServiceImpl implements OperatorService {

	private final OperatorRepository repository;

	@Autowired
	public OperatorServiceImpl(OperatorRepository repository) {
		this.repository = repository;
	}

	@Override
	public Page<Operator> page(Example<Operator> filter, Pageable pageable) {
		return repository.findAll(filter, pageable);
	}

	@Override
	public Optional<Operator> get(String id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Operator> findOne(Example<Operator> filter) {
		return repository.findOne(filter);
	}

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
	public Operator save(Operator entity) {
		beforeSave(entity);

		return repository.save(entity);
	}

	@Override
	public void fixDataBeforeSave(Operator entity) {
		var salt = entity.getSlat();

		salt = StringAssist.isNullOrEmpty(salt) ? UUID.randomUUID()
													  .toString()
													  .replaceAll("-", "")
													  .toLowerCase() : salt;

		entity.setSlat(salt);
	}
}
