package com.lzt.operate.codetools.common;

import com.lzt.operate.swagger2.model.ApiSingleParam;

public class GlobalString {
    // @ApiSingleParam(value = "错误码", example = "0", type = Integer.class)
    // public static final String JSON_ERROR_CODE = "errorCode";
    //
    // @ApiSingleParam(value = "错误信息", example = "OK")
    // public static final String JSON_ERROR_MSG = "errorMsg";
    //
    // @ApiSingleParam(value = "用户姓名", example = "test1")
    // public static final String JSON_USER_NAME = "userName";

    @ApiSingleParam(value = "用户名称", example = "test1")
    public static final String LOGIN_USERNAME = "userName";

    @ApiSingleParam(value = "登录密码", example = "123456")
    public static final String LOGIN_PASSWORD = "password";

    // @ApiSingleParam(value = "用户邮箱", example = "17721026877@qq.com")
    // public static final String JSON_USER_EMAIL = "userEmail";

    @ApiSingleParam(value = "数据库连接名称", example = "my connection")
    public static final String CONNECTION_NAME = "connectionName";

    @ApiSingleParam(value = "数据库类型", example = "mysql")
    public static final String CONNECTION_DBTYPE = "dbtype";

    @ApiSingleParam(value = "数据库Host", example = "localhost")
    public static final String CONNECTION_HOST = "host";

    @ApiSingleParam(value = "连接Port", example = "8090")
    public static final String CONNECTION_PORT = "port";

    @ApiSingleParam(value = "schema", example = "dbo")
    public static final String CONNECTION_SCHEMA = "schema";

    @ApiSingleParam(value = "用户名", example = "test")
    public static final String CONNECTION_USERNAME = "username";

    @ApiSingleParam(value = "密码", example = "123456")
    public static final String CONNECTION_PASSWORD = "password";

    @ApiSingleParam(value = "编码模式", example = "utf8")
    public static final String CONNECTION_ENCODING = "encoding";

    @ApiSingleParam(value = "lport", example = "lport")
    public static final String CONNECTION_LPORT = "lPort";

    @ApiSingleParam(value = "rport", example = "rport")
    public static final String CONNECTION_RPORT = "rProt";

    @ApiSingleParam(value = "sshPort", example = "sshPort")
    public static final String CONNECTION_SSHPORT = "sshPort";

    @ApiSingleParam(value = "sshHost", example = "sshHost")
    public static final String CONNECTION_SSHHOST = "sshHost";

    @ApiSingleParam(value = "sshUser", example = "sshUser")
    public static final String CONNECTION_SSHUSER = "sshUser";

    @ApiSingleParam(value = "sshPassword", example = "sshPassword")
    public static final String CONNECTION_SSHPASSWORD = "sshPassword";

}
