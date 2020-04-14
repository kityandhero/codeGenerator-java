package com.lzt.operate.code.generator.app.bridge;

import com.lzt.operate.code.generator.app.util.ConfigHelper;
import com.lzt.operate.code.generator.app.util.DatabaseTypeUtil;
import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.code.generator.common.enums.FileEncoding;
import com.lzt.operate.code.generator.dao.service.DataTableGeneratorConfigService;
import com.lzt.operate.code.generator.dao.service.DatabaseGeneratorConfigService;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataTableGeneratorConfig;
import com.lzt.operate.code.generator.entities.DatabaseGeneratorConfig;
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
public class MybatisGeneratorBridge {

    private final DatabaseGeneratorConfigService databaseGeneratorConfigService;

    private final DataTableGeneratorConfigService dataTableGeneratorConfigService;

    private final ConnectionConfig connectionConfig;

    private ProgressCallback progressCallback;

    private List<IgnoredColumn> ignoredColumns;

    private List<ColumnOverride> columnOverrides;

    public MybatisGeneratorBridge(
            @NotNull ConnectionConfig selectedConnectionConfig,
            @NotNull DatabaseGeneratorConfigService databaseGeneratorConfigService,
            @NotNull DataTableGeneratorConfigService dataTableGeneratorConfigService) {
        this.connectionConfig = selectedConnectionConfig;
        this.databaseGeneratorConfigService = databaseGeneratorConfigService;
        this.dataTableGeneratorConfigService = dataTableGeneratorConfigService;
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
        String projectFolder = Optional.ofNullable(databaseGeneratorConfig.getProjectFolder()).orElse("").trim();
        String modelTargetFolder = Optional.ofNullable(databaseGeneratorConfig.getModelTargetFolder())
                                           .orElse("")
                                           .trim();
        String daoTargetFolder = Optional.ofNullable(databaseGeneratorConfig.getDaoTargetFolder()).orElse("").trim();
        String mappingXmlTargetFolder = Optional.ofNullable(databaseGeneratorConfig.getMappingXmlTargetFolder())
                                                .orElse("").trim();

        if (StringAssist.isNullOrEmpty(projectFolder)) {
            return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("请先配置项目文件夹"));
        }

        File file = new File(projectFolder);

        if (!file.exists()) {
            boolean mkdirsResult = file.mkdirs();

            if (!mkdirsResult) {
                return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("创建项目文件夹失败"));
            }
        }

        if (!file.isDirectory()) {
            return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("项目文件夹路径无效"));
        }

        modelTargetFolder = Optional.of(modelTargetFolder).orElse("");

        if (!StringAssist.isNullOrEmpty(modelTargetFolder)) {
            if (StringAssist.contains(modelTargetFolder, ConstantCollection.EMPTY_STRING)) {
                return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("model文件夹不能含有空格"));
            }

            String modelFolder = StringAssist.merge(projectFolder, modelTargetFolder);

            file = new File(modelFolder);

            if (!file.exists()) {
                boolean mkdirsResult = file.mkdirs();

                if (!mkdirsResult) {
                    return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("创建model文件夹失败"));
                }
            }

            if (!file.isDirectory()) {
                return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("model文件夹路径无效"));
            }
        }

        daoTargetFolder = Optional.of(daoTargetFolder).orElse("");

        if (!StringAssist.isNullOrEmpty(daoTargetFolder)) {
            if (StringAssist.contains(modelTargetFolder, ConstantCollection.EMPTY_STRING)) {
                return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("dao文件夹不能含有空格"));
            }

            String daoFolder = StringAssist.merge(projectFolder, daoTargetFolder);

            file = new File(daoFolder);

            if (!file.exists()) {
                boolean mkdirsResult = file.mkdirs();

                if (!mkdirsResult) {
                    return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("创建dao文件夹失败"));
                }
            }

            if (!file.isDirectory()) {
                return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("dao文件夹路径无效"));
            }
        }

        mappingXmlTargetFolder = Optional.of(mappingXmlTargetFolder).orElse("");

        if (!StringAssist.isNullOrEmpty(mappingXmlTargetFolder)) {
            if (StringAssist.contains(modelTargetFolder, ConstantCollection.EMPTY_STRING)) {
                return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("mappingXml文件夹不能含有空格"));
            }

            String mappingXmlFolder = StringAssist.merge(projectFolder, mappingXmlTargetFolder);

            file = new File(mappingXmlFolder);

            if (!file.exists()) {
                boolean mkdirsResult = file.mkdirs();

                if (!mkdirsResult) {
                    return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("创建mappingXml文件夹失败"));
                }
            }

            if (!file.isDirectory()) {
                return new ExecutiveSimpleResult(ReturnDataCode.Exception.toMessage("mappingXml文件夹路径无效"));
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

        List<DataTableGeneratorConfig> list = this.dataTableGeneratorConfigService.list();

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

        String modelTargetFolder = Optional.ofNullable(databaseGeneratorConfig.getModelTargetFolder())
                                           .orElse("")
                                           .trim();
        String daoTargetFolder = Optional.ofNullable(databaseGeneratorConfig.getDaoTargetFolder()).orElse("").trim();
        String mappingXmlTargetFolder = Optional.ofNullable(databaseGeneratorConfig.getMappingXmlTargetFolder())
                                                .orElse("").trim();

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

        // 自动识别数据库关键字，默认false，如果设置为true，根据SqlReservedWords中定义的关键字列表；
        //         一般保留默认值，遇到数据库关键字（Java关键字），使用columnOverride覆盖
        context.addProperty("autoDelimitKeywords", String.valueOf(Whether.Yes.getFlag().equals(databaseGeneratorConfig
                .getAutoDelimitKeywords())));

        if (DatabaseType.MySQL.getFlag().equals(databaseType.getFlag()) || DatabaseType.MySQL_8.getFlag()
                                                                                               .equals(databaseType.getFlag())) {
            // 由于beginningDelimiter和endingDelimiter的默认值为双引号(")，在Mysql中不能这么写，所以还要将这两个默认值改为`
            context.addProperty("beginningDelimiter", "`");
            context.addProperty("endingDelimiter", "`");
        }

        boolean useSchemaPrefix = Whether.Yes.getFlag().equals(databaseGeneratorConfig.getUseSchemaPrefix());

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

        String mapperExtensionName = Optional.ofNullable(databaseGeneratorConfig.getMapperExtensionName()).orElse("");

        if (!StringAssist.isNullOrEmpty(mapperExtensionName)) {
            String mapperName = StringAssist.merge(StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getDomainObjectName()) ? dataTableGeneratorConfig
                    .getTableName() : dataTableGeneratorConfig.getDomainObjectName(), mapperExtensionName);
            tableConfig.setMapperName(mapperName);
        }

        // add ignore columns
        if (this.ignoredColumns != null) {
            this.ignoredColumns.forEach(tableConfig::addIgnoredColumn);
        }

        if (this.columnOverrides != null) {
            this.columnOverrides.forEach(tableConfig::addColumnOverride);
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
        modelConfig.setTargetPackage(databaseGeneratorConfig.getModelPackage());

        if (StringAssist.isNullOrEmpty(modelTargetFolder)) {
            modelConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder());
        } else {
            modelConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder() + "/" + modelTargetFolder);
        }

        modelConfig.getProperties().setProperty("enableSubPackages", "false");
        modelConfig.getProperties().setProperty("trimStrings", "true");

        // Mapper configuration
        SqlMapGeneratorConfiguration mapperConfig = new SqlMapGeneratorConfiguration();
        mapperConfig.setTargetPackage(databaseGeneratorConfig.getMappingXmlPackage());

        if (StringAssist.isNullOrEmpty(mappingXmlTargetFolder)) {
            mapperConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder());
        } else {
            mapperConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder() + "/" + mappingXmlTargetFolder);
        }

        mapperConfig.getProperties().setProperty("enableSubPackages", "false");

        // dao
        JavaClientGeneratorConfiguration daoConfig = new JavaClientGeneratorConfiguration();
        daoConfig.setConfigurationType("XMLMAPPER");
        daoConfig.setTargetPackage(databaseGeneratorConfig.getDaoPackage());

        if (StringAssist.isNullOrEmpty(daoTargetFolder)) {
            daoConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder());
        } else {
            daoConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder() + "/" + daoTargetFolder);
        }

        daoConfig.getProperties().setProperty("enableSubPackages", "false");

        // service
        JavaServiceGeneratorConfiguration serviceConfig = new JavaServiceGeneratorConfiguration();
        serviceConfig.setTargetPackage(databaseGeneratorConfig.getDaoPackage());
        serviceConfig.setImplementationPackage(databaseGeneratorConfig.getDaoPackage() + ".impl");

        if (StringAssist.isNullOrEmpty(daoTargetFolder)) {
            serviceConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder());
        } else {
            serviceConfig.setTargetProject(databaseGeneratorConfig.getProjectFolder() + "/" + daoTargetFolder);
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
        if (Whether.Yes.getFlag().equals(databaseGeneratorConfig.getNeedToStringHashCodeEquals())) {
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
        if (Whether.Yes.getFlag().equals(databaseGeneratorConfig.getOffsetLimit())) {
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
        if (Whether.Yes.getFlag().equals((databaseGeneratorConfig.getJsr310Support()))) {
            JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();

            String javaTypeResolverJsr310ImplName = JavaTypeResolverJsr310Impl.class.getName();

            javaTypeResolverConfiguration.setConfigurationType(javaTypeResolverJsr310ImplName);

            context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
        }

        //forUpdate 插件
        if (Whether.Yes.getFlag().equals(databaseGeneratorConfig.getNeedForUpdate())) {
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
        if (Whether.Yes.getFlag().equals(databaseGeneratorConfig.getAnnotationDAO())) {
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
        if (Whether.Yes.getFlag().equals(databaseGeneratorConfig.getUseDAOExtendStyle())) {
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
        Set<String> fullyqualifiedTables = new HashSet<>();
        Set<String> contexts = new HashSet<>();

        //override=true
        ShellCallback shellCallback = new DefaultShellCallback(true);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, shellCallback, warnings);

        // if overrideXML selected, delete oldXML ang generate new one
        if (Whether.Yes.getFlag().equals(databaseGeneratorConfig.getOverrideXML())) {
            String mappingXmlFilePath = this.getMappingXmlFilePath(databaseGeneratorConfig, dataTableGeneratorConfig);
            File mappingXmlFile = new File(mappingXmlFilePath);
            if (mappingXmlFile.exists()) {
                boolean delete = mappingXmlFile.delete();

                if (!delete) {
                    throw new Exception("文件" + mappingXmlFilePath + "删除失败！");
                }
            }
        }

        myBatisGenerator.generate(this.progressCallback, contexts, fullyqualifiedTables);

        List<GeneratedJavaFile> javaFileList = myBatisGenerator.getGeneratedJavaFiles();

        String modelFileName = StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getDomainObjectName()) ? dataTableGeneratorConfig
                .getTableName() : dataTableGeneratorConfig.getDomainObjectName();

        String exampleFileName = StringAssist.merge(modelFileName, "Example");

        mapperExtensionName = StringAssist.isNullOrEmpty(databaseGeneratorConfig
                .getMapperExtensionName()) ? "Mapper" : databaseGeneratorConfig.getMapperExtensionName();

        String mapperFileName = StringAssist.merge(modelFileName, mapperExtensionName);

        String serviceFileName = StringAssist.merge(modelFileName, "Service");
        String serviceImplFileName = StringAssist.merge(modelFileName, "ServiceImpl");

        for (GeneratedJavaFile file : javaFileList) {
            String fileBaseName = org.apache.commons.io.FilenameUtils.getBaseName(file.getFileName());

            // String serviceFileName = StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getDomainObjectName()) ? StringAssist
            // 		.merge(dataTableGeneratorConfig
            // 				.getTableName(), "Service") : dataTableGeneratorConfig.getDomainObjectName();
            // String modelFileName = StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getDomainObjectName()) ? dataTableGeneratorConfig
            // 		.getTableName() : dataTableGeneratorConfig.getDomainObjectName();

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
                dataTableGeneratorConfig.setServiceContent(file.getFormattedContent());
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

    private String getMappingXmlFilePath(DatabaseGeneratorConfig generatorConfig, DataTableGeneratorConfig dataTableGeneratorConfig) {
        StringBuilder sb = new StringBuilder();
        sb.append(generatorConfig.getProjectFolder()).append("/");
        sb.append(generatorConfig.getMappingXmlTargetFolder()).append("/");

        String mappingXmlPackage = generatorConfig.getMappingXmlPackage();

        if (StringUtils.isNotEmpty(mappingXmlPackage)) {
            sb.append(mappingXmlPackage.replace(".", "/")).append("/");
        }

        String modelName = StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getDomainObjectName()) ? dataTableGeneratorConfig
                .getTableName() : dataTableGeneratorConfig.getDomainObjectName();

        String mapperExtensionName = StringAssist.isNullOrEmpty(generatorConfig
                .getMapperExtensionName()) ? "Mapper" : generatorConfig.getMapperExtensionName();

        String mapperFileName = StringAssist.merge(modelName, mapperExtensionName, ".xml");

        sb.append(mapperFileName);

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
