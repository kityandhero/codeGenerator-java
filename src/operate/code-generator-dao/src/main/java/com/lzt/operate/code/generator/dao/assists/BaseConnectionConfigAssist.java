package com.lzt.operate.code.generator.dao.assists;

import com.lzt.operate.code.generator.dao.service.ConnectionConfigService;
import com.lzt.operate.code.generator.dao.service.DataColumnService;
import com.lzt.operate.code.generator.dao.service.DataTableGeneratorConfigService;
import com.lzt.operate.code.generator.dao.service.DatabaseGeneratorConfigService;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author luzhitao
 */
public abstract class BaseConnectionConfigAssist {

	private final ConnectionConfigService connectionConfigService;
	private final DatabaseGeneratorConfigService databaseGeneratorConfigService;
	private final DataTableGeneratorConfigService dataTableGeneratorConfigService;
	private final DataColumnService dataColumnService;

	public BaseConnectionConfigAssist(
			ConnectionConfigService connectionConfigService,
			DatabaseGeneratorConfigService databaseGeneratorConfigService,
			DataTableGeneratorConfigService dataTableGeneratorConfigService,
			DataColumnService dataColumnService) {
		this.connectionConfigService = connectionConfigService;
		this.databaseGeneratorConfigService = databaseGeneratorConfigService;
		this.dataTableGeneratorConfigService = dataTableGeneratorConfigService;
		this.dataColumnService = dataColumnService;
	}

	public ConnectionConfigService getConnectionConfigService() {
		Optional<ConnectionConfigService> optional = Optional.ofNullable(this.connectionConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("ConnectionConfigService获取失败");
	}

	public DatabaseGeneratorConfigService getDatabaseGeneratorConfigService() {
		Optional<DatabaseGeneratorConfigService> optional = Optional.ofNullable(this.databaseGeneratorConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("DatabaseGeneratorConfigService获取失败");
	}

	public DataTableGeneratorConfigService getDataTableGeneratorConfigService() {
		Optional<DataTableGeneratorConfigService> optional = Optional.ofNullable(this.dataTableGeneratorConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("DataTableGeneratorConfigService获取失败");
	}

	public DataColumnService getDataColumnService() {
		Optional<DataColumnService> optional = Optional.ofNullable(this.dataColumnService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("DataColumnService获取失败");
	}

	/**
	 * getConnectionConfig
	 *
	 * @param connectionConfigId connectionConfigId
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	public Optional<ConnectionConfig> getConnectionConfig(long connectionConfigId) {
		return getConnectionConfigService().get(connectionConfigId);
	}

	/**
	 * deleteById
	 *
	 * @param connectionConfigId connectionConfigId
	 */
	public void deleteById(Long connectionConfigId) {
		if (connectionConfigId <= 0) {
			return;
		}

		this.getConnectionConfigService().deleteById(connectionConfigId);
	}

	/**
	 * createConnectionConfig
	 *
	 * @param paramJson paramJson
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	public abstract ExecutiveResult<ConnectionConfig> createConnectionConfig(ParamData paramJson);

	/**
	 * updateConnectionConfig
	 *
	 * @param connectionConfig connectionConfig
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	public ConnectionConfig saveConnectionConfig(@NonNull ConnectionConfig connectionConfig) {
		return getConnectionConfigService().save(connectionConfig);
	}

	/**
	 * fillFromParamJson
	 *
	 * @param connectionConfig connectionConfig
	 * @param paramJson        paramJson
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	public abstract ExecutiveResult<ConnectionConfig> fillFromParamJson(@NonNull ConnectionConfig connectionConfig, ParamData paramJson);

}
