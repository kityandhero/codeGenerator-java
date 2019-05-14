package com.lzt.operate.codetools.entrance.controller;

import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.entity.ParamData;
import com.lzt.operate.entity.ResultDataCore;
import com.lzt.operate.entity.ResultDataFactory;
import com.lzt.operate.entity.ResultSingleData;
import com.lzt.operate.extensions.StringEx;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.lzt.operate.codetools.common.GlobalString.JSON_NAME;
import static com.lzt.operate.codetools.common.GlobalString.JSON_PASSWORD;

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
    @ApiJsonObject(name = "参数信息", value = {
            @ApiJsonProperty(name = JSON_NAME),
            @ApiJsonProperty(name = JSON_PASSWORD)},
            result = @ApiJsonResult({}))
    @ApiImplicitParam(name = "json", required = true, dataType = "参数信息")
    @ApiResponses({@ApiResponse(code = ResultDataFactory.CODE_ACCESS_SUCCESS, message = ResultDataFactory.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
    @PostMapping(path = "/signIn", consumes = "application/json", produces = "application/json")
    public ResultDataCore signIn(@RequestBody Map<String, String> json) {
        // 直接将json信息打印出来
        System.out.println(json);

        ParamData paramJson = new ParamData(json);

        // 将获取的json数据封装一层，然后在给返回
        String name = paramJson.get("name");
        String password = paramJson.get("password");

        Operator operator = new Operator();
        operator.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                                               .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                                               .withIgnorePaths("createTime", "password", "friendlyName");

        Example<Operator> example = Example.of(operator, matcher);

        List<Operator> list = this.operatorRepository.findAll(example);

        Optional<Operator> op = this.operatorRepository.findOne(example);
        Operator searchResult = op.orElse(null);

        if (searchResult == null) {
            Date now = new Date();
            operator = new Operator();

            operator.setName(name);
            operator.setPassword(StringEx.ToMD5(password).toString());
            operator.setFriendlyName(name);
            operator.setCreateTime(now);

            Operator operatorSave = this.operatorRepository.save(operator);

            return this.singleData(operatorSave);
        } else {
            return this.singleData(searchResult);
        }

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
    @PostMapping(path = "/signUp", consumes = "application/json", produces = "application/json")
    public ResultSingleData signUp() {
        return this.success();
    }
}
