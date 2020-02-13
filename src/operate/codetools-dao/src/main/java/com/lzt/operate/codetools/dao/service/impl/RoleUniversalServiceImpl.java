package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.common.enums.WhetherSuper;
import com.lzt.operate.codetools.dao.repositories.PermissionRepository;
import com.lzt.operate.codetools.dao.repositories.RoleUniversalRepository;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.entities.RoleUniversal;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.extensions.StringEx;
import com.lzt.operate.utility.pojo.Competence;
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
	private final PermissionRepository permissionRepository;
	private final UidGenService uidGenService;

	@Autowired
	public RoleUniversalServiceImpl(RoleUniversalRepository repository, PermissionRepository permissionRepository, UidGenService uidGenService) {
		this.repository = repository;
		this.permissionRepository = permissionRepository;
		this.uidGenService = uidGenService;
	}

	@Override
	public long generateId() {
		return uidGenService.getUid();
	}

	@Override
	public Page<RoleUniversal> page(Example<RoleUniversal> filter, Pageable pageable) {
		return repository.findAll(filter, pageable);
	}

	@Override
	public Optional<RoleUniversal> findOne(Example<RoleUniversal> filter) {
		return repository.findOne(filter);
	}

	@Override
	public Optional<RoleUniversal> get(Long id) {
		return repository.findById(id);
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

	@Override
	public RoleUniversal save(RoleUniversal entity) {
		beforeSave(entity);

		return repository.save(entity);
	}

	/**
	 * 保存前预处理
	 *
	 * @param entity 数据实体
	 */
	@Override
	public void fixDataBeforeSave(RoleUniversal entity) {
		var competence = entity.getCompetence();

		if (StringAssist.isNullOrEmpty(competence)) {
			entity.setCompetence("");
			entity.setModuleCount(0);

		} else {
			var moduleCount = getCompetenceEntityCollection(entity).size();

			entity.setModuleCount(moduleCount);
		}
	}

	private List<Competence> getCompetenceEntityCollection(RoleUniversal entity) {
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
						c.setGuidTag((new StringEx((String) cv[0])));
						c.setExpansionSet((new StringEx((String) cv[1])));
					} else if (cv.length == 1) {
						c.setGuidTag((new StringEx((String) cv[0])));
					}
				} else {
					c.setGuidTag((new StringEx(item)));
				}

				result.add(c);
			});

			if (result.size() > 0) {

				var listPermission = permissionRepository.findAll((root, query, cb) -> {
					Predicate predicate = root.isNotNull();
					predicate = cb.and(predicate, cb.and(root.get("guidTag")
															 .as(String.class)
															 .in(result.stream().map(Competence::getGuidTag))));
					return predicate;
				});

				for (var c : result) {
					for (var aw : listPermission) {
						if (aw.getGuidTag().equals(c.getGuidTag().toString())) {
							c.setName(new StringEx(aw.getName()));
							c.setExplain(new StringEx(aw.getExpand()));
							c.setRelativePath(new StringEx(aw.getRelativePath()));
						}
					}
				}
			}
		}

		return result;
	}

}
