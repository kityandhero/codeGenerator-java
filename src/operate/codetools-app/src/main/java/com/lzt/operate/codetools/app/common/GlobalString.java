package com.lzt.operate.codetools.app.common;

import com.lzt.operate.swagger2.model.ApiSingleParam;

/**
 * @author luzhitao
 */
public class GlobalString {

	/**
	 * 定义的请求头中使用的标记key，用来传递 token
	 */
	public static final String AUTH_TOKEN = "token";

	//region CONNECTION_LIST

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_LIST, value = "页码", example = "1")
	public static final String CONNECTION_LIST_PAGE_NO = "pageNo";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_LIST, value = "页条目数", example = "10")
	public static final String CONNECTION_LIST_PAGE_SIZE = "pageSize";

	//endregion

	//region Entrance_SING_IN

	@ApiSingleParam(modelName = ModelNameCollection.ENTRANCE_SING_IN, value = "用户名称", example = "test1")
	public static final String LOGIN_USERNAME = "userName";

	@ApiSingleParam(modelName = ModelNameCollection.ENTRANCE_SING_IN, value = "登录密码", example = "123456")
	public static final String LOGIN_PASSWORD = "password";

	//endregion

	//region ENTRANCE_REGISTER

	@ApiSingleParam(modelName = ModelNameCollection.ENTRANCE_REGISTER, value = "用户名称", example = "test1")
	public static final String REGISTER_USERNAME = "userName";

	@ApiSingleParam(modelName = ModelNameCollection.ENTRANCE_REGISTER, value = "登录密码", example = "123456")
	public static final String REGISTER_PASSWORD = "password";

	@ApiSingleParam(modelName = ModelNameCollection.ENTRANCE_REGISTER, value = "验证密码", example = "123456")
	public static final String REGISTER_RE_PASSWORD = "rePassword";

	//endregion

	//region CONNECTION_MODEL

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "数据库连接名称", example = "my connection")
	public static final String CONNECTION_CONFIG_ID = "connectionConfigId";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "数据库连接名称", example = "my connection")
	public static final String CONNECTION_NAME = "connectionName";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "数据库类型", example = "mysql")
	public static final String CONNECTION_DB_TYPE = "dbtype";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "数据库Host", example = "localhost")
	public static final String CONNECTION_HOST = "host";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "连接Port", example = "8090")
	public static final String CONNECTION_PORT = "port";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "schema", example = "dbo")
	public static final String CONNECTION_SCHEMA = "schema";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "用户名", example = "test")
	public static final String CONNECTION_USERNAME = "username";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "密码", example = "111111")
	public static final String CONNECTION_PASSWORD = "password";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "编码模式", example = "utf8")
	public static final String CONNECTION_ENCODING = "encoding";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "lport", example = "lport")
	public static final String CONNECTION_L_PORT = "lPort";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "rport", example = "rport")
	public static final String CONNECTION_R_PORT = "rProt";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "sshPort", example = "sshPort")
	public static final String CONNECTION_SSH_PORT = "sshPort";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "sshHost", example = "sshHost")
	public static final String CONNECTION_SSH_HOST = "sshHost";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "sshUser", example = "sshUser")
	public static final String CONNECTION_SSH_USER = "sshUser";

	@ApiSingleParam(modelName = ModelNameCollection.CONNECTION_MODEL, value = "sshPassword", example = "sshPassword")
	public static final String CONNECTION_SSH_PASSWORD = "sshPassword";

	//endregion

	//region OPERATOR_ROLE_CHANGE_COLLECTION

	@ApiSingleParam(modelName = ModelNameCollection.OPERATOR_ROLE_CHANGE_COLLECTION, value = "账户标识", example = "test1")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_OPERATOR_ID = "operatorId";

	@ApiSingleParam(modelName = ModelNameCollection.ENTRANCE_SING_IN, value = "系统角色集合")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_UNIVERSAL_COLLECTION = "universalCollection";

	@ApiSingleParam(modelName = ModelNameCollection.ENTRANCE_SING_IN, value = "自建角色集合")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_INDEPENDENT_ESTABLISHMENT_COLLECTION = "independentEstablishmentCollection";

	//endregion

}
