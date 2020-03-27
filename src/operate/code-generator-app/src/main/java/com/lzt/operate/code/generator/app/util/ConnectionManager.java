package com.lzt.operate.code.generator.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Owen on 8/21/16.
 */
public class ConnectionManager {
    private static final Logger _LOG = LoggerFactory.getLogger(ConnectionManager.class);
    private static final String DB_URL = "jdbc:sqlite:./config/sqlite3.db";

    public static Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        File file = new File(ConnectionManager.DB_URL.substring("jdbc:sqlite:".length())).getAbsoluteFile();
        ConnectionManager._LOG.info("database FilePath :{}", file.getAbsolutePath());
        return DriverManager.getConnection(ConnectionManager.DB_URL);
    }
}
