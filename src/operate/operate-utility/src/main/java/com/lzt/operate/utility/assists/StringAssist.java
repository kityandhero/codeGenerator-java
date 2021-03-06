package com.lzt.operate.utility.assists;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static com.lzt.operate.utility.assists.ConstantAssist.NUMBER_SET;

/**
 * @author luzhitao
 */
public class StringAssist {

	private static final Random random = new Random();

	/**
	 * 转换为首字母小写
	 *
	 * @param s 源字符串
	 * @return 转换后的字符串
	 */
	public static String toFirstLower(String s) {
		if (!isNullOrEmpty(s)) {
			return Character.toLowerCase(s.charAt(0)) + s.substring(1);
		}

		return "";
	}

	/**
	 * 判断字符串时候为null 或空白字符串
	 *
	 * @param s 要检测的字符串
	 * @return 判断结果
	 */
	public static boolean isNullOrEmpty(@Nullable String s) {
		return Strings.isNullOrEmpty(s);
	}

	/**
	 * 合并字符串
	 *
	 * @param values values
	 * @return String
	 */
	public static String merge(String... values) {
		return join(values, "");
	}

	/**
	 * 通过分隔符构建字符串
	 *
	 * @param parts 合并的源
	 * @return 已合并的字符串
	 */
	public static String join(String[] parts) {
		return join(parts, ConstantAssist.SEPARATOR_COMMA);
	}

	/**
	 * 通过分隔符构建字符串
	 *
	 * @param separator 分隔符
	 * @param parts     合并的源
	 * @return 已合并的字符串
	 */
	public static String join(String[] parts, String separator) {
		return join(parts, separator, false);
	}

	/**
	 * 通过分隔符构建字符串
	 *
	 * @param separator   分隔符
	 * @param parts       合并的源
	 * @param ignoreEmpty 是否忽略空白项
	 * @return 已合并的字符串
	 */
	public static String join(String[] parts, String separator, boolean ignoreEmpty) {
		return join(parts, separator, ignoreEmpty, false);
	}

	/**
	 * 通过分隔符构建字符串
	 *
	 * @param separator       分隔符
	 * @param parts           合并的源
	 * @param ignoreEmpty     是否忽略空白项
	 * @param whetherTrimItem 是否针对每一项执行trim
	 * @return 已合并的字符串
	 */
	public static String join(String[] parts, String separator, boolean ignoreEmpty, boolean whetherTrimItem) {
		Iterator<String> iterator = Arrays.asList(parts).iterator();

		if (iterator.hasNext()) {

			List<String> collection = new ArrayList<String>(Collections.emptyList()) {
				private static final long serialVersionUID = 4090918772117415982L;
			};

			while (iterator.hasNext()) {

				String item = iterator.next();

				if (ignoreEmpty) {
					String se = Optional.of(item).orElse("");
					if (!"".equals(Optional.of(item).orElse(""))) {
						if (whetherTrimItem) {
							String handleTrimItem = Optional.of(item)
																  .orElse(null)
																  .trim();

							if (!"".equals(Optional.of(handleTrimItem).orElse(""))) {
								collection.add(item);
							}
						} else {
							collection.add(item);
						}
					}
				} else {
					collection.add(item);
				}

			}

			return Joiner.on(Optional.of(separator).orElse("")).join(collection);
		}

		return "";
	}

	/**
	 * 通过分隔符构建字符串
	 *
	 * @param parts 合并的源
	 * @return 已合并的字符串
	 */
	public static <TIterable extends Iterable<?>> String join(TIterable parts) {
		return join(parts, ConstantAssist.SEPARATOR_COMMA);
	}

	/**
	 * 通过分隔符构建字符串
	 *
	 * @param separator 分隔符
	 * @param parts     合并的源
	 * @return 已合并的字符串
	 */
	public static <TIterable extends Iterable<?>> String join(TIterable parts, String separator) {
		return join(parts, separator, false);
	}

	/**
	 * 通过分隔符构建字符串
	 *
	 * @param separator   分隔符
	 * @param parts       合并的源
	 * @param ignoreEmpty 是否忽略空白项
	 * @return 已合并的字符串
	 */
	public static <TIterable extends Iterable<?>> String join(TIterable parts, String separator, boolean ignoreEmpty) {
		return join(parts, separator, ignoreEmpty, false);
	}

	/**
	 * 通过分隔符构建字符串
	 *
	 * @param separator       分隔符
	 * @param parts           合并的源
	 * @param ignoreEmpty     是否忽略空白项
	 * @param whetherTrimItem 是否针对每一项执行trim
	 * @return 已合并的字符串
	 */
	public static <TIterable extends Iterable<?>> String join(TIterable parts, String separator, boolean ignoreEmpty, boolean whetherTrimItem) {
		Iterator<?> iterator = parts.iterator();

		if (iterator.hasNext()) {

			List<Object> collection = new ArrayList<Object>(Collections.emptyList()) {
				private static final long serialVersionUID = 4090918772117415982L;
			};

			while (iterator.hasNext()) {

				Object item = iterator.next();

				if (ignoreEmpty) {

					if (!"".equals(Optional.of(item).orElse(""))) {
						if (whetherTrimItem) {
							Object handleTrimItem = Optional.of(item)
																  .orElse(null)
																  .toString()
																  .trim();

							if (!"".equals(Optional.of(handleTrimItem).orElse(""))) {
								collection.add(item);
							}
						} else {
							collection.add(item);
						}
					}
				} else {
					collection.add(item);
				}

			}

			return Joiner.on(Optional.of(separator).orElse("")).join(collection);
		}

		return "";
	}

	/**
	 * 格式化字符串
	 *
	 * @param format 格式化规格
	 * @param args   要格式化的参数
	 * @return 已格式化的结果
	 */
	public static String format(String format, Object... args) {
		String f = Optional.of(format).orElse("");

		return String.format(f, args);
	}

	/**
	 * 格式化字符串
	 *
	 * @param template 格式化规格
	 * @param args     要格式化的参数
	 * @return 已格式化的结果
	 */
	public static String lenientFormat(String template, @Nullable Object @Nullable ... args) {
		String f = Optional.of(template).orElse("");

		return Strings.lenientFormat(f, args);
	}

	/**
	 * 将字符串转换为驼峰式
	 *
	 * @param s 要格式化的字符串
	 * @return 已格式化的结果
	 */
	public static String toCamelStyle(String s) {
		if (s != null) {
			final String underline = "_";

			if (s.contains(underline)) {
				s = s.toLowerCase();
				StringBuilder sb = new StringBuilder();
				sb.append(String.valueOf(s.charAt(0)).toUpperCase());
				for (int i = 1; i < s.length(); i++) {
					char c = s.charAt(i);
					if (c != '_') {
						sb.append(c);
					} else {
						if (i + 1 < s.length()) {
							sb.append(String.valueOf(s.charAt(i + 1)).toUpperCase());
							i++;
						}
					}
				}
				return sb.toString();
			} else {
				String firstChar = String.valueOf(s.charAt(0)).toUpperCase();
				String otherChars = s.substring(1);

				return firstChar + otherChars;
			}
		}

		return null;
	}

	/***
	 * 转换成蛇形命名（用于Java属性转换为数据库列名）
	 * @param camelCaseArray 驼峰式字符串数组
	 * @return 结果
	 */
	public static String[] toSnakeCase(String[] camelCaseArray) {
		if (camelCaseArray == null) {
			return null;
		}
		String[] snakeCaseArray = new String[camelCaseArray.length];
		for (int i = 0; i < camelCaseArray.length; i++) {
			snakeCaseArray[i] = toSnakeCase(camelCaseArray[i]);
		}
		return snakeCaseArray;
	}

	/***
	 * 转换成蛇形命名（用于Java属性转换为数据库列名）
	 * @param camelCaseArray 驼峰式字符串数组
	 * @return 结果
	 */
	public static List<String> toSnakeCase(List<String> camelCaseArray) {
		if (camelCaseArray == null) {
			return null;
		}
		List<String> snakeCaseList = new ArrayList<>(camelCaseArray.size());
		for (String camelCaseStr : camelCaseArray) {
			snakeCaseList.add(toSnakeCase(camelCaseStr));
		}
		return snakeCaseList;
	}

	/***
	 * 转换成小写蛇形命名（用于Java属性转换为小写数据库列名）
	 * @param camelCase 驼峰式字符串
	 * @return 结果
	 */
	public static String toSnakeCase(String camelCase) {
		if (ValidatorAssist.isEmpty(camelCase)) {
			return null;
		}
		// 全小写
		if (camelCase.toLowerCase().equals(camelCase)) {
			return camelCase;
		}
		// 全大写直接return小写
		if (camelCase.toUpperCase().equals(camelCase)) {
			return camelCase.toLowerCase();
		}
		// 大小写混合，则遇“大写”转换为“_小写”
		char[] chars = camelCase.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			if (Character.isUpperCase(c)) {
				if (sb.length() > 0) {
					sb.append(ConstantAssist.SEPARATOR_UNDERSCORE);
				}
			}
			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}

	/**
	 * 将字符串按照指定的分隔符分隔为 List<String>
	 *
	 * @param target    要处理的源字符串
	 * @param separator 分隔符
	 * @return 已分隔的结果
	 */
	public static List<String> split(String target, char separator) {
		List<String> list = new ArrayList<>();

		Splitter.on(separator).omitEmptyStrings().split(target).forEach(list::add);

		return list;
	}

	/**
	 * 将字符串按照指定的分隔符分隔为 List<String>
	 *
	 * @param target    要处理的源字符串
	 * @param separator 分隔符
	 * @return 已分隔的结果
	 */
	public static List<String> split(String target, String separator) {
		List<String> list = new ArrayList<>();

		Splitter.on(separator).omitEmptyStrings().split(target).forEach(list::add);

		return list;
	}

	/**
	 * 将字符串按照指定的分隔符分隔为 List<String>
	 *
	 * @param target    要处理的源字符串
	 * @param separator 分隔符
	 * @return 已分隔的结果
	 */
	public static List<String> splitToList(String target, char separator) {
		return Splitter.on(separator).omitEmptyStrings().splitToList(target);
	}

	/**
	 * 将字符串按照指定的分隔符分隔为 List<String>
	 *
	 * @param target    要处理的源字符串
	 * @param separator 分隔符
	 * @return 已分隔的结果
	 */
	public static List<String> splitToList(String target, String separator) {
		return Splitter.on(separator).omitEmptyStrings().splitToList(target);
	}

	/**
	 * 获取子字符串
	 *
	 * @param source 字符串源
	 * @param index  开始的位置
	 * @param length 长度
	 * @return 结果
	 */
	public static String substring(String source, int index, int length) {
		return source.substring(index, index + length);
	}

	/**
	 * 获取子字符串
	 *
	 * @param source 字符串源
	 * @param index  开始的位置
	 * @return 结果
	 */
	public static String substring(String source, int index) {
		return source.substring(index);
	}

	/**
	 * 获取子字符串
	 *
	 * @param source    字符串源
	 * @param separator 目标字符串
	 * @return 结果
	 */
	public static String substringAfter(String source, String separator) {
		return StringUtils.substringAfter(source, separator);
	}

	/**
	 * 获取子字符串
	 *
	 * @param source    字符串源
	 * @param separator 目标字符串
	 * @return 结果
	 */
	public static String substringAfterLast(String source, String separator) {
		return StringUtils.substringAfterLast(source, separator);
	}

	/**
	 * 获取子字符串
	 *
	 * @param source    字符串源
	 * @param separator 目标字符串
	 * @return 结果
	 */
	public static String substringBefore(String source, String separator) {
		return StringUtils.substringBefore(source, separator);
	}

	/**
	 * 获取子字符串
	 *
	 * @param source    字符串源
	 * @param separator 目标字符串
	 * @return 结果
	 */
	public static String substringBeforeLast(String source, String separator) {
		return StringUtils.substringBeforeLast(source, separator);
	}

	/**
	 * 获取指定长度的含有字母和数字的字符串
	 *
	 * @param count 长度
	 * @return 结果
	 */
	public static String randomAlphanumeric(int count) {
		return RandomStringUtils.randomAlphanumeric(count);
	}

	/***
	 * 将首字母转为大写
	 *
	 * @param s 源字符串
	 * @return 转换后的字符串
	 */
	public static String toFirstUpper(String s) {
		if (!isNullOrEmpty(s)) {
			return Character.toUpperCase(s.charAt(0)) + s.substring(1);
		}
		return null;
	}

	/**
	 * 获取随机的数字字符串
	 *
	 * @param length 字符串长度
	 * @return 结果
	 */
	public static String randomNumber(int length) {
		StringBuilder sb = new StringBuilder();

		sb.append(NUMBER_SET.charAt(random.nextInt(9)));

		for (int i = 1; i < length; i++) {
			sb.append(NUMBER_SET.charAt(random.nextInt(10)));
		}
		return sb.toString();
	}

	/**
	 * 获取uuid
	 *
	 * @return uuid字符串
	 */
	public static String newUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/***
	 * 将多个空格替换为一个
	 * @param s 源字符串
	 * @return 结果
	 */
	public static String removeDuplicateBlank(String s) {
		if (isNullOrEmpty(s)) {
			return s;
		}
		return s.trim().replaceAll(" +", " ");
	}

	/***
	 * 字符串转换为boolean
	 * @param s 源字符串
	 * @param defaultBoolean 默认值
	 * @return 执行结果
	 */
	public static boolean toBoolean(String s, boolean defaultBoolean) {
		if (isNullOrEmpty(s)) {
			return ValidatorAssist.isTrue(s);
		}
		return defaultBoolean;
	}

	/***
	 * 裁剪字符串，显示前部分+...
	 * @param s 源字符串
	 * @return 结果
	 */
	public static String cut(String s, int cutLength) {
		return substring(s, 0, cutLength);
	}

	/**
	 * trim
	 *
	 * @param s 源字符串
	 * @return 结果
	 */
	public static String trim(String s) {
		return s.trim();
	}

	/**
	 * trim
	 *
	 * @param source source
	 * @param target target
	 * @return 结果
	 */
	public static boolean contains(@NotNull String source, @NotNull String target) {
		return StringAssist.contains(source, target, false);
	}

	/**
	 * trim
	 *
	 * @param source     source
	 * @param target     target
	 * @param ignoreCase ignoreCase
	 * @return 结果
	 */
	public static boolean contains(@NotNull String source, @NotNull String target, boolean ignoreCase) {
		if (StringAssist.isNullOrEmpty(source)) {
			return false;
		}

		if (StringAssist.isNullOrEmpty(target)) {
			return false;
		}

		if (ignoreCase) {
			return source.toLowerCase().contains(target.toLowerCase());
		}

		return source.contains(target);
	}

}
