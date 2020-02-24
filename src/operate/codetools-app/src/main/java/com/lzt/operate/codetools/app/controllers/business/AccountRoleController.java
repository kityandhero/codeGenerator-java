package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.assists.AccountAssist;
import com.lzt.operate.codetools.app.common.OperateBaseController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.AccountRoleService;
import com.lzt.operate.codetools.dao.service.AccountService;
import com.lzt.operate.codetools.dao.service.RoleCodeToolsService;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.dao.service.impl.AccountRoleServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.AccountServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleCodeToolsServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.SerializableData;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/operatorRole")
@Api(tags = {"账户拥有角色管理"})
public class AccountRoleController extends OperateBaseController {

	private CustomJsonWebTokenConfig customJsonWebTokenConfig;

	private AccountService accountService;

	private final AccountRoleService accountRoleService;

	private final RoleUniversalService roleUniversalService;

	private final RoleCodeToolsService roleCodeToolsService;

	@Autowired
	public AccountRoleController(
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			AccountServiceImpl accountService,
			AccountRoleServiceImpl accountRoleService,
			RoleUniversalServiceImpl roleUniversalService,
			RoleCodeToolsServiceImpl roleCodeToolsService) {
		this.customJsonWebTokenConfig = customJsonWebTokenConfig;
		this.accountService = accountService;
		this.accountRoleService = accountRoleService;
		this.roleUniversalService = roleUniversalService;
		this.roleCodeToolsService = roleCodeToolsService;
	}

	private AccountAssist getAccountAssist() {
		return new AccountAssist(
				this.customJsonWebTokenConfig,
				this.accountService,
				this.accountRoleService,
				this.roleUniversalService,
				this.roleCodeToolsService);
	}

	@ApiOperation(value = "变更拥有角色", notes = "变更拥有角色", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.OPERATOR_ROLE_CHANGE_COLLECTION, value = {
			@ApiJsonProperty(name = GlobalString.OPERATOR_ROLE_CHANGE_COLLECTION_OPERATOR_ID),
			@ApiJsonProperty(name = GlobalString.OPERATOR_ROLE_CHANGE_COLLECTION_UNIVERSAL_COLLECTION),
			@ApiJsonProperty(name = GlobalString.OPERATOR_ROLE_CHANGE_COLLECTION_INDEPENDENT_ESTABLISHMENT_COLLECTION)},
			result = @ApiJsonResult({}))
	@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.OPERATOR_ROLE_CHANGE_COLLECTION)
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/changeCollection", consumes = "application/json", produces = "application/json")
	public BaseResultData changeCollection(@RequestBody Map<String, String> json) {
		// 直接将json信息打印出来
		System.out.println(json);

		ParamData paramJson = new ParamData(json);

		// 将获取的json数据封装一层，然后在给返回
		var operatorId = paramJson.getStringExByKey(GlobalString.OPERATOR_ROLE_CHANGE_COLLECTION_OPERATOR_ID, "0");
		var universalCollection = paramJson.getStringExByKey(GlobalString.OPERATOR_ROLE_CHANGE_COLLECTION_UNIVERSAL_COLLECTION, "");
		var independentEstablishmentCollection = paramJson.getStringExByKey(GlobalString.OPERATOR_ROLE_CHANGE_COLLECTION_INDEPENDENT_ESTABLISHMENT_COLLECTION, "");

		ExecutiveSimpleResult result = getAccountAssist().changeRole(operatorId.toLong(), universalCollection.toString(), independentEstablishmentCollection
				.toString());

		if (result.getSuccess()) {

			SerializableData data = new SerializableData();

			data.append("operatorId", operatorId);

			return singleData(data);
		} else {
			var error = result.getCode();

			return fail(error);
		}

	}

}
