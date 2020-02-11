package com.lzt.operate.utility.assists;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author luzhitao
 */
public class DateAssist extends DateUtils {

	/**
	 * 星期
	 */
	protected static final String[] WEEK = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

	/***
	 * 当前的日期时间
	 * @return format指定格式的日期时间
	 */
	public static Date now() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/***
	 * 当前的日期时间
	 * @return format指定格式的日期时间
	 */
	public static String now(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	/**
	 * 当前日期时间串
	 *
	 * @return yyMMddhhmmss
	 */
	public static String toTimestamp(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantAssist.FORMAT_TIMESTAMP);
		return sdf.format(date.getTime());
	}

	/**
	 * 获取月份
	 *
	 * @return 结果
	 */
	public static String getMonth() {
		return now(ConstantAssist.FORMAT_DATE_YYMM);
	}

	/***
	 * 获取今天的日期
	 * @return yyyyMMdd
	 */
	public static String today() {
		return now(ConstantAssist.FORMAT_DATE_YYYYMMDD);
	}

	/**
	 * 获取格式化的日期
	 *
	 * @param date       基准日期
	 * @param daysOffset 偏移量
	 * @return yyyy-MM-dd
	 */
	public static String getDate(Date date, int... daysOffset) {
		if (date == null) {
			date = new Date();
		}
		if (daysOffset != null && daysOffset.length > 0) {
			date = addDays(date, daysOffset[0]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantAssist.FORMAT_DATE_YYYY_MM_DD);
		return sdf.format(date);
	}

	/***
	 * 获取格式化的日期时间
	 * @param date date
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getDateTime(Date date, int... daysOffset) {
		if (date == null) {
			date = new Date();
		}
		if (daysOffset != null && daysOffset.length > 0) {
			date = addDays(date, daysOffset[0]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantAssist.FORMAT_DATETIME_Y4MDHM);
		return sdf.format(date);
	}

	/**
	 * 是否是工作时间段，用于后台程序等
	 *
	 * @return boolean
	 */
	public static boolean isWorkingTime() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		return (hour >= 8 && hour < 20);
	}

	/***
	 * 获取上午/下午
	 * @return String
	 */
	public static String getAmPm() {
		Calendar c = Calendar.getInstance();
		int hours = c.get(Calendar.HOUR_OF_DAY);
		if (hours <= 9) {
			return "早上";
		} else if (9 < hours && hours <= 12) {
			return "上午";
		} else if (hours == 13) {
			return "中午";
		} else if (13 < hours && hours <= 18) {
			return "下午";
		} else {
			return "晚上";
		}
	}

	/**
	 * 得到当前的年月YYMM，用于生成文件夹名称
	 *
	 * @return String
	 */
	public static String getYearMonth() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantAssist.FORMAT_DATE_YYMM);
		return sdf.format(cal.getTime());
	}

	/**
	 * 得到当前的年月YYMM，用于生成文件夹
	 *
	 * @return String
	 */
	public static String getYearMonthDay() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantAssist.FORMAT_DATE_YYMMDD);
		return sdf.format(cal.getTime());
	}

	/**
	 * 得到当前的年月YYMM，用于生成文件夹
	 *
	 * @return int
	 */
	public static int getDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/***
	 * 获取日期对应的星期
	 * @param date date
	 * @return String
	 */
	public static String getWeek(Date date) {
		return WEEK[Calendar.getInstance().get(Calendar.DAY_OF_WEEK)];
	}

	/**
	 * 毫秒数转date
	 *
	 * @param timeMillis timeMillis
	 * @return Date
	 */
	public static Date timeMillis2Date(Long timeMillis) {
		return new Date(timeMillis);
	}

}
