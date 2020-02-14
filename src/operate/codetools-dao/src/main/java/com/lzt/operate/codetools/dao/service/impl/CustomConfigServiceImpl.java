package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.dao.repositories.CustomConfigRepository;
import com.lzt.operate.codetools.dao.service.CustomConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luzhitao
 */
@Service
public class CustomConfigServiceImpl implements CustomConfigService {

	private final UidGenService uidGenService;

	private final CustomConfigRepository repository;

	@Autowired
	public CustomConfigServiceImpl(UidGenService uidGenService, CustomConfigRepository repository) {
		this.uidGenService = uidGenService;
		this.repository = repository;
	}

	@Override
	public UidGenService getUidGenService() {
		return this.uidGenService;
	}

	@Override
	public CustomConfigRepository getRepository() {
		return this.repository;
	}

}
