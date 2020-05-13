package com.lzt.operate.code.generator.app.assists;

import com.lzt.operate.code.generator.common.enums.ConnectionType;
import com.lzt.operate.code.generator.common.enums.DatabaseEncoding;
import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.utility.enums.CustomDataType;
import com.lzt.operate.code.generator.common.jackson.ObjectMapperAssist;
import com.lzt.operate.code.generator.common.pojos.DataTable;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.custommessagequeue.errorlog.ErrorLogProducer;
import com.lzt.operate.code.generator.custommessagequeue.errorlog.ErrorLogProducerFactory;
import com.lzt.operate.code.generator.dao.assists.BaseConnectionConfigAssist;
import com.lzt.operate.code.generator.dao.service.ConnectionConfigService;
import com.lzt.operate.code.generator.dao.service.DataColumnService;
import com.lzt.operate.code.generator.dao.service.DataTableGeneratorConfigService;
import com.lzt.operate.code.generator.dao.service.DatabaseGeneratorConfigService;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataTableGeneratorConfig;
import com.lzt.operate.code.generator.entities.DatabaseGeneratorConfig;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.extensions.StringEx;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class ConnectionConfigAssist extends BaseConnectionConfigAssist {

	public ConnectionConfigAssist(
			ConnectionConfigService connectionConfigService,
			DatabaseGeneratorConfigService databaseGeneratorConfigService,
			DataTableGeneratorConfigService dataTableGeneratorConfigService, DataColumnService dataColumnService) {
		super(connectionConfigService, databaseGeneratorConfigService, dataTableGeneratorConfigService, dataColumnService);
	}

	/**
	 * createConnectionConfig
	 *
	 * @param paramJson paramJson
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	@Override
	public ExecutiveResult<ConnectionConfig> createConnectionConfig(ParamData paramJson) {
		StringEx name = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_NAME);

		if (name.isNullOrEmpty()) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_NAME, "不能为空值"));
		}

		int connectionType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, ConnectionType.TCP_IP
				.getFlag().toString()).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(ConnectionType.values()), ConnectionType::getFlag, connectionType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

		int databaseType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DATABASE_TYPE).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(DatabaseType.values()), DatabaseType::getFlag, databaseType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

		int encoding = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ENCODING).toInt();

		if (!EnumAssist.existTargetValue(DatabaseEncoding.valuesToList(), DatabaseEncoding::getFlag, encoding)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_ENCODING, "允许范围之外的值"));
		}

		StringEx description = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DESCRIPTION);
		StringEx host = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_HOST);
		StringEx port = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_PORT);
		StringEx schema = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SCHEMA);
		StringEx username = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_USERNAME);
		StringEx password = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_PASSWORD);
		StringEx localPort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_LOCAL_PORT);
		StringEx remotePort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_REMOTE_PORT);
		StringEx sshPort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_PORT);
		StringEx sshHost = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_HOST);
		StringEx sshUser = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_USER);
		StringEx sshPassword = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_PASSWORD);

		ConnectionConfig connectionConfig = new ConnectionConfig();

		connectionConfig.setName(name.toString());
		connectionConfig.setDescription(description.toString());
		connectionConfig.setConnectionType(connectionType);
		connectionConfig.setDatabaseType(databaseType);
		connectionConfig.setHost(host.toString());
		connectionConfig.setPort(port.toString());
		connectionConfig.setSchema(schema.toString());
		connectionConfig.setUserName(username.toString());
		connectionConfig.setPassword(password.toString());
		connectionConfig.setEncoding(DatabaseEncoding.valueOfFlag(encoding).orElse(DatabaseEncoding.UTF8));
		connectionConfig.setLocalPort(localPort.toString());
		connectionConfig.setRemotePort(remotePort.toString());
		connectionConfig.setSshPort(sshPort.toString());
		connectionConfig.setSshHost(sshHost.toString());
		connectionConfig.setSshUser(sshUser.toString());
		connectionConfig.setSshPassword(sshPassword.toString());

		return new ExecutiveResult<>(ReturnDataCode.Ok, connectionConfig);
	}

	/**
	 * fillFromParamJson
	 *
	 * @param connectionConfig connectionConfig
	 * @param paramJson        paramJson
	 * @return ExecutiveResult<ConnectionConfig>
	 */
	@Override
	public ExecutiveResult<ConnectionConfig> fillFromParamJson(@NonNull ConnectionConfig connectionConfig, ParamData paramJson) {
		StringEx name = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_NAME);

		if (name.isNullOrEmpty()) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_NAME, "不能为空值"));
		}

		int connectionType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, ConnectionType.TCP_IP
				.getFlag().toString()).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(ConnectionType.values()), ConnectionType::getFlag, connectionType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

		int databaseType = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_DATABASE_TYPE).toInt();

		if (!EnumAssist.existTargetValue(Arrays.asList(DatabaseType.values()), DatabaseType::getFlag, databaseType)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_CONNECTION_TYPE, "允许范围之外的值"));
		}

		int encoding = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ENCODING).toInt();

		if (!EnumAssist.existTargetValue(DatabaseEncoding.valuesToList(), DatabaseEncoding::getFlag, encoding)) {
			return new ExecutiveResult<>(ReturnDataCode.ParamError.appendMessage(GlobalString.CONNECTION_CONFIG_ENCODING, "允许范围之外的值"));
		}

		StringEx host = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_HOST);
		StringEx port = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_PORT);
		StringEx schema = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SCHEMA);
		StringEx username = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_USERNAME);
		StringEx password = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_PASSWORD);
		StringEx localPort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_LOCAL_PORT);
		StringEx remotePort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_REMOTE_PORT);
		StringEx sshPort = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_PORT);
		StringEx sshHost = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_HOST);
		StringEx sshUser = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_USER);
		StringEx sshPassword = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_SSH_PASSWORD);

		connectionConfig.setName(name.toString());
		connectionConfig.setConnectionType(connectionType);
		connectionConfig.setDatabaseType(databaseType);
		connectionConfig.setHost(host.toString());
		connectionConfig.setPort(port.toString());
		connectionConfig.setSchema(schema.toString());
		connectionConfig.setUserName(username.toString());
		connectionConfig.setPassword(password.toString());
		connectionConfig.setEncoding(DatabaseEncoding.valueOfFlag(encoding).orElse(DatabaseEncoding.UTF8));
		connectionConfig.setLocalPort(localPort.toString());
		connectionConfig.setRemotePort(remotePort.toString());
		connectionConfig.setSshPort(sshPort.toString());
		connectionConfig.setSshHost(sshHost.toString());
		connectionConfig.setSshUser(sshUser.toString());
		connectionConfig.setSshPassword(sshPassword.toString());

		return new ExecutiveResult<>(ReturnDataCode.Ok, connectionConfig);
	}

	public ExecutiveResult<DataTableGeneratorConfig> initializeDataTableGeneratorConfig(long connectionConfigId, @NotNull String tableName) throws Exception {
		Optional<ConnectionConfig> optionalConnectionConfig = this.getConnectionConfigService().get(connectionConfigId);

		ConnectionConfig connectionConfig;

		if (!optionalConnectionConfig.isPresent()) {
			return new ExecutiveResult<>(ReturnDataCode.NoData.toMessage("指定的数据连接不存在"));
		} else {
			connectionConfig = optionalConnectionConfig.get();
		}

		Optional<DatabaseGeneratorConfig> optionalDatabaseGeneratorConfig = this.getDatabaseGeneratorConfigService()
																				.findByConnectionConfigId(connectionConfigId);

		DatabaseGeneratorConfig databaseGeneratorConfig;

		if (!optionalDatabaseGeneratorConfig.isPresent()) {
			return new ExecutiveResult<>(ReturnDataCode.NoData.toMessage("指定的数据库生成配置不存在"));
		} else {
			databaseGeneratorConfig = optionalDatabaseGeneratorConfig.get();
		}

		if (StringAssist.isNullOrEmpty(tableName)) {
			return new ExecutiveResult<>(ReturnDataCode.NoData.toMessage("数据库表名称参数不能为空"));
		}

		List<DataTable> list = DatabaseAssist.listDataTable(connectionConfig);

		if (ConstantCollection.ZERO_LONG.equals(list.stream().filter(o -> o.getName().contains(tableName)).count())) {
			return new ExecutiveResult<>(ReturnDataCode.NoData.toMessage("数据表不存在"));
		}

		Specification<DataTableGeneratorConfig> specification = new Specification<DataTableGeneratorConfig>() {

			private static final long serialVersionUID = 5826322526864777111L;

			@Override
			public Predicate toPredicate(@NonNull Root<DataTableGeneratorConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getConnectionConfigId)), connectionConfigId));

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getDatabaseGeneratorConfigId)), databaseGeneratorConfig
						.getId()));

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getTableName)), tableName));

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Optional<DataTableGeneratorConfig> optionalDataTableGeneratorConfig = this.getDataTableGeneratorConfigService()
																				  .findFirst(specification);
		DataTableGeneratorConfig dataTableGeneratorConfig;

		if (optionalDataTableGeneratorConfig.isPresent()) {
			dataTableGeneratorConfig = optionalDataTableGeneratorConfig.get();
		} else {
			dataTableGeneratorConfig = new DataTableGeneratorConfig();

			dataTableGeneratorConfig.setConnectionConfigId(connectionConfigId);
			dataTableGeneratorConfig.setDatabaseGeneratorConfigId(databaseGeneratorConfig.getId());
			dataTableGeneratorConfig.setTableName(tableName);

			try {
				dataTableGeneratorConfig = this.getDataTableGeneratorConfigService().save(dataTableGeneratorConfig);
			} catch (Exception e) {
				ErrorLogProducer errorLogProducer = ErrorLogProducerFactory.getInstance()
																		   .getProducer();

				errorLogProducer.pushException(e, "保存数据表生成配置时", ConvertAssist.serialize(ObjectMapperAssist.createObjectMapper(), dataTableGeneratorConfig), CustomDataType.JsonObject);

				return new ExecutiveResult<>(ReturnDataCode.Exception.toMessage(e.getMessage()));
			}
		}

		return new ExecutiveResult<>(ReturnDataCode.Ok, dataTableGeneratorConfig);
	}

}
