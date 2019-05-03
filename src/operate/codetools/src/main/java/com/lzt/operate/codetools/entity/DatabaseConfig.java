package com.lzt.operate.codetools.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author lzt
 * Created by Owen on 5/13/16.
 */
@Getter
@Setter
public class DatabaseConfig {

    /**
     * The primary key in the sqlite db
     */
    private Integer id;

    private String dbType;
    /**
     * The name of the config
     */
    private String name;

    private String host;

    private String port;

    private String schema;

    private String username;

    private String password;

    private String encoding;

    private String lport;

    private String rport;

    private String sshPort;

    private String sshHost;

    private String sshUser;

    private String sshPassword;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        DatabaseConfig that = (DatabaseConfig) o;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.dbType, that.dbType) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.host, that.host) &&
                Objects.equals(this.port, that.port) &&
                Objects.equals(this.schema, that.schema) &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.encoding, that.encoding) &&
                Objects.equals(this.lport, that.lport) &&
                Objects.equals(this.rport, that.rport) &&
                Objects.equals(this.sshPort, that.sshPort) &&
                Objects.equals(this.sshHost, that.sshHost) &&
                Objects.equals(this.sshUser, that.sshUser) &&
                Objects.equals(this.sshPassword, that.sshPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.dbType, this.name, this.host, this.port, this.schema, this.username, this.password, this.encoding, this.lport, this.rport, this.sshPort, this.sshHost, this.sshUser, this.sshPassword);
    }

    @Override
    public String toString() {
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
