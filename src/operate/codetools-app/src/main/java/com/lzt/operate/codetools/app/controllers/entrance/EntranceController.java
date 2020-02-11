package com.lzt.operate.codetools.app.controllers.entrance;

import com.lzt.operate.codetools.app.common.GlobalString;
import com.lzt.operate.codetools.app.common.ModelNameCollection;
import com.lzt.operate.codetools.app.common.OperateBaseController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.dao.service.impl.OperatorServiceImpl;
import com.lzt.operate.codetools.entities.Operator;
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
import com.lzt.operate.utility.pojo.SerializableData;
import com.lzt.operate.utility.secret.Md5Assist;
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

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/entrance")
@Api(tags = {"用户登录登出"})
public class EntranceController extends OperateBaseController {

	private CustomJsonWebTokenConfig jwtConfig;

	private OperatorServiceImpl operatorServiceImpl;

	@Autowired
	public EntranceController(OperatorServiceImpl operatorServiceImpl, CustomJsonWebTokenConfig jwtConfig) {
		this.operatorServiceImpl = operatorServiceImpl;
		this.jwtConfig = jwtConfig;
	}

	@ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ENTRANCE_SING_IN, value = {
			@ApiJsonProperty(name = GlobalString.LOGIN_USERNAME),
			@ApiJsonProperty(name = GlobalString.LOGIN_PASSWORD)},
			result = @ApiJsonResult({}))
	@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ENTRANCE_SING_IN)
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/signIn", consumes = "application/json", produces = "application/json")
	public BaseResultData signIn(@RequestBody Map<String, String> json) throws Exception {
		// 直接将json信息打印出来
		System.out.println(json);

		ParamData paramJson = new ParamData(json);

		// 将获取的json数据封装一层，然后在给返回
		var userName = paramJson.getStringExByKey(GlobalString.LOGIN_USERNAME);
		var password = paramJson.getStringExByKey(GlobalString.LOGIN_PASSWORD);

		Operator operator = new Operator();
		operator.setUserName(userName.toString());

		var optionalResult = operatorServiceImpl.findByUserName(userName.toString());

		if (optionalResult.isPresent()) {
			var searchResult = optionalResult.get();

			if (!Md5Assist.verifyMd5(searchResult.getPassword(), password.toString(), searchResult.getSlat())) {
				var error = ReturnDataCode.NODATA;

				error.setMessage("密码错误!");

				return fail(error);
			}

			String token = CustomJsonWebToken.generateToken(searchResult.getId(), searchResult
					.getUserName(), searchResult.getPassword(), jwtConfig);

			SerializableData data = new SerializableData();

			data.append("token", token);

			return singleData(data);
		} else {
			var error = ReturnDataCode.NODATA;

			error.setMessage("账户不存在!");

			return fail(error);
		}

	}

	@ApiOperation(value = "用户登出", notes = "用户登出", httpMethod = "POST")
	@PostMapping(path = "/signUp", produces = "application/json")
	public ResultSingleData signUp() {
		return success();
	}

	@ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ENTRANCE_REGISTER, value = {
			@ApiJsonProperty(name = GlobalString.REGISTER_USERNAME),
			@ApiJsonProperty(name = GlobalString.REGISTER_PASSWORD),
			@ApiJsonProperty(name = GlobalString.REGISTER_RE_PASSWORD)},
			result = @ApiJsonResult({}))
	@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ENTRANCE_REGISTER)
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public BaseResultData register(@RequestBody Map<String, Serializable> json) throws NoSuchAlgorithmException {

		var paramJson = getParamData(json);

		StringEx userName = paramJson.getStringExByKey(GlobalString.REGISTER_USERNAME);
		StringEx password = paramJson.getStringExByKey(GlobalString.REGISTER_PASSWORD);
		StringEx rePassword = paramJson.getStringExByKey(GlobalString.REGISTER_RE_PASSWORD);

		if (!password.equals(rePassword)) {
			var error = ReturnDataCode.PARAM_ERROR;
			error.setMessage("两次密码输入不一致");

			return fail(error);
		}

		var existOperator = operatorServiceImpl.findByUserName(userName.toString());

		if (existOperator.isPresent()) {
			var error = ReturnDataCode.PARAM_ERROR;
			error.setMessage("登录名已存在");

			return fail(error);
		}

		var operator = new Operator();

		operator.setUserName(userName.toString());
		operator.setSlat(StringAssist.randomAlphanumeric(6)
									 .toLowerCase());
		operator.setPassword(Md5Assist.toMd5(password, operator.getSlat()));

		operatorServiceImpl.save(operator);

		return success();
	}
}
