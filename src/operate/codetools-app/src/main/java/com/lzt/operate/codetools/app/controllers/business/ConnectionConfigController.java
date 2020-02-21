package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.assists.ConnectionConfigAssist;
import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.common.GlobalString;
import com.lzt.operate.codetools.app.common.ModelNameCollection;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.app.enums.ConnectionType;
import com.lzt.operate.codetools.app.enums.DatabaseType;
import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.dao.service.ConnectionConfigService;
import com.lzt.operate.codetools.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.codetools.entities.ConnectionConfig;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/connectionConfig")
@Api(tags = {"数据库连接"})
public class ConnectionConfigController extends BaseOperateAuthController {
	private static final String CONTROLLER_DESCRIPTION = "数据库连接/";

	private ConnectionConfigService connectionConfigService;

	@Autowired
	public ConnectionConfigController(CustomJsonWebTokenConfig customJsonWebTokenConfig, ConnectionConfigServiceImpl connectionConfigServiceImpl) {
		super(customJsonWebTokenConfig);

		this.connectionConfigService = connectionConfigServiceImpl;
	}

	private ConnectionConfigAssist getConnectionConfigAssist() {
		return new ConnectionConfigAssist(this.connectionConfigService);
	}

	@ApiOperation(value = "连接列表", notes = "创建数据库连接,如果链接有效则直接打开数据库获取数据表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_LIST, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParam(name = "connectionConfig", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_LIST)
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "连接列表", tag = "f201e035-bfcc-4eee-a263-70fdc2968e64", config = {"显示路径", "显示子权限"})
	public ResultListData list(@RequestBody Map<String, Serializable> query) {
		var paramJson = getParamData(query);

		var pageNo = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_LIST_PAGE_NO, "1").toInt();
		var pageSize = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		ConnectionConfig connectionConfig = new ConnectionConfig();

		ExampleMatcher matcher = ExampleMatcher.matching()
											   .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
											   .withIgnorePaths("createTime", "password", "friendlyName");

		Example<ConnectionConfig> filter = Example.of(connectionConfig, matcher);

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(ConnectionConfig::getCreateTime));

		Page<ConnectionConfig> result = this.connectionConfigService.page(filter, pageable);

		return this.pageData(result);
	}

	@ApiOperation(value = "获取连接", notes = "获取数据库连接", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_MODEL, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParam(name = "connectionJson", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_MODEL)
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "连接详情", tag = "6b0d1fbe-9e31-48ce-86ab-5dc1ebe387db")
	public BaseResultData get(@RequestBody Map<String, Serializable> connectionJson) {
		var paramJson = getParamData(connectionJson);

		long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		Optional<ConnectionConfig> result = getConnectionConfigAssist().getConnectionConfig(connectionConfigId);

		if (result.isPresent()) {
			return this.singleData(result.get());
		}

		return this.fail(ReturnDataCode.NoData);
	}

	@ApiOperation(value = "创建连接", notes = "创建数据库连接,如果链接有效则直接打开数据库获取数据表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_MODEL, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_NAME),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_DATABASE_TYPE),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_HOST),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SCHEMA),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_USERNAME),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_PASSWORD),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ENCODING),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_LOCAL_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_REMOTE_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_HOST),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_USER),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_PASSWORD)},
			result = @ApiJsonResult({}))
	@ApiImplicitParam(name = "connectionJson", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_MODEL)
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/addBasicInfo", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "新增连接", tag = "94520b18-bcb8-499c-90fd-afb82f45f3f0")
	public BaseResultData addBasicInfo(@RequestBody Map<String, Serializable> connectionJson) {
		var paramJson = getParamData(connectionJson);

		ExecutiveResult<ConnectionConfig> result = getConnectionConfigAssist().createConnectionConfig(paramJson);

		if (result.getSuccess()) {
			ConnectionConfig connectionConfig = result.getData();

			connectionConfig.setChannel(Channel.CodeTools.getFlag());
			connectionConfig.setChannelNote(Channel.CodeTools.getNote());

			long operatorId = getOperatorId();

			connectionConfig.setCreateOperatorId(operatorId);
			connectionConfig.setUpdateOperatorId(operatorId);

			return this.singleData(result.getData());
		}

		return this.fail(result);
	}

	@ApiOperation(value = "更新连接", notes = "更新数据库连接,如果链接有效则直接打开数据库获取数据表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_MODEL, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_NAME),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_DATABASE_TYPE),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_HOST),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SCHEMA),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_USERNAME),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_PASSWORD),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ENCODING),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_LOCAL_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_REMOTE_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_PORT),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_HOST),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_USER),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_SSH_PASSWORD)},
			result = @ApiJsonResult({}))
	@ApiImplicitParam(name = "connectionJson", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_MODEL)
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/updateBasicInfo", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "更新基本信息", tag = "3fab7782-4641-4e8b-832c-3996ddc61b3f")
	public BaseResultData updateBasicInfo(@RequestBody Map<String, Serializable> connectionJson) {
		var paramJson = getParamData(connectionJson);

		var connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_ID, "数据无效");
		}

		var name = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_NAME);

		if (name.isNullOrEmpty()) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_NAME, "不能为空值");
		}

		var connectionType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, ConnectionType.TCP_IP
				.getFlag().toString()).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(ConnectionType.values()), ConnectionType::getFlag, connectionType)) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值");
		}

		var databaseType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DATABASE_TYPE).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(DatabaseType.values()), DatabaseType::getFlag, databaseType)) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值");
		}

		ConnectionConfigAssist connectionConfigAssist = getConnectionConfigAssist();

		Optional<ConnectionConfig> result = connectionConfigAssist.getConnectionConfig(connectionConfigId);

		if (result.isPresent()) {
			ConnectionConfig connectionConfig = result.get();

			var host = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_HOST);
			var port = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_PORT);
			var schema = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SCHEMA);
			var username = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_USERNAME);
			var password = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_PASSWORD);
			var encoding = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ENCODING);
			var localPort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_LOCAL_PORT);
			var remotePort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_REMOTE_PORT);
			var sshPort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_PORT);
			var sshHost = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_HOST);
			var sshUser = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_USER);
			var sshPassword = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_PASSWORD);

			connectionConfig.setName(name.toString());
			connectionConfig.setConnectionType(connectionType);
			connectionConfig.setDatabaseType(databaseType);
			connectionConfig.setHost(host.toString());
			connectionConfig.setPort(port.toString());
			connectionConfig.setSchema(schema.toString());
			connectionConfig.setUsername(username.toString());
			connectionConfig.setPassword(password.toString());
			connectionConfig.setEncoding(encoding.toString());
			connectionConfig.setLocalPort(localPort.toString());
			connectionConfig.setRemotePort(remotePort.toString());
			connectionConfig.setSshPort(sshPort.toString());
			connectionConfig.setSshHost(sshHost.toString());
			connectionConfig.setSshUser(sshUser.toString());
			connectionConfig.setSshPassword(sshPassword.toString());

			ConnectionConfig saveResult = connectionConfigAssist.saveConnectionConfig(connectionConfig);

			return this.singleData(saveResult);
		}

		return this.fail(ReturnDataCode.NoData);

	}

	@ApiOperation(value = "更新连接", notes = "更新数据库连接,如果链接有效则直接打开数据库获取数据表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_MODEL, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParam(name = "connectionJson", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_MODEL)
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/remove", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "移除连接", tag = "17e57607-d519-4289-9b8a-949bbcff603e")
	public BaseResultData remove(@RequestBody Map<String, Serializable> connectionJson) {
		var paramJson = getParamData(connectionJson);

		var connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_ID, "数据无效");
		}

		getConnectionConfigAssist().deleteById(connectionConfigId);

		return this.success();
	}
}
