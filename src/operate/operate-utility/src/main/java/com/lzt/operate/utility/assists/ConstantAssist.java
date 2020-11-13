package com.lzt.operate.utility.assists;

import org.springframework.core.Ordered;

/**
 * @author luzhitao
 */
public class ConstantAssist {

	/**
	 * 默认字符集UTF-8
	 */
	public static final String CHARSET_UTF8 = "UTF-8";

	/***
	 * 生成指定位数的数字/验证码
	 */
	public static final String NUMBER_SET = "12345678901";

	/**
	 * 逗号分隔符 ,
	 */
	public static final String SEPARATOR_COMMA = ",";

	/**
	 * 连字符-
	 */
	public static final String HYPHEN = "-";

	/**
	 * 下划线分隔符_
	 */
	public static final String SEPARATOR_UNDERSCORE = "_";

	/**
	 * 排序 - 降序标记
	 */
	public static final String ORDER_DESC = "DESC";

	/**
	 * 排序 - 升序标记
	 */
	public static final String ORDER_ASC = "ASC";

	/***
	 * 鉴权校验执行优先级
	 */
	public static final int AUTHORIZATION_ORDER = Ordered.LOWEST_PRECEDENCE - 1;

	/***
	 * 登录校验执行优先级
	 */
	public static final int AUTHENTICATION_ORDER = Ordered.LOWEST_PRECEDENCE - 2;

	//region 日期时间格式

	public static final String FORMAT_DATE_YYMM = "yyMM";
	public static final String FORMAT_DATE_YYMMDD = "yyMMdd";
	public static final String FORMAT_DATE_YYYY = "yyyy";
	public static final String FORMAT_DATE_YYYYMMDD = "yyyyMMdd";
	public static final String FORMAT_DATE_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String FORMAT_TIMESTAMP = "yyMMddhhmmss";
	public static final String FORMAT_TIME_HHmm = "HH:mm";
	public static final String FORMAT_TIME_HHmmss = "HH:mm:ss";
	public static final String FORMAT_DATETIME_Y4MDHM = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_DATETIME_Y4MDHMS = "yyyy-MM-dd HH:mm:ss";

	public static final String USER_HOME = "user.home";

	//endregion

}
