package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.assists.AccountAssist;
import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.dao.service.AccountRoleService;
import com.lzt.operate.codetools.dao.service.AccountService;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.dao.service.impl.AccountRoleServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.AccountServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleCodeToolsServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.codetools.entities.Account;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/operator")
@Api(tags = {"操作者信息"})
public class OperatorController extends BaseOperateAuthController {

	private AccountService operatorService;

	private final AccountRoleService accountRoleService;

	private final RoleUniversalService roleUniversalService;

	private final RoleCodeToolsServiceImpl roleCodeToolsService;

	@Autowired
	public OperatorController(
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			AccountServiceImpl operatorService,
			AccountRoleServiceImpl operatorRoleService,
			RoleUniversalServiceImpl roleUniversalService,
			RoleCodeToolsServiceImpl roleCodeToolsService) {
		super(customJsonWebTokenConfig);

		this.operatorService = operatorService;
		this.accountRoleService = operatorRoleService;
		this.roleUniversalService = roleUniversalService;
		this.roleCodeToolsService = roleCodeToolsService;
	}

	private AccountAssist getAccountAssist() {
		return new AccountAssist(
				this.getCustomJsonWebTokenConfig(),
				this.operatorService,
				this.accountRoleService,
				this.roleUniversalService,
				this.roleCodeToolsService);
	}

	@ApiOperation(value = "当前操作者信息", notes = "当前操作者信息", httpMethod = "POST")
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/getCurrent", produces = "application/json")
	public BaseResultData getCurrent() {
		AccountAssist assist = getAccountAssist();

		Optional<Account> result = assist.getCurrent();

		if (result.isPresent()) {
			return this.singleData(result.get());
		}

		return this.fail(ReturnDataCode.NoData);
	}

	@ApiOperation(value = "当前操作者基本信息", notes = "当前操作者基本信息", httpMethod = "POST")
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/getCurrentBasicInfo", produces = "application/json")
	public BaseResultData getCurrentBasicInfo() {
		AccountAssist assist = getAccountAssist();

		Optional<Account> result = assist.getCurrent();

		if (result.isPresent()) {
			return this.singleData(result.get());
		}

		return this.fail(ReturnDataCode.NoData);
	}
}
