package com.lzt.operate.codetools.dao.service.bases;

import com.lzt.operate.codetools.dao.jpa.JpaRepositoryEx;
import com.lzt.operate.codetools.dao.repositories.AccessWayRepository;
import com.lzt.operate.codetools.entities.AccessWay;
import com.lzt.operate.codetools.entities.bases.BaseRole;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.pojo.Competence;
import lombok.var;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public interface BaseRoleService<R extends JpaRepositoryEx<S, Long>, S extends BaseRole> extends BaseService<R, S> {

	/**
	 * getAccessWayRepository
	 *
	 * @return AccessWayRepository
	 */
	AccessWayRepository getAccessWayRepository();

	/**
	 * findByIdCollection
	 *
	 * @param idCollection idCollection
	 * @return List<S>
	 */
	default List<S> findByIdCollection(Collection<Long> idCollection) {
		if (!Optional.ofNullable(idCollection).isPresent()) {
			return new ArrayList<>();
		}

		Specification<S> spec = new Specification<S>() {

			private static final long serialVersionUID = 6315433399074638390L;

			@Override
			public Predicate toPredicate(Root<S> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get(ReflectAssist.getFieldName(S::getId)));

				for (Long v : idCollection) {
					in.value(v);
				}

				list.add(in);

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		return getRepository().findAll(spec);
	}

	/**
	 * 保存前预处理
	 *
	 * @param entity 数据实体
	 */
	@Override
	default void fixDataBeforeSave(S entity) {
		if (Optional.ofNullable(entity).isPresent()) {
			var competence = entity.getCompetence();

			if (StringAssist.isNullOrEmpty(competence)) {
				entity.setCompetence("");
				entity.setModuleCount(0);

			} else {
				var moduleCount = getCompetenceEntityCollection(entity).size();

				entity.setModuleCount(moduleCount);
			}
		}

	}

	/**
	 * getCompetenceEntityCollection
	 *
	 * @param entity entity
	 * @return List<Competence>
	 */
	default List<Competence> getCompetenceEntityCollection(S entity) {
		if (!Optional.ofNullable(entity).isPresent()) {
			return new ArrayList<>();
		}

		var competence = entity.getCompetence();

		List<Competence> result = new ArrayList<>();

		if (!StringAssist.isNullOrEmpty(competence)) {
			var list = StringAssist.split(competence, ',').stream().filter(o -> !StringAssist.isNullOrEmpty(o));

			final String splitTag = "|";

			list.forEach(item -> {
				var c = new Competence();

				if (item.contains(splitTag)) {
					var cv = StringAssist.split(item, '|')
										 .stream()
										 .filter(o -> !StringAssist.isNullOrEmpty(o))
										 .toArray();

					final int mustValue = 2;

					if (cv.length == mustValue) {
						c.setTag((String) cv[0]);
						c.setExpansionSet((String) cv[1]);
					} else if (cv.length == 1) {
						c.setTag((String) cv[0]);
					}
				} else {
					c.setTag(item);
				}

				result.add(c);
			});

			if (result.size() > 0) {

				var listPermission = getAccessWayRepository().findAll((root, query, cb) -> {
					Predicate predicate = root.isNotNull();

					predicate = cb.and(predicate, cb.and(root.get(ReflectAssist.getFieldName(AccessWay::getTag))
															 .as(String.class)
															 .in(result.stream().map(Competence::getTag).toArray())));

					return predicate;
				});

				for (var c : result) {
					for (var aw : listPermission) {
						if (aw.getTag().equals(c.getTag())) {
							c.setName(aw.getName());
							c.setExplain(aw.getExpand());
							c.setRelativePath(aw.getRelativePath());
						}
					}
				}
			}
		}

		return result;
	}

}