package com.lzt.operate.codetools.app.assists;

import com.lzt.operate.codetools.app.enums.ConnectionType;
import com.lzt.operate.codetools.app.enums.DatabaseType;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.dao.assists.BaseConnectionConfigAssist;
import com.lzt.operate.codetools.dao.service.ConnectionConfigService;
import com.lzt.operate.codetools.entities.ConnectionConfig;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.extensions.StringEx;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import org.springframework.lang.NonNull;

import java.util.Arrays;

/**
 * @author luzhitao
 */
public class ConnectionConfigAssist extends BaseConnectionConfigAssist {

	public ConnectionConfigAssist(ConnectionConfigService connectionConfigService) {
		super(connectionConfigService);
	}

	/**
	 * createConnectionConfig
	 *
	 * @param paramJson paramJson
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	@Override
	public ExecutiveResult<ConnectionConfig> createConnectionConfig(ParamData paramJson) {
		StringEx name = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_NAME);

		if (name.isNullOrEmpty()) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_NAME, "不能为空值"));
		}

		int connectionType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, ConnectionType.TCP_IP
				.getFlag().toString()).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(ConnectionType.values()), ConnectionType::getFlag, connectionType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

		int databaseType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DATABASE_TYPE).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(DatabaseType.values()), DatabaseType::getFlag, databaseType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

		StringEx description = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DESCRIPTION);
		StringEx host = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_HOST);
		StringEx port = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_PORT);
		StringEx schema = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SCHEMA);
		StringEx username = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_USERNAME);
		StringEx password = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_PASSWORD);
		StringEx encoding = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ENCODING);
		StringEx localPort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_LOCAL_PORT);
		StringEx remotePort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_REMOTE_PORT);
		StringEx sshPort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_PORT);
		StringEx sshHost = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_HOST);
		StringEx sshUser = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_USER);
		StringEx sshPassword = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_PASSWORD);

		ConnectionConfig connectionConfig = new ConnectionConfig();

		connectionConfig.setName(name.toString());
		connectionConfig.setDescription(description.toString());
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
	 * fillFromParamJson
	 *
	 * @param connectionConfig connectionConfig
	 * @param paramJson        paramJson
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	@Override
	public ExecutiveResult<ConnectionConfig> fillFromParamJson(@NonNull ConnectionConfig connectionConfig, ParamData paramJson) {
		StringEx name = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_NAME);

		if (name.isNullOrEmpty()) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_NAME, "不能为空值"));
		}

		int connectionType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, ConnectionType.TCP_IP
				.getFlag().toString()).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(ConnectionType.values()), ConnectionType::getFlag, connectionType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

		int databaseType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DATABASE_TYPE).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(DatabaseType.values()), DatabaseType::getFlag, databaseType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

		StringEx host = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_HOST);
		StringEx port = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_PORT);
		StringEx schema = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SCHEMA);
		StringEx username = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_USERNAME);
		StringEx password = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_PASSWORD);
		StringEx encoding = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ENCODING);
		StringEx localPort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_LOCAL_PORT);
		StringEx remotePort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_REMOTE_PORT);
		StringEx sshPort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_PORT);
		StringEx sshHost = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_HOST);
		StringEx sshUser = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_USER);
		StringEx sshPassword = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_PASSWORD);

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
