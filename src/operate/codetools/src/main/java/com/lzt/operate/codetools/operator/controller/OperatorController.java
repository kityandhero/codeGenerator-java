package com.lzt.operate.codetools.operator.controller;

import com.lzt.operate.codetools.assists.OperatorAssist;
import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.entities.BaseResultData;
import com.lzt.operate.entities.ResultSingleData;
import com.lzt.operate.enums.ReturnDataCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;
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
@RequestMapping("/operator")
@Api(tags = {"操作者信息"})
public class OperatorController extends OperateBaseController {

    private OperatorRepository operatorRepository;

    @Autowired
    public OperatorController(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @ApiOperation(value = "当前操作者信息", notes = "当前操作者信息", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
    @PostMapping(path = "/getCurrent", produces = "application/json")
    public BaseResultData getCurrent() {

        OperatorAssist assist = new OperatorAssist(this.operatorRepository);

        Operator operator = assist.getCurrent();

        if (operator != null) {
            return this.singleData(operator);
        } else {
            var error = ReturnDataCode.NODATA;

            error.setMessage("没有数据");

            return this.fail(error);
        }
    }
}
