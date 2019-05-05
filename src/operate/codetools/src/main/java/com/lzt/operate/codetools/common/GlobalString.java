package com.lzt.operate.codetools.common;

import com.lzt.operate.codetools.swagger2.model.ApiSingleParam;

public class GlobalString {
    @ApiSingleParam(value = "错误码", example = "0", type = Integer.class)
    public static final String JSON_ERROR_CODE = "errorCode";

    @ApiSingleParam(value = "错误信息", example = "OK")
    public static final String JSON_ERROR_MSG = "errorMsg";

    @ApiSingleParam(value = "用户姓名", example = "test1")
    public static final String JSON_USER_NAME = "userName";

    @ApiSingleParam(value = "用户邮箱", example = "17721026877@qq.com")
    public static final String JSON_USER_EMAIL = "userEmail";
}
