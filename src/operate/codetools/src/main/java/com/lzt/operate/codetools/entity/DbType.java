package com.lzt.operate.codetools.entity;


import lombok.Getter;

/**
 * @author lzt
 * Created by Owen on 6/14/16.
 */
@Getter
public enum DbType {

    /**
     * MySQL
     */
    MySQL("com.mysql.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=%s", "mysql-connector-java-5.1.38.jar"),

    /**
     * MySQL_8
     */
    MySQL_8("com.mysql.cj.jdbc.Driver", "jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=%s", "mysql-connector-java-8.0.11.jar"),

    /**
     * Oracle
     */
    Oracle("oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@//%s:%s/%s", "ojdbc6.jar"),

    /**
     * PostgreSQL
     */
    PostgreSQL("org.postgresql.Driver", "jdbc:postgresql://%s:%s/%s", "postgresql-9.4.1209.jar"),

    /**
     * SQL_Server
     */
    SQL_Server("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s", "sqljdbc4-4.0.jar"),

    /**
     * Sqlite
     */
    Sqlite("org.sqlite.JDBC", "jdbc:sqlite:%s", "sqlite-jdbc-3.19.3.jar");

    private final String driverClass;
    private final String connectionUrlPattern;
    private final String connectorJarFile;

    DbType(String driverClass, String connectionUrlPattern, String connectorJarFile) {
        this.driverClass = driverClass;
        this.connectionUrlPattern = connectionUrlPattern;
        this.connectorJarFile = connectorJarFile;
    }
}