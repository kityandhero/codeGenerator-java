package com.lzt.operate.code.generator.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.code.generator.dao.repositories.DatabaseGeneratorConfigRepository;
import com.lzt.operate.code.generator.dao.service.DatabaseGeneratorConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author luzhitao
 */
@Service
public class DataBaseGeneratorConfigServiceImpl implements DatabaseGeneratorConfigService {

	private final UidGenService uidGenService;

	private final DatabaseGeneratorConfigRepository repository;

	@Autowired
	public DataBaseGeneratorConfigServiceImpl(UidGenService uidGenService, DatabaseGeneratorConfigRepository repository) {
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
	public DatabaseGeneratorConfigRepository getRepository() {
		if (Optional.ofNullable(this.repository).isPresent()) {
			return this.repository;
		}

		throw new RuntimeException("Repository不存在");
	}

}
