package com.lzt.operate.utility.extensions;

import com.google.common.base.Strings;
import com.lzt.operate.utility.assists.StringAssist;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 字符串扩展类
 * CreteTime:2019-04-28 23:14
 * UpdateTIme:2019-04-28 23:14
 *
 * @author luzhitao
 */
public class StringEx implements Serializable {
	private static final long serialVersionUID = 4500327534935128311L;
	private StringBuilder builder;

	public StringEx(String source) {
		builder = new StringBuilder();
		builder.append(Optional.of(source).orElse(""));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if ((obj instanceof String)) {
			return builder.toString().equals(obj);
		} else if (!(obj instanceof StringEx)) {
			return false;
		} else {
			return builder.toString().equals(obj.toString());
		}

	}

	@Override
	public int hashCode() {
		return builder.toString().hashCode();
	}

	public List<String> split(char separator) {
		return StringAssist.split(builder.toString(), separator);
	}

	public Iterable<String> splitToList(char separator) {
		return StringAssist.splitToList(builder.toString(), separator);
	}

	@Override
	public String toString() {
		return builder.toString();
	}

	public boolean isNullOrEmpty() {
		return Strings.isNullOrEmpty(builder.toString());
	}

	public int toInt() {
		return Integer.parseInt(builder.toString());
	}

	public int toInteger() {
		return toInt();
	}

	public IntegerEx toIntegerEx() {
		return new IntegerEx(toInteger());
	}

	public StringEx padEnd(int minLength, char padChar) {
		String s = Strings.padEnd(builder.toString(), minLength, padChar);
		return new StringEx(s);
	}

	public StringEx padStart(int minLength, char padChar) {
		String s = Strings.padStart(builder.toString(), minLength, padChar);
		return new StringEx(s);
	}

	public StringEx repeat(int count) {
		String s = Strings.repeat(builder.toString(), count);
		return new StringEx(s);
	}

	public StringEx append(String target) {
		builder.append(Optional.of(target).orElse(""));
		return new StringEx(builder.toString());
	}

	public StringEx appendFormat(String format, Object... args) {
		builder.append(StringAssist.format(format, args).toString());
		return new StringEx(builder.toString());
	}

	public StringEx appendLenientFormat(@Nullable String template, @Nullable Object @Nullable ... args) {
		builder.append(StringAssist.lenientFormat(template, args).toString());
		return new StringEx(builder.toString());
	}

	public boolean contains(String target) {
		if (StringAssist.isNullOrEmpty(target)) {
			return false;
		}

		return builder.toString().contains(target);
	}

	public StringEx substring(int index, int length) {
		return new StringEx(StringAssist.substring(toString(), index, length));
	}

	public StringEx substring(int index) {
		return new StringEx(StringAssist.substring(toString(), index));
	}

	/**
	 * 替换指定字符字符串为目标字符串
	 *
	 * @param target      替换目标
	 * @param replaceText 替换值
	 * @return 替换后的字符串
	 */
	public StringEx replace(String target, String replaceText) {
		String result = builder.toString().replace(target, replaceText);
		return new StringEx(result);
	}

	/**
	 * 序列化方法
	 *
	 * @return 转换后的字符串
	 */
	public String serialize() {
		return builder.toString();
	}
}
