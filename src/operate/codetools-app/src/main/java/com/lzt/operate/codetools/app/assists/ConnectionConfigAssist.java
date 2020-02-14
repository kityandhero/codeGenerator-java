package com.lzt.operate.codetools.app.assists;

import com.lzt.operate.codetools.app.common.GlobalString;
import com.lzt.operate.codetools.dao.service.ConnectionConfigService;
import com.lzt.operate.codetools.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.codetools.entities.ConnectionConfig;
import com.lzt.operate.utility.pojo.ParamData;
import lombok.var;

import java.util.Optional;

/**
 * @author luzhitao
 */
public class ConnectionConfigAssist {

	private ConnectionConfigService connectionConfigServiceImpl;

	public ConnectionConfigAssist(ConnectionConfigServiceImpl connectionConfigServiceImpl) {
		this.connectionConfigServiceImpl = connectionConfigServiceImpl;
	}

	public ConnectionConfig fillFromParamJson(ConnectionConfig connectionConfig, ParamData paramJson) {
		var name = paramJson.getStringExByKey(GlobalString.CONNECTION_NAME);
		var dbtype = paramJson.getStringExByKey(GlobalString.CONNECTION_DB_TYPE);
		var host = paramJson.getStringExByKey(GlobalString.CONNECTION_HOST);
		var port = paramJson.getStringExByKey(GlobalString.CONNECTION_PORT);
		var schema = paramJson.getStringExByKey(GlobalString.CONNECTION_SCHEMA);
		var username = paramJson.getStringExByKey(GlobalString.CONNECTION_USERNAME);
		var password = paramJson.getStringExByKey(GlobalString.CONNECTION_PASSWORD);
		var encoding = paramJson.getStringExByKey(GlobalString.CONNECTION_ENCODING);
		var lPort = paramJson.getStringExByKey(GlobalString.CONNECTION_L_PORT);
		var rProt = paramJson.getStringExByKey(GlobalString.CONNECTION_R_PORT);
		var sshPort = paramJson.getStringExByKey(GlobalString.CONNECTION_SSH_PORT);
		var sshHost = paramJson.getStringExByKey(GlobalString.CONNECTION_SSH_HOST);
		var sshUser = paramJson.getStringExByKey(GlobalString.CONNECTION_SSH_USER);
		var sshPassword = paramJson.getStringExByKey(GlobalString.CONNECTION_SSH_PASSWORD);

		ConnectionConfig c = Optional.of(connectionConfig).orElse(new ConnectionConfig());

		c.setName(name.toString());
		c.setDbType(dbtype.toString());
		c.setHost(host.toString());
		c.setPort(port.toString());
		c.setSchema(schema.toString());
		c.setUsername(username.toString());
		c.setPassword(password.toString());
		c.setEncoding(encoding.toString());
		c.setLport(lPort.toString());
		c.setRport(rProt.toString());
		c.setSshPort(sshPort.toString());
		c.setSshHost(sshHost.toString());
		c.setSshUser(sshUser.toString());
		c.setSshPassword(sshPassword.toString());

		return c;
	}
}
