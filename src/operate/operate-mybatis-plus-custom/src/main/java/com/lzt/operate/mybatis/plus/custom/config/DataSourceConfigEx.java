package com.lzt.operate.mybatis.plus.custom.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.lzt.operate.code.generator.app.util.ConfigHelper;
import org.mybatis.generator.internal.util.ClassloaderUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DataSourceConfigEx extends DataSourceConfig {

	@Override
	public Connection getConn() {
		List<String> driverJars = ConfigHelper.getAllJDBCDriverJarPaths();
		ClassLoader classloader = ClassloaderUtility.getCustomClassloader(driverJars);

		try {
			Class.forName(this.getDriverName(), true, classloader);
			return DriverManager.getConnection(this.getUrl(), this.getUsername(), this.getPassword());
		} catch (SQLException | ClassNotFoundException var3) {
			throw new RuntimeException(var3);
		}
	}

}
