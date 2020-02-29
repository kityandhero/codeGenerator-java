package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.common.enums.WhetherSuper;
import com.lzt.operate.codetools.dao.repositories.AccessWayRepository;
import com.lzt.operate.codetools.dao.repositories.RoleUniversalRepository;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.entities.RoleUniversal;
import com.lzt.operate.utility.assists.ReflectAssist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		if (Optional.ofNullable(this.uidGenService).isPresent()) {
			return this.uidGenService;
		}

		throw new RuntimeException("UidGenService不存在");
	}

	@Override
	public RoleUniversalRepository getRepository() {
		if (Optional.ofNullable(this.repository).isPresent()) {
			return this.repository;
		}

		throw new RuntimeException("Repository不存在");
	}

	@Override
	public AccessWayRepository getAccessWayRepository() {
		if (Optional.ofNullable(this.accessWayRepository).isPresent()) {
			return this.accessWayRepository;
		}

		throw new RuntimeException("AccessWayRepository不存在");
	}

	@Override
	public Boolean existSuper(int channel) {
		Optional<RoleUniversal> result = findSuper(channel);

		return result.isPresent();
	}

	@Override
	public Optional<RoleUniversal> findSuper(int channel) {
		RoleUniversalRepository resultRepository = getRepository();

		Specification<RoleUniversal> spec = new Specification<RoleUniversal>() {
			private static final long serialVersionUID = -2260955832137429106L;

			@Override
			public Predicate toPredicate(Root<RoleUniversal> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(RoleUniversal::getChannel)), channel));
				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(RoleUniversal::getWhetherSuper)), WhetherSuper.Yes
						.getValue()));

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		return resultRepository.findOne(spec);
	}

}
