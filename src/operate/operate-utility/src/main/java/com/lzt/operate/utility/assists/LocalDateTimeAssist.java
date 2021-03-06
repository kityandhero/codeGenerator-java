package com.lzt.operate.utility.assists;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author luzhitao
 */
public class LocalDateTimeAssist {

    /**
     * 转换为UnixTime
     *
     * @param localDateTime 目标时间
     * @return 转换结果
     */
    public static long toUnixTime(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
