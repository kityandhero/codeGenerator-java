package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.assists.AccountAssist;
import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.utils.CustomConstants;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.AccountRoleService;
import com.lzt.operate.codetools.dao.service.AccountService;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.dao.service.impl.AccountRoleServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.AccountServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleCodeToolsServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.codetools.entities.Account;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.SerializableData;
import com.lzt.operate.utility.secret.Md5Assist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/operator")
@Api(tags = {"操作者信息"})
public class OperatorController extends BaseOperateAuthController {

	private final AccountRoleService accountRoleService;
	private final RoleUniversalService roleUniversalService;
	private final RoleCodeToolsServiceImpl roleCodeToolsService;
	private AccountService operatorService;

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

		return this.noDataError();
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

		return this.noDataError();
	}

	@ApiOperation(value = "更新操作者基本信息", notes = "更新操作者基本信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCOUNT_UPDATE_BASIC_INFO, value = {
			@ApiJsonProperty(name = GlobalString.ACCOUNT_NAME),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_CITY_NAME),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_CITY_CODE),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_EMAIL),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_PHONE),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_AVATAR),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_DESCRIPTION)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCOUNT_UPDATE_BASIC_INFO)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/updateCurrentBasicInfo", produces = "application/json")
	public BaseResultData updateCurrentBasicInfo(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		String name = paramJson.getStringByKey(GlobalString.ACCOUNT_NAME);
		String cityName = paramJson.getStringByKey(GlobalString.ACCOUNT_CITY_NAME);
		long cityCode = paramJson.getStringExByKey(GlobalString.ACCOUNT_CITY_CODE).toLong();
		String email = paramJson.getStringByKey(GlobalString.ACCOUNT_EMAIL);
		String phone = paramJson.getStringByKey(GlobalString.ACCOUNT_PHONE);
		String avatar = paramJson.getStringByKey(GlobalString.ACCOUNT_AVATAR);
		String description = paramJson.getStringByKey(GlobalString.ACCOUNT_DESCRIPTION);

		AccountAssist assist = getAccountAssist();

		Optional<Account> optional = assist.getCurrent();

		if (optional.isPresent()) {
			Account data = optional.get();

			data.setName(name);
			data.setCityName(cityName);
			data.setCityCode(cityCode);
			data.setEmail(email);
			data.setPhone(phone);
			data.setAvatar(avatar);
			data.setDescription(description);

			long operatorId = getOperatorId();

			data.setUpdateOperatorId(operatorId);

			return this.success();
		}

		return this.noDataError();
	}

	@ApiOperation(value = "修改操作者密码", notes = "修改操作者密码", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCOUNT_UPDATE_RESET_PASSWORD, value = {
			@ApiJsonProperty(name = GlobalString.ORIGINAL_PASSWORD),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_PASSWORD),
			@ApiJsonProperty(name = GlobalString.RE_PASSWORD)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCOUNT_UPDATE_RESET_PASSWORD)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/changeCurrentPassword", consumes = "application/json", produces = "application/json")
	public BaseResultData changeCurrentPassword(@RequestBody Map<String, Serializable> json) throws NoSuchAlgorithmException {
		ParamData paramJson = getParamData(json);

		String password = paramJson.getStringByKey(GlobalString.ACCOUNT_PASSWORD);

		if (StringAssist.isNullOrEmpty(password)) {
			return this.paramError(GlobalString.ACCOUNT_PASSWORD, "密码无效");
		}

		if (password.length() <= CustomConstants.ACCOUNT_PASSWORD_MIN_LENGTH || password.length() > CustomConstants.ACCOUNT_PASSWORD_MAX_LENGTH) {
			return this.paramError(GlobalString.ACCOUNT_PASSWORD, "密码长度为6~32位");
		}

		String rePassword = paramJson.getStringByKey(GlobalString.RE_PASSWORD);

		if (StringAssist.isNullOrEmpty(rePassword)) {
			return this.paramError(GlobalString.RE_PASSWORD, "确认密码无效");
		}

		if (!password.equals(rePassword)) {
			return this.paramError(GlobalString.RE_PASSWORD, "密码与确认密码不一致");
		}

		String originalPassword = paramJson.getStringByKey(GlobalString.ORIGINAL_PASSWORD);

		AccountAssist assist = getAccountAssist();

		Optional<Account> optional = assist.getCurrent();

		if (optional.isPresent()) {
			Account data = optional.get();

			if (!data.getPassword().equals(Md5Assist.toMd5(originalPassword, data.getSlat()))) {
				return this.fail(ReturnDataCode.PasswordNotMatch.toMessage().toMessage("原密码错误"));
			}

			data.setPassword(Md5Assist.toMd5(password, data.getSlat()));

			long operatorId = getOperatorId();

			data.setUpdateOperatorId(operatorId);

			data = assist.getAccountService().save(data);

			return this.singleData(new SerializableData().append(GlobalString.ACCOUNT_ID, data.getId()));
		}

		return this.noDataError();
	}

}
