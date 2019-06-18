package com.lzt.operate.secret;

import com.lzt.operate.extensions.StringEx;

public class SecretAssist {
    private static final String Key = "ikjuoperkiosj7p4c68s98eda1ioec5x";

    public static String encrypt(String source) throws Exception {
        if (StringEx.isNullOrEmpty(source)) {
            throw new Exception("空字符串不允许加密");
        }

        String sourceMix = StringEx.randomAlphanumeric(4).toString() + source + StringEx.randomAlphanumeric(4)
                                                                                        .toString();
        StringEx result = new StringEx(DesAssist.encryptWithCBC(source, Key));
        result = result.replace("=", "").replace("+", "-").replace("/", "@");

        return result.toString();
    }
}
