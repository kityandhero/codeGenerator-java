package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.dao.repositories.AccessWayRepository;
import com.lzt.operate.codetools.dao.repositories.RoleCodeToolsRepository;
import com.lzt.operate.codetools.dao.service.RoleCodeToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luzhitao
 */
@Service
public class RoleCodeToolsServiceImpl implements RoleCodeToolsService {

	private final RoleCodeToolsRepository repository;
	private final AccessWayRepository accessWayRepository;
	private final UidGenService uidGenService;

	@Autowired
	public RoleCodeToolsServiceImpl(RoleCodeToolsRepository repository, AccessWayRepository accessWayRepository, UidGenService uidGenService) {
		this.repository = repository;
		this.accessWayRepository = accessWayRepository;
		this.uidGenService = uidGenService;
	}

	@Override
	public UidGenService getUidGenService() {
		return this.uidGenService;
	}

	@Override
	public RoleCodeToolsRepository getRepository() {
		return this.repository;
	}

	@Override
	public AccessWayRepository getAccessWayRepository() {
		return this.accessWayRepository;
	}

}
