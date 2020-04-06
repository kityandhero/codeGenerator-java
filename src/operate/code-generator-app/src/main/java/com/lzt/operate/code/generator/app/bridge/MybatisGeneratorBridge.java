package com.lzt.operate.code.generator.app.bridge;

import com.lzt.operate.code.generator.app.util.ConfigHelper;
import com.lzt.operate.code.generator.app.util.DatabaseTypeUtil;
import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.code.generator.common.enums.FileEncoding;
import com.lzt.operate.code.generator.dao.service.DatabaseGeneratorConfigService;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataTableGeneratorConfig;
import com.lzt.operate.code.generator.entities.DatabaseGeneratorConfig;
import com.lzt.operate.mybatis.custom.plugins.CommonDAOInterfacePlugin;
import com.lzt.operate.mybatis.custom.plugins.DbRemarksCommentGenerator;
import com.lzt.operate.mybatis.custom.plugins.JavaTypeResolverJsr310Impl;
import com.lzt.operate.mybatis.custom.plugins.mysql.UpdatePlugin;
import com.lzt.operate.mybatis.custom.plugins.mysql.LimitPlugin;
import com.lzt.operate.mybatis.custom.plugins.RepositoryPlugin;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.ColumnOverride;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.IgnoredColumn;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The bridge between GUI and the mybatis generator. All the operation to  mybatis generator should proceed through this
 * class
 * <p>
 * Created by Owen on 6/30/16.
 */
@Slf4j
public class MybatisGeneratorBridge {

	private final DatabaseGeneratorConfigService databaseGeneratorConfigService;

	private final ConnectionConfig connectionConfig;

	private ProgressCallback progressCallback;

	private List<IgnoredColumn> ignoredColumns;

	private List<ColumnOverride> columnOverrides;

	public MybatisGeneratorBridge(@NotNull ConnectionConfig selectedConnectionConfig, @NotNull DatabaseGeneratorConfigService databaseGeneratorConfigService) {
		this.connectionConfig = selectedConnectionConfig;
		this.databaseGeneratorConfigService = databaseGeneratorConfigService;
	}

	private DatabaseGeneratorConfig getDataBaseGeneratorConfig() throws RuntimeException {
		Optional<DatabaseGeneratorConfig> optional = this.databaseGeneratorConfigService.findByConnectionConfigId(this.connectionConfig
				.getId());

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("数据连接缺少相关生成配置");
	}

	public ExecutiveSimpleResult generate(@NotNull DataTableGeneratorConfig dataTableGeneratorConfig) throws Exception {
		DatabaseGeneratorConfig databaseGeneratorConfig = this.getDataBaseGeneratorConfig();

		Long connectionConfigId = databaseGeneratorConfig.getConnectionConfigId();

		if (!connectionConfigId.equals(dataTableGeneratorConfig.getConnectionConfigId())) {
			throw new RuntimeException("数据表生成配置与数据连接配置不相配");
		}

		Optional<FileEncoding> optionalFileEncoding = FileEncoding.valueOfFlag(databaseGeneratorConfig.getEncoding());

		FileEncoding fileEncoding = FileEncoding.UTF8;

		if (optionalFileEncoding.isPresent()) {
			fileEncoding = optionalFileEncoding.get();
		}

		Optional<DatabaseType> optionalDatabaseType = DatabaseType.valueOfFlag(this.connectionConfig.getDatabaseType());

		if (!optionalDatabaseType.isPresent()) {
			return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("databaseType无效"));
		}

		DatabaseType databaseType = optionalDatabaseType.get();

		String tableName = dataTableGeneratorConfig.getTableName();

		if (StringAssist.isNullOrEmpty(tableName)) {
			return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("tableName无效"));
		}

		Configuration configuration = new Configuration();

		Context context = new Context(ModelType.CONDITIONAL);

		configuration.addContext(context);
		context.addProperty("javaFileEncoding", fileEncoding.getName());

		String connectorLibPath = ConfigHelper.findConnectorLibPath(databaseType.getName());

		// log.info("connectorLibPath: {}", connectorLibPath);
		configuration.addClasspathEntry(connectorLibPath);

		// Table configuration
		TableConfiguration tableConfig = new TableConfiguration(context);

		tableConfig.setTableName(tableName);
		tableConfig.setDomainObjectName(Optional.ofNullable(dataTableGeneratorConfig.getDomainObjectName()).orElse(""));

		if (!ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getUseExample())) {
			tableConfig.setUpdateByExampleStatementEnabled(false);
			tableConfig.setCountByExampleStatementEnabled(false);
			tableConfig.setDeleteByExampleStatementEnabled(false);
			tableConfig.setSelectByExampleStatementEnabled(false);
		}

		// 自动识别数据库关键字，默认false，如果设置为true，根据SqlReservedWords中定义的关键字列表；
		//         一般保留默认值，遇到数据库关键字（Java关键字），使用columnOverride覆盖
		context.addProperty("autoDelimitKeywords", ConstantCollection.NO_INT.equals(databaseGeneratorConfig.getAutoDelimitKeywords()) ? "false" : "true");

		if (DatabaseType.MySQL.getFlag().equals(databaseType.getFlag()) || DatabaseType.MySQL_8.getFlag()
																							   .equals(databaseType.getFlag())) {
			tableConfig.setSchema(this.connectionConfig.getSchema());
			// 由于beginningDelimiter和endingDelimiter的默认值为双引号(")，在Mysql中不能这么写，所以还要将这两个默认值改为`
			context.addProperty("beginningDelimiter", "`");
			context.addProperty("endingDelimiter", "`");
		} else {
			tableConfig.setCatalog(this.connectionConfig.getSchema());
		}
		if (ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getUseSchemaPrefix())) {
			if (DatabaseType.MySQL.getFlag().equals(databaseType.getFlag()) || DatabaseType.MySQL_8.getFlag()
																								   .equals(databaseType.getFlag())) {
				tableConfig.setSchema(this.connectionConfig.getSchema());
			} else if (DatabaseType.Oracle.name().equals(databaseType.getName())) {
				//Oracle的schema为用户名，如果连接用户拥有dba等高级权限，若不设schema，会导致把其他用户下同名的表也生成一遍导致mapper中代码重复
				tableConfig.setSchema(this.connectionConfig.getUserName());
			} else {
				tableConfig.setCatalog(this.connectionConfig.getSchema());
			}
		}
		// 针对 postgresql 单独配置
		if (DatabaseType.PostgreSQL.name().equals(databaseType.getName())) {
			tableConfig.setDelimitIdentifiers(true);
		}

		//添加GeneratedKey主键生成
		if (!ConstantCollection.ZERO_INT.equals(dataTableGeneratorConfig.getUseGenerateKey())) {
			if (StringUtils.isNotEmpty(dataTableGeneratorConfig.getGenerateKeys())) {

				if (DatabaseType.MySQL.name().equals(databaseType.getName()) || DatabaseType.MySQL_8.name()
																									.equals(databaseType
																											.getName())) {

					//dbType为JDBC，且配置中开启useGeneratedKeys时，Mybatis会使用Jdbc3KeyGenerator,
					//使用该KeyGenerator的好处就是直接在一次INSERT 语句内，通过resultSet获取得到 生成的主键值，
					//并很好的支持设置了读写分离代理的数据库
					//例如阿里云RDS + 读写分离代理
					//无需指定主库
					//当使用SelectKey时，Mybatis会使用SelectKeyGenerator，INSERT之后，多发送一次查询语句，获得主键值
					//在上述读写分离被代理的情况下，会得不到正确的主键
					tableConfig.setGeneratedKey(new GeneratedKey(dataTableGeneratorConfig.getGenerateKeys(), "JDBC", true, null));
				} else {
					tableConfig.setGeneratedKey(new GeneratedKey(dataTableGeneratorConfig.getGenerateKeys(), databaseType
							.getName(), true, null));
				}

			}
		}

		if (dataTableGeneratorConfig.getMapperName() != null) {
			tableConfig.setMapperName(dataTableGeneratorConfig.getMapperName());
		}
		// add ignore columns
		if (this.ignoredColumns != null) {
			this.ignoredColumns.forEach(tableConfig::addIgnoredColumn);
		}
		if (this.columnOverrides != null) {
			this.columnOverrides.forEach(tableConfig::addColumnOverride);
		}
		if (ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getUseActualColumnNames())) {
			tableConfig.addProperty("useActualColumnNames", "true");
		}

		if (ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getUseTableNameAlias())) {
			tableConfig.setAlias(dataTableGeneratorConfig.getTableName());
		}

		JDBCConnectionConfiguration jdbcConfig = new JDBCConnectionConfiguration();
		if (DatabaseType.MySQL.name().equals(databaseType.getName()) || DatabaseType.MySQL_8.name()
																							.equals(databaseType.getName())) {
			jdbcConfig.addProperty("nullCatalogMeansCurrent", "true");
		}
		jdbcConfig.setDriverClass(databaseType.getDriverClass());
		jdbcConfig.setConnectionURL(DatabaseTypeUtil.getConnectionUrlWithSchema(this.connectionConfig));
		jdbcConfig.setUserId(this.connectionConfig.getUserName());
		jdbcConfig.setPassword(this.connectionConfig.getPassword());
		if (DatabaseType.Oracle.name().equals(databaseType.getName())) {
			jdbcConfig.getProperties().setProperty("remarksReporting", "true");
		}
		// java model
		JavaModelGeneratorConfiguration modelConfig = new JavaModelGeneratorConfiguration();
		modelConfig.setTargetPackage(databaseGeneratorConfig.getModelPackage());
		modelConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder() + "/" + databaseGeneratorConfig
				.getModelTargetFolder());
		// Mapper configuration
		SqlMapGeneratorConfiguration mapperConfig = new SqlMapGeneratorConfiguration();
		mapperConfig.setTargetPackage(databaseGeneratorConfig.getMappingXmlPackage());
		mapperConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder() + "/" + databaseGeneratorConfig
				.getMappingXmlTargetFolder());
		// DAO
		JavaClientGeneratorConfiguration daoConfig = new JavaClientGeneratorConfiguration();
		daoConfig.setConfigurationType("XMLMAPPER");
		daoConfig.setTargetPackage(databaseGeneratorConfig.getDaoPackage());
		daoConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder() + "/" + databaseGeneratorConfig.getDaoTargetFolder());

		context.setId("myid");
		context.addTableConfiguration(tableConfig);
		context.setJdbcConnectionConfiguration(jdbcConfig);
		context.setJavaModelGeneratorConfiguration(modelConfig);
		context.setSqlMapGeneratorConfiguration(mapperConfig);
		context.setJavaClientGeneratorConfiguration(daoConfig);
		// Comment
		CommentGeneratorConfiguration commentConfig = new CommentGeneratorConfiguration();
		commentConfig.setConfigurationType(DbRemarksCommentGenerator.class.getName());

		if (!StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getComment())) {
			commentConfig.addProperty("columnRemarks", "true");
		}

		if (ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getAnnotation())) {
			commentConfig.addProperty("annotations", "true");
		}

		context.setCommentGeneratorConfiguration(commentConfig);
		context.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, fileEncoding.getName());

		//实体添加序列化
		PluginConfiguration serializablePluginConfiguration = new PluginConfiguration();
		serializablePluginConfiguration.addProperty("type", "org.mybatis.generator.plugins.SerializablePlugin");
		serializablePluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
		context.addPluginConfiguration(serializablePluginConfiguration);
		// toString, hashCode, equals插件
		if (ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getNeedToStringHashCodeEquals())) {
			PluginConfiguration pluginConfiguration1 = new PluginConfiguration();
			pluginConfiguration1.addProperty("type", "org.mybatis.generator.plugins.EqualsHashCodePlugin");
			pluginConfiguration1.setConfigurationType("org.mybatis.generator.plugins.EqualsHashCodePlugin");
			context.addPluginConfiguration(pluginConfiguration1);
			PluginConfiguration pluginConfiguration2 = new PluginConfiguration();

			pluginConfiguration2.addProperty("type", "org.mybatis.generator.plugins.ToStringPlugin");
			pluginConfiguration2.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");

			context.addPluginConfiguration(pluginConfiguration2);
		}
		// limit/offset插件
		if (ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getOffsetLimit())) {
			if (DatabaseType.MySQL.name().equals(databaseType.getName()) || DatabaseType.MySQL_8.name()
																								.equals(databaseType.getName())
					|| DatabaseType.PostgreSQL.name().equals(databaseType.getName())) {
				PluginConfiguration pluginConfiguration = new PluginConfiguration();

				String limitPluginName = LimitPlugin.class.getName();

				pluginConfiguration.addProperty("type", limitPluginName);
				pluginConfiguration.setConfigurationType(limitPluginName);

				context.addPluginConfiguration(pluginConfiguration);
			}
		}
		//for JSR310
		if (ConstantCollection.ZERO_INT.equals((databaseGeneratorConfig.getJsr310Support()))) {
			JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();

			String javaTypeResolverJsr310ImplName = JavaTypeResolverJsr310Impl.class.getName();

			javaTypeResolverConfiguration.setConfigurationType(javaTypeResolverJsr310ImplName);

			context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
		}

		//forUpdate 插件
		if (ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getNeedForUpdate())) {
			if (DatabaseType.MySQL.name().equals(databaseType.getName())
					|| DatabaseType.PostgreSQL.name().equals(databaseType.getName())) {
				PluginConfiguration pluginConfiguration = new PluginConfiguration();

				String updatePluginName = UpdatePlugin.class.getName();

				pluginConfiguration.addProperty("type", updatePluginName);
				pluginConfiguration.setConfigurationType(updatePluginName);

				context.addPluginConfiguration(pluginConfiguration);
			}
		}
		//repository 插件
		if (ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getAnnotationDAO())) {
			if (DatabaseType.MySQL.name().equals(databaseType.getName()) || DatabaseType.MySQL_8.name()
																								.equals(databaseType.getName())
					|| DatabaseType.PostgreSQL.name().equals(databaseType.getName())) {
				PluginConfiguration pluginConfiguration = new PluginConfiguration();

				String repositoryPluginName = RepositoryPlugin.class.getName();

				pluginConfiguration.addProperty("type", repositoryPluginName);
				pluginConfiguration.setConfigurationType(repositoryPluginName);

				context.addPluginConfiguration(pluginConfiguration);
			}
		}
		if (ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getUseDAOExtendStyle())) {
			if (DatabaseType.MySQL.name().equals(databaseType.getName()) || DatabaseType.MySQL_8.name()
																								.equals(databaseType.getName())
					|| DatabaseType.PostgreSQL.name().equals(databaseType.getName())) {
				PluginConfiguration pluginConfiguration = new PluginConfiguration();
				pluginConfiguration.addProperty("useExample", String.valueOf(ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig
						.getUseExample())));

				String commonInterfacePluginName = CommonDAOInterfacePlugin.class.getName();

				pluginConfiguration.addProperty("type", commonInterfacePluginName);
				pluginConfiguration.setConfigurationType(commonInterfacePluginName);

				context.addPluginConfiguration(pluginConfiguration);
			}
		}

		context.setTargetRuntime("MyBatis3");

		List<String> warnings = new ArrayList<>();
		Set<String> fullyqualifiedTables = new HashSet<>();
		Set<String> contexts = new HashSet<>();

		//override=true
		ShellCallback shellCallback = new DefaultShellCallback(true);

		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, shellCallback, warnings);

		// if overrideXML selected, delete oldXML ang generate new one
		if (ConstantCollection.ZERO_INT.equals(databaseGeneratorConfig.getOverrideXML())) {
			String mappingXMLFilePath = this.getMappingXmlFilePath(databaseGeneratorConfig, dataTableGeneratorConfig);
			File mappingXMLFile = new File(mappingXMLFilePath);
			if (mappingXMLFile.exists()) {
				boolean delete = mappingXMLFile.delete();

				if (!delete) {
					throw new Exception("文件" + mappingXMLFilePath + "删除失败！");
				}
			}
		}

		myBatisGenerator.generate(this.progressCallback, contexts, fullyqualifiedTables);

		return new ExecutiveSimpleResult(ReturnDataCode.Ok.toMessage());
	}

	private String getMappingXmlFilePath(DatabaseGeneratorConfig generatorConfig, DataTableGeneratorConfig dataTableGeneratorConfig) {
		StringBuilder sb = new StringBuilder();
		sb.append(generatorConfig.getProjectFolder()).append("/");
		sb.append(generatorConfig.getMappingXmlTargetFolder()).append("/");
		String mappingXMLPackage = generatorConfig.getMappingXmlPackage();
		if (StringUtils.isNotEmpty(mappingXMLPackage)) {
			sb.append(mappingXMLPackage.replace(".", "/")).append("/");
		}
		if (StringUtils.isNotEmpty(dataTableGeneratorConfig.getMapperName())) {
			sb.append(dataTableGeneratorConfig.getMapperName()).append(".xml");
		} else {
			sb.append(dataTableGeneratorConfig.getDomainObjectName()).append("Mapper.xml");
		}

		return sb.toString();
	}

	public void setProgressCallback(ProgressCallback progressCallback) {
		this.progressCallback = progressCallback;
	}

	public void setIgnoredColumns(List<IgnoredColumn> ignoredColumns) {
		this.ignoredColumns = ignoredColumns;
	}

	public void setColumnOverrides(List<ColumnOverride> columnOverrides) {
		this.columnOverrides = columnOverrides;
	}
}
