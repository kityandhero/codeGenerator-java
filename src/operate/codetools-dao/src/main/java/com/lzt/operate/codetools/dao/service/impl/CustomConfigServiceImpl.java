package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.dao.repositories.CustomConfigRepository;
import com.lzt.operate.codetools.dao.service.CustomConfigService;
import com.lzt.operate.codetools.entities.CustomConfig;
import com.lzt.operate.utility.assists.ReflectAssist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
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
public class CustomConfigServiceImpl implements CustomConfigService {

	private final UidGenService uidGenService;

	private final CustomConfigRepository repository;

	@Autowired
	public CustomConfigServiceImpl(UidGenService uidGenService, CustomConfigRepository repository) {
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
	public CustomConfigRepository getRepository() {
		if (Optional.ofNullable(this.repository).isPresent()) {
			return this.repository;
		}

		throw new RuntimeException("Repository不存在");
	}

	@Override
	public Optional<CustomConfig> findByUuid(String uuid) {
		Specification<CustomConfig> spec = new Specification<CustomConfig>() {
			private static final long serialVersionUID = -2260955832137429106L;

			@Override
			public Predicate toPredicate(Root<CustomConfig> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Path<Object> path = root.get(ReflectAssist.getFieldName(CustomConfig::getUuid));

				return criteriaBuilder.equal(path, uuid);
			}
		};

		return repository.findOne(spec);
	}

}
