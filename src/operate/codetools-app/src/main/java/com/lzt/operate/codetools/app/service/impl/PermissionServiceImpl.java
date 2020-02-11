package com.lzt.operate.codetools.app.service.impl;

import com.lzt.operate.codetools.app.entity.Permission;
import com.lzt.operate.codetools.app.repositories.PermissionRepository;
import com.lzt.operate.codetools.app.service.PermissionService;
import com.lzt.operate.utility.pojo.Competence;
import com.lzt.operate.utility.extensions.StringEx;
import com.lzt.operate.utility.assists.StringAssist;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author luzhitao
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	private final PermissionRepository repository;

	@Autowired
	public PermissionServiceImpl(PermissionRepository repository) {
		this.repository = repository;
	}

	@Override
	public Page<Permission> page(Example<Permission> filter, Pageable pageable) {
		return repository.findAll(filter, pageable);
	}

	@Override
	public Optional<Permission> get(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Permission> findOne(Example<Permission> filter) {
		return repository.findOne(filter);
	}

	@Override
	public Permission save(Permission data) {
		beforeSave(data);

		return repository.save(data);
	}

	public Competence buildCompetenceEntity(Permission data) {
		var expand = data.getExpand();
		var guidTag = data.getGuidTag();
		var name = data.getName();
		var relativePath = data.getRelativePath();

		var v = new StringEx("");

		if (!StringAssist.isNullOrEmpty(expand)) {
			String splitFlag = "|";

			if (expand.contains(splitFlag)) {

				StringAssist.split(expand, '|')
							.stream()
							.filter(o -> !StringAssist.isNullOrEmpty(o))
							.forEach(item -> v.append("0"));

			} else {
				v.append("0");
			}
		}

		var result = new Competence();

		result.setGuidTag(new StringEx(guidTag));
		result.setName(new StringEx(name));
		result.setRelativePath(new StringEx(relativePath));
		result.setExplain(new StringEx(expand));
		result.setExpansionSet(v);

		return result;
	}

	@Override
	public void fixDataBeforeSave(Permission entity) {

	}
}
