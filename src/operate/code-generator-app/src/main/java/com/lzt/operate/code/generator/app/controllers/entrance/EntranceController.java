package com.lzt.operate.code.generator.app.controllers.entrance;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.lzt.operate.code.generator.app.assists.AccountAssist;
import com.lzt.operate.code.generator.app.caches.ListCompetenceCache;
import com.lzt.operate.code.generator.app.common.OperateBaseController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.enums.AccountStatus;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.AccountRoleService;
import com.lzt.operate.code.generator.dao.service.AccountService;
import com.lzt.operate.code.generator.dao.service.RoleUniversalService;
import com.lzt.operate.code.generator.dao.service.impl.AccountRoleServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.AccountServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.RoleCodeToolsServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.code.generator.entities.Account;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.extensions.StringEx;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.ReturnMessage;
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
@RequestMapping("/entrance")
@Api(tags = {"用户登录登出"})
public class EntranceController extends OperateBaseController {

	private final LoadingCache<String, Object> loadingCache;
	private final AccountRoleService accountRoleService;
	private final RoleUniversalService roleUniversalService;
	private final RoleCodeToolsServiceImpl roleCodeToolsService;
	private CustomJsonWebTokenConfig customJsonWebTokenConfig;
	private AccountService accountService;

	@Autowired
	public EntranceController(
			LoadingCache<String, Object> loadingCache,
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			AccountServiceImpl accountService,
			AccountRoleServiceImpl accountRoleService,
			RoleUniversalServiceImpl roleUniversalService,
			RoleCodeToolsServiceImpl roleCodeToolsService) {
		this.loadingCache = loadingCache;
		this.customJsonWebTokenConfig = customJsonWebTokenConfig;
		this.accountService = accountService;
		this.accountRoleService = accountRoleService;
		this.roleUniversalService = roleUniversalService;
		this.roleCodeToolsService = roleCodeToolsService;
	}

	private AccountAssist getAccountAssist() {
		return new AccountAssist(
				this.loadingCache,
				this.customJsonWebTokenConfig,
				this.accountService,
				this.accountRoleService,
				this.roleUniversalService,
				this.roleCodeToolsService);
	}

	@ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ENTRANCE_SING_IN, value = {
			@ApiJsonProperty(name = GlobalString.ACCOUNT_USERNAME),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_PASSWORD)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ENTRANCE_SING_IN),
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/signIn", consumes = "application/json", produces = "application/json")
	public BaseResultData signIn(@RequestBody Map<String, String> json) throws Exception {
		// 直接将json信息打印出来
		System.out.println(json);

		ParamData paramJson = new ParamData(json);

		// 将获取的json数据封装一层，然后在给返回
		String userName = paramJson.getStringByKey(GlobalString.ACCOUNT_USERNAME);
		String password = paramJson.getStringByKey(GlobalString.ACCOUNT_PASSWORD);

		Account operator = new Account();
		operator.setUserName(userName);

		Optional<Account> optionalResult = accountService.findByUserName(userName);

		if (optionalResult.isPresent()) {
			Account searchResult = optionalResult.get();

			if (!Md5Assist.verifyMd5(searchResult.getPassword(), password, searchResult.getSlat())) {
				ReturnMessage error = ReturnDataCode.NoData.toMessage();

				error.toMessage("密码错误!");

				return fail(error);
			}

			String token = CustomJsonWebToken.generateToken(searchResult.getId(), searchResult
					.getUserName(), searchResult.getPassword(), customJsonWebTokenConfig);

			SerializableData data = new SerializableData();

			data.append("token", token);

			AccountAssist operatorAssist = getAccountAssist();

			data.append("currentAuthority", operatorAssist.getCompetenceTagCollection(searchResult.getId()).toArray());

			ListCompetenceCache.removeCache(searchResult.getId(), this.getAccountAssist().getLoadingCache());

			return singleData(data);
		} else {
			ReturnMessage error = ReturnDataCode.NoData.toMessage();

			error.toMessage("账户不存在!");

			return fail(error);
		}

	}

	@ApiOperation(value = "用户登出", notes = "用户登出", httpMethod = "POST")
	@PostMapping(path = "/signOut", produces = "application/json")
	public ResultSingleData signOut() {
		return success();
	}

	@ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ENTRANCE_REGISTER, value = {
			@ApiJsonProperty(name = GlobalString.ACCOUNT_USERNAME),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_PASSWORD),
			@ApiJsonProperty(name = GlobalString.RE_PASSWORD)},
			result = @ApiJsonResult({}))
	@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ENTRANCE_REGISTER)
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public BaseResultData register(@RequestBody Map<String, Serializable> json) throws NoSuchAlgorithmException {

		ParamData paramJson = getParamData(json);

		StringEx userName = paramJson.getStringExByKey(GlobalString.ACCOUNT_NAME);
		StringEx password = paramJson.getStringExByKey(GlobalString.ACCOUNT_PASSWORD);
		StringEx rePassword = paramJson.getStringExByKey(GlobalString.RE_PASSWORD);

		if (!password.equals(rePassword)) {
			ReturnMessage error = ReturnDataCode.ParamError.toMessage();

			error.toMessage("两次密码输入不一致");

			return fail(error);
		}

		Optional<Account> existAccount = accountService.findByUserName(userName.toString());

		if (existAccount.isPresent()) {
			ReturnMessage error = ReturnDataCode.ParamError.toMessage();

			error.toMessage("登录名已存在");

			return fail(error);
		}

		Account account = new Account();

		account.setUserName(userName.toString());
		account.setSlat(StringAssist.randomAlphanumeric(6)
									.toLowerCase());
		account.setPassword(Md5Assist.toMd5(password, account.getSlat()));
		account.setStatus(AccountStatus.Enabled, AccountStatus::getFlag, AccountStatus::getName);

		accountService.save(account);

		return success();
	}
}
