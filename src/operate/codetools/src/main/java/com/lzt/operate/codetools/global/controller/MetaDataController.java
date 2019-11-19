package com.lzt.operate.codetools.global.controller;

import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.entity.ResultDataCore;
import com.lzt.operate.entity.ResultDataFactory;
import com.lzt.operate.entity.ResultListData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzt
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/metaData")
@Api(tags = "公共资源", description = "公用的各项数据资源集合")
public class MetaDataController extends OperateBaseController {

    @ApiOperation(value = "公共数据", notes = "公共数据资源", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = ResultDataFactory.CODE_ACCESS_SUCCESS, message = ResultDataFactory.MESSAGE_ACCESS_SUCCESS, response = ResultListData.class)})
    @PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
    public ResultDataCore get(@RequestBody Map<String, String> connectionJson) {

        var result = new HashMap<String, Object>();

        return this.singleData(result);
    }

}
