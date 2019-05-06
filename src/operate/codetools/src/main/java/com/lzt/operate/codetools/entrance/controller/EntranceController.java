package com.lzt.operate.codetools.entrance.controller;

import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.codetools.swagger2.model.ApiJsonObject;
import com.lzt.operate.codetools.swagger2.model.ApiJsonProperty;
import com.lzt.operate.codetools.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
import java.util.HashMap;
import java.util.Map;

import static com.lzt.operate.codetools.common.GlobalString.JSON_USER_EMAIL;
import static com.lzt.operate.codetools.common.GlobalString.JSON_USER_NAME;

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
            @ApiJsonProperty(name = JSON_USER_NAME),
            @ApiJsonProperty(name = JSON_USER_EMAIL)},
            result = @ApiJsonResult({}))
    @ApiImplicitParam(name = "json", required = true, dataType = "参数信息")
    // @ApiResponses({@ApiResponse(code = 200, message = "OK", reference = "参数信息")})
    @ApiResponses({@ApiResponse(code = ReturnData.CODE_ACCESS_SUCCESS, message = ReturnData.MESSAGE_ACCESS_SUCCESS, response = ReturnData.class)})
    // @ApiResponses({
    //         //返回的东西，有返回码和信息，可以自定义
    //         @ApiResponse(code = 200, message = "success", response = ReturnData.class),
    //         @ApiResponse(code = 403, message = "不支持的请求"),
    //         @ApiResponse(code = 404, message = "找不到资源"),
    //         //自定义返回码
    //         @ApiResponse(code = -99, message = "未知异常", response = Exception.class),
    // })
    @PostMapping(path = "/signIn", consumes = "application/json", produces = "application/json")
    public HashMap<String, Serializable> signIn(@RequestBody Map<String, String> json) {
        // 直接将json信息打印出来
        System.out.println(json);

        // // 将获取的json数据封装一层，然后在给返回
        // String name = jsonParam.getString("name");
        // String password = jsonParam.getString("password");
        //
        // Operator operator = new Operator();
        // operator.setName(name);
        // Example<Operator> example = Example.of(operator);
        //
        // Optional<Operator> op = this.operatorRepository.findOne(example);
        // Operator searchResult = op.orElse(null);
        //
        // if (searchResult == null) {
        //     Date now = new Date();
        //     operator = new Operator();
        //
        //     operator.setName(name);
        //     operator.setPassword(StringEx.ToMD5("123456").toString());
        //     operator.setFriendlyName(name);
        //     operator.setCreateTime(now);
        //
        //     Operator operatorSave = this.operatorRepository.save(operator);
        //
        //     return this.success(operatorSave);
        // } else {
        //     return this.success(searchResult);
        // }
        return this.success();
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
    // @ApiResponses({
    //         //返回的东西，有返回码和信息，可以自定义
    //         @ApiResponse(code = 200, message = "success", response = ReturnData.class),
    //         @ApiResponse(code = 403, message = "不支持的请求"),
    //         @ApiResponse(code = 404, message = "找不到资源"),
    //         //自定义返回码
    //         @ApiResponse(code = -99, message = "未知异常", response = Exception.class),
    // })
    @PostMapping(path = "/signUp", consumes = "application/json", produces = "application/json")
    public HashMap<String, Serializable> signUp() {
        return this.success();
    }
}
