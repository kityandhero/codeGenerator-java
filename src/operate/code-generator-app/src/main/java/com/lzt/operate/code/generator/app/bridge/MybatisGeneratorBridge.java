package com.lzt.operate.code.generator.app.bridge;

import com.lzt.operate.code.generator.app.plugins.DbRemarksCommentGenerator;
import com.lzt.operate.code.generator.app.util.DatabaseTypeUtil;
import com.lzt.operate.code.generator.app.util.ConfigHelper;
import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataBaseGeneratorConfig;
import com.lzt.operate.code.generator.entities.DataTableGeneratorConfig;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class MybatisGeneratorBridge {

	private static final Logger _LOG = LoggerFactory.getLogger(MybatisGeneratorBridge.class);

	private DataBaseGeneratorConfig generatorConfig;

	private ConnectionConfig selectedConnectionConfig;

	private ProgressCallback progressCallback;

	private List<IgnoredColumn> ignoredColumns;

	private List<ColumnOverride> columnOverrides;

	// public MybatisGeneratorBridge() {
	// }

	public void setGeneratorConfig(DataBaseGeneratorConfig generatorConfig) {
		this.generatorConfig = generatorConfig;
	}

	public void setConnectionConfig(ConnectionConfig ConnectionConfig) {
		this.selectedConnectionConfig = ConnectionConfig;
	}

	public ExecutiveSimpleResult generate(DataTableGeneratorConfig dataTableGeneratorConfig) throws Exception {
		Configuration configuration = new Configuration();
		Context context = new Context(ModelType.CONDITIONAL);
		configuration.addContext(context);

		context.addProperty("javaFileEncoding", "UTF-8");

		Optional<DatabaseType> optionalDatabaseType = DatabaseType.valueOfFlag(this.selectedConnectionConfig.getDatabaseType());

		if (!optionalDatabaseType.isPresent()) {
			return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("databaseType无效"));
		}
		DatabaseType databaseType = optionalDatabaseType.get();

		String connectorLibPath = ConfigHelper.findConnectorLibPath(databaseType.getName());

		MybatisGeneratorBridge._LOG.info("connectorLibPath: {}", connectorLibPath);
		configuration.addClasspathEntry(connectorLibPath);
		// Table configuration
		TableConfiguration tableConfig = new TableConfiguration(context);
		tableConfig.setTableName(dataTableGeneratorConfig.getTableName());
		tableConfig.setDomainObjectName(dataTableGeneratorConfig.getDomainObjectName());
		if (!ConstantCollection.ZERO_INT.equals(this.generatorConfig.getUseExample())) {
			tableConfig.setUpdateByExampleStatementEnabled(false);
			tableConfig.setCountByExampleStatementEnabled(false);
			tableConfig.setDeleteByExampleStatementEnabled(false);
			tableConfig.setSelectByExampleStatementEnabled(false);
		}

		context.addProperty("autoDelimitKeywords", "true");
		if (DatabaseType.MySQL.getFlag().equals(databaseType) || DatabaseType.MySQL_8.getFlag()
																					 .equals(databaseType)) {
			tableConfig.setSchema(this.selectedConnectionConfig.getSchema());
			// 由于beginningDelimiter和endingDelimiter的默认值为双引号(")，在Mysql中不能这么写，所以还要将这两个默认值改为`
			context.addProperty("beginningDelimiter", "`");
			context.addProperty("endingDelimiter", "`");
		} else {
			tableConfig.setCatalog(this.selectedConnectionConfig.getSchema());
		}
		if (ConstantCollection.ZERO_INT.equals(this.generatorConfig.getUseSchemaPrefix())) {
			if (DatabaseType.MySQL.getFlag().equals(databaseType) || DatabaseType.MySQL_8.getFlag()
																						 .equals(databaseType)) {
				tableConfig.setSchema(this.selectedConnectionConfig.getSchema());
			} else if (DatabaseType.Oracle.name().equals(databaseType)) {
				//Oracle的schema为用户名，如果连接用户拥有dba等高级权限，若不设schema，会导致把其他用户下同名的表也生成一遍导致mapper中代码重复
				tableConfig.setSchema(this.selectedConnectionConfig.getUserName());
			} else {
				tableConfig.setCatalog(this.selectedConnectionConfig.getSchema());
			}
		}
		// 针对 postgresql 单独配置
		if (DatabaseType.PostgreSQL.name().equals(databaseType)) {
			tableConfig.setDelimitIdentifiers(true);
		}

		//添加GeneratedKey主键生成
		if (StringUtils.isNotEmpty(this.generatorConfig.getGenerateKeys())) {

			if (DatabaseType.MySQL.name().equals(databaseType.getName()) || DatabaseType.MySQL_8.name()
																								.equals(databaseType.getName())) {

				//dbType为JDBC，且配置中开启useGeneratedKeys时，Mybatis会使用Jdbc3KeyGenerator,
				//使用该KeyGenerator的好处就是直接在一次INSERT 语句内，通过resultSet获取得到 生成的主键值，
				//并很好的支持设置了读写分离代理的数据库
				//例如阿里云RDS + 读写分离代理
				//无需指定主库
				//当使用SelectKey时，Mybatis会使用SelectKeyGenerator，INSERT之后，多发送一次查询语句，获得主键值
				//在上述读写分离被代理的情况下，会得不到正确的主键
				tableConfig.setGeneratedKey(new GeneratedKey(this.generatorConfig.getGenerateKeys(), "JDBC", true, null));
			} else {
				tableConfig.setGeneratedKey(new GeneratedKey(this.generatorConfig.getGenerateKeys(), databaseType.getName(), true, null));
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
		if (ConstantCollection.ZERO_INT.equals(this.generatorConfig.getUseActualColumnNames())) {
			tableConfig.addProperty("useActualColumnNames", "true");
		}

		if (ConstantCollection.ZERO_INT.equals(this.generatorConfig.getUseTableNameAlias())) {
			tableConfig.setAlias(dataTableGeneratorConfig.getTableName());
		}

		JDBCConnectionConfiguration jdbcConfig = new JDBCConnectionConfiguration();
		if (DatabaseType.MySQL.name().equals(databaseType.getName()) || DatabaseType.MySQL_8.name()
																							.equals(databaseType.getName())) {
			jdbcConfig.addProperty("nullCatalogMeansCurrent", "true");
		}
		jdbcConfig.setDriverClass(databaseType.getDriverClass());
		jdbcConfig.setConnectionURL(DatabaseTypeUtil.getConnectionUrlWithSchema(this.selectedConnectionConfig));
		jdbcConfig.setUserId(this.selectedConnectionConfig.getUserName());
		jdbcConfig.setPassword(this.selectedConnectionConfig.getPassword());
		if (DatabaseType.Oracle.name().equals(databaseType)) {
			jdbcConfig.getProperties().setProperty("remarksReporting", "true");
		}
		// java model
		JavaModelGeneratorConfiguration modelConfig = new JavaModelGeneratorConfiguration();
		modelConfig.setTargetPackage(this.generatorConfig.getModelPackage());
		modelConfig.setTargetProject(this.generatorConfig.getProjectFolder() + "/" + this.generatorConfig.getModelTargetFolder());
		// Mapper configuration
		SqlMapGeneratorConfiguration mapperConfig = new SqlMapGeneratorConfiguration();
		mapperConfig.setTargetPackage(this.generatorConfig.getMappingXmlPackage());
		mapperConfig.setTargetProject(this.generatorConfig.getProjectFolder() + "/" + this.generatorConfig.getMappingXmlTargetFolder());
		// DAO
		JavaClientGeneratorConfiguration daoConfig = new JavaClientGeneratorConfiguration();
		daoConfig.setConfigurationType("XMLMAPPER");
		daoConfig.setTargetPackage(this.generatorConfig.getDaoPackage());
		daoConfig.setTargetProject(this.generatorConfig.getProjectFolder() + "/" + this.generatorConfig.getDaoTargetFolder());

		context.setId("myid");
		context.addTableConfiguration(tableConfig);
		context.setJdbcConnectionConfiguration(jdbcConfig);
		context.setJavaModelGeneratorConfiguration(modelConfig);
		context.setSqlMapGeneratorConfiguration(mapperConfig);
		context.setJavaClientGeneratorConfiguration(daoConfig);
		// Comment
		CommentGeneratorConfiguration commentConfig = new CommentGeneratorConfiguration();
		commentConfig.setConfigurationType(DbRemarksCommentGenerator.class.getName());
		if (ConstantCollection.ZERO_INT.equals(dataTableGeneratorConfig.getComment())) {
			commentConfig.addProperty("columnRemarks", "true");
		}
		if (ConstantCollection.ZERO_INT.equals(this.generatorConfig.getAnnotation())) {
			commentConfig.addProperty("annotations", "true");
		}
		context.setCommentGeneratorConfiguration(commentConfig);
		// set java file encoding
		context.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, this.generatorConfig.getEncoding());

		//实体添加序列化
		PluginConfiguration serializablePluginConfiguration = new PluginConfiguration();
		serializablePluginConfiguration.addProperty("type", "org.mybatis.generator.plugins.SerializablePlugin");
		serializablePluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
		context.addPluginConfiguration(serializablePluginConfiguration);
		// toString, hashCode, equals插件
		if (ConstantCollection.ZERO_INT.equals(this.generatorConfig.getNeedToStringHashCodeEquals())) {
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
		if (ConstantCollection.ZERO_INT.equals(this.generatorConfig.getOffsetLimit())) {
			if (DatabaseType.MySQL.name().equals(databaseType) || DatabaseType.MySQL_8.name()
																					  .equals(databaseType)
					|| DatabaseType.PostgreSQL.name().equals(databaseType)) {
				PluginConfiguration pluginConfiguration = new PluginConfiguration();
				pluginConfiguration.addProperty("type", "com.zzg.mybatis.generator.plugins.MySQLLimitPlugin");
				pluginConfiguration.setConfigurationType("com.zzg.mybatis.generator.plugins.MySQLLimitPlugin");
				context.addPluginConfiguration(pluginConfiguration);
			}
		}
		//for JSR310
		if (ConstantCollection.ZERO_INT.equals((this.generatorConfig.getJsr310Support()))) {
			JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
			javaTypeResolverConfiguration.setConfigurationType("com.zzg.mybatis.generator.plugins.JavaTypeResolverJsr310Impl");
			context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
		}
		//forUpdate 插件
		if (ConstantCollection.ZERO_INT.equals(this.generatorConfig.getNeedForUpdate())) {
			if (DatabaseType.MySQL.name().equals(databaseType)
					|| DatabaseType.PostgreSQL.name().equals(databaseType)) {
				PluginConfiguration pluginConfiguration = new PluginConfiguration();
				pluginConfiguration.addProperty("type", "com.zzg.mybatis.generator.plugins.MySQLForUpdatePlugin");
				pluginConfiguration.setConfigurationType("com.zzg.mybatis.generator.plugins.MySQLForUpdatePlugin");
				context.addPluginConfiguration(pluginConfiguration);
			}
		}
		//repository 插件
		if (ConstantCollection.ZERO_INT.equals(this.generatorConfig.getAnnotationDAO())) {
			if (DatabaseType.MySQL.name().equals(databaseType) || DatabaseType.MySQL_8.name()
																					  .equals(databaseType)
					|| DatabaseType.PostgreSQL.name().equals(databaseType)) {
				PluginConfiguration pluginConfiguration = new PluginConfiguration();
				pluginConfiguration.addProperty("type", "com.zzg.mybatis.generator.plugins.RepositoryPlugin");
				pluginConfiguration.setConfigurationType("com.zzg.mybatis.generator.plugins.RepositoryPlugin");
				context.addPluginConfiguration(pluginConfiguration);
			}
		}
		if (ConstantCollection.ZERO_INT.equals(this.generatorConfig.getUseDAOExtendStyle())) {
			if (DatabaseType.MySQL.name().equals(databaseType) || DatabaseType.MySQL_8.name()
																					  .equals(databaseType)
					|| DatabaseType.PostgreSQL.name().equals(databaseType)) {
				PluginConfiguration pluginConfiguration = new PluginConfiguration();
				pluginConfiguration.addProperty("useExample", String.valueOf(ConstantCollection.ZERO_INT.equals(this.generatorConfig
						.getUseExample())));
				pluginConfiguration.addProperty("type", "com.zzg.mybatis.generator.plugins.CommonDAOInterfacePlugin");
				pluginConfiguration.setConfigurationType("com.zzg.mybatis.generator.plugins.CommonDAOInterfacePlugin");
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
		if (ConstantCollection.ZERO_INT.equals(this.generatorConfig.getOverrideXML())) {
			String mappingXMLFilePath = this.getMappingXMLFilePath(this.generatorConfig, dataTableGeneratorConfig);
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

	private String getMappingXMLFilePath(DataBaseGeneratorConfig generatorConfig, DataTableGeneratorConfig dataTableGeneratorConfig) {
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
