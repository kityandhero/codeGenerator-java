package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.dao.repositories.DataTableGeneratorConfigRepository;
import com.lzt.operate.codetools.dao.service.DataTableGeneratorConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author luzhitao
 */
@Service
public class DataTableGeneratorConfigServiceImpl implements DataTableGeneratorConfigService {

	private final UidGenService uidGenService;

	private final DataTableGeneratorConfigRepository repository;

	@Autowired
	public DataTableGeneratorConfigServiceImpl(UidGenService uidGenService, DataTableGeneratorConfigRepository repository) {
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
	public DataTableGeneratorConfigRepository getRepository() {
		if (Optional.ofNullable(this.repository).isPresent()) {
			return this.repository;
		}

		throw new RuntimeException("Repository不存在");
	}
}
