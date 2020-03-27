package com.lzt.operate.code.generator.dao.assists;

import com.lzt.operate.code.generator.dao.service.ConnectionConfigService;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author luzhitao
 */
public abstract class BaseConnectionConfigAssist {

	private ConnectionConfigService connectionConfigService;

	public BaseConnectionConfigAssist(ConnectionConfigService connectionConfigServiceImpl) {
		this.connectionConfigService = connectionConfigServiceImpl;
	}

	private ConnectionConfigService getConnectionConfigService() {
		Optional<ConnectionConfigService> optional = Optional.ofNullable(this.connectionConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("ConnectionConfigService获取失败");
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
