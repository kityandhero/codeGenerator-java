package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.common.enums.DatabaseEncoding;
import com.lzt.operate.code.generator.entities.bases.BaseEntity;

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

	@Column(name = "database_type", nullable = false)
	private int databaseType;

	@Column(name = "connection_type", nullable = false)
	private int connectionType;

	@Column(nullable = false)
	private String name;

	@Column(length = 300, nullable = false)
	private String description;

	@Column(nullable = false)
	private String host;

	@Column(nullable = false)
	private String port;

	@Column(nullable = false)
	private String schema;

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private int encoding;

	@Column(name = "local_port", nullable = false)
	private String localPort;

	@Column(name = "remote_port", nullable = false)
	private String remotePort;

	@Column(name = "ssh_port", nullable = false)
	private String sshPort;

	@Column(name = "ssh_host", nullable = false)
	private String sshHost;

	@Column(name = "ssh_user", nullable = false)
	private String sshUser;

	@Column(name = "ssh_password", nullable = false)
	private String sshPassword;

	public ConnectionConfig() {
		super();

		this.encoding = DatabaseEncoding.UTF8.getFlag();
	}

	public int getDatabaseType() {
		return this.databaseType;
	}

	public void setDatabaseType(int databaseType) {
		this.databaseType = databaseType;
	}

	public int getConnectionType() {
		return this.connectionType;
	}

	public void setConnectionType(int connectionType) {
		this.connectionType = connectionType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSchema() {
		return this.schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEncoding() {
		return this.encoding;
	}

	public void setEncoding(DatabaseEncoding databaseEncoding) {
		this.encoding = databaseEncoding.getFlag();
	}

	public String getLocalPort() {
		return this.localPort;
	}

	public void setLocalPort(String localPort) {
		this.localPort = localPort;
	}

	public String getRemotePort() {
		return this.remotePort;
	}

	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}

	public String getSshPort() {
		return this.sshPort;
	}

	public void setSshPort(String sshPort) {
		this.sshPort = sshPort;
	}

	public String getSshHost() {
		return this.sshHost;
	}

	public void setSshHost(String sshHost) {
		this.sshHost = sshHost;
	}

	public String getSshUser() {
		return this.sshUser;
	}

	public void setSshUser(String sshUser) {
		this.sshUser = sshUser;
	}

	public String getSshPassword() {
		return this.sshPassword;
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
				Objects.equals(this.userName, that.userName) &&
				Objects.equals(this.password, that.password) &&
				Objects.equals(this.encoding, that.encoding) &&
				Objects.equals(this.localPort, that.localPort) &&
				Objects.equals(this.remotePort, that.remotePort) &&
				Objects.equals(this.sshPort, that.sshPort) &&
				Objects.equals(this.sshHost, that.sshHost) &&
				Objects.equals(this.sshUser, that.sshUser) &&
				Objects.equals(this.sshPassword, that.sshPassword);
	}

	@Override
	public int hashCode() {
		int result = this.getDatabaseType();
		result = 31 * result + this.getConnectionType();
		result = 31 * result + (this.getName() != null ? this.getName().hashCode() : 0);
		result = 31 * result + (this.getDescription() != null ? this.getDescription().hashCode() : 0);
		result = 31 * result + (this.getHost() != null ? this.getHost().hashCode() : 0);
		result = 31 * result + (this.getPort() != null ? this.getPort().hashCode() : 0);
		result = 31 * result + (this.getSchema() != null ? this.getSchema().hashCode() : 0);
		result = 31 * result + (this.getUserName() != null ? this.getUserName().hashCode() : 0);
		result = 31 * result + (this.getPassword() != null ? this.getPassword().hashCode() : 0);
		result = 31 * result + this.getEncoding();
		result = 31 * result + (this.getLocalPort() != null ? this.getLocalPort().hashCode() : 0);
		result = 31 * result + (this.getRemotePort() != null ? this.getRemotePort().hashCode() : 0);
		result = 31 * result + (this.getSshPort() != null ? this.getSshPort().hashCode() : 0);
		result = 31 * result + (this.getSshHost() != null ? this.getSshHost().hashCode() : 0);
		result = 31 * result + (this.getSshUser() != null ? this.getSshUser().hashCode() : 0);
		result = 31 * result + (this.getSshPassword() != null ? this.getSshPassword().hashCode() : 0);
		return result;
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
