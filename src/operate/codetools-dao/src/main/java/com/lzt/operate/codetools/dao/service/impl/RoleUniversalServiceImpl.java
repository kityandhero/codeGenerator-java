package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.common.enums.WhetherSuper;
import com.lzt.operate.codetools.dao.repositories.AccessWayRepository;
import com.lzt.operate.codetools.dao.repositories.RoleUniversalRepository;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.entities.RoleUniversal;
import com.lzt.operate.utility.assists.ReflectAssist;
import lombok.var;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhitao
 */
@Service
public class RoleUniversalServiceImpl implements RoleUniversalService {

	private final RoleUniversalRepository repository;
	private final AccessWayRepository accessWayRepository;
	private final UidGenService uidGenService;

	@Autowired
	public RoleUniversalServiceImpl(RoleUniversalRepository repository, AccessWayRepository accessWayRepository, UidGenService uidGenService) {
		this.repository = repository;
		this.accessWayRepository = accessWayRepository;
		this.uidGenService = uidGenService;
	}

	@Override
	public UidGenService getUidGenService() {
		return this.uidGenService;
	}

	@Override
	public RoleUniversalRepository getRepository() {
		return this.repository;
	}

	@Override
	public AccessWayRepository getAccessWayRepository() {
		return this.accessWayRepository;
	}

	@Override
	public Boolean existSuper(int channel) {
		Specification<RoleUniversal> spec = new Specification<RoleUniversal>() {
			private static final long serialVersionUID = -2260955832137429106L;

			@Override
			public Predicate toPredicate(Root<RoleUniversal> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(RoleUniversal::getChannel)), channel));
				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(RoleUniversal::getWhetherSuper)), WhetherSuper.Yes
						.getValue()));

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		var optional = repository.findOne(spec);

		return optional.isPresent();
	}

}
