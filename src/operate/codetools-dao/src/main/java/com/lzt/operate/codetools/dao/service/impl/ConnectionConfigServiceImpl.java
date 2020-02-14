package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.dao.repositories.ConnectionConfigRepository;
import com.lzt.operate.codetools.dao.service.ConnectionConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luzhitao
 */
@Service
public class ConnectionConfigServiceImpl implements ConnectionConfigService {

	private final UidGenService uidGenService;

	private final ConnectionConfigRepository repository;

	@Autowired
	public ConnectionConfigServiceImpl(UidGenService uidGenService, ConnectionConfigRepository repository) {
		this.uidGenService = uidGenService;
		this.repository = repository;
	}

	@Override
	public UidGenService getUidGenService() {
		return this.uidGenService;
	}

	@Override
	public ConnectionConfigRepository getRepository() {
		return this.repository;
	}

}
