package com.lzt.operate.codetools.app.assists;

import com.lzt.operate.codetools.app.common.GlobalString;
import com.lzt.operate.codetools.app.enums.ConnectionType;
import com.lzt.operate.codetools.app.enums.DatabaseType;
import com.lzt.operate.codetools.dao.service.ConnectionConfigService;
import com.lzt.operate.codetools.entities.ConnectionConfig;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
import lombok.var;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class ConnectionConfigAssist {

	private ConnectionConfigService connectionConfigService;

	public ConnectionConfigAssist(ConnectionConfigService connectionConfigServiceImpl) {
		this.connectionConfigService = connectionConfigServiceImpl;
	}

	public ConnectionConfigService getConnectionConfigService() {
		Optional<ConnectionConfigService> optional = Optional.ofNullable(this.connectionConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("ConnectionConfigService获取失败");
	}

	/**
	 * getConnectionConfig
	 *
	 * @param connectionConfigId connectionConfigId
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	public ExecutiveResult<ConnectionConfig> getConnectionConfig(long connectionConfigId) {
		return getConnectionConfigService().get(connectionConfigId);
	}

	/**
	 * deleteById
	 *
	 * @param connectionConfigId connectionConfigId
	 * @return ExecutiveSimpleResult
	 */
	public ExecutiveSimpleResult deleteById(Long connectionConfigId) {
		if (connectionConfigId <= 0) {
			return new ExecutiveSimpleResult(ReturnDataCode.ParamError, "标识无效");
		}

		return this.getConnectionConfigService().deleteById(connectionConfigId);
	}

	/**
	 * createConnectionConfig
	 *
	 * @param paramJson paramJson
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	public ExecutiveResult<ConnectionConfig> createConnectionConfig(ParamData paramJson) {
		var name = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_NAME);

		if (name.isNullOrEmpty()) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_NAME, "不能为空值"));
		}

		var connectionType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, ConnectionType.TCP_IP
				.getFlag().toString()).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(ConnectionType.values()), ConnectionType::getFlag, connectionType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

		var databaseType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DATABASE_TYPE).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(DatabaseType.values()), DatabaseType::getFlag, databaseType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

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

		var connectionConfig = new ConnectionConfig();

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

		return new ExecutiveResult<>(ReturnDataCode.Ok, connectionConfig);
	}

	/**
	 * addConnectionConfig
	 *
	 * @param paramJson paramJson
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	public ExecutiveResult<ConnectionConfig> addConnectionConfig(ParamData paramJson) {
		ExecutiveResult<ConnectionConfig> createResult = createConnectionConfig(paramJson);

		if (createResult.getSuccess()) {
			return saveConnectionConfig(createResult.getData());
		}

		return createResult;
	}

	/**
	 * updateConnectionConfig
	 *
	 * @param connectionConfig connectionConfig
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	public ExecutiveResult<ConnectionConfig> saveConnectionConfig(@NonNull ConnectionConfig connectionConfig) {
		return getConnectionConfigService().save(connectionConfig);
	}

	/**
	 * fillFromParamJson
	 *
	 * @param connectionConfig connectionConfig
	 * @param paramJson        paramJson
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	public ExecutiveResult<ConnectionConfig> fillFromParamJson(@NonNull ConnectionConfig connectionConfig, ParamData paramJson) {
		var name = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_NAME);

		if (name.isNullOrEmpty()) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_NAME, "不能为空值"));
		}

		var connectionType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, ConnectionType.TCP_IP
				.getFlag().toString()).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(ConnectionType.values()), ConnectionType::getFlag, connectionType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

		var databaseType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DATABASE_TYPE).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(DatabaseType.values()), DatabaseType::getFlag, databaseType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

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

		return new ExecutiveResult<>(ReturnDataCode.Ok, connectionConfig);
	}
}
