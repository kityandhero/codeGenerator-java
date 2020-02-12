package com.lzt.operate.codetools.dao.service.impl;

import com.lzt.operate.codetools.dao.repositories.ConnectionConfigRepository;
import com.lzt.operate.codetools.dao.service.ConnectionConfigService;
import com.lzt.operate.codetools.entities.ConnectionConfig;
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
public class ConnectionConfigServiceImpl implements ConnectionConfigService {

	private final ConnectionConfigRepository repository;

	@Autowired
	public ConnectionConfigServiceImpl(ConnectionConfigRepository repository) {
		this.repository = repository;
	}

	@Override
	public Page<ConnectionConfig> page(Example<ConnectionConfig> filter, Pageable pageable) {
		return this.repository.findAll(filter, pageable);
	}

	@Override
	public Optional<ConnectionConfig> get(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public Optional<ConnectionConfig> findOne(Example<ConnectionConfig> filter) {
		return this.repository.findOne(filter);
	}

	@Override
	public ConnectionConfig save(ConnectionConfig entity) {
		beforeSave(entity);

		return this.repository.save(entity);
	}

	@Override
	public void fixDataBeforeSave(ConnectionConfig entity) {
	}

}
