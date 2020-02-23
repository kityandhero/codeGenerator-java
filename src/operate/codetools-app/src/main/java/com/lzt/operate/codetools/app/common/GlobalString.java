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

	@ApiSingleParam(value = "页码", example = "1")
	public static final String CONNECTION_CONFIG_LIST_PAGE_NO = "pageNo";

	@ApiSingleParam(value = "页条目数", example = "10")
	public static final String CONNECTION_CONFIG_LIST_PAGE_SIZE = "pageSize";

	//endregion

	//region Entrance_SING_IN

	@ApiSingleParam(value = "用户名称", example = "admin")
	public static final String LOGIN_USERNAME = "userName";

	@ApiSingleParam(value = "登录密码", example = "123456")
	public static final String LOGIN_PASSWORD = "password";

	//endregion

	//region ENTRANCE_REGISTER

	@ApiSingleParam(value = "用户名称", example = "test1")
	public static final String REGISTER_USERNAME = "userName";

	@ApiSingleParam(value = "登录密码", example = "123456")
	public static final String REGISTER_PASSWORD = "password";

	@ApiSingleParam(value = "验证密码", example = "123456")
	public static final String REGISTER_RE_PASSWORD = "rePassword";

	//endregion

	//region CONNECTION_MODEL

	@ApiSingleParam(value = "数据库连接标识", type = Long.class, example = "0")
	public static final String CONNECTION_CONFIG_ID = "connectionConfigId";

	@ApiSingleParam(value = "数据库连接类型", example = "100")
	public static final String CONNECTION_CONFIG_CONNECTION_TYPE = "connectionType";

	@ApiSingleParam(value = "数据库连接名称", example = "my connection")
	public static final String CONNECTION_CONFIG_NAME = "name";

	@ApiSingleParam(value = "数据库类型", example = "mysql")
	public static final String CONNECTION_CONFIG_DATABASE_TYPE = "databaseType";

	@ApiSingleParam(value = "数据库Host", example = "localhost")
	public static final String CONNECTION_CONFIG_HOST = "host";

	@ApiSingleParam(value = "连接Port", example = "8090")
	public static final String CONNECTION_CONFIG_PORT = "port";

	@ApiSingleParam(value = "schema", example = "dbo")
	public static final String CONNECTION_CONFIG_SCHEMA = "schema";

	@ApiSingleParam(value = "用户名", example = "test")
	public static final String CONNECTION_CONFIG_USERNAME = "username";

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

	//region OPERATOR_ROLE_CHANGE_COLLECTION

	@ApiSingleParam(value = "账户标识", example = "test1")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_OPERATOR_ID = "operatorId";

	@ApiSingleParam(value = "系统角色集合")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_UNIVERSAL_COLLECTION = "universalCollection";

	@ApiSingleParam(value = "自建角色集合")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_INDEPENDENT_ESTABLISHMENT_COLLECTION = "independentEstablishmentCollection";

	//endregion

}
