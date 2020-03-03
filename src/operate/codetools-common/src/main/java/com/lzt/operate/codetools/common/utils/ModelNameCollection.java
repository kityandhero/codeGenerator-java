package com.lzt.operate.codetools.common.utils;

/**
 * @author luzhitao
 */
public class ModelNameCollection {

	/**
	 * 登录
	 */
	public static final String ENTRANCE_SING_IN = "ENTRANCE_SING_IN";

	/**
	 * 账户拥有角色
	 */
	public static final String OPERATOR_ROLE_CHANGE_COLLECTION = "OPERATOR_ROLE_CHANGE_COLLECTION";

	/**
	 * 注册
	 */
	public static final String ENTRANCE_REGISTER = "ENTRANCE_REGISTER";

	//region CONNECTION_CONFIG 数据库连接

	public static final String CONNECTION_CONFIG_LIST = "CONNECTION_CONFIG_LIST";

	public static final String CONNECTION_CONFIG_GET = "CONNECTION_CONFIG_GET";

	public static final String CONNECTION_CONFIG_ADD_BASIC_INFO = "CONNECTION_CONFIG_ADD_BASIC_INFO";

	public static final String CONNECTION_CONFIG_UPDATE_BASIC_INFO = "CONNECTION_CONFIG_UPDATE_BASIC_INFO";

	public static final String CONNECTION_CONFIG_REMOVE = "CONNECTION_CONFIG_REMOVE";

	//endregion

	//region ACCOUNT 账户

	public static final String ACCOUNT_LIST = "ACCOUNT_LIST";

	public static final String ACCOUNT_GET = "ACCOUNT_GET";

	public static final String ACCOUNT_ADD_BASIC_INFO = "ACCOUNT_ADD_BASIC_INFO";

	public static final String ACCOUNT_UPDATE_BASIC_INFO = "ACCOUNT_UPDATE_BASIC_INFO";

	public static final String ACCOUNT_UPDATE_RESET_PASSWORD = "ACCOUNT_UPDATE_RESET_PASSWORD";

	public static final String ACCOUNT_SET_ENABLED = "ACCOUNT_SET_ENABLED";

	public static final String ACCOUNT_SET_DISABLED = "ACCOUNT_SET_DISABLED";

	//endregion

	//region ACCESS_WAY 公共角色

	public static final String ACCESS_WAY_LIST = "ROLE_UNIVERSAL_LIST";

	public static final String ACCESS_WAY_GET = "ROLE_UNIVERSAL_GET";

	//endregion

	//region ROLE_UNIVERSAL 公共角色

	public static final String ROLE_UNIVERSAL_LIST = "ROLE_UNIVERSAL_LIST";

	public static final String ROLE_UNIVERSAL_GET = "ROLE_UNIVERSAL_GET";

	public static final String ROLE_UNIVERSAL_ADD_BASIC_INFO = "ROLE_UNIVERSAL_ADD_BASIC_INFO";

	public static final String ROLE_UNIVERSAL_UPDATE_BASIC_INFO = "ROLE_UNIVERSAL_UPDATE_BASIC_INFO";

	//endregion

	//region ERROR_LOG 错误日志

	public static final String ERROR_LOG_LIST = "ERROR_LOG_LIST";

	public static final String ERROR_LOG_GET = "ERROR_LOG_GET";

	//endregion

	//region GENERAL_LOG 错误日志

	public static final String GENERAL_LOG_LIST = "GENERAL_LOG_LIST";

	public static final String GENERAL_LOG_GET = "GENERAL_LOG_GET";

	//endregion

	//region CUSTOM_CONFIG 自定义设置

	public static final String CUSTOM_CONFIG_LIST = "CUSTOM_CONFIG_LIST";

	public static final String GENERAL_LOG_SET = "GENERAL_LOG_SET";

	//endregion



	/**
	 * 打开数据库
	 */
	public static final String CONNECTION_CONFIG_OPEN = "CONNECTION_CONFIG_OPEN";

}
