package com.lzt.operate.codetools.common.utils;

import com.lzt.operate.swagger2.model.ApiSingleParam;

/**
 * @author luzhitao
 */
public class GlobalString {

	/**
	 * 定义的请求头中使用的标记key，用来传递 token
	 */
	public static final String AUTH_TOKEN = "token";

	//region COMMON

	@ApiSingleParam(value = "页码", example = "1")
	public static final String LIST_PAGE_NO = "pageNo";

	@ApiSingleParam(value = "页条目数", example = "10")
	public static final String LIST_PAGE_SIZE = "pageSize";

	@ApiSingleParam(value = "原始密码", example = "111111")
	public static final String ORIGINAL_PASSWORD = "originalPassword";

	@ApiSingleParam(value = "验证密码", example = "123456")
	public static final String RE_PASSWORD = "rePassword";

	//endregion

	//region CONNECTION_MODEL

	@ApiSingleParam(value = "标识", type = Long.class, example = "201")
	public static final String CONNECTION_CONFIG_ID = "connectionConfigId";

	@ApiSingleParam(value = "连接类型", example = "100")
	public static final String CONNECTION_CONFIG_CONNECTION_TYPE = "connectionType";

	@ApiSingleParam(value = "名称", example = "my connection")
	public static final String CONNECTION_CONFIG_NAME = "name";

	@ApiSingleParam(value = "简介描述", example = "简单描述")
	public static final String CONNECTION_CONFIG_DESCRIPTION = "description";

	@ApiSingleParam(value = "数据库类型", example = "mysql")
	public static final String CONNECTION_CONFIG_DATABASE_TYPE = "databaseType";

	@ApiSingleParam(value = "数据库Host", example = "localhost")
	public static final String CONNECTION_CONFIG_HOST = "host";

	@ApiSingleParam(value = "连接Port", example = "8090")
	public static final String CONNECTION_CONFIG_PORT = "port";

	@ApiSingleParam(value = "schema", example = "dbo")
	public static final String CONNECTION_CONFIG_SCHEMA = "schema";

	@ApiSingleParam(value = "用户名", example = "test")
	public static final String CONNECTION_CONFIG_USERNAME = "userName";

	@ApiSingleParam(value = "密码", example = "111111")
	public static final String CONNECTION_CONFIG_PASSWORD = "password";

	@ApiSingleParam(value = "编码模式", example = "utf8")
	public static final String CONNECTION_CONFIG_ENCODING = "encoding";

	@ApiSingleParam(value = "localPort", example = "localPort")
	public static final String CONNECTION_CONFIG_LOCAL_PORT = "localPort";

	@ApiSingleParam(value = "remotePort", example = "localPort")
	public static final String CONNECTION_CONFIG_REMOTE_PORT = "remotePort";

	@ApiSingleParam(value = "sshPort", example = "sshPort")
	public static final String CONNECTION_CONFIG_SSH_PORT = "sshPort";

	@ApiSingleParam(value = "sshHost", example = "sshHost")
	public static final String CONNECTION_CONFIG_SSH_HOST = "sshHost";

	@ApiSingleParam(value = "sshUser", example = "sshUser")
	public static final String CONNECTION_CONFIG_SSH_USER = "sshUser";

	@ApiSingleParam(value = "sshPassword", example = "sshPassword")
	public static final String CONNECTION_CONFIG_SSH_PASSWORD = "sshPassword";

	//endregion

	//region CONNECTION_MODEL

	@ApiSingleParam(value = "数据标识", type = Long.class, example = "201")
	public static final String DATABASE_GENERATOR_CONFIG_ID = "dataBaseGeneratorConfigId";

	@ApiSingleParam(value = "连接标识", type = Long.class, example = "20211")
	public static final String DATABASE_GENERATOR_CONFIG_CONNECTION_CONFIG_ID = "connectionConfigId";

	@ApiSingleParam(value = "connectorJarPath", example = "20211")
	public static final String DATABASE_GENERATOR_CONFIG_CONNECTOR_JAR_PATH = "connectorJarPath";

	@ApiSingleParam(value = "connectorJarPath", example = "projectFolder")
	public static final String DATABASE_GENERATOR_CONFIG_PROJECT_FOLDER = "projectFolder";

	@ApiSingleParam(value = "modelPackage", example = "modelPackage")
	public static final String DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE = "modelPackage";

	@ApiSingleParam(value = "modelPackageTargetFolder", example = "modelPackageTargetFolder")
	public static final String DATABASE_GENERATOR_CONFIG_MODEL_PACKAGE_TARGET_FOLDER = "modelTargetFolder";

	@ApiSingleParam(value = "daoPackage", example = "daoPackage")
	public static final String DATABASE_GENERATOR_CONFIG_DAO_PACKAGE = "daoPackage";

	@ApiSingleParam(value = "daoTargetFolder", example = "daoTargetFolder")
	public static final String DATABASE_GENERATOR_CONFIG_DAO_TARGET_FOLDER = "daoTargetFolder";

	@ApiSingleParam(value = "mappingXMLPackage", example = "mappingXMLPackage")
	public static final String DATABASE_GENERATOR_CONFIG_MAPPING_XML_PACKAGE = "mappingXMLPackage";

	@ApiSingleParam(value = "mappingXMLTargetFolder", example = "mappingXMLTargetFolder")
	public static final String DATABASE_GENERATOR_CONFIG_MAPPING_XML_TARGET_FOLDER = "mappingXMLTargetFolder";

	@ApiSingleParam(value = "offsetLimit", type = Integer.class, example = "offsetLimit")
	public static final String DATABASE_GENERATOR_CONFIG_OFFSET_LIMIT = "offsetLimit";

	@ApiSingleParam(value = "needToStringHashcodeEquals", type = Integer.class, example = "needToStringHashcodeEquals")
	public static final String DATABASE_GENERATOR_CONFIG_NEED_TO_STRING_HASHCODE_EQUALS = "needToStringHashcodeEquals";

	@ApiSingleParam(value = "needForUpdate", type = Integer.class, example = "needForUpdate")
	public static final String DATABASE_GENERATOR_CONFIG_NEED_FOR_UPDATE = "needForUpdate";

	@ApiSingleParam(value = "annotationDAO", type = Integer.class, example = "annotationDAO")
	public static final String DATABASE_GENERATOR_CONFIG_ANNOTATION_DAO = "annotationDAO";

	@ApiSingleParam(value = "annotation", type = Integer.class, example = "annotation")
	public static final String DATABASE_GENERATOR_CONFIG_ANNOTATION = "annotation";

	@ApiSingleParam(value = "useActualColumnNames", type = Integer.class, example = "useActualColumnNames")
	public static final String DATABASE_GENERATOR_CONFIG_USE_ACTUAL_COLUMN_NAMES = "useActualColumnNames";

	@ApiSingleParam(value = "useExample", type = Integer.class, example = "useExample")
	public static final String DATABASE_GENERATOR_CONFIG_USE_EXAMPLE = "useExample";

	@ApiSingleParam(value = "generateKeys", type = Integer.class, example = "generateKeys")
	public static final String DATABASE_GENERATOR_CONFIG_GENERATE_KEYS = "generateKeys";

	@ApiSingleParam(value = "encoding", example = "encoding")
	public static final String DATABASE_GENERATOR_CONFIG_ENCODING = "encoding";

	@ApiSingleParam(value = "useTableNameAlias", type = Integer.class, example = "useTableNameAlias")
	public static final String DATABASE_GENERATOR_CONFIG_USE_TABLE_NAME_ALIAS = "useTableNameAlias";

	@ApiSingleParam(value = "useDAOExtendStyle", type = Integer.class, example = "useDAOExtendStyle")
	public static final String DATABASE_GENERATOR_CONFIG_USE_DAO_EXTEND_STYLE = "useDAOExtendStyle";

	@ApiSingleParam(value = "useSchemaPrefix", type = Integer.class, example = "useSchemaPrefix")
	public static final String DATABASE_GENERATOR_CONFIG_USE_SCHEMA_PREFIX = "useSchemaPrefix";

	@ApiSingleParam(value = "jsr310Support", type = Integer.class, example = "jsr310Support")
	public static final String DATABASE_GENERATOR_CONFIG_JSR_310_SUPPORT = "jsr310Support";

	@ApiSingleParam(value = "overrideXML", type = Integer.class, example = "overrideXML")
	public static final String DATABASE_GENERATOR_CONFIG_OVERRIDE_XML = "overrideXML";

	//endregion

	//region ACCOUNT

	@ApiSingleParam(value = "用户标识", type = Long.class, example = "101")
	public static final String ACCOUNT_ID = "accountId";

	@ApiSingleParam(value = "用户名称", example = "admin")
	public static final String ACCOUNT_USERNAME = "userName";

	@ApiSingleParam(value = "登录密码", example = "123456")
	public static final String ACCOUNT_PASSWORD = "password";

	@ApiSingleParam(value = "姓名", example = "张三")
	public static final String ACCOUNT_NAME = "name";

	@ApiSingleParam(value = "所在地区", example = "北京")
	public static final String ACCOUNT_CITY_NAME = "cityName";

	@ApiSingleParam(value = "地区代码", example = "100000000")
	public static final String ACCOUNT_CITY_CODE = "cityCode";

	@ApiSingleParam(value = "电子邮箱", example = "XXX@XXX.com")
	public static final String ACCOUNT_EMAIL = "email";

	@ApiSingleParam(value = "联系方式", example = "15612562365")
	public static final String ACCOUNT_PHONE = "phone";

	@ApiSingleParam(value = "头像", example = "http://XXX/user.png")
	public static final String ACCOUNT_AVATAR = "avatar";

	@ApiSingleParam(value = "姓名", example = "张三的简介")
	public static final String ACCOUNT_DESCRIPTION = "description";

	@ApiSingleParam(value = "状态", example = "1")
	public static final String ACCOUNT_STATUS = "status";

	//endregion

	//region ACCESS_WAY

	@ApiSingleParam(value = "模块标识", type = Long.class, example = "101")
	public static final String ACCESS_WAY_ID = "accessWayId";

	@ApiSingleParam(value = "模块名称", example = "账户列表")
	public static final String ACCESS_WAY_NAME = "name";

	@ApiSingleParam(value = "简介描述", example = "描述信息")
	public static final String ACCESS_WAY_DESCRIPTION = "description";

	@ApiSingleParam(value = "模块标记", example = "86592727-3210-4eaf-bead-c785332615f6")
	public static final String ACCESS_WAY_TAG = "tag";

	@ApiSingleParam(value = "访问路径", example = "/business/account/list")
	public static final String ACCESS_WAY_RELATIVE_PATH = "relativePath";

	@ApiSingleParam(value = "扩展权限", example = "查看价格")
	public static final String ACCESS_WAY_EXPAND = "expand";

	//endregion

	//region ROLE_UNIVERSAL

	@ApiSingleParam(value = "角色标识", type = Long.class, example = "101")
	public static final String ROLE_UNIVERSAL_ID = "roleUniversalId";

	@ApiSingleParam(value = "角色名称", example = "管理员")
	public static final String ROLE_UNIVERSAL_NAME = "name";

	@ApiSingleParam(value = "简介描述", example = "描述信息")
	public static final String ROLE_UNIVERSAL_DESCRIPTION = "description";

	//endregion

	//region ERROR_LOG

	@ApiSingleParam(value = "错误日志标识", type = Long.class, example = "101")
	public static final String ERROR_LOG_ID = "errorLogId";

	@ApiSingleParam(value = "错误描述", example = "参数错误")
	public static final String ERROR_LOG_MESSAGE = "message";

	@ApiSingleParam(value = "渠道标识", example = "12")
	public static final String ERROR_LOG_CHANNEL = "channel";

	//endregion

	//region

	@ApiSingleParam(value = "一般日志标识", type = Long.class, example = "101")
	public static final String GENERAL_LOG_ID = "generalLogId";

	@ApiSingleParam(value = "日志描述", example = "参数错误")
	public static final String GENERAL_LOG_MESSAGE = "message";

	@ApiSingleParam(value = "渠道标识", example = "12")
	public static final String GENERAL_LOG_CHANNEL = "channel";

	//endregion

	//region CUSTOM_CONFIG

	@ApiSingleParam(value = "设置项标识", type = Long.class, example = "101")
	public static final String CUSTOM_CONFIG_ID = "customConfigId";

	@ApiSingleParam(value = "设置项类别", example = "10")
	public static final String CUSTOM_CONFIG_CATEGORY = "category";

	@ApiSingleParam(value = "uuid标记", example = "e111b44a-aa06-4adb-b082-229bb11ab6e7")
	public static final String CUSTOM_CONFIG_UUID = "uuid";

	@ApiSingleParam(value = "设置项值", example = "1")
	public static final String CUSTOM_CONFIG_VALUE = "value";

	//endregion

	//region DATA_TABLE

	@ApiSingleParam(value = "数据表名", example = "table1")
	public static final String DATA_TABLE_NAME = "name";

	//endregion

	//region DATA_COLUMN

	@ApiSingleParam(value = "数据库列定制数据标识", type = Long.class, example = "101")
	public static final String DATA_COLUMN_ID = "dataColumnId";

	@ApiSingleParam(value = "数据表名", example = "table1")
	public static final String DATA_COLUMN_TABLE_NAME = "tableName";

	@ApiSingleParam(value = "数据表列名", example = "table1")
	public static final String DATA_COLUMN_NAME = "name";

	@ApiSingleParam(value = "列别名", example = "aliasName")
	public static final String DATA_COLUMN_ALIAS_NAME = "aliasName";

	@ApiSingleParam(value = "Java类型", example = "String")
	public static final String DATA_COLUMN_JAVA_TYPE = "javaType";

	@ApiSingleParam(value = "Type Handler", example = "typeHandler")
	public static final String DATA_COLUMN_TYPE_HANDLER = "typeHandler";

	//endregion

	//region HELP_CATEGORY

	@ApiSingleParam(value = "帮助列别标识", type = Long.class, example = "101")
	public static final String HELP_CATEGORY_ID = "helpCategoryId";

	@ApiSingleParam(value = "名称", example = "10")
	public static final String HELP_CATEGORY_NAME = "name";

	//endregion

	//region CUSTOM_CONFIG

	@ApiSingleParam(value = "帮助标识", type = Long.class, example = "101")
	public static final String HELP_ID = "helpId";

	@ApiSingleParam(value = "标题", example = "帮助标题")
	public static final String HELP_TITLE = "title";

	@ApiSingleParam(value = "标识标识", type = Long.class, example = "1")
	public static final String HELP_HELP_CATEGORY_ID = "helpCategoryId";

	@ApiSingleParam(value = "简介描述", example = "帮助简介")
	public static final String HELP_DESCRIPTION = "description";

	//endregion

	//region OPERATOR_ROLE_CHANGE_COLLECTION

	@ApiSingleParam(value = "账户标识", example = "test1")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_OPERATOR_ID = "operatorId";

	@ApiSingleParam(value = "系统角色集合")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_UNIVERSAL_COLLECTION = "universalCollection";

	@ApiSingleParam(value = "自建角色集合")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_INDEPENDENT_ESTABLISHMENT_COLLECTION = "independentEstablishmentCollection";

	//endregion

}
