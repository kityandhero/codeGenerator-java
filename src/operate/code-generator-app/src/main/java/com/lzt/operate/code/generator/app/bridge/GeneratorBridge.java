package com.lzt.operate.code.generator.app.bridge;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.lzt.operate.code.generator.app.assists.DatabaseAssist;
import com.lzt.operate.code.generator.app.util.DatabaseTypeUtil;
import com.lzt.operate.code.generator.common.config.mybatis.generator.MybatisGeneratorGlobalConfig;
import com.lzt.operate.code.generator.common.config.mybatisplus.generator.MybatisPlusGeneratorGlobalConfig;
import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.code.generator.common.enums.FileEncoding;
import com.lzt.operate.code.generator.common.enums.mybatis.DaoType;
import com.lzt.operate.code.generator.common.enums.mybatis.GeneratorType;
import com.lzt.operate.code.generator.common.pojos.DataTable;
import com.lzt.operate.code.generator.common.utils.ConfigHelper;
import com.lzt.operate.code.generator.dao.service.DataColumnService;
import com.lzt.operate.code.generator.dao.service.DataTableGeneratorConfigService;
import com.lzt.operate.code.generator.dao.service.DatabaseGeneratorConfigService;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataColumn;
import com.lzt.operate.code.generator.entities.DataTableGeneratorConfig;
import com.lzt.operate.code.generator.entities.DatabaseGeneratorConfig;
import com.lzt.operate.mybatis.base.model.BaseModel;
import com.lzt.operate.mybatis.custom.config.ContextCustom;
import com.lzt.operate.mybatis.custom.config.JavaServiceGeneratorConfiguration;
import com.lzt.operate.mybatis.custom.config.xml.ConfigurationCustom;
import com.lzt.operate.mybatis.custom.plugins.CommentGeneratorCustom;
import com.lzt.operate.mybatis.custom.plugins.CommonDAOInterfacePlugin;
import com.lzt.operate.mybatis.custom.plugins.JavaTypeResolverJsr310Impl;
import com.lzt.operate.mybatis.custom.plugins.RepositoryPlugin;
import com.lzt.operate.mybatis.custom.plugins.SerializablePlugin;
import com.lzt.operate.mybatis.custom.plugins.mysql.LimitPlugin;
import com.lzt.operate.mybatis.custom.plugins.mysql.UpdatePlugin;
import com.lzt.operate.mybatis.plus.custom.config.DataSourceConfigEx;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.enums.Whether;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.pojo.ReturnMessage;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.ColumnOverride;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
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
import org.mybatis.generator.plugins.ToStringPlugin;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// 选择一个table来生成相关文件，可以有一个或多个table，必须要有table元素
// 选择的table会生成一下文件：
// 1，SQL map文件
// 2，生成一个主键类；
// 3，除了BLOB和主键的其他字段的类；
// 4，包含BLOB的类；
// 5，一个用户生成动态查询的条件类（selectByExample, deleteByExample），可选；
// 6，Mapper接口（可选）
//
// tableName（必要）：要生成对象的表名；
// 注意：大小写敏感问题。正常情况下，MBG会自动的去识别数据库标识符的大小写敏感度，在一般情况下，MBG会
// 根据设置的schema，catalog或tablename去查询数据表，按照下面的流程：
// 1，如果schema，catalog或tablename中有空格，那么设置的是什么格式，就精确的使用指定的大小写格式去查询；
// 2，否则，如果数据库的标识符使用大写的，那么MBG自动把表名变成大写再查找；
// 3，否则，如果数据库的标识符使用小写的，那么MBG自动把表名变成小写再查找；
// 4，否则，使用指定的大小写格式查询；
// 另外的，如果在创建表的时候，使用的""把数据库对象规定大小写，就算数据库标识符是使用的大写，在这种情况下也会使用给定的大小写来创建表名；
// 这个时候，请设置delimitIdentifiers="true"即可保留大小写格式；
//
// 可选：
// 1，schema：数据库的schema；
// 2，catalog：数据库的catalog；
// 3，alias：为数据表设置的别名，如果设置了alias，那么生成的所有的SELECT SQL语句中，列名会变成：alias_actualColumnName
// 4，domainObjectName：生成的domain类的名字，如果不设置，直接使用表名作为domain类的名字；可以设置为somepck.domainName，那么会自动把domainName类再放到somepck包里面；
// 5，enableInsert（默认true）：指定是否生成insert语句；
// 6，enableSelectByPrimaryKey（默认true）：指定是否生成按照主键查询对象的语句（就是getById或get）；
// 7，enableSelectByExample（默认true）：MyBatis3Simple为false，指定是否生成动态查询语句；
// 8，enableUpdateByPrimaryKey（默认true）：指定是否生成按照主键修改对象的语句（即update)；
// 9，enableDeleteByPrimaryKey（默认true）：指定是否生成按照主键删除对象的语句（即delete）；
// 10，enableDeleteByExample（默认true）：MyBatis3Simple为false，指定是否生成动态删除语句；
// 11，enableCountByExample（默认true）：MyBatis3Simple为false，指定是否生成动态查询总条数语句（用于分页的总条数查询）；
// 12，enableUpdateByExample（默认true）：MyBatis3Simple为false，指定是否生成动态修改语句（只修改对象中不为空的属性）；
// 13，modelType：参考context元素的defaultModelType，相当于覆盖；
// 14，delimitIdentifiers：参考tableName的解释，注意，默认的delimitIdentifiers是双引号，如果类似MYSQL这样的数据库，使用的是`（反引号，那么还需要设置context的beginningDelimiter和endingDelimiter属性）
// 15，delimitAllColumns：设置是否所有生成的SQL中的列名都使用标识符引起来。默认为false，delimitIdentifiers参考context的属性
//
// 注意，table里面很多参数都是对javaModelGenerator，context等元素的默认属性的一个复写

/**
 * The bridge between GUI and the mybatis generator. All the operation to  mybatis generator should proceed through this
 * class
 * <p>
 *
 * @author luzhitao
 */
@Slf4j
public class GeneratorBridge {

	private final DatabaseGeneratorConfigService databaseGeneratorConfigService;

	private final DataTableGeneratorConfigService dataTableGeneratorConfigService;

	private final DataColumnService dataColumnService;

	private final ConnectionConfig connectionConfig;

	private ProgressCallback progressCallback;

	public GeneratorBridge(
			@NotNull ConnectionConfig selectedConnectionConfig,
			@NotNull DatabaseGeneratorConfigService databaseGeneratorConfigService,
			@NotNull DataTableGeneratorConfigService dataTableGeneratorConfigService,
			@NotNull DataColumnService dataColumnService) {
		this.connectionConfig = selectedConnectionConfig;
		this.databaseGeneratorConfigService = databaseGeneratorConfigService;
		this.dataTableGeneratorConfigService = dataTableGeneratorConfigService;
		this.dataColumnService = dataColumnService;
	}

	private DatabaseGeneratorConfig getDataBaseGeneratorConfig() throws RuntimeException {
		Optional<DatabaseGeneratorConfig> optional = this.databaseGeneratorConfigService.findByConnectionConfigId(this.connectionConfig
				.getId());

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("数据连接缺少相关生成配置");
	}

	private ExecutiveSimpleResult checkFolder(@NotNull DatabaseGeneratorConfig databaseGeneratorConfig) {
		MybatisGeneratorGlobalConfig mybatisGeneratorGlobalConfig = databaseGeneratorConfig.BuildMybatisGeneratorGlobalConfig();

		String projectFolder = Optional.ofNullable(mybatisGeneratorGlobalConfig.getProjectFolder()).orElse("").trim();
		String modelTargetFolder = Optional.ofNullable(mybatisGeneratorGlobalConfig.getModelTargetFolder())
										   .orElse("")
										   .trim();
		String daoTargetFolder = Optional.ofNullable(mybatisGeneratorGlobalConfig.getDaoTargetFolder())
										 .orElse("")
										 .trim();
		String mappingXmlTargetFolder = Optional.ofNullable(mybatisGeneratorGlobalConfig.getMappingXmlTargetFolder())
												.orElse("").trim();
		String serviceTargetFolder = Optional.ofNullable(mybatisGeneratorGlobalConfig.getServiceTargetFolder())
											 .orElse("").trim();

		if (StringAssist.isNullOrEmpty(projectFolder)) {
			return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("请先配置项目文件夹"));
		}

		File file = new File(projectFolder);

		if (!file.exists()) {
			boolean mkdirsResult = file.mkdirs();

			if (!mkdirsResult) {
				return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("创建项目文件夹失败：" + file.getAbsolutePath()));
			}
		}

		if (!file.isDirectory()) {
			return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("项目文件夹路径无效"));
		}

		modelTargetFolder = Optional.of(modelTargetFolder).orElse("");

		if (!StringAssist.isNullOrEmpty(modelTargetFolder)) {
			String modelFolder;

			int modelTargetFolderRelativeMode = mybatisGeneratorGlobalConfig.getModelTargetFolderRelativeMode();

			if (Whether.Yes.getFlag().equals(modelTargetFolderRelativeMode)) {
				if (StringAssist.contains(modelTargetFolder, ConstantCollection.EMPTY_STRING)) {
					return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("model文件夹不能含有空格"));
				}

				modelFolder = StringAssist.merge(projectFolder, modelTargetFolder);
			} else {
				modelFolder = modelTargetFolder;
			}

			file = new File(modelFolder);

			if (!file.exists()) {
				boolean mkdirsResult = file.mkdirs();

				if (!mkdirsResult) {
					return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("创建model文件夹失败：" + file.getAbsolutePath()));
				}
			}

			if (!file.isDirectory()) {
				return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("model文件夹路径无效"));
			}
		}

		daoTargetFolder = Optional.of(daoTargetFolder).orElse("");

		if (!StringAssist.isNullOrEmpty(daoTargetFolder)) {
			String daoFolder;
			int daoTargetFolderRelativeMode = mybatisGeneratorGlobalConfig.getDaoTargetFolderRelativeMode();

			if (Whether.Yes.getFlag().equals(daoTargetFolderRelativeMode)) {
				if (StringAssist.contains(daoTargetFolder, ConstantCollection.EMPTY_STRING)) {
					return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("dao文件夹不能含有空格"));
				}

				daoFolder = StringAssist.merge(projectFolder, daoTargetFolder);
			} else {
				daoFolder = daoTargetFolder;
			}

			file = new File(daoFolder);

			if (!file.exists()) {
				boolean mkdirsResult = file.mkdirs();

				if (!mkdirsResult) {
					return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("创建dao文件夹失败：" + file.getAbsolutePath()));
				}
			}

			if (!file.isDirectory()) {
				return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("dao文件夹路径无效"));
			}
		}

		mappingXmlTargetFolder = Optional.of(mappingXmlTargetFolder).orElse("");

		if (!StringAssist.isNullOrEmpty(mappingXmlTargetFolder)) {
			String mappingXmlFolder;
			int mappingXmlTargetFolderRelativeMode = mybatisGeneratorGlobalConfig.getMappingXmlTargetFolderRelativeMode();

			if (Whether.Yes.getFlag().equals(mappingXmlTargetFolderRelativeMode)) {
				if (StringAssist.contains(mappingXmlTargetFolder, ConstantCollection.EMPTY_STRING)) {
					return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("mappingXml文件夹不能含有空格"));
				}

				mappingXmlFolder = StringAssist.merge(projectFolder, mappingXmlTargetFolder);
			} else {
				mappingXmlFolder = mappingXmlTargetFolder;
			}

			file = new File(mappingXmlFolder);

			if (!file.exists()) {
				boolean mkdirsResult = file.mkdirs();

				if (!mkdirsResult) {
					return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("创建mappingXml文件夹失败：" + file.getAbsolutePath()));
				}
			}

			if (!file.isDirectory()) {
				return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("mappingXml文件夹路径无效"));
			}
		}

		serviceTargetFolder = Optional.of(serviceTargetFolder).orElse("");

		if (!StringAssist.isNullOrEmpty(serviceTargetFolder)) {
			String serviceFolder;
			int serviceTargetFolderRelativeMode = mybatisGeneratorGlobalConfig.getServiceTargetFolderRelativeMode();

			if (Whether.Yes.getFlag().equals(serviceTargetFolderRelativeMode)) {
				if (StringAssist.contains(serviceTargetFolder, ConstantCollection.EMPTY_STRING)) {
					return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("service文件夹不能含有空格"));
				}

				serviceFolder = StringAssist.merge(projectFolder, serviceTargetFolder);
			} else {
				serviceFolder = serviceTargetFolder;
			}

			file = new File(serviceFolder);

			if (!file.exists()) {
				boolean mkdirsResult = file.mkdirs();

				if (!mkdirsResult) {
					return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("创建service文件夹失败"));
				}
			}

			if (!file.isDirectory()) {
				return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("service文件夹路径无效：" + file.getAbsolutePath()));
			}
		}

		return new ExecutiveSimpleResult(ReturnDataCode.Ok.toMessage());
	}

	public ExecutiveSimpleResult generateAll() throws Exception {
		DatabaseGeneratorConfig databaseGeneratorConfig = this.getDataBaseGeneratorConfig();

		ExecutiveSimpleResult checkFolderResult = this.checkFolder(databaseGeneratorConfig);

		if (!checkFolderResult.getSuccess()) {
			return new ExecutiveSimpleResult(checkFolderResult.getCode());
		}

		List<String> listError = new ArrayList<>();

		ConnectionConfig connectionConfig = this.connectionConfig;
		List<DataTable> listDataTable = DatabaseAssist.listDataTable(connectionConfig);

		Specification<DataTableGeneratorConfig> specification = new Specification<DataTableGeneratorConfig>() {

			private static final long serialVersionUID = 5826322529864777111L;

			@Override
			public Predicate toPredicate(@NonNull Root<DataTableGeneratorConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getConnectionConfigId)), connectionConfig
						.getId()));

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getDatabaseGeneratorConfigId)), databaseGeneratorConfig
						.getId()));

				CriteriaBuilder.In<String> in = criteriaBuilder.in(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getTableName)));

				listDataTable.forEach(o -> in.value(o.getName()));

				list.add(in);

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		List<DataTableGeneratorConfig> list = this.dataTableGeneratorConfigService.list(specification);

		if (ConstantCollection.ZERO_INT.equals(list.size())) {
			return new ExecutiveSimpleResult(ReturnDataCode.DataError.toMessage("没有任何数据表进行过生成初始化，请进入数据库表页"));
		}

		for (DataTableGeneratorConfig item : list) {
			ExecutiveSimpleResult itemResult = this.generateCore(databaseGeneratorConfig, item);

			if (!itemResult.getSuccess()) {

				listError.add(itemResult.getMessage());
			}
		}

		ReturnMessage returnMessage = listError.size() > 0 ? ReturnDataCode.DataError.toMessage() : ReturnDataCode.Ok.toMessage();

		return new ExecutiveSimpleResult(returnMessage.toMessage(StringAssist.join(listError)));
	}

	public ExecutiveSimpleResult generate(@NotNull DataTableGeneratorConfig dataTableGeneratorConfig) throws Exception {
		DatabaseGeneratorConfig databaseGeneratorConfig = this.getDataBaseGeneratorConfig();

		ExecutiveSimpleResult checkFolderResult = this.checkFolder(databaseGeneratorConfig);

		if (!checkFolderResult.getSuccess()) {
			return checkFolderResult;
		}

		return this.generateCore(databaseGeneratorConfig, dataTableGeneratorConfig);
	}

	private ExecutiveSimpleResult generateCore(@NotNull DatabaseGeneratorConfig databaseGeneratorConfig, @NotNull DataTableGeneratorConfig dataTableGeneratorConfig) throws Exception {
		Long connectionConfigId = databaseGeneratorConfig.getConnectionConfigId();

		if (!connectionConfigId.equals(dataTableGeneratorConfig.getConnectionConfigId())) {
			return new ExecutiveResult<>(ReturnDataCode.Exception.toMessage("数据表生成配置与数据连接配置不相配"));
		}

		Optional<GeneratorType> optionalGeneratorType = GeneratorType.valueOfFlag(databaseGeneratorConfig.getGeneratorType());

		if (!optionalGeneratorType.isPresent()) {
			return new ExecutiveSimpleResult(ReturnDataCode.DataError.toMessage(StringAssist.merge("错误的生成器类型配置")));
		}

		GeneratorType generatorType = optionalGeneratorType.get();

		if (GeneratorType.MybatisGenerator.getFlag().equals(generatorType.getFlag())) {
			return this.generateByMybatisGeneratorCore(databaseGeneratorConfig, dataTableGeneratorConfig);
		}

		if (GeneratorType.MybatisPlusGenerator.getFlag().equals(generatorType.getFlag())) {
			return this.generateByMybatisPlusGeneratorCore(databaseGeneratorConfig, dataTableGeneratorConfig);
		}

		return new ExecutiveSimpleResult(ReturnDataCode.NoChange.toMessage(StringAssist.merge("暂不支持生成器：", generatorType.getName())));
	}

	private ExecutiveSimpleResult generateByMybatisGeneratorCore(@NotNull DatabaseGeneratorConfig databaseGeneratorConfig, @NotNull DataTableGeneratorConfig dataTableGeneratorConfig) throws Exception {
		MybatisGeneratorGlobalConfig mybatisGeneratorGlobalConfig = databaseGeneratorConfig.BuildMybatisGeneratorGlobalConfig();

		Optional<DaoType> optionalDaoType = DaoType.valueOfFlag(mybatisGeneratorGlobalConfig.getDaoType());

		if (!optionalDaoType.isPresent()) {
			return new ExecutiveResult<>(ReturnDataCode.Exception.toMessage("无效的daoType设置"));
		}

		DaoType daoType = optionalDaoType.get();

		String modelTargetFolder = Optional.ofNullable(mybatisGeneratorGlobalConfig.getModelTargetFolder())
										   .orElse("")
										   .trim();
		int modelTargetFolderRelativeMode1 = mybatisGeneratorGlobalConfig.getModelTargetFolderRelativeMode();
		String daoTargetFolder = Optional.ofNullable(mybatisGeneratorGlobalConfig.getDaoTargetFolder())
										 .orElse("")
										 .trim();
		int daoTargetFolderRelativeMode1 = mybatisGeneratorGlobalConfig.getDaoTargetFolderRelativeMode();
		String mappingXmlTargetFolder = Optional.ofNullable(mybatisGeneratorGlobalConfig.getMappingXmlTargetFolder())
												.orElse("").trim();
		int mappingXmlTargetFolderRelativeMode1 = mybatisGeneratorGlobalConfig.getMappingXmlTargetFolderRelativeMode();
		String serviceTargetFolder = Optional.ofNullable(mybatisGeneratorGlobalConfig.getServiceTargetFolder())
											 .orElse("").trim();
		int serviceTargetFolderRelativeMode1 = mybatisGeneratorGlobalConfig.getServiceTargetFolderRelativeMode();

		Optional<FileEncoding> optionalFileEncoding = FileEncoding.valueOfFlag(mybatisGeneratorGlobalConfig.getEncoding());

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

		ConfigurationCustom configuration = new ConfigurationCustom();

		ContextCustom context = new ContextCustom(ModelType.CONDITIONAL);

		configuration.addContext(context);
		context.addProperty("javaFileEncoding", fileEncoding.getName());

		String connectorLibPath = ConfigHelper.findConnectorLibPath(databaseType.getName());

		configuration.addClasspathEntry(connectorLibPath);

		// Table configuration
		TableConfiguration tableConfig = new TableConfiguration(context);

		tableConfig.setTableName(tableName);
		tableConfig.setDomainObjectName(Optional.ofNullable(dataTableGeneratorConfig.getDomainObjectName()).orElse(""));

		// boolean useExample = Whether.Yes.getFlag().equals(dataTableGeneratorConfig.getUseExample());

		// tableConfig.setUpdateByExampleStatementEnabled(useExample);
		// tableConfig.setCountByExampleStatementEnabled(useExample);
		// tableConfig.setDeleteByExampleStatementEnabled(useExample);
		// tableConfig.setSelectByExampleStatementEnabled(useExample);

		tableConfig.setUpdateByExampleStatementEnabled(true);
		tableConfig.setCountByExampleStatementEnabled(true);
		tableConfig.setDeleteByExampleStatementEnabled(true);
		tableConfig.setSelectByExampleStatementEnabled(true);

		List<ColumnOverride> columnOverrideList = this.buildColumnOverrideList(dataTableGeneratorConfig);

		for (ColumnOverride columnOverride : columnOverrideList) {
			tableConfig.addColumnOverride(columnOverride);
		}

		List<IgnoredColumn> ignoredColumnList = this.buildIgnoredColumnList(dataTableGeneratorConfig);

		for (IgnoredColumn ignoredColumn : ignoredColumnList) {
			tableConfig.addIgnoredColumn(ignoredColumn);
		}

		// 自动识别数据库关键字，默认false，如果设置为true，根据SqlReservedWords中定义的关键字列表；
		//         一般保留默认值，遇到数据库关键字（Java关键字），使用columnOverride覆盖
		context.addProperty("autoDelimitKeywords", String.valueOf(Whether.Yes.getFlag()
																			 .equals(mybatisGeneratorGlobalConfig
																					 .getAutoDelimitKeywords())));

		if (DatabaseType.MySQL.getFlag().equals(databaseType.getFlag()) || DatabaseType.MySQL_8.getFlag()
																							   .equals(databaseType.getFlag())) {
			// 由于beginningDelimiter和endingDelimiter的默认值为双引号(")，在Mysql中不能这么写，所以还要将这两个默认值改为`
			context.addProperty("beginningDelimiter", "`");
			context.addProperty("endingDelimiter", "`");
		}

		boolean useSchemaPrefix = Whether.Yes.getFlag().equals(mybatisGeneratorGlobalConfig.getUseSchemaPrefix());

		if (useSchemaPrefix) {
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

		boolean useGenerateKey = Whether.Yes.getFlag().equals(dataTableGeneratorConfig.getUseGenerateKey());

		//添加GeneratedKey主键生成
		if (useGenerateKey) {
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

		String mapperExtensionName = Optional.ofNullable(mybatisGeneratorGlobalConfig.getMapperExtensionName())
											 .orElse("");

		if (!StringAssist.isNullOrEmpty(mapperExtensionName)) {
			String mapperName = StringAssist.merge(StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getDomainObjectName()) ? dataTableGeneratorConfig
					.getTableName() : dataTableGeneratorConfig.getDomainObjectName(), mapperExtensionName);
			tableConfig.setMapperName(mapperName);
		}

		boolean useActualColumnNames = Whether.Yes.getFlag().equals(dataTableGeneratorConfig.getUseActualColumnNames());
		// 如果设置为true，生成的model类会直接使用column本身的名字，而不会再使用驼峰命名方法，比如BORN_DATE，生成的属性名字就是BORN_DATE,而不会是bornDate
		tableConfig.addProperty("useActualColumnNames", String.valueOf(useActualColumnNames));

		if (Whether.No.getFlag().equals(dataTableGeneratorConfig.getUseTableNameAlias())) {
			tableConfig.setAlias(dataTableGeneratorConfig.getTableName());
		} else {
			tableConfig.setAlias(dataTableGeneratorConfig.getAliasName());
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
		modelConfig.setTargetPackage(mybatisGeneratorGlobalConfig.getModelPackage());

		if (StringAssist.isNullOrEmpty(modelTargetFolder)) {
			modelConfig.setTargetProject(mybatisGeneratorGlobalConfig.getProjectFolder());
		} else {
			if (Whether.Yes.getFlag().equals(modelTargetFolderRelativeMode1)) {
				modelConfig.setTargetProject(mybatisGeneratorGlobalConfig.getProjectFolder() + "/" + modelTargetFolder);
			} else {
				modelConfig.setTargetProject(modelTargetFolder);
			}
		}

		modelConfig.getProperties().setProperty("enableSubPackages", "false");
		modelConfig.getProperties().setProperty("rootClass", BaseModel.class.getName());
		modelConfig.getProperties().setProperty("trimStrings", "true");

		// Mapper configuration
		SqlMapGeneratorConfiguration mapperConfig = new SqlMapGeneratorConfiguration();
		mapperConfig.setTargetPackage(mybatisGeneratorGlobalConfig.getMappingXmlPackage());

		if (StringAssist.isNullOrEmpty(mappingXmlTargetFolder)) {
			mapperConfig.setTargetProject(mybatisGeneratorGlobalConfig.getProjectFolder());
		} else {
			if (Whether.Yes.getFlag().equals(mappingXmlTargetFolderRelativeMode1)) {
				mapperConfig.setTargetProject(mybatisGeneratorGlobalConfig.getProjectFolder() + "/" + mappingXmlTargetFolder);
			} else {
				mapperConfig.setTargetProject(mappingXmlTargetFolder);
			}
		}

		mapperConfig.getProperties().setProperty("enableSubPackages", "false");

		// dao
		JavaClientGeneratorConfiguration daoConfig = new JavaClientGeneratorConfiguration();
		daoConfig.setConfigurationType(daoType.getType());
		daoConfig.setTargetPackage(mybatisGeneratorGlobalConfig.getDaoPackage());

		if (StringAssist.isNullOrEmpty(daoTargetFolder)) {
			daoConfig.setTargetProject(mybatisGeneratorGlobalConfig.getProjectFolder());
		} else {
			if (Whether.Yes.getFlag().equals(daoTargetFolderRelativeMode1)) {
				daoConfig.setTargetProject(mybatisGeneratorGlobalConfig.getProjectFolder() + "/" + daoTargetFolder);
			} else {
				daoConfig.setTargetProject(daoTargetFolder);
			}
		}

		daoConfig.getProperties().setProperty("enableSubPackages", "false");

		// service
		JavaServiceGeneratorConfiguration serviceConfig = new JavaServiceGeneratorConfiguration();
		serviceConfig.setTargetPackage(mybatisGeneratorGlobalConfig.getServicePackage());
		serviceConfig.setImplementationPackage(mybatisGeneratorGlobalConfig.getServicePackage() + ".impl");

		if (StringAssist.isNullOrEmpty(serviceTargetFolder)) {
			serviceConfig.setTargetProject(mybatisGeneratorGlobalConfig.getProjectFolder());
		} else {
			if (Whether.Yes.getFlag().equals(serviceTargetFolderRelativeMode1)) {
				serviceConfig.setTargetProject(mybatisGeneratorGlobalConfig.getProjectFolder() + "/" + serviceTargetFolder);
			} else {
				serviceConfig.setTargetProject(serviceTargetFolder);
			}
		}

		serviceConfig.getProperties().setProperty("enableSubPackages", "false");

		context.setId("myContext");
		context.addTableConfiguration(tableConfig);
		context.setJdbcConnectionConfiguration(jdbcConfig);
		context.setJavaModelGeneratorConfiguration(modelConfig);
		context.setSqlMapGeneratorConfiguration(mapperConfig);
		context.setJavaClientGeneratorConfiguration(daoConfig);
		context.setJavaServiceGeneratorConfiguration(serviceConfig);

		context.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, fileEncoding.getName());

		// CommentGenerator
		{
			CommentGeneratorConfiguration commentGeneratorCustomConfiguration = new CommentGeneratorConfiguration();

			String commentGeneratorCustomConfigurationName = CommentGeneratorCustom.class.getName();

			commentGeneratorCustomConfiguration.setConfigurationType(commentGeneratorCustomConfigurationName);

			context.setCommentGeneratorConfiguration(commentGeneratorCustomConfiguration);
		}

		// PluginChain
		{
			PluginConfiguration pluginChainConfiguration = new PluginConfiguration();

			String pluginChainName = com.lzt.operate.mybatis.custom.plugins.PluginChain.class.getName();

			pluginChainConfiguration.setConfigurationType(pluginChainName);

			context.addPluginConfiguration(pluginChainConfiguration);
		}

		//实体添加序列化
		{
			PluginConfiguration serializablePluginConfiguration = new PluginConfiguration();

			String serializablePluginConfigurationName = SerializablePlugin.class.getName();

			serializablePluginConfiguration.setConfigurationType(serializablePluginConfigurationName);

			context.addPluginConfiguration(serializablePluginConfiguration);
		}

		// ToStringPlugin
		{
			PluginConfiguration toStringPluginConfiguration = new PluginConfiguration();

			String toStringPluginConfigurationName = ToStringPlugin.class.getName();

			toStringPluginConfiguration.setConfigurationType(toStringPluginConfigurationName);

			context.addPluginConfiguration(toStringPluginConfiguration);
		}

		// toString, hashCode, equals插件
		if (Whether.Yes.getFlag().equals(mybatisGeneratorGlobalConfig.getNeedToStringHashCodeEquals())) {
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
		if (Whether.Yes.getFlag().equals(mybatisGeneratorGlobalConfig.getOffsetLimit())) {
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
		if (Whether.Yes.getFlag().equals((mybatisGeneratorGlobalConfig.getJsr310Support()))) {
			JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();

			String javaTypeResolverJsr310ImplName = JavaTypeResolverJsr310Impl.class.getName();

			javaTypeResolverConfiguration.setConfigurationType(javaTypeResolverJsr310ImplName);

			context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
		}

		//forUpdate 插件
		if (Whether.Yes.getFlag().equals(mybatisGeneratorGlobalConfig.getNeedForUpdate())) {
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
		if (Whether.Yes.getFlag().equals(mybatisGeneratorGlobalConfig.getAnnotationDAO())) {
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
		if (Whether.Yes.getFlag().equals(mybatisGeneratorGlobalConfig.getUseDAOExtendStyle())) {
			if (DatabaseType.MySQL.name().equals(databaseType.getName()) || DatabaseType.MySQL_8.name()
																								.equals(databaseType.getName())
					|| DatabaseType.PostgreSQL.name().equals(databaseType.getName())) {
				PluginConfiguration pluginConfiguration = new PluginConfiguration();
				pluginConfiguration.addProperty("useExample", "true");

				String commonInterfacePluginName = CommonDAOInterfacePlugin.class.getName();

				pluginConfiguration.addProperty("type", commonInterfacePluginName);
				pluginConfiguration.setConfigurationType(commonInterfacePluginName);

				context.addPluginConfiguration(pluginConfiguration);
			}
		}

		context.setTargetRuntime("MyBatis3");

		List<String> warnings = new ArrayList<>();
		Set<String> fullyQualifiedTables = new HashSet<>();
		Set<String> contexts = new HashSet<>();

		//override=true
		ShellCallback shellCallback = new DefaultShellCallback(true);

		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, shellCallback, warnings);

		// if overrideXML selected, delete oldXML ang generate new one
		if (Whether.Yes.getFlag().equals(mybatisGeneratorGlobalConfig.getOverrideXML())) {
			String mappingXmlFilePath = this.getMappingXmlFilePath(databaseGeneratorConfig, dataTableGeneratorConfig);
			File mappingXmlFile = new File(mappingXmlFilePath);
			if (mappingXmlFile.exists()) {
				boolean delete = mappingXmlFile.delete();

				if (!delete) {
					throw new Exception("文件" + mappingXmlFilePath + "删除失败！");
				}
			}
		}

		myBatisGenerator.generate(this.progressCallback, contexts, fullyQualifiedTables);

		List<GeneratedJavaFile> javaFileList = myBatisGenerator.getGeneratedJavaFiles();

		String modelFileName = StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getDomainObjectName()) ? dataTableGeneratorConfig
				.getTableName() : dataTableGeneratorConfig.getDomainObjectName();

		String exampleFileName = StringAssist.merge(modelFileName, "Example");

		mapperExtensionName = StringAssist.isNullOrEmpty(mybatisGeneratorGlobalConfig
				.getMapperExtensionName()) ? "Mapper" : mybatisGeneratorGlobalConfig.getMapperExtensionName();

		String mapperFileName = StringAssist.merge(modelFileName, mapperExtensionName);

		String serviceFileName = StringAssist.merge(modelFileName, "Service");
		String serviceImplFileName = StringAssist.merge(modelFileName, "ServiceImpl");

		for (GeneratedJavaFile file : javaFileList) {
			String fileBaseName = org.apache.commons.io.FilenameUtils.getBaseName(file.getFileName());

			if (modelFileName.toLowerCase().equals(fileBaseName.toLowerCase())) {
				dataTableGeneratorConfig.setModelContent(file.getFormattedContent());
			}

			if (exampleFileName.toLowerCase().equals(fileBaseName.toLowerCase())) {
				dataTableGeneratorConfig.setExampleContent(file.getFormattedContent());
			}

			if (mapperFileName.toLowerCase().equals(fileBaseName.toLowerCase())) {
				dataTableGeneratorConfig.setMapperContent(file.getFormattedContent());
			}

			if (serviceFileName.toLowerCase().equals(fileBaseName.toLowerCase())) {
				dataTableGeneratorConfig.setServiceContent(file.getFormattedContent());
			}

			if (serviceImplFileName.toLowerCase().equals(fileBaseName.toLowerCase())) {
				dataTableGeneratorConfig.setServiceImplContent(file.getFormattedContent());
			}
		}

		List<GeneratedXmlFile> xmlFileList = myBatisGenerator.getGeneratedXmlFiles();

		for (GeneratedXmlFile file : xmlFileList) {
			String fileBaseName = org.apache.commons.io.FilenameUtils.getBaseName(file.getFileName());

			if (mapperFileName.toLowerCase().equals(fileBaseName.toLowerCase())) {
				dataTableGeneratorConfig.setMappingXmlContent(file.getFormattedContent());
			}
		}

		dataTableGeneratorConfig.setLastGenerateTime(LocalDateTime.now());

		this.dataTableGeneratorConfigService.save(dataTableGeneratorConfig);

		return new ExecutiveSimpleResult(ReturnDataCode.Ok.toMessage());
	}

	private ExecutiveSimpleResult generateByMybatisPlusGeneratorCore(@NotNull DatabaseGeneratorConfig databaseGeneratorConfig, @NotNull DataTableGeneratorConfig dataTableGeneratorConfig) throws Exception {
		MybatisPlusGeneratorGlobalConfig mybatisPlusGeneratorGlobalConfig = databaseGeneratorConfig.BuildMybatisPlusGeneratorGlobalConfig();

		Optional<DaoType> optionalDaoType = DaoType.valueOfFlag(mybatisPlusGeneratorGlobalConfig.getDaoType());

		if (!optionalDaoType.isPresent()) {
			return new ExecutiveResult<>(ReturnDataCode.Exception.toMessage("无效的daoType设置"));
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

		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = mybatisPlusGeneratorGlobalConfig.getProjectFolder();
		gc.setOutputDir(projectPath);
		gc.setAuthor("luzhitao");
		gc.setOpen(false);
		// 实体属性 Swagger2 注解
		gc.setSwagger2(true);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfigEx dsc = new DataSourceConfigEx();
		dsc.setUrl(DatabaseTypeUtil.getConnectionUrlWithSchema(this.connectionConfig));
		// dsc.setSchemaName("public");
		dsc.setDriverName(databaseType.getDriverClass());
		dsc.setUsername(this.connectionConfig.getUserName());
		dsc.setPassword(this.connectionConfig.getPassword());
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(mybatisPlusGeneratorGlobalConfig.getDaoPackage());
		pc.setParent(mybatisPlusGeneratorGlobalConfig.getDaoPackage());
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};

		// 如果模板引擎是 freemarker
		String templatePath = "/templates/mapper.xml.ftl";
		// 如果模板引擎是 velocity
		// String templatePath = "/templates/mapper.xml.vm";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
						+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();

		// 配置自定义输出模板
		//指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		// templateConfig.setEntity("templates/entity2.java");
		// templateConfig.setService();
		// templateConfig.setController();

		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
		// strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(dataTableGeneratorConfig.getTableName());
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();

		return new ExecutiveSimpleResult(ReturnDataCode.Ok.toMessage());
	}

	private String getMappingXmlFilePath(DatabaseGeneratorConfig generatorConfig, DataTableGeneratorConfig dataTableGeneratorConfig) {
		MybatisGeneratorGlobalConfig mybatisGeneratorGlobalConfig = generatorConfig.BuildMybatisGeneratorGlobalConfig();

		StringBuilder sb = new StringBuilder();
		sb.append(mybatisGeneratorGlobalConfig.getProjectFolder()).append("/");
		sb.append(mybatisGeneratorGlobalConfig.getMappingXmlTargetFolder()).append("/");

		String mappingXmlPackage = mybatisGeneratorGlobalConfig.getMappingXmlPackage();

		if (StringUtils.isNotEmpty(mappingXmlPackage)) {
			sb.append(mappingXmlPackage.replace(".", "/")).append("/");
		}

		String modelName = StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getDomainObjectName()) ? dataTableGeneratorConfig
				.getTableName() : dataTableGeneratorConfig.getDomainObjectName();

		String mapperExtensionName = StringAssist.isNullOrEmpty(mybatisGeneratorGlobalConfig
				.getMapperExtensionName()) ? "Mapper" : mybatisGeneratorGlobalConfig.getMapperExtensionName();

		String mapperFileName = StringAssist.merge(modelName, mapperExtensionName, ".xml");

		sb.append(mapperFileName);

		return sb.toString();
	}

	public void setProgressCallback(ProgressCallback progressCallback) {
		this.progressCallback = progressCallback;
	}

	private List<ColumnOverride> buildColumnOverrideList(DataTableGeneratorConfig dataTableGeneratorConfig) {
		List<DataColumn> columnList = this.dataColumnService.findByConnectionConfigIdAndTableName(this.connectionConfig
				.getId(), dataTableGeneratorConfig
				.getTableName());

		List<ColumnOverride> columnOverrideList = new ArrayList<>();

		for (DataColumn c : columnList) {
			if (Whether.No.getFlag().equals(c.getIgnore())) {
				ColumnOverride columnOverride = new ColumnOverride(c.getColumnName());

				if (!StringAssist.isNullOrEmpty(c.getAliasName())) {
					columnOverride.setJavaProperty(c.getAliasName());
				}

				if (!StringAssist.isNullOrEmpty(c.getAliasName())) {
					columnOverride.setJavaType(c.getJavaType());
				}

				if (!StringAssist.isNullOrEmpty(c.getTypeHandler())) {
					columnOverride.setTypeHandler(c.getTypeHandler());
				}

				columnOverrideList.add(columnOverride);
			}
		}

		return columnOverrideList;
	}

	private List<IgnoredColumn> buildIgnoredColumnList(DataTableGeneratorConfig dataTableGeneratorConfig) {
		List<DataColumn> columnList = this.dataColumnService.findByConnectionConfigIdAndTableName(this.connectionConfig
				.getId(), dataTableGeneratorConfig
				.getTableName());

		List<IgnoredColumn> columnOverrideList = new ArrayList<>();

		for (DataColumn c : columnList) {
			if (Whether.Yes.getFlag().equals(c.getIgnore())) {
				IgnoredColumn ignoredColumn = new IgnoredColumn(c.getColumnName());

				columnOverrideList.add(ignoredColumn);
			}
		}

		return columnOverrideList;
	}
}
