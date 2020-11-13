package com.lzt.operate.code.generator.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.code.generator.dao.repositories.HelpRepository;
import com.lzt.operate.code.generator.dao.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author luzhitao
 */
@Service
public class HelpServiceImpl implements HelpService {

	private final UidGenService uidGenService;

	private final HelpRepository repository;

	@Autowired
	public HelpServiceImpl(UidGenService uidGenService, HelpRepository repository) {
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
	public HelpRepository getRepository() {
		if (Optional.ofNullable(this.repository).isPresent()) {
			return this.repository;
		}

		throw new RuntimeException("Repository不存在");
	}

}
