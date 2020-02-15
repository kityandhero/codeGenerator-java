package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.assists.OperatorAssist;
import com.lzt.operate.codetools.app.common.OperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.dao.service.OperatorRoleService;
import com.lzt.operate.codetools.dao.service.OperatorService;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.dao.service.impl.OperatorRoleServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.OperatorServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleCodeToolsServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.codetools.entities.Operator;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;
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
@RequestMapping("/business/currentOperator")
@Api(tags = {"操作者信息"})
public class CurrentOperator extends OperateAuthController {

	private final CustomJsonWebTokenConfig customJsonWebTokenConfig;

	private OperatorService operatorService;

	private final OperatorRoleService operatorRoleService;

	private final RoleUniversalService roleUniversalService;

	private final RoleCodeToolsServiceImpl roleCodeToolsService;

	@Autowired
	public CurrentOperator(
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			OperatorServiceImpl operatorService,
			OperatorRoleServiceImpl operatorRoleService,
			RoleUniversalServiceImpl roleUniversalService,
			RoleCodeToolsServiceImpl roleCodeToolsService) {
		this.customJsonWebTokenConfig = customJsonWebTokenConfig;
		this.operatorService = operatorService;
		this.operatorRoleService = operatorRoleService;
		this.roleUniversalService = roleUniversalService;
		this.roleCodeToolsService = roleCodeToolsService;
	}

	private OperatorAssist getOperatorAssist() {
		return new OperatorAssist(
				this.customJsonWebTokenConfig,
				this.operatorService,
				this.operatorRoleService,
				this.roleUniversalService,
				this.roleCodeToolsService);
	}

	@ApiOperation(value = "当前操作者信息", notes = "当前操作者信息", httpMethod = "POST")
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/getCurrent", produces = "application/json")
	public BaseResultData getCurrent() {
		OperatorAssist assist = getOperatorAssist();

		Optional<Operator> op = assist.getCurrent();

		if (op.isPresent()) {
			return this.singleData(op.get());
		} else {
			var error = ReturnDataCode.NoData;

			error.setMessage("没有数据");

			return this.fail(error);
		}
	}

	@ApiOperation(value = "当前操作者基本信息", notes = "当前操作者基本信息", httpMethod = "POST")
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/getCurrentBasicInfo", produces = "application/json")
	public BaseResultData getCurrentBasicInfo() {
		OperatorAssist assist = getOperatorAssist();

		Optional<Operator> op = assist.getCurrent();

		if (op.isPresent()) {
			return this.singleData(op.get());
		} else {
			var error = ReturnDataCode.NoData;

			error.setMessage("没有数据");

			return this.fail(error);
		}
	}
}
