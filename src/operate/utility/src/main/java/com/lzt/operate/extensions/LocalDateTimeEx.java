package com.lzt.operate.extensions;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 日期时间类扩展方法
 */
public class LocalDateTimeEx {
    private LocalDateTime time;

    public LocalDateTimeEx(LocalDateTime time) {
        this.time = time;
    }

    public java.time.Duration Duration(LocalDateTime time) {
        return Duration.between(this.time, time);
    }
}
