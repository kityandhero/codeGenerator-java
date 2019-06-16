package com.lzt.operate.codetools.operator.controller;

import com.lzt.operate.codetools.Assists.OperatorAssist;
import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.entity.ErrorMessage;
import com.lzt.operate.entity.ResultDataCore;
import com.lzt.operate.entity.ResultDataFactory;
import com.lzt.operate.entity.ResultSingleData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultDataCore getCurrent() {

        Operator operator = OperatorAssist.getCurrent(this.operatorRepository);

        if (operator != null) {
            return this.singleData(operator);
        } else {
            return this.fail(ErrorMessage.noDataError.getCode(), "没有数据");
        }
    }
}
