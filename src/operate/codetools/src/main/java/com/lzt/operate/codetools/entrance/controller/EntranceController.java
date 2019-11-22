package com.lzt.operate.codetools.entrance.controller;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.codetools.common.ModelNameCollection;
import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.entities.BaseResultData;
import com.lzt.operate.entities.ParamData;
import com.lzt.operate.entities.ResultSingleData;
import com.lzt.operate.entities.SerializableData;
import com.lzt.operate.enums.ReturnDataCode;
import com.lzt.operate.secret.SecretAssist;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lzt
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/entrance")
@Api(tags = {"用户登录登出"})
public class EntranceController extends OperateBaseController {

    private OperatorRepository operatorRepository;

    @Autowired
    public EntranceController(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @ApiJsonObject(name = ModelNameCollection.Entrance_SING_IN, value = {
            @ApiJsonProperty(name = GlobalString.LOGIN_USERNAME),
            @ApiJsonProperty(name = GlobalString.LOGIN_PASSWORD)},
            result = @ApiJsonResult({}))
    @ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.Entrance_SING_IN)
    @ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
    @PostMapping(path = "/signIn", consumes = "application/json", produces = "application/json")
    public BaseResultData signIn(@RequestBody Map<String, String> json) throws Exception {
        // 直接将json信息打印出来
        System.out.println(json);

        ParamData paramJson = new ParamData(json);

        // 将获取的json数据封装一层，然后在给返回
        var name = paramJson.getByKey(GlobalString.LOGIN_USERNAME);
        var password = paramJson.getByKey(GlobalString.LOGIN_PASSWORD);

        Operator operator = new Operator();
        operator.setUserName(name.toString());

        ExampleMatcher matcher = ExampleMatcher.matching()
                                               .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                                               .withIgnorePaths("createTime", "password", "friendlyName");

        Example<Operator> example = Example.of(operator, matcher);

        var optionalResult = this.operatorRepository.findOne(example);

        Operator searchResult = optionalResult.orElse(null);

        if (searchResult == null) {
            var error = ReturnDataCode.NODATA;

            error.setMessage("账户不存在!");

            return this.fail(error);
        }

        var passwordEncrypt = password.toMD5();

        if (!searchResult.getPassword().equals(passwordEncrypt)) {
            return this.fail(ReturnDataCode.PARAM_ERROR);
        }

        SerializableData data = new SerializableData();

        data.append("token", SecretAssist.encryptWithExpirationTime(searchResult.getId(), 1440));

        return this.singleData(data);
    }

    @ApiOperation(value = "用户登出", notes = "用户登出", httpMethod = "POST")
    @PostMapping(path = "/signUp", produces = "application/json")
    public ResultSingleData signUp() {
        return this.success();
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
    public BaseResultData register(@RequestBody Map<String, String> json) {

        var paramJson = getParamData(json);

        var name = paramJson.getByKey(GlobalString.REGISTER_USERNAME);
        var password = paramJson.getByKey(GlobalString.REGISTER_PASSWORD);
        var rePassword = paramJson.getByKey(GlobalString.REGISTER_RE_PASSWORD);

        var operator = new Operator();

        operator.setUserName(name.toString());
        operator.setPassword(password.toMD5());

        Operator operatorSave = this.operatorRepository.saveAfterPretreatment(operator);

        return this.singleData(operatorSave);
    }
}
