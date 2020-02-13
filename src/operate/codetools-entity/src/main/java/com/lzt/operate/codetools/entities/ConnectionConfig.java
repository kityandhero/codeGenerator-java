package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据库连接配置
 *
 * @author luzhitao
 * @date 2019-11-19
 */
@Data
@Entity
@Table(name = "connection_config")
@EqualsAndHashCode(callSuper = true)
public class ConnectionConfig extends BaseEntity {

	private static final long serialVersionUID = -3628391768513027313L;

	@Column
	private String dbType;

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

	@Column
	private String lport;

	@Column
	private String rport;

	@Column
	private String sshPort;

	@Column
	private String sshHost;

	@Column
	private String sshUser;

	@Column
	private String sshPassword;

	public ConnectionConfig() {

	}

	// @Override
	// public boolean equals(Object o) {
	//     if (this == o) {
	//         return true;
	//     }
	//     if (o == null || this.getClass() != o.getClass()) {
	//         return false;
	//     }
	//     ConnectionConfig that = (ConnectionConfig) o;
	//     return Objects.equals(this.id, that.id) &&
	//             Objects.equals(this.dbType, that.dbType) &&
	//             Objects.equals(this.name, that.name) &&
	//             Objects.equals(this.host, that.host) &&
	//             Objects.equals(this.port, that.port) &&
	//             Objects.equals(this.schema, that.schema) &&
	//             Objects.equals(this.username, that.username) &&
	//             Objects.equals(this.password, that.password) &&
	//             Objects.equals(this.encoding, that.encoding) &&
	//             Objects.equals(this.lport, that.lport) &&
	//             Objects.equals(this.rport, that.rport) &&
	//             Objects.equals(this.sshPort, that.sshPort) &&
	//             Objects.equals(this.sshHost, that.sshHost) &&
	//             Objects.equals(this.sshUser, that.sshUser) &&
	//             Objects.equals(this.sshPassword, that.sshPassword);
	// }

	public String build() {
		return "DatabaseConfig{" +
				"id=" + this.getId() +
				", dbType='" + this.dbType + '\'' +
				", name='" + this.name + '\'' +
				", host='" + this.host + '\'' +
				", port='" + this.port + '\'' +
				", schema='" + this.schema + '\'' +
				", username='" + this.username + '\'' +
				", password='" + this.password + '\'' +
				", encoding='" + this.encoding + '\'' +
				", lport='" + this.lport + '\'' +
				", rport='" + this.rport + '\'' +
				", sshPort='" + this.sshPort + '\'' +
				", sshHost='" + this.sshHost + '\'' +
				", sshUser='" + this.sshUser + '\'' +
				", sshPassword='" + this.sshPassword + '\'' +
				'}';
	}

}
