package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.OperateBaseController;
import com.lzt.operate.codetools.dao.repositories.AccountRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/account")
@Api(tags = {"操作者信息"})
public class AccountController extends OperateBaseController {

	private AccountRepository accountRepository;

	@Autowired
	public AccountController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

}
