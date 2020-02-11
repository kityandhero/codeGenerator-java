package com.lzt.operate.codetools.service.impl;

import com.lzt.operate.codetools.entity.Role;
import com.lzt.operate.codetools.repository.PermissionRepository;
import com.lzt.operate.codetools.repository.RoleRepository;
import com.lzt.operate.codetools.service.RoleService;
import com.lzt.operate.entities.Competence;
import com.lzt.operate.extensions.StringEx;
import com.lzt.operate.utility.StringAssist;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository repository;
	private final PermissionRepository permissionRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository repository, PermissionRepository permissionRepository) {
		this.repository = repository;
		this.permissionRepository = permissionRepository;
	}

	@Override
	public Page<Role> page(Example<Role> filter, Pageable pageable) {
		return repository.findAll(filter, pageable);
	}

	@Override
	public Optional<Role> findOne(Example<Role> filter) {
		return repository.findOne(filter);
	}

	@Override
	public Optional<Role> get(Long id) {
		return repository.findById(id);
	}

	@Override
	public Role save(Role entity) {
		beforeSave(entity);

		return repository.save(entity);
	}

	/**
	 * 保存前预处理
	 *
	 * @param entity 数据实体
	 */
	@Override
	public void fixDataBeforeSave(Role entity) {
		var competence = entity.getCompetence();

		if (StringAssist.isNullOrEmpty(competence)) {
			entity.setCompetence("");
			entity.setModuleCount(0);

		} else {
			var moduleCount = getCompetenceEntityCollection(entity).size();

			entity.setModuleCount(moduleCount);
		}
	}

	private List<Competence> getCompetenceEntityCollection(Role entity) {
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
