package com.lzt.operate.codetools.app.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.lzt.operate.codetools.app.enums.DatabaseType;
import com.lzt.operate.codetools.app.exceptions.DbDriverLoadingException;
import com.lzt.operate.codetools.entities.ConnectionConfig;
import com.lzt.operate.codetools.entities.DataColumnInfo;
import com.lzt.operate.codetools.entities.DataTableInfo;
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
public class DbUtil {

	private static final Logger _LOG = LoggerFactory.getLogger(DbUtil.class);
	private static final int DB_CONNECTION_TIMEOUTS_SECONDS = 1;

	private static Map<DatabaseType, Driver> drivers = new HashMap<>();

	private static ExecutorService executorService = Executors.newSingleThreadExecutor();
	private static volatile boolean portForwaring = false;
	private static Map<Integer, Session> portForwardingSession = new ConcurrentHashMap<>();

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
			Future<?> result = DbUtil.executorService.submit(() -> {
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
					Session session = DbUtil.portForwardingSession.get(lport);
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
					DbUtil.portForwardingSession.put(lport, sshSession);
					DbUtil.portForwaring = true;
					DbUtil._LOG.info("portForwarding Enabled, {}", assinged_port);
				} catch (JSchException e) {
					DbUtil._LOG.error("Connect Over SSH failed", e);
					String a = "Address already in use: JVM_Bind";

					if (e.getCause() != null && a.equals(e.getCause().getMessage())) {
						throw new RuntimeException("Address already in use: JVM_Bind");
					}
					throw new RuntimeException(e.getMessage());
				}
			});
			try {
				result.get(5, TimeUnit.SECONDS);
			} catch (Exception e) {
				DbUtil.shutdownPortForwarding(sshSession);
				if (e.getCause() instanceof RuntimeException) {
					throw (RuntimeException) e.getCause();
				}
				if (e instanceof TimeoutException) {
					throw new RuntimeException("OverSSH 连接超时：超过5秒");
				}

				DbUtil._LOG.info("executorService isShutdown:{}", DbUtil.executorService.isShutdown());
				// AlertUtil.showErrorAlert("OverSSH 失败，请检查连接设置:" + e.getMessage());
				throw new RuntimeException("OverSSH 失败，请检查连接设置:" + e.getMessage());
			}
		}
	}

	private static void shutdownPortForwarding(Session session) {
		DbUtil.portForwaring = false;
		if (session != null && session.isConnected()) {
			session.disconnect();
			DbUtil._LOG.info("portForwarding turn OFF");
		}
//		executorService.shutdown();
	}

	private static Connection getConnection(ConnectionConfig config) throws SQLException {
		DatabaseType dbType = DatabaseType.valueOf(config.getDatabaseType());
		if (DbUtil.drivers.get(dbType) == null) {
			DbUtil.loadDbDriver(dbType);
		}

		String url = DbUtil.getConnectionUrlWithSchema(config);
		Properties props = new Properties();

		// $NON-NLS-1$
		props.setProperty("user", config.getUsername());

		//$NON-NLS-1$
		props.setProperty("password", config.getPassword());

		DriverManager.setLoginTimeout(DbUtil.DB_CONNECTION_TIMEOUTS_SECONDS);
		Connection connection = DbUtil.drivers.get(dbType).connect(url, props);
		DbUtil._LOG.info("getConnection, connection url: {}", connection);
		return connection;
	}

	public static List<DataTableInfo> getTableNames(ConnectionConfig config) throws Exception {
		Session sshSession = DbUtil.getSSHSession(config);
		DbUtil.engagePortForwarding(sshSession, config);
		try (Connection connection = DbUtil.getConnection(config)) {
			List<DataTableInfo> tables = new ArrayList<>();
			DatabaseMetaData md = connection.getMetaData();
			ResultSet rs;
			if (DatabaseType.valueOf(config.getDatabaseType()) == DatabaseType.SQL_Server) {
				String sql = "select name from sysobjects  where xtype='u' or xtype='v' order by name";
				rs = connection.createStatement().executeQuery(sql);
				while (rs.next()) {
					tables.add(new DataTableInfo(rs.getString("name")));
				}
			} else if (DatabaseType.valueOf(config.getDatabaseType()) == DatabaseType.Oracle) {
				rs = md.getTables(null, config.getUsername()
											  .toUpperCase(), null, new String[]{"TABLE", "VIEW"});
			} else if (DatabaseType.valueOf(config.getDatabaseType()) == DatabaseType.Sqlite) {
				String sql = "Select name from sqlite_master;";
				rs = connection.createStatement().executeQuery(sql);
				while (rs.next()) {
					tables.add(new DataTableInfo(rs.getString("name")));
				}
			} else {
				// rs = md.getTables(null, config.getUsername().toUpperCase(), null, null);

				rs = md.getTables(config.getSchema(), null, "%", new String[]{"TABLE", "VIEW"});            //针对 postgresql 的左侧数据表显示
			}
			while (rs.next()) {
				tables.add(new DataTableInfo(rs.getString(3)));
			}
			return tables;
		} finally {
			DbUtil.shutdownPortForwarding(sshSession);
		}
	}

	public static List<DataColumnInfo> getTableColumns(ConnectionConfig dbConfig, String tableName) throws Exception {
		String url = DbUtil.getConnectionUrlWithSchema(dbConfig);
		DbUtil._LOG.info("getTableColumns, connection url: {}", url);
		Session sshSession = DbUtil.getSSHSession(dbConfig);
		DbUtil.engagePortForwarding(sshSession, dbConfig);
		try (Connection conn = DbUtil.getConnection(dbConfig)) {
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getColumns(dbConfig.getSchema(), null, tableName, null);
			List<DataColumnInfo> columns = new ArrayList<>();
			while (rs.next()) {

				String name = rs.getString("COLUMN_NAME");
				String type = rs.getString("TYPE_NAME");

				DataColumnInfo col = new DataColumnInfo();
				col.setName(name);
				col.setType(type);

				columns.add(col);
			}
			return columns;
		} finally {
			DbUtil.shutdownPortForwarding(sshSession);
		}
	}

	public static String getConnectionUrlWithSchema(ConnectionConfig dbConfig) {
		DatabaseType dbType = DatabaseType.valueOf(dbConfig.getDatabaseType());
		String connectionUrl = String.format(dbType.getConnectionUrlPattern(),
				DbUtil.portForwaring ? "127.0.0.1" : dbConfig.getHost(), DbUtil.portForwaring ? dbConfig.getLocalPort() : dbConfig
						.getPort(), dbConfig
						.getSchema(), dbConfig.getEncoding());
		DbUtil._LOG.info("getConnectionUrlWithSchema, connection url: {}", connectionUrl);
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
			Class clazz = Class.forName(dbType.getDriverClass(), true, classloader);
			Driver driver = (Driver) clazz.newInstance();
			DbUtil._LOG.info("load driver class: {}", driver);
			DbUtil.drivers.put(dbType, driver);
		} catch (Exception e) {
			DbUtil._LOG.error("load driver error", e);
			throw new DbDriverLoadingException("找不到" + dbType.getConnectorJarFile() + "驱动");
		}
	}
}
