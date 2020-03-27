package com.lzt.operate.code.generator.common.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 * Created by Owen on 6/14/16.
 */
public enum DatabaseType {

	/**
	 * MySQL
	 */
	MySQL(100, "MySQL", "MySQL", "com.mysql.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=%s", "mysql-connector-java-5.1.47.jar"),

	/**
	 * MySQL_8
	 */
	MySQL_8(101, "MySQL_8", "MySQL_8", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=%s", "mysql-connector-java-8.0.19.jar"),

	/**
	 * Oracle
	 */
	Oracle(102, "Oracle", "Oracle", "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@//%s:%s/%s", "ojdbc6.jar"),

	/**
	 * PostgreSQL
	 */
	PostgreSQL(103, "PostgreSQL", "PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://%s:%s/%s", "postgresql-42.2.10.jar"),

	/**
	 * SQL_Server
	 */
	SQL_Server(104, "SQL_Server", "SQL_Server", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s", "sqljdbc42.jar"),

	/**
	 * Sqlite
	 */
	Sqlite(105, "Sqlite", "Sqlite", "org.sqlite.JDBC", "jdbc:sqlite:%s", "sqlite-jdbc-3.30.1.jar");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "databaseDatabaseTypeList";

	private final Integer flag;
	private final String name;
	private final String description;
	private final String driverClass;
	private final String connectionUrlPattern;
	private final String connectorJarFile;

	DatabaseType(int flag, String name, String description, String driverClass, String connectionUrlPattern, String connectorJarFile) {
		this.flag = flag;
		this.name = name;
		this.description = description;
		this.driverClass = driverClass;
		this.connectionUrlPattern = connectionUrlPattern;
		this.connectorJarFile = connectorJarFile;

	}

	public static Optional<DatabaseType> valueOfFlag(@NonNull Integer flag) {
		DatabaseType[] values = DatabaseType.values();

		for (DatabaseType d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<DatabaseType> valuesToList() {
		return Arrays.asList(DatabaseType.values());
	}

	public Integer getFlag() {
		return flag;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getConnectionUrlPattern() {
		return connectionUrlPattern;
	}

	public String getConnectorJarFile() {
		return connectorJarFile;
	}

}