package com.lzt.operate.codetools.domain;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.entity.ParamData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author lzt
 */
@Data
@Entity
@Table(name = "connectionConfig")
@EqualsAndHashCode(callSuper = true)
public class ConnectionConfig extends BaseDomain {

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

    public void fillFromParamJson(ParamData paramJson) {
        var dbtype = paramJson.getByKey(GlobalString.CONNECTION_DB_TYPE);
        var host = paramJson.getByKey(GlobalString.CONNECTION_HOST);
        var port = paramJson.getByKey(GlobalString.CONNECTION_PORT);
        var schema = paramJson.getByKey(GlobalString.CONNECTION_SCHEMA);
        var username = paramJson.getByKey(GlobalString.CONNECTION_USERNAME);
        var password = paramJson.getByKey(GlobalString.CONNECTION_PASSWORD);
        var encoding = paramJson.getByKey(GlobalString.CONNECTION_ENCODING);
        var lPort = paramJson.getByKey(GlobalString.CONNECTION_L_PORT);
        var rProt = paramJson.getByKey(GlobalString.CONNECTION_R_PORT);
        var sshPort = paramJson.getByKey(GlobalString.CONNECTION_SSH_PORT);
        var sshHost = paramJson.getByKey(GlobalString.CONNECTION_SSH_HOST);
        var sshUser = paramJson.getByKey(GlobalString.CONNECTION_SSH_USER);
        var sshPassword = paramJson.getByKey(GlobalString.CONNECTION_SSH_PASSWORD);

        this.setName(name);
        this.setDbType(dbtype);
        this.setHost(host);
        this.setPort(port);
        this.setSchema(schema);
        this.setUsername(username);
        this.setPassword(password);
        this.setEncoding(encoding);
        this.setLport(lPort);
        this.setRport(rProt);
        this.setSshPort(sshPort);
        this.setSshHost(sshHost);
        this.setSshUser(sshUser);
        this.setSshPassword(sshPassword);
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
                "id=" + this.id +
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
