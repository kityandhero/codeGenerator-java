package com.lzt.operate.utility.extensions;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 日期时间类扩展方法
 *
 * @author luzhitao
 */
public class LocalDateTimeEx {
	private LocalDateTime time;

	public LocalDateTimeEx(LocalDateTime time) {
		this.time = time;
	}

	public java.time.Duration duration(LocalDateTime time) {
		return Duration.between(this.time, time);
	}
}
