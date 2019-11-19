package com.lzt.operate.codetools.database.controller;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.codetools.common.ModelNameCollection;
import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.domain.ConnectionConfig;
import com.lzt.operate.codetools.repository.ConnectionConfigRepository;
import com.lzt.operate.codetools.util.DbUtil;
import com.lzt.operate.entity.ParamData;
import com.lzt.operate.entity.ResultDataCore;
import com.lzt.operate.entity.ResultDataFactory;
import com.lzt.operate.entity.ResultListData;
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
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@Api(tags = {"数据库连接"})
public class ConnectionController extends OperateBaseController {

    private ConnectionConfigRepository connectionConfigRepository;

    @Autowired
    public ConnectionController(ConnectionConfigRepository connectionConfigRepository) {
        this.connectionConfigRepository = connectionConfigRepository;
    }

    @ApiOperation(value = "连接列表", notes = "创建数据库连接,如果链接有效则直接打开数据库获取数据表", httpMethod = "POST")
    @ApiJsonObject(name = ModelNameCollection.CONNECTION_LIST, value = {
            @ApiJsonProperty(name = GlobalString.CONNECTION_LIST_PAGE_NO),
            @ApiJsonProperty(name = GlobalString.CONNECTION_LIST_PAGE_SIZE)},
            result = @ApiJsonResult({}))
    @ApiImplicitParam(name = "connection", required = true, dataType = ModelNameCollection.CONNECTION_LIST)
    @ApiResponses({@ApiResponse(code = ResultDataFactory.CODE_ACCESS_SUCCESS, message = ResultDataFactory.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
    @PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
    public ResultListData list(@RequestBody Map<String, String> query) {
        var paramJson = getParamData(query);

        var pageNo = paramJson.getByKey(GlobalString.CONNECTION_LIST_PAGE_NO).toInt();
        var pageSize = paramJson.getByKey(GlobalString.CONNECTION_LIST_PAGE_SIZE).toInt();

        pageNo = Math.max(pageNo, 1);
        pageSize = Math.max(pageSize, 1);

        ConnectionConfig connectionConfig = new ConnectionConfig();

        ExampleMatcher matcher = ExampleMatcher.matching()
                                               .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                                               .withIgnorePaths("createTime", "password", "friendlyName");

        Example<ConnectionConfig> filter = Example.of(connectionConfig, matcher);

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, "createTime");

        var page = this.connectionConfigRepository.findAll(filter, pageable);

        return this.pageData(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages());
    }

    @ApiOperation(value = "获取连接", notes = "获取数据库连接", httpMethod = "POST")
    @ApiJsonObject(name = ModelNameCollection.CONNECTION_MODEL, value = {
            @ApiJsonProperty(name = GlobalString.CONNECTION_ConfigId)},
            result = @ApiJsonResult({}))
    @ApiImplicitParam(name = "connectionJson", required = true, dataType = ModelNameCollection.CONNECTION_MODEL)
    @ApiResponses({@ApiResponse(code = ResultDataFactory.CODE_ACCESS_SUCCESS, message = ResultDataFactory.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
    @PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
    public ResultDataCore get(@RequestBody Map<String, String> connectionJson) {
        ParamData paramJson = new ParamData(connectionJson);
        var connectionConfigId = paramJson.getByKey(GlobalString.CONNECTION_ConfigId);

        if (StringEx.isNullOrEmpty(connectionConfigId)) {
            return this.paramError(GlobalString.CONNECTION_ConfigId, "不能为空值");
        }

        var optionalResult = this.connectionConfigRepository.findById(connectionConfigId);

        if (!optionalResult.isPresent()) {
            return this.noDataError();
        }

        return this.singleData(optionalResult.get());
    }

    @ApiOperation(value = "创建连接", notes = "创建数据库连接,如果链接有效则直接打开数据库获取数据表", httpMethod = "POST")
    @ApiJsonObject(name = ModelNameCollection.CONNECTION_MODEL, value = {
            @ApiJsonProperty(name = GlobalString.CONNECTION_NAME),
            @ApiJsonProperty(name = GlobalString.CONNECTION_DB_TYPE),
            @ApiJsonProperty(name = GlobalString.CONNECTION_HOST),
            @ApiJsonProperty(name = GlobalString.CONNECTION_PORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SCHEMA),
            @ApiJsonProperty(name = GlobalString.CONNECTION_USERNAME),
            @ApiJsonProperty(name = GlobalString.CONNECTION_PASSWORD),
            @ApiJsonProperty(name = GlobalString.CONNECTION_ENCODING),
            @ApiJsonProperty(name = GlobalString.CONNECTION_L_PORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_R_PORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSH_PORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSH_HOST),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSH_USER),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSH_PASSWORD)},
            result = @ApiJsonResult({}))
    @ApiImplicitParam(name = "connectionJson", required = true, dataType = ModelNameCollection.CONNECTION_MODEL)
    @ApiResponses({@ApiResponse(code = ResultDataFactory.CODE_ACCESS_SUCCESS, message = ResultDataFactory.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResultDataCore add(@RequestBody Map<String, String> connectionJson) {
        ParamData paramJson = new ParamData(connectionJson);

        var name = paramJson.getByKey(GlobalString.CONNECTION_NAME);

        if (StringEx.isNullOrEmpty(name)) {
            return this.paramError(GlobalString.CONNECTION_NAME, "不能为空值");
        }

        var connectionConfig = new ConnectionConfig();
        connectionConfig.fillFromParamJson(paramJson);

        try {
            var listTableName = DbUtil.getTableNames(connectionConfig);
            return this.listData(listTableName);
        } catch (Exception e) {
            return this.exceptionError(e);
        }

    }

    @ApiOperation(value = "更新连接", notes = "更新数据库连接,如果链接有效则直接打开数据库获取数据表", httpMethod = "POST")
    @ApiJsonObject(name = ModelNameCollection.CONNECTION_MODEL, value = {
            @ApiJsonProperty(name = GlobalString.CONNECTION_ConfigId),
            @ApiJsonProperty(name = GlobalString.CONNECTION_NAME),
            @ApiJsonProperty(name = GlobalString.CONNECTION_DB_TYPE),
            @ApiJsonProperty(name = GlobalString.CONNECTION_HOST),
            @ApiJsonProperty(name = GlobalString.CONNECTION_PORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SCHEMA),
            @ApiJsonProperty(name = GlobalString.CONNECTION_USERNAME),
            @ApiJsonProperty(name = GlobalString.CONNECTION_PASSWORD),
            @ApiJsonProperty(name = GlobalString.CONNECTION_ENCODING),
            @ApiJsonProperty(name = GlobalString.CONNECTION_L_PORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_R_PORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSH_PORT),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSH_HOST),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSH_USER),
            @ApiJsonProperty(name = GlobalString.CONNECTION_SSH_PASSWORD)},
            result = @ApiJsonResult({}))
    @ApiImplicitParam(name = "connectionJson", required = true, dataType = ModelNameCollection.CONNECTION_MODEL)
    @ApiResponses({@ApiResponse(code = ResultDataFactory.CODE_ACCESS_SUCCESS, message = ResultDataFactory.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
    @PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public ResultDataCore update(@RequestBody Map<String, String> connectionJson) {
        ParamData paramJson = new ParamData(connectionJson);
        var name = paramJson.getByKey(GlobalString.CONNECTION_NAME);

        if (StringEx.isNullOrEmpty(name)) {
            return this.paramError(GlobalString.CONNECTION_NAME, "不能为空值");
        }

        var connectionConfig = new ConnectionConfig();
        connectionConfig.fillFromParamJson(paramJson);

        try {
            var listTableName = DbUtil.getTableNames(connectionConfig);
            return this.listData(listTableName);
        } catch (Exception e) {
            return this.exceptionError(e);
        }
    }
}
