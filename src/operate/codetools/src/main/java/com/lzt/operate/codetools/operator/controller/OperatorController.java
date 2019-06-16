package com.lzt.operate.codetools.operator.controller;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.entity.ParamData;
import com.lzt.operate.entity.ResultDataCore;
import com.lzt.operate.entity.ResultDataFactory;
import com.lzt.operate.entity.ResultSingleData;
import com.lzt.operate.extensions.StringEx;
import io.swagger.annotations.Api;
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

import java.util.Date;
import java.util.Map;

/**
 * @author lzt
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/entrance")
@Api(tags = "用户信息", description = "用于获取和操作用户的信息")
public class OperatorController extends OperateBaseController {

    private OperatorRepository operatorRepository;

    @Autowired
    public OperatorController(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @ApiOperation(value = "当前用户信息", notes = "当前用户信息", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = ResultDataFactory.CODE_ACCESS_SUCCESS, message = ResultDataFactory.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
    @PostMapping(path = "/getCurrent", consumes = "application/json", produces = "application/json")
    public ResultDataCore getCurrent(@RequestBody Map<String, String> json) {
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
}
