package com.lzt.operate.code.generator.app.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.lzt.operate.code.generator.app.exceptions.DbDriverLoadingException;
import com.lzt.operate.code.generator.common.enums.DatabaseEncoding;
import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.code.generator.common.pojos.DataTable;
import com.lzt.operate.code.generator.custommessagequeue.generallog.GeneralLogAssist;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataColumn;
import com.lzt.operate.utility.assists.StringAssist;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.mybatis.generator.internal.util.ClassloaderUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Owen on 6/12/16.
 */
public class DatabaseTypeUtil {

	private static final Logger _LOG = LoggerFactory.getLogger(DatabaseTypeUtil.class);
	private static final int DB_CONNECTION_TIMEOUTS_SECONDS = 1;

	private static final Map<DatabaseType, Driver> drivers = new HashMap<>();

	private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
	private static volatile boolean portForwaring = false;
	private static final Map<Integer, Session> portForwardingSession = new ConcurrentHashMap<>();

	private static Session getSSHSession(ConnectionConfig connectionConfig) {
		if (StringUtils.isBlank(connectionConfig.getSshHost())
				|| StringUtils.isBlank(connectionConfig.getSshPort())
				|| StringUtils.isBlank(connectionConfig.getSshUser())
				|| StringUtils.isBlank(connectionConfig.getSshPassword())
		) {
			return null;
		}
		Session session = null;
		try {
			//Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Integer sshPort = NumberUtils.createInteger(connectionConfig.getSshPort());
			int port = sshPort == null ? 22 : sshPort;
			session = jsch.getSession(connectionConfig.getSshUser(), connectionConfig.getSshHost(), port);
			session.setPassword(connectionConfig.getSshPassword());
			session.setConfig(config);
		} catch (JSchException e) {
			//Ignore
		}
		return session;
	}

	private static void engagePortForwarding(Session sshSession, ConnectionConfig config) {
		if (sshSession != null) {
			AtomicInteger assinged_port = new AtomicInteger();
			Future<?> result = DatabaseTypeUtil.executorService.submit(() -> {
				try {
					Integer localPort = NumberUtils.createInteger(config.getLocalPort());
					Integer RemotePort;
					RemotePort = NumberUtils.createInteger(config.getRemotePort());
					int lport = localPort != null ? localPort : Integer.parseInt(config.getPort());
					int rport;
					if (RemotePort == null) {
						rport = Integer.parseInt(config.getPort());
					} else {
						rport = RemotePort;
					}
					Session session = DatabaseTypeUtil.portForwardingSession.get(lport);
					if (session != null && session.isConnected()) {
						String s = session.getPortForwardingL()[0];
						String[] split = StringUtils.split(s, ":");
						boolean portForwarding = String.format("%s:%s", split[0], split[1])
													   .equals(lport + ":" + config.getHost());
						if (portForwarding) {
							return;
						}
					}
					sshSession.connect();
					assinged_port.set(sshSession.setPortForwardingL(lport, config.getHost(), rport));
					DatabaseTypeUtil.portForwardingSession.put(lport, sshSession);
					DatabaseTypeUtil.portForwaring = true;
					DatabaseTypeUtil._LOG.info("portForwarding Enabled, {}", assinged_port);
				} catch (JSchException e) {
					DatabaseTypeUtil._LOG.error("Connect Over SSH failed", e);
					final String a = "Address already in use: JVM_Bind";

					if (e.getCause() != null && a.equals(e.getCause().getMessage())) {
						throw new RuntimeException("Address already in use: JVM_Bind");
					}
					throw new RuntimeException(e.getMessage());
				}
			});
			try {
				result.get(5, TimeUnit.SECONDS);
			} catch (Exception e) {
				DatabaseTypeUtil.shutdownPortForwarding(sshSession);
				if (e.getCause() instanceof RuntimeException) {
					throw (RuntimeException) e.getCause();
				}
				if (e instanceof TimeoutException) {
					throw new RuntimeException("OverSSH 连接超时：超过5秒");
				}

				DatabaseTypeUtil._LOG.info("executorService isShutdown:{}", DatabaseTypeUtil.executorService.isShutdown());
				// AlertUtil.showErrorAlert("OverSSH 失败，请检查连接设置:" + e.getMessage());
				throw new RuntimeException("OverSSH 失败，请检查连接设置:" + e.getMessage());
			}
		}
	}

	private static void shutdownPortForwarding(Session session) {
		DatabaseTypeUtil.portForwaring = false;
		if (session != null && session.isConnected()) {
			session.disconnect();
			DatabaseTypeUtil._LOG.info("portForwarding turn OFF");
		}
//		executorService.shutdown();
	}

	private static Connection getConnection(ConnectionConfig config) throws SQLException {
		Optional<DatabaseType> optionalDatabaseType = DatabaseType.valueOfFlag(config.getDatabaseType());

		if (!optionalDatabaseType.isPresent()) {
			throw new SQLException("连接字符串无效");
		}

		DatabaseType dbType = optionalDatabaseType.get();

		if (DatabaseTypeUtil.drivers.get(dbType) == null) {
			DatabaseTypeUtil.loadDbDriver(dbType);
		}

		String url = DatabaseTypeUtil.getConnectionUrlWithSchema(config);
		Properties props = new Properties();

		// $NON-NLS-1$
		props.setProperty("user", config.getUserName());

		//$NON-NLS-1$
		props.setProperty("password", config.getPassword());

		DriverManager.setLoginTimeout(DatabaseTypeUtil.DB_CONNECTION_TIMEOUTS_SECONDS);
		Connection connection = DatabaseTypeUtil.drivers.get(dbType).connect(url, props);
		DatabaseTypeUtil._LOG.info("getConnection, connection url: {}", connection);
		return connection;
	}

	public static List<DataTable> getTableNames(ConnectionConfig config) throws Exception {
		Session sshSession = DatabaseTypeUtil.getSSHSession(config);
		DatabaseTypeUtil.engagePortForwarding(sshSession, config);
		try (Connection connection = DatabaseTypeUtil.getConnection(config)) {
			List<DataTable> tables = new ArrayList<>();
			DatabaseMetaData md = connection.getMetaData();
			ResultSet rs;
			if (config.getDatabaseType() == DatabaseType.SQL_Server.getFlag()) {
				final String sql = "select name from sysobjects  where xtype='u' or xtype='v' order by name";
				rs = connection.createStatement().executeQuery(sql);
				while (rs.next()) {
					tables.add(new DataTable(rs.getString("name")));
				}
			} else if (config.getDatabaseType() == DatabaseType.Oracle.getFlag()) {
				rs = md.getTables(null, config.getUserName()
											  .toUpperCase(), null, new String[]{"TABLE", "VIEW"});
			} else if (config.getDatabaseType() == DatabaseType.Sqlite.getFlag()) {
				final String sql = "Select name from sqlite_master;";
				rs = connection.createStatement().executeQuery(sql);
				while (rs.next()) {
					tables.add(new DataTable(rs.getString("name")));
				}
			} else {
				// rs = md.getTables(null, config.getUsername().toUpperCase(), null, null);

				rs = md.getTables(config.getSchema(), null, "%", new String[]{"TABLE", "VIEW"});            //针对 postgresql 的左侧数据表显示
			}
			while (rs.next()) {
				tables.add(new DataTable(rs.getString(3)));
			}
			return tables;
		} finally {
			DatabaseTypeUtil.shutdownPortForwarding(sshSession);
		}
	}

	public static List<DataColumn> getTableColumns(ConnectionConfig dbConfig, String tableName) throws Exception {
		String url = DatabaseTypeUtil.getConnectionUrlWithSchema(dbConfig);
		DatabaseTypeUtil._LOG.info("getTableColumns, connection url: {}", url);
		Session sshSession = DatabaseTypeUtil.getSSHSession(dbConfig);
		DatabaseTypeUtil.engagePortForwarding(sshSession, dbConfig);
		try (Connection conn = DatabaseTypeUtil.getConnection(dbConfig)) {
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getColumns(dbConfig.getSchema(), null, tableName, null);
			List<DataColumn> columns = new ArrayList<>();
			while (rs.next()) {

				String name = rs.getString("COLUMN_NAME");
				String type = rs.getString("TYPE_NAME");

				DataColumn col = new DataColumn();

				col.setColumnName(name);
				col.setColumnType(type);

				columns.add(col);
			}
			return columns;
		} finally {
			DatabaseTypeUtil.shutdownPortForwarding(sshSession);
		}
	}

	public static String getConnectionUrlWithSchema(ConnectionConfig connectionConfig) throws SQLException {
		Optional<DatabaseType> optionalDatabaseType = DatabaseType.valueOfFlag(connectionConfig.getDatabaseType());

		if (!optionalDatabaseType.isPresent()) {
			throw new SQLException("连接字符串无效");
		}

		DatabaseType dbType = optionalDatabaseType.get();

		String connectionUrl = String.format(dbType.getConnectionUrlPattern(),
				DatabaseTypeUtil.portForwaring ? "127.0.0.1" : connectionConfig.getHost(), DatabaseTypeUtil.portForwaring ? connectionConfig
						.getLocalPort() : connectionConfig
						.getPort(), connectionConfig
						.getSchema(), DatabaseEncoding.valueOfFlag(connectionConfig.getEncoding())
													  .orElse(DatabaseEncoding.UTF8)
													  .getName());
		DatabaseTypeUtil._LOG.info("getConnectionUrlWithSchema, connection url: {}", connectionUrl);
		return connectionUrl;
	}

	/**
	 * 加载数据库驱动
	 *
	 * @param dbType 数据库类型
	 */
	private static void loadDbDriver(DatabaseType dbType) {
		List<String> driverJars = ConfigHelper.getAllJDBCDriverJarPaths();
		ClassLoader classloader = ClassloaderUtility.getCustomClassloader(driverJars);
		try {
			Class<?> clazz = Class.forName(dbType.getDriverClass(), true, classloader);
			Driver driver = (Driver) clazz.newInstance();
			DatabaseTypeUtil._LOG.info("load driver class: {}", driver);
			DatabaseTypeUtil.drivers.put(dbType, driver);
		} catch (Exception e) {
			DatabaseTypeUtil._LOG.error("load driver error", e);

			for (String driver : driverJars) {
				GeneralLogAssist.quickRecord(StringAssist.merge("驱动路径（loadDbDriver）:", driver));
			}

			throw new DbDriverLoadingException("找不到" + dbType.getConnectorJarFile() + "驱动");
		}
	}
}
