package com.lzt.operate.secret;

import com.lzt.operate.extensions.LocalDateTimeEx;
import com.lzt.operate.extensions.StringEx;
import com.lzt.operate.utility.Convert;
import lombok.var;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SecretAssist {
    private static final String Key = "ikjuoperkiosj7p4c68s98eda1ioec5x";

    public static String encrypt(String source) throws Exception {
        if (StringEx.isNullOrEmpty(source)) {
            throw new Exception("空字符串不允许加密");
        }

        String sourceMix = StringEx.randomAlphanumeric(4).toString() + source + StringEx.randomAlphanumeric(4)
                                                                                        .toString();
        StringEx result = new StringEx(DesAssist.encryptWithCBC(sourceMix, Key));
        result = result.replace("=", "").replace("+", "-").replace("/", "@");

        return result.toString().toLowerCase();
    }

    public static String EncryptWithExpirationTime(String source, long minute) throws Exception {
        LocalDateTime time = LocalDateTime.now().plusMinutes(minute);
        return EncryptWithExpirationTime(source, time);
    }

    private static String EncryptWithExpirationTime(String source, LocalDateTime expirationtime) throws Exception {

        if (StringEx.isNullOrEmpty(source)) {
            throw new Exception("空字符串不允许加密");
        }

        String sourceMix = StringEx.randomAlphanumeric(4)
                                   .toString() + Convert.localDateTimeToString(expirationtime, DateTimeFormatter.ISO_LOCAL_DATE_TIME) + source + StringEx
                .randomAlphanumeric(4);
        StringEx result = new StringEx(DesAssist.encryptWithCBC(sourceMix, Key));
        result = result.replace("=", "").replace("+", "-").replace("/", "@");

        return result.toString().toLowerCase();

    }

    public static String Decrypt(String target) {
        String result = "";

        if (!StringEx.isNullOrEmpty(target)) {
            target = target.toUpperCase().replace("%25", "%").replace("%40", "@");
            target = target.replace("-", "+").replace("@", "/");

            int residue = target.length() % 4;

            if (residue > 0) {
                int complement = 4 - residue;

                StringBuilder targetBuilder = new StringBuilder(target);

                for (int i = 0; i < complement; i++) {
                    targetBuilder.append("=");
                }

                target = targetBuilder.toString();
            }

            result = DesAssist.decryptWithCBC(target, Key);
            result = new StringEx(result).substring(0, result.length() - 4).toString();
            result = result.substring(4);
        }

        return result;
    }

    public static String DecryptWithExpirationTime(String target, boolean expired) {
        String result = "";
        expired = false;

        if (!StringEx.isNullOrEmpty(target)) {
            target = target.toUpperCase().replace("%25", "%").replace("%40", "@");
            target = target.replace("-", "+").replace("@", "/");

            int residue = target.length() % 4;

            if (residue > 0) {
                int complement = 4 - residue;

                StringBuilder targetBuilder = new StringBuilder(target);

                for (int i = 0; i < complement; i++) {
                    targetBuilder.append("=");
                }

                target = targetBuilder.toString();
            }

            result = DesAssist.decryptWithCBC(target, Key);
            result = new StringEx(result).substring(0, result.length() - 4).toString();
            result = new StringEx(result).substring(4).toString();

            String timeString = new StringEx(result).substring(0, 19).toString();

            var localDateTime = Convert.stringToLocalDateTime(timeString);

            var duration = new LocalDateTimeEx(localDateTime).Duration(LocalDateTime.now());

            // 转换为毫秒数
            if (duration.toMillis() < 0) {
                expired = true;
            }

            result = result.substring(19);
        }

        return result;
    }
}
