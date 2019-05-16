package com.lzt.operate.codetools.database.controller;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.repository.ConnectionConfigRepository;
import com.lzt.operate.entity.ParamData;
import com.lzt.operate.entity.ResultDataCore;
import com.lzt.operate.entity.ResultDataFactory;
import com.lzt.operate.entity.ResultSingleData;
import com.lzt.operate.entity.SerializableData;
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
@RequestMapping("/connection")
@Api(tags = "数据库连接", description = "用于数据库连接等的操作")
public class ConnectionController extends OperateBaseController {

    private ConnectionConfigRepository connectionConfigRepository;

    @Autowired
    public ConnectionController(ConnectionConfigRepository connectionConfigRepository) {
        this.connectionConfigRepository = connectionConfigRepository;
    }

    @ApiOperation(value = "创建连接", notes = "创建数据库连接,如果链接有效则直接打开数据库获取数据表", httpMethod = "POST")
    @ApiJsonObject(name = "新建数据库连接参数", value = {
            @ApiJsonProperty(name = GlobalString.CONNECTION_NAME),
            @ApiJsonProperty(name = GlobalString.CONNECTION_DBTYPE),
            @ApiJsonProperty(name = GlobalString.CONNECTION_DBTYPE),
            @ApiJsonProperty(name = GlobalString.CONNECTION_HOST),
            @ApiJsonProperty(name = GlobalString.CONNECTION_PORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SCHEMA),
            @ApiJsonProperty(name = GlobalString.CONNECTION_USERNAME),
            @ApiJsonProperty(name = GlobalString.CONNECTION_PASSWORD),
            @ApiJsonProperty(name = GlobalString.CONNECTION_ENCODING),
            @ApiJsonProperty(name = GlobalString.CONNECTION_LPORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_RPORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSHPORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSHHOST),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSHUSER),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSHPASSWORD)},
            result = @ApiJsonResult({}))
    @ApiImplicitParam(name = "connection", required = true, dataType = "参数信息")
    @ApiResponses({@ApiResponse(code = ResultDataFactory.CODE_ACCESS_SUCCESS, message = ResultDataFactory.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
    @PostMapping(path = "/open", consumes = "application/json", produces = "application/json")
    public ResultDataCore open(@RequestBody Map<String, String> connection) {

        ParamData paramJson = new ParamData(connection);

        // // 将获取的json数据封装一层，然后在给返回
        // String name = paramJson.get("name");
        // String password = paramJson.get("password");
        //
        // Operator operator = new Operator();
        // operator.setName(name);
        //
        // ExampleMatcher matcher = ExampleMatcher.matching()
        //                                        .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
        //                                        .withIgnorePaths("createTime", "password", "friendlyName");
        //
        // Example<Operator> example = Example.of(operator, matcher);
        //
        // var op = this.connectionConfigRepository.findFirst(example);
        //
        // Operator searchResult = op.orElse(null);
        //
        // if (searchResult == null) {
        //     Date now = new Date();
        //     operator = new Operator();
        //
        //     operator.setName(name);
        //     operator.setPassword(StringEx.ToMD5(password).toString());
        //     operator.setFriendlyName(name);
        //     operator.setCreateTime(now);
        //
        //     Operator operatorSave = this.connectionConfigRepository.save(operator);
        //
        //     return this.singleData(operatorSave);
        // } else {
        //     return this.singleData(searchResult);
        // }

        SerializableData data = new SerializableData(connection);

        return this.singleData(data);
    }

}
