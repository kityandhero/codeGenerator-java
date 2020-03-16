package com.lzt.operate.utility.assists;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author luzhitao
 */
public class ConvertAssist {
	private static final Logger log = LoggerFactory.getLogger(ConvertAssist.class);

	public static String serializeObject(Object target) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(target);
		} catch (Exception e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	/**
	 * 转换为String类型（判空，避免NPE）
	 *
	 * @param v 字符类型值
	 * @return 转换结果
	 */
	public static String longToString(long v) {
		return Long.toString(v);
	}

	/**
	 * 转换为Long类型（判空，避免NPE）
	 *
	 * @param s 源字符串
	 * @return 转换结果
	 */
	public static long stringToLong(String s) {
		return stringToLong(s, 0);
	}

	/**
	 * 转换为Long类型（判空，避免NPE）
	 *
	 * @param s           字符类型值
	 * @param defaultLong 默认值
	 * @return 结果
	 */
	public static long stringToLong(String s, long defaultLong) {
		if (ValidatorAssist.isEmpty(s)) {
			return defaultLong;
		}
		return Long.parseLong(s);
	}

	/**
	 * 转换为String类型（判空，避免NPE）
	 *
	 * @param v 字符类型值
	 * @return 转换结果
	 */
	public static String intToString(int v) {
		return Integer.toString(v);
	}

	/**
	 * 转换为int类型（判空，避免NPE）
	 *
	 * @param v 字符类型值
	 * @return 转换结果
	 */
	public static int stringToInt(String v) {
		return stringToInt(v, 0);
	}

	public static int stringToInt(String v, int defaultInt) {
		if (StringAssist.isNullOrEmpty(v)) {
			return defaultInt;
		}

		return Integer.parseInt(v);
	}

	public static <T> List<T> iterableToList(Iterable<T> source) {
		ArrayList<T> list = new ArrayList<>();

		source.forEach(list::add);

		return list;
	}

	public static String localDateTimeToString(LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter) {
		return dateTime.format(dateTimeFormatter);
	}

	public static Date stringToData(String s, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(s);
	}

	public static LocalDateTime stringToLocalDateTime(String s) {
		return LocalDateTime.parse(s);

	}

	/**
	 * 字符串转换为boolean
	 *
	 * @param s 源字符串
	 * @return 转换结果
	 */
	public static boolean stringToBoolean(String s) {
		return stringToBoolean(s, false);
	}

	/**
	 * 字符串转换为boolean
	 *
	 * @param s              源字符串
	 * @param defaultBoolean 默认值
	 * @return 转换结果
	 */
	public static boolean stringToBoolean(String s, boolean defaultBoolean) {
		if (ValidatorAssist.notEmpty(s)) {
			return ValidatorAssist.isTrue(s);
		}
		return defaultBoolean;
	}

	/**
	 * 字符串时间戳转日期
	 *
	 * @param dateTime 时间字符串
	 * @return 结果
	 */
	public static Date stringToDate(String dateTime, String... dateFormat) {
		String f = ConstantAssist.FORMAT_DATETIME_Y4MDHM;

		if (dateFormat != null && dateFormat.length > 0) {
			f = dateFormat[0];
		}
		return stringToDate(dateTime, f);
	}

	/***
	 * 转换字符串为日期date
	 * @param datetime 时间字符串
	 * @param format 格式化字符串
	 * @return 结果
	 */
	public static Date stringToDate(String datetime, String format) {
		if (StringUtils.isBlank(datetime)) {
			return null;
		}

		SimpleDateFormat f = new SimpleDateFormat(format);

		try {
			return f.parse(datetime);
		} catch (ParseException e) {
			log.warn("日期格式转换异常");
		}
		return null;
	}

	/***
	 * 模糊转换日期
	 * @param dateString 日期字符串
	 * @return 结果
	 */
	public static Date stringToFuzzyDate(String dateString) {
		if (ValidatorAssist.isEmpty(dateString)) {
			return null;
		}

		// 清洗
		if (dateString.contains(ConstantAssist.HYPHEN)) {
		} else if (dateString.contains("月")) {
			dateString = dateString.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "").replaceAll("号", "");
		} else {
			dateString = dateString.replaceAll("\\/", "-").replaceAll("\\.", "-");
		}
		String[] parts = dateString.split(" ");
		String[] ymd = parts[0].split("-");

		if (ymd.length >= 3) {
			if (ymd[0].length() == 2) {
				ymd[0] = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(0, 2) + ymd[0];
			}
			if (ymd[1].length() == 1) {
				ymd[1] = "0" + ymd[1];
			}
			if (ymd[2].length() == 1) {
				ymd[2] = "0" + ymd[2];
			}
		}
		parts[0] = StringAssist.join(ymd, "-");
		if (parts.length == 1) {
			return stringToDate(parts[0], ConstantAssist.FORMAT_DATE_YYYY_MM_DD);
		}
		// 18:20:30:103
		String[] hmsArray = new String[3];
		String[] hms = parts[1].split(":");
		if (hms[0].length() == 1) {
			hms[0] = "0" + hms[0];
		}
		hmsArray[0] = hms[0];
		if (hms.length >= 2) {
			if (hms[1].length() == 1) {
				hms[1] = "0" + hms[1];
			}
			hmsArray[1] = hms[1];
		} else {
			hmsArray[1] = "00";
		}
		if (hms.length >= 3) {
			if (hms[2].length() == 1) {
				hms[2] = "0" + hms[2];
			}
			hmsArray[2] = hms[2];
		} else {
			hmsArray[2] = "00";
		}
		parts[1] = StringAssist.join(hmsArray, ":");

		return stringToDate(StringAssist.join(parts, " "), ConstantAssist.FORMAT_DATETIME_Y4MDHMS);
	}

	/**
	 * 序列化
	 *
	 * @param o Object
	 * @return String Json
	 */
	public static String serialize(Object o) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(o);
	}

	/**
	 * 序列化
	 *
	 * @param json json
	 * @return String Json
	 */
	public static <T> T deserialize(String json, Class<T> clazz) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(json, clazz);
	}

	/**
	 * 序列化
	 *
	 * @param json json
	 * @return String Json
	 */
	public static <T> List<T> deserializeToList(String json, Class<T> clazz) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);

		return mapper.readValue(json, javaType);
	}

}
