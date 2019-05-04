package com.lzt.operate.codetools.entrance.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.extensions.StringEx;
import com.lzt.operate.utility.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author lzt
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/entrance")
@Api(tags = "用户登录登出", description = "用于用户登录登出，登录后可以加载用户的个性化信息")
@ApiResponses({
        //返回的东西，有返回码和信息，可以自定义
        @ApiResponse(code = 200, message = "success", response = ReturnData.class),
        @ApiResponse(code = 403, message = "不支持的请求"),
        @ApiResponse(code = 404, message = "找不到资源"),
        //自定义返回码
        @ApiResponse(code = -99, message = "未知异常", response = Exception.class),
})
public class EntranceController extends OperateBaseController {

    private OperatorRepository operatorRepository;

    @Autowired
    public EntranceController(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "账户名", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true),
    })
    @PostMapping(path = "/signIn", consumes = "application/json", produces = "application/json")
    public HashMap<String, Serializable> signIn(@RequestBody JSONObject jsonParam) {
        // 将获取的json数据封装一层，然后在给返回
        String name = jsonParam.getString("name");
        String password = jsonParam.getString("password");

        Operator operator = new Operator();
        operator.setName(name);
        Example<Operator> example = Example.of(operator);

        Optional<Operator> op = this.operatorRepository.findOne(example);
        Operator searchResult = op.orElse(null);

        if (searchResult == null) {
            Date now = new Date();
            operator = new Operator();

            operator.setName(name);
            operator.setPassword(StringEx.ToMD5("123456").toString());
            operator.setFriendlyName(name);
            operator.setCreateTime(now);

            Operator operatorSave = this.operatorRepository.save(operator);

            return this.success(operatorSave);
        } else {
            return this.success(searchResult);
        }

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
    public HashMap<String, Serializable> signUp() {
        return this.success();
    }
}
