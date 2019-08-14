package com.lzt.operate.codetools.entrance.controller;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.codetools.common.ModelNameCollection;
import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.entity.ErrorMessage;
import com.lzt.operate.entity.ParamData;
import com.lzt.operate.entity.ResultDataCore;
import com.lzt.operate.entity.ResultDataFactory;
import com.lzt.operate.entity.ResultSingleData;
import com.lzt.operate.entity.SerializableData;
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
@Api(tags = "用户登录登出", description = "用于用户登录登出，登录后可以加载用户的个性化信息")
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
    @ApiResponses({@ApiResponse(code = ResultDataFactory.CODE_ACCESS_SUCCESS, message = ResultDataFactory.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
    @PostMapping(path = "/signIn", consumes = "application/json", produces = "application/json")
    public ResultDataCore signIn(@RequestBody Map<String, String> json) throws Exception {
        // 直接将json信息打印出来
        System.out.println(json);

        ParamData paramJson = new ParamData(json);

        // 将获取的json数据封装一层，然后在给返回
        String name = paramJson.getByKey(GlobalString.LOGIN_USERNAME);
        String password = paramJson.getByKey(GlobalString.LOGIN_PASSWORD);

        Operator operator = new Operator();
        operator.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                                               .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                                               .withIgnorePaths("createTime", "password", "friendlyName");

        Example<Operator> example = Example.of(operator, matcher);

        var optionalResult = this.operatorRepository.findFirst(example);

        Operator searchResult = optionalResult.orElse(null);

        if (searchResult == null) {
            // Date now = new Date();
            // operator = new Operator();
            //
            // operator.setName(name);
            // operator.setPassword(StringEx.ToMD5(password).toString());
            // operator.setFriendlyName(name);
            // operator.setCreateTime(now);
            //
            // Operator operatorSave = this.operatorRepository.save(operator);
            //
            // return this.singleData(operatorSave);

            return this.fail(ErrorMessage.noDataError.getCode(), "账户不存在或密码错误！");
        }

        SerializableData data = new SerializableData();

        data.append("token", SecretAssist.EncryptWithExpirationTime(searchResult.getId(), 240));

        return this.singleData(data);

        // SerializableData data = new SerializableData(json);

        // return this.singleData(data);
    }

    // @RequestMapping("/entrance/dataTest")
    // @ResponseBody
    // public HashMap<String, Serializable> dataTest() {
    //     return this.success();
    // }
    //
    // @RequestMapping("/entrance/listTest")
    // @ResponseBody
    // public HashMap<String, Serializable> listTest() {
    //     ArrayList<String> a = new ArrayList<String>();
    //     a.add("1");
    //     a.add("2");
    //     a.add("3");
    //     a.add("4");
    //
    //     return this.pageData(a);
    // }

    @ApiOperation(value = "用户登出", notes = "用户登出", httpMethod = "POST")
    @PostMapping(path = "/signUp", produces = "application/json")
    public ResultSingleData signUp() {
        return this.success();
    }
}
