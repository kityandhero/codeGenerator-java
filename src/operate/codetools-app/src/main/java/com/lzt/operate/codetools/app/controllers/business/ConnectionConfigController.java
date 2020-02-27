package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.assists.ConnectionConfigAssist;
import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.app.enums.ConnectionType;
import com.lzt.operate.codetools.app.enums.DatabaseType;
import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.ConnectionConfigService;
import com.lzt.operate.codetools.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.codetools.entities.ConnectionConfig;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.SerializableData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@ApiOperation(value = "连接列表", notes = "数据库连接列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_LIST, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_NAME),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "连接列表", description = "数据库连接列表", tag = "f201e035-bfcc-4eee-a263-70fdc2968e64", config = {"显示路径", "显示子权限"})
	public ResultListData list(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		String name = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_NAME);

		Specification<ConnectionConfig> specification = new Specification<ConnectionConfig>() {

			private static final long serialVersionUID = 8024589834916855002L;

			@Override
			public Predicate toPredicate(@NonNull Root<ConnectionConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!StringAssist.isNullOrEmpty(name)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(ConnectionConfig::getName)), name));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(ConnectionConfig::getCreateTime));

		Page<ConnectionConfig> result = this.connectionConfigService.page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<ConnectionConfig>> getterList = new ArrayList<>();

												getterList.add(ConnectionConfig::getName);
												getterList.add(ConnectionConfig::getDescription);
												getterList.add(ConnectionConfig::getChannel);
												getterList.add(ConnectionConfig::getChannelNote);
												getterList.add(ConnectionConfig::getStatus);
												getterList.add(ConnectionConfig::getStatusNote);
												getterList.add(ConnectionConfig::getCreateTime);
												getterList.add(ConnectionConfig::getUpdateTime);

												SerializableData data = SerializableData.toSerializableData(o, getterList);
												data.append(ReflectAssist.getFriendlyIdName(ConnectionConfig.class), o.getId());

												return data;
											})
											.collect(Collectors.toList());

		int pageIndex = result.getNumber();
		long totalPages = result.getTotalPages();

		return this.pageData(list, pageIndex, pageSize, totalPages);
	}

	@ApiOperation(value = "获取连接", notes = "获取数据库连接", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_GET, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "连接详情", description = "获取数据库连接", tag = "6b0d1fbe-9e31-48ce-86ab-5dc1ebe387db")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		Optional<ConnectionConfig> result = getConnectionConfigAssist().getConnectionConfig(connectionConfigId);

		if (result.isPresent()) {
			ConnectionConfig connectionConfig = result.get();

			List<IGetter<ConnectionConfig>> getterList = new ArrayList<>();

			getterList.add(ConnectionConfig::getName);
			getterList.add(ConnectionConfig::getDescription);
			getterList.add(ConnectionConfig::getChannel);
			getterList.add(ConnectionConfig::getChannelNote);
			getterList.add(ConnectionConfig::getStatus);
			getterList.add(ConnectionConfig::getStatusNote);
			getterList.add(ConnectionConfig::getCreateTime);
			getterList.add(ConnectionConfig::getUpdateTime);

			SerializableData data = SerializableData.toSerializableData(connectionConfig, getterList);
			data.append(ReflectAssist.getFriendlyIdName(ConnectionConfig.class), connectionConfig.getId());

			return this.singleData(data);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

	@ApiOperation(value = "创建连接", notes = "创建数据库连接", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_ADD_BASIC_INFO, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_NAME),
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_DESCRIPTION),
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
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_ADD_BASIC_INFO)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/addBasicInfo", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "新增连接", description = "创建数据库连接", tag = "94520b18-bcb8-499c-90fd-afb82f45f3f0")
	public BaseResultData addBasicInfo(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		ExecutiveResult<ConnectionConfig> result = getConnectionConfigAssist().createConnectionConfig(paramJson);

		if (result.getSuccess()) {
			ConnectionConfig data = result.getData();

			data.setChannel(Channel.CodeTools);

			long operatorId = getOperatorId();

			data.setCreateOperatorId(operatorId);
			data.setUpdateOperatorId(operatorId);

			getConnectionConfigAssist().saveConnectionConfig(data);

			return this.singleData(result.getData());
		}

		return this.fail(result);
	}

	@SuppressWarnings("DuplicatedCode")
	@ApiOperation(value = "更新连接", notes = "更新数据库连接", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_UPDATE_BASIC_INFO, value = {
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
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_UPDATE_BASIC_INFO)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/updateBasicInfo", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "更新基本信息", description = "更新数据库连接", tag = "3fab7782-4641-4e8b-832c-3996ddc61b3f")
	public BaseResultData updateBasicInfo(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_ID, "数据无效");
		}

		String name = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_NAME);

		if (StringAssist.isNullOrEmpty(name)) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_NAME, "不能为空值");
		}

		int connectionType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, ConnectionType.TCP_IP
				.getFlag().toString()).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(ConnectionType.values()), ConnectionType::getFlag, connectionType)) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值");
		}

		int databaseType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DATABASE_TYPE).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(DatabaseType.values()), DatabaseType::getFlag, databaseType)) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值");
		}

		ConnectionConfigAssist connectionConfigAssist = getConnectionConfigAssist();

		Optional<ConnectionConfig> result = connectionConfigAssist.getConnectionConfig(connectionConfigId);

		if (result.isPresent()) {
			ConnectionConfig data = result.get();

			String description = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_DESCRIPTION);
			String host = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_HOST);
			String port = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_PORT);
			String schema = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_SCHEMA);
			String username = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_USERNAME);
			String password = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_PASSWORD);
			String encoding = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_ENCODING);
			String localPort = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_LOCAL_PORT);
			String remotePort = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_REMOTE_PORT);
			String sshPort = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_SSH_PORT);
			String sshHost = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_SSH_HOST);
			String sshUser = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_SSH_USER);
			String sshPassword = paramJson.getStringByKey(GlobalString.CONNECTION_CONFIG_SSH_PASSWORD);

			data.setName(name);
			data.setDescription(description);
			data.setConnectionType(connectionType);
			data.setDatabaseType(databaseType);
			data.setHost(host);
			data.setPort(port);
			data.setSchema(schema);
			data.setUsername(username);
			data.setPassword(password);
			data.setEncoding(encoding);
			data.setLocalPort(localPort);
			data.setRemotePort(remotePort);
			data.setSshPort(sshPort);
			data.setSshHost(sshHost);
			data.setSshUser(sshUser);
			data.setSshPassword(sshPassword);

			long operatorId = getOperatorId();

			data.setUpdateOperatorId(operatorId);

			ConnectionConfig saveResult = connectionConfigAssist.saveConnectionConfig(data);

			return this.singleData(saveResult);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());

	}

	@ApiOperation(value = "移除连接", notes = "移除数据库连接", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CONNECTION_CONFIG_REMOVE, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CONNECTION_CONFIG_REMOVE)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/remove", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "移除连接", description = "移除数据库连接", tag = "17e57607-d519-4289-9b8a-949bbcff603e")
	public BaseResultData remove(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.paramError(GlobalString.CONNECTION_CONFIG_ID, "数据无效");
		}

		getConnectionConfigAssist().deleteById(connectionConfigId);

		return this.success();
	}

}
