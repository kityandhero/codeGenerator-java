package com.lzt.operate.codetools.app.service.impl;

import com.lzt.operate.codetools.app.entity.CustomConfig;
import com.lzt.operate.codetools.app.repositories.CustomConfigRepository;
import com.lzt.operate.codetools.app.service.CustomConfigService;
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
public class CustomConfigServiceImpl implements CustomConfigService {
	private final CustomConfigRepository repository;

	@Autowired
	public CustomConfigServiceImpl(CustomConfigRepository repository) {
		this.repository = repository;
	}

	@Override
	public Page<CustomConfig> page(Example<CustomConfig> filter, Pageable pageable) {
		return this.repository.findAll(filter, pageable);
	}

	@Override
	public Optional<CustomConfig> get(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public Optional<CustomConfig> findOne(Example<CustomConfig> filter) {
		return this.repository.findOne(filter);
	}

	@Override
	public CustomConfig save(CustomConfig entity) {
		beforeSave(entity);

		return this.repository.save(entity);
	}

	@Override
	public void fixDataBeforeSave(CustomConfig entity) {
	}
}
