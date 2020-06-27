package com.lzt.operate.mybatis.plus.custom.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.lzt.operate.code.generator.app.core.assists.DatabaseAssist;
import com.lzt.operate.code.generator.entities.ConnectionConfig;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author luzhitao
 */
public class DataSourceConfigEx extends DataSourceConfig {

	ConnectionConfig connectionConfig;

	public DataSourceConfigEx(@NotNull ConnectionConfig connectionConfig) {
		super();

		this.connectionConfig = connectionConfig;
	}

	public ConnectionConfig getConnectionConfig() {
		return this.connectionConfig;
	}

	public void setConnectionConfig(ConnectionConfig connectionConfig) {
		this.connectionConfig = connectionConfig;
	}

	@Override
	public Connection getConn() {
		try {
			return DatabaseAssist.getConnection(this.getConnectionConfig());

		} catch (SQLException ex) {
			ex.printStackTrace();

			throw new RuntimeException(ex);
		}
	}

}
