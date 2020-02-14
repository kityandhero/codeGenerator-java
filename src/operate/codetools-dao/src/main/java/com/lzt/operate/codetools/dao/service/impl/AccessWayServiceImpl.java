package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.dao.repositories.AccessWayRepository;
import com.lzt.operate.codetools.dao.service.AccessWayService;
import com.lzt.operate.codetools.entities.AccessWay;
import com.lzt.operate.utility.assists.ReflectAssist;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccessWayServiceImpl implements AccessWayService {

	private final UidGenService uidGenService;

	private final AccessWayRepository repository;

	@Autowired
	public AccessWayServiceImpl(UidGenService uidGenService, AccessWayRepository repository) {
		this.uidGenService = uidGenService;
		this.repository = repository;
	}

	@Override
	public UidGenService getUidGenService() {
		return this.uidGenService;
	}

	@Override
	public AccessWayRepository getRepository() {
		return this.repository;
	}

	@Override
	public Optional<AccessWay> findByTag(String tag) {
		Specification<AccessWay> spec = new Specification<AccessWay>() {
			private static final long serialVersionUID = 4482864256737131456L;

			@Override
			public Predicate toPredicate(Root<AccessWay> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Path<Object> path = root.get(ReflectAssist.getFieldName(AccessWay::getTag));

				return criteriaBuilder.equal(path, tag);
			}
		};

		return repository.findOne(spec);
	}
}
