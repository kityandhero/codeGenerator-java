package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * 数据库连接配置
 *
 * @author luzhitao
 * @date 2019-11-19
 */
@Entity
@Table(name = "connection_config")
public class ConnectionConfig extends BaseEntity {

	private static final long serialVersionUID = -3628391768513027313L;

	@Column(name = "database_type")
	private int databaseType;

	@Column(name = "connection_type")
	private int connectionType;

	@Column
	private String name;

	@Column
	private String host;

	@Column
	private String port;

	@Column
	private String schema;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String encoding;

	@Column(name = "local_port")
	private String localPort;

	@Column(name = "remote_port")
	private String remotePort;

	@Column(name = "ssh_port")
	private String sshPort;

	@Column(name = "ssh_host")
	private String sshHost;

	@Column(name = "ssh_user")
	private String sshUser;

	@Column(name = "ssh_password")
	private String sshPassword;

	public ConnectionConfig() {
		super();
	}

	public int getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(int databaseType) {
		this.databaseType = databaseType;
	}

	public int getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(int connectionType) {
		this.connectionType = connectionType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getLocalPort() {
		return localPort;
	}

	public void setLocalPort(String localPort) {
		this.localPort = localPort;
	}

	public String getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}

	public String getSshPort() {
		return sshPort;
	}

	public void setSshPort(String sshPort) {
		this.sshPort = sshPort;
	}

	public String getSshHost() {
		return sshHost;
	}

	public void setSshHost(String sshHost) {
		this.sshHost = sshHost;
	}

	public String getSshUser() {
		return sshUser;
	}

	public void setSshUser(String sshUser) {
		this.sshUser = sshUser;
	}

	public String getSshPassword() {
		return sshPassword;
	}

	public void setSshPassword(String sshPassword) {
		this.sshPassword = sshPassword;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		ConnectionConfig that = (ConnectionConfig) o;
		return Objects.equals(this.getId(), that.getId()) &&
				Objects.equals(this.connectionType, that.connectionType) &&
				Objects.equals(this.databaseType, that.databaseType) &&
				Objects.equals(this.name, that.name) &&
				Objects.equals(this.host, that.host) &&
				Objects.equals(this.port, that.port) &&
				Objects.equals(this.schema, that.schema) &&
				Objects.equals(this.username, that.username) &&
				Objects.equals(this.password, that.password) &&
				Objects.equals(this.encoding, that.encoding) &&
				Objects.equals(this.localPort, that.localPort) &&
				Objects.equals(this.remotePort, that.remotePort) &&
				Objects.equals(this.sshPort, that.sshPort) &&
				Objects.equals(this.sshHost, that.sshHost) &&
				Objects.equals(this.sshUser, that.sshUser) &&
				Objects.equals(this.sshPassword, that.sshPassword);
	}

	// public String build() {
	// 	return "DatabaseConfig{" +
	// 			"id=" + this.getId() +
	// 			", dbType='" + this.databaseType + '\'' +
	// 			", name='" + this.name + '\'' +
	// 			", host='" + this.host + '\'' +
	// 			", port='" + this.port + '\'' +
	// 			", schema='" + this.schema + '\'' +
	// 			", username='" + this.username + '\'' +
	// 			", password='" + this.password + '\'' +
	// 			", encoding='" + this.encoding + '\'' +
	// 			", lport='" + this.localPort + '\'' +
	// 			", rport='" + this.remotePort + '\'' +
	// 			", sshPort='" + this.sshPort + '\'' +
	// 			", sshHost='" + this.sshHost + '\'' +
	// 			", sshUser='" + this.sshUser + '\'' +
	// 			", sshPassword='" + this.sshPassword + '\'' +
	// 			'}';
	// }

}
