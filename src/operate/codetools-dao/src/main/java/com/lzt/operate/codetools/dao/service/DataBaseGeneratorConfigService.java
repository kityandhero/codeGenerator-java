package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.dao.repositories.DataBaseGeneratorConfigRepository;
import com.lzt.operate.codetools.dao.service.bases.BaseService;
import com.lzt.operate.codetools.entities.DataBaseGeneratorConfig;
import com.lzt.operate.utility.assists.ReflectAssist;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

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
public interface DataBaseGeneratorConfigService extends BaseService<DataBaseGeneratorConfigRepository, DataBaseGeneratorConfig> {

	/**
	 * findByConnectionConfigId
	 *
	 * @param connectionConfigId connectionConfigId
	 * @return Optional<DataColumn>
	 */
	default Optional<DataBaseGeneratorConfig> findByConnectionConfigId(long connectionConfigId) {
		Specification<DataBaseGeneratorConfig> specification = new Specification<DataBaseGeneratorConfig>() {

			private static final long serialVersionUID = -3508744018398462569L;

			@Override
			public Predicate toPredicate(@NonNull Root<DataBaseGeneratorConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataBaseGeneratorConfig::getConnectionConfigId)), connectionConfigId));

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		return getRepository().findOne(specification);
	}

}
