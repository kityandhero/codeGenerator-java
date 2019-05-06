package com.lzt.operate.codetools.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.lzt.operate.codetools.entity.DatabaseConfig;
import com.lzt.operate.codetools.entity.DbType;
import com.lzt.operate.codetools.entity.UITableColumnVO;
import com.lzt.operate.codetools.exception.DbDriverLoadingException;
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
import java.util.Collections;
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

    private static Map<DbType, Driver> drivers = new HashMap<>();

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static volatile boolean portForwaring = false;
    private static Map<Integer, Session> portForwardingSession = new ConcurrentHashMap<>();

    public static Session getSSHSession(DatabaseConfig databaseConfig) {
        if (StringUtils.isBlank(databaseConfig.getSshHost())
                || StringUtils.isBlank(databaseConfig.getSshPort())
                || StringUtils.isBlank(databaseConfig.getSshUser())
                || StringUtils.isBlank(databaseConfig.getSshPassword())
        ) {
            return null;
        }
        Session session = null;
        try {
            //Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Integer sshPort = NumberUtils.createInteger(databaseConfig.getSshPort());
            int port = sshPort == null ? 22 : sshPort;
            session = jsch.getSession(databaseConfig.getSshUser(), databaseConfig.getSshHost(), port);
            session.setPassword(databaseConfig.getSshPassword());
            session.setConfig(config);
        } catch (JSchException e) {
            //Ignore
        }
        return session;
    }

    public static void engagePortForwarding(Session sshSession, DatabaseConfig config) {
        if (sshSession != null) {
            AtomicInteger assinged_port = new AtomicInteger();
            Future<?> result = DbUtil.executorService.submit(() -> {
                try {
                    Integer localPort = NumberUtils.createInteger(config.getLport());
                    Integer RemotePort = NumberUtils.createInteger(config.getRport());
                    int lport = localPort == null ? Integer.parseInt(config.getPort()) : localPort;
                    int rport = RemotePort == null ? Integer.parseInt(config.getPort()) : RemotePort;
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

    public static void shutdownPortForwarding(Session session) {
        DbUtil.portForwaring = false;
        if (session != null && session.isConnected()) {
            session.disconnect();
            DbUtil._LOG.info("portForwarding turn OFF");
        }
//		executorService.shutdown();
    }

    public static Connection getConnection(DatabaseConfig config) throws SQLException {
        DbType dbType = DbType.valueOf(config.getDbType());
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

    public static List<String> getTableNames(DatabaseConfig config) throws Exception {
        Session sshSession = DbUtil.getSSHSession(config);
        DbUtil.engagePortForwarding(sshSession, config);
        try (Connection connection = DbUtil.getConnection(config)) {
            List<String> tables = new ArrayList<>();
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs;
            if (DbType.valueOf(config.getDbType()) == DbType.SQL_Server) {
                String sql = "select name from sysobjects  where xtype='u' or xtype='v' order by name";
                rs = connection.createStatement().executeQuery(sql);
                while (rs.next()) {
                    tables.add(rs.getString("name"));
                }
            } else if (DbType.valueOf(config.getDbType()) == DbType.Oracle) {
                rs = md.getTables(null, config.getUsername().toUpperCase(), null, new String[]{"TABLE", "VIEW"});
            } else if (DbType.valueOf(config.getDbType()) == DbType.Sqlite) {
                String sql = "Select name from sqlite_master;";
                rs = connection.createStatement().executeQuery(sql);
                while (rs.next()) {
                    tables.add(rs.getString("name"));
                }
            } else {
                // rs = md.getTables(null, config.getUsername().toUpperCase(), null, null);


                rs = md.getTables(config.getSchema(), null, "%", new String[]{"TABLE", "VIEW"});            //针对 postgresql 的左侧数据表显示
            }
            while (rs.next()) {
                tables.add(rs.getString(3));
            }

            if (tables.size() > 1) {
                Collections.sort(tables);
            }
            return tables;
        } finally {
            DbUtil.shutdownPortForwarding(sshSession);
        }
    }

    public static List<UITableColumnVO> getTableColumns(DatabaseConfig dbConfig, String tableName) throws Exception {
        String url = DbUtil.getConnectionUrlWithSchema(dbConfig);
        DbUtil._LOG.info("getTableColumns, connection url: {}", url);
        Session sshSession = DbUtil.getSSHSession(dbConfig);
        DbUtil.engagePortForwarding(sshSession, dbConfig);
        try (Connection conn = DbUtil.getConnection(dbConfig)) {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getColumns(dbConfig.getSchema(), null, tableName, null);
            List<UITableColumnVO> columns = new ArrayList<>();
            while (rs.next()) {
                UITableColumnVO columnVO = new UITableColumnVO();
                String columnName = rs.getString("COLUMN_NAME");
                columnVO.setColumnName(columnName);
                columnVO.setJdbcType(rs.getString("TYPE_NAME"));
                columns.add(columnVO);
            }
            return columns;
        } finally {
            DbUtil.shutdownPortForwarding(sshSession);
        }
    }

    public static String getConnectionUrlWithSchema(DatabaseConfig dbConfig) {
        DbType dbType = DbType.valueOf(dbConfig.getDbType());
        String connectionUrl = String.format(dbType.getConnectionUrlPattern(),
                DbUtil.portForwaring ? "127.0.0.1" : dbConfig.getHost(), DbUtil.portForwaring ? dbConfig.getLport() : dbConfig
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
    private static void loadDbDriver(DbType dbType) {
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