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

	//region ACCOUNT

	@ApiSingleParam(value = "用户标识", type = Long.class, example = "101")
	public static final String ACCOUNT_ID = "accountId";

	@ApiSingleParam(value = "用户名称", example = "admin")
	public static final String ACCOUNT_USERNAME = "userName";

	@ApiSingleParam(value = "登录密码", example = "123456")
	public static final String ACCOUNT_PASSWORD = "password";

	@ApiSingleParam(value = "姓名", example = "张三")
	public static final String ACCOUNT_NAME = "name";

	@ApiSingleParam(value = "姓名", example = "张三")
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

	//region OPERATOR_ROLE_CHANGE_COLLECTION

	@ApiSingleParam(value = "账户标识", example = "test1")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_OPERATOR_ID = "operatorId";

	@ApiSingleParam(value = "系统角色集合")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_UNIVERSAL_COLLECTION = "universalCollection";

	@ApiSingleParam(value = "自建角色集合")
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION_INDEPENDENT_ESTABLISHMENT_COLLECTION = "independentEstablishmentCollection";

	//endregion

}
