package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.enums.AccountStatus;
import com.lzt.operate.codetools.common.utils.CustomConstants;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.AccountService;
import com.lzt.operate.codetools.dao.service.impl.AccountServiceImpl;
import com.lzt.operate.codetools.entities.Account;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.general.Constants;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.ReturnMessage;
import com.lzt.operate.utility.pojo.SerializableData;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
import com.lzt.operate.utility.secret.Md5Assist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/account")
@Api(tags = {"账户管理"})
public class AccountController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "账户管理/";

	private AccountService accountService;

	@Autowired
	public AccountController(CustomJsonWebTokenConfig customJsonWebTokenConfig, AccountServiceImpl accountRepository) {
		super(customJsonWebTokenConfig);

		this.accountService = accountRepository;
	}

	public AccountService getAccountService() {
		Optional<AccountService> optional = Optional.ofNullable(this.accountService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("AccountService获取失败");
	}

	@ApiOperation(value = "账户列表", notes = "账户列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCOUNT_LIST, value = {
			@ApiJsonProperty(name = GlobalString.ACCOUNT_USERNAME),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_NAME),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_STATUS),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCOUNT_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "账户列表", description = "账户列表", tag = "116c5994-2993-4904-8a1b-14ad8318d6b5")
	public ResultListData list(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		String userName = paramJson.getStringByKey(GlobalString.ACCOUNT_USERNAME);
		String name = paramJson.getStringByKey(GlobalString.ACCOUNT_NAME);
		Integer status = paramJson.getStringExByKey(GlobalString.ACCOUNT_STATUS, Constants.SEARCH_UNLIMITED_STRING)
								  .toInt();

		if (!StringAssist.isNullOrEmpty(name) && !EnumAssist.existTargetValue(Arrays.asList(AccountStatus.values()), AccountStatus::getValue, status)) {
			return this.pageDataEmpty(pageSize);
		}

		Specification<Account> specification = new Specification<Account>() {

			private static final long serialVersionUID = 8923524992879206679L;

			@Override
			public Predicate toPredicate(@NonNull Root<Account> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!StringAssist.isNullOrEmpty(userName)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(Account::getUserName)), userName));
				}

				if (!StringAssist.isNullOrEmpty(name)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(Account::getName)), name));
				}

				if (!status.equals(Constants.SEARCH_UNLIMITED_NUMBER)) {
					list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(Account::getStatus)), status));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(Account::getCreateTime));

		Page<Account> result = this.accountService.page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<Account>> getterList = new ArrayList<>();

												getterList.add(Account::getUserName);
												getterList.add(Account::getName);
												getterList.add(Account::getDescription);
												getterList.add(Account::getChannel);
												getterList.add(Account::getChannelNote);
												getterList.add(Account::getStatus);
												getterList.add(Account::getStatusNote);
												getterList.add(Account::getCreateTime);
												getterList.add(Account::getUpdateTime);

												SerializableData data = SerializableData.toSerializableData(o, getterList);
												data.append(ReflectAssist.getFriendlyIdName(Account.class), o.getId());

												return data;
											})
											.collect(Collectors.toList());

		int pageIndex = result.getNumber();
		long totalPages = result.getTotalPages();

		return this.pageData(list, pageIndex, pageSize, totalPages);
	}

	@ApiOperation(value = "获取账户", notes = "获取账户信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCOUNT_GET, value = {
			@ApiJsonProperty(name = GlobalString.ACCOUNT_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCOUNT_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "账户详情", description = "获取账户信息", tag = "c1dfe241-5b5c-4b44-acac-4b3e0845d005")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long accountId = paramJson.getStringExByKey(GlobalString.ACCOUNT_ID, "0").toLong();

		Optional<Account> result = getAccountService().get(accountId);

		if (result.isPresent()) {
			Account account = result.get();

			List<IGetter<Account>> getterList = new ArrayList<>();

			getterList.add(Account::getUserName);
			getterList.add(Account::getName);
			getterList.add(Account::getDescription);
			getterList.add(Account::getChannel);
			getterList.add(Account::getChannelNote);
			getterList.add(Account::getStatus);
			getterList.add(Account::getStatusNote);
			getterList.add(Account::getCreateTime);
			getterList.add(Account::getUpdateTime);

			SerializableData data = SerializableData.toSerializableData(account, getterList);
			data.append(ReflectAssist.getFriendlyIdName(Account.class), account.getId());

			return this.singleData(data);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

	@ApiOperation(value = "创建账户", notes = "创建账户信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCOUNT_ADD_BASIC_INFO, value = {
			@ApiJsonProperty(name = GlobalString.ACCOUNT_NAME),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_PASSWORD),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_NAME),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_DESCRIPTION)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCOUNT_ADD_BASIC_INFO)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/addBasicInfo", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "新增账户", description = "创建账户信息", tag = "eb0105b1-f2db-4c44-ae8a-356c6f57e726")
	public BaseResultData addBasicInfo(@RequestBody Map<String, Serializable> json) throws NoSuchAlgorithmException {
		ParamData paramJson = getParamData(json);

		String userName = paramJson.getStringByKey(GlobalString.ACCOUNT_USERNAME).trim();

		ExecutiveSimpleResult result = getAccountService().verifyUserName(userName);

		if (!result.getSuccess()) {
			return this.fail(result);
		}

		String password = paramJson.getStringByKey(GlobalString.ACCOUNT_PASSWORD);
		String rePassword = paramJson.getStringByKey(GlobalString.RE_PASSWORD);

		result = getAccountService().verifyPassword(password, rePassword, true);

		if (!result.getSuccess()) {
			return this.fail(result);
		}

		String name = paramJson.getStringByKey(GlobalString.ACCOUNT_NAME);

		Optional<Account> existAccount = accountService.findByUserName(userName);

		if (existAccount.isPresent()) {
			ReturnMessage error = ReturnDataCode.ParamError.toMessage();

			error.toMessage("登录名已存在");

			return this.paramError(GlobalString.ACCOUNT_USERNAME, "登录名已存在");
		}

		String description = paramJson.getStringByKey(GlobalString.ACCOUNT_DESCRIPTION);

		Account data = new Account();

		data.setUserName(userName);
		data.setName(name);
		data.setDescription(description);
		data.setSlat(StringAssist.randomAlphanumeric(6)
								 .toLowerCase());
		data.setPassword(Md5Assist.toMd5(password, data.getSlat()));
		data.setStatus(AccountStatus.Enabled, AccountStatus::getValue, AccountStatus::getName);

		long operatorId = getOperatorId();

		data.setCreateOperatorId(operatorId);
		data.setUpdateOperatorId(operatorId);

		data = getAccountService().save(data);

		return this.singleData(new SerializableData().append(GlobalString.ACCOUNT_ID, data.getId()));
	}

	@ApiOperation(value = "更新账户", notes = "更新账户信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCOUNT_UPDATE_BASIC_INFO, value = {
			@ApiJsonProperty(name = GlobalString.ACCOUNT_ID),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_NAME),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_DESCRIPTION)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCOUNT_UPDATE_BASIC_INFO)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/updateBasicInfo", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "更新基本信息", description = "更新账户信息", tag = "fd1b4b9d-4b92-4986-a695-986b16cad8a8")
	public BaseResultData updateBasicInfo(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long accountId = paramJson.getStringExByKey(GlobalString.ACCOUNT_ID, "0").toLong();

		if (accountId <= 0) {
			return this.paramError(GlobalString.ACCOUNT_ID, "数据无效");
		}

		String name = paramJson.getStringByKey(GlobalString.ACCOUNT_NAME);
		String description = paramJson.getStringByKey(GlobalString.ACCOUNT_DESCRIPTION);

		Optional<Account> result = getAccountService().get(accountId);

		if (result.isPresent()) {
			Account data = result.get();

			data.setName(name);
			data.setDescription(description);

			long operatorId = getOperatorId();

			data.setUpdateOperatorId(operatorId);

			data = getAccountService().save(data);

			return this.singleData(new SerializableData().append(GlobalString.ACCOUNT_ID, data.getId()));
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

	@ApiOperation(value = "重置密码", notes = "重置密码", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCOUNT_UPDATE_RESET_PASSWORD, value = {
			@ApiJsonProperty(name = GlobalString.ACCOUNT_ID),
			@ApiJsonProperty(name = GlobalString.ACCOUNT_PASSWORD),
			@ApiJsonProperty(name = GlobalString.RE_PASSWORD)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCOUNT_UPDATE_RESET_PASSWORD)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/resetPassword", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "更新基本信息", description = "重置密码", tag = "fd1b4b9d-4b92-4986-a695-986b16cad8a8")
	public BaseResultData resetPassword(@RequestBody Map<String, Serializable> json) throws NoSuchAlgorithmException {
		ParamData paramJson = getParamData(json);

		long accountId = paramJson.getStringExByKey(GlobalString.ACCOUNT_ID, "0").toLong();

		if (accountId <= 0) {
			return this.paramError(GlobalString.ACCOUNT_ID, "数据无效");
		}

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

		Optional<Account> result = getAccountService().get(accountId);

		if (result.isPresent()) {
			Account data = result.get();

			data.setPassword(Md5Assist.toMd5(password, data.getSlat()));

			long operatorId = getOperatorId();

			data.setUpdateOperatorId(operatorId);

			data = getAccountService().save(data);

			return this.singleData(new SerializableData().append(GlobalString.ACCOUNT_ID, data.getId()));
		}

		return this.noDataError();
	}

	@ApiOperation(value = "启用账户", notes = "设置账户状态为启用状态", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCOUNT_SET_ENABLED, value = {
			@ApiJsonProperty(name = GlobalString.ACCOUNT_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCOUNT_SET_ENABLED)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/setEnabled", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "启用账户", description = "设置账户状态为启用状态", tag = "72c3771b-6c5e-411c-9e49-d8555ab99743")
	public BaseResultData setEnabled(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long accountId = paramJson.getStringExByKey(GlobalString.ACCOUNT_ID, "0").toLong();

		return this.setStatusCore(accountId, AccountStatus.Enabled);
	}

	@ApiOperation(value = "禁用账户", notes = "设置账户状态为禁用状态", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCOUNT_SET_DISABLED, value = {
			@ApiJsonProperty(name = GlobalString.ACCOUNT_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCOUNT_SET_DISABLED)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/setDisabled", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "禁用账户", description = "设置账户状态为禁用状态", tag = "2ab71d44-ecda-462c-822c-8f37d3000847")
	public BaseResultData setDisabled(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long accountId = paramJson.getStringExByKey(GlobalString.ACCOUNT_ID, "0").toLong();

		return this.setStatusCore(accountId, AccountStatus.Disabled);
	}

	private BaseResultData setStatusCore(long accountId, AccountStatus status) {
		if (accountId <= 0) {
			return this.paramError(GlobalString.ACCOUNT_ID, "数据无效");
		}

		Optional<Account> result = getAccountService().get(accountId);

		if (result.isPresent()) {
			Account data = result.get();

			data.setStatus(status, AccountStatus::getValue, AccountStatus::getName);

			long operatorId = getOperatorId();

			data.setUpdateOperatorId(operatorId);

			data = getAccountService().save(data);

			return this.singleData(new SerializableData().append(GlobalString.ACCOUNT_ID, data.getId()));
		}

		return this.noDataError();
	}

}
