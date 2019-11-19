package com.lzt.operate.extensions;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.apache.commons.codec.digest.Md5Crypt;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;
import java.util.Optional;

/**
 * 字符串扩展类
 * CreteTime:2019-04-28 23:14
 * UpdateTIme:2019-04-28 23:14
 *
 * @author lzt
 */
public class StringEx implements Serializable {
    private StringBuilder builder;

    public StringEx(String source) {
        this.builder = new StringBuilder();
        this.builder.append(Optional.of(source).orElse(""));
    }

    public static StringEx join(String separator, Iterable<?> parts) {
        String s = Joiner.on(Optional.of(separator).orElse("")).join(parts);
        return new StringEx(s);
    }

    public static boolean isNullOrEmpty(@Nullable String target) {
        return Strings.isNullOrEmpty(target);
    }

    public static StringEx format(String format, Object... args) {
        String f = Optional.of(format).orElse("");
        String v = String.format(f, args);

        return new StringEx(v);
    }

    private static StringEx lenientFormat(String template, @Nullable Object @Nullable ... args) {
        String f = Optional.of(template).orElse("");
        String v = Strings.lenientFormat(f, args);
        return new StringEx(v);
    }

    private static StringEx ToCamelStyle(String str) {
        if (str != null) {
            if (str.contains("_")) {
                str = str.toLowerCase();
                StringBuilder sb = new StringBuilder();
                sb.append(String.valueOf(str.charAt(0)).toUpperCase());
                for (int i = 1; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if (c != '_') {
                        sb.append(c);
                    } else {
                        if (i + 1 < str.length()) {
                            sb.append(String.valueOf(str.charAt(i + 1)).toUpperCase());
                            i++;
                        }
                    }
                }
                return new StringEx(sb.toString());
            } else {
                String firstChar = String.valueOf(str.charAt(0)).toUpperCase();
                String otherChars = str.substring(1);
                return new StringEx(firstChar + otherChars);
            }
        }
        return null;
    }

    public static StringEx ToMD5(String target) {
        String v = Optional.of(target).orElse("");

        return new StringEx(Md5Crypt.md5Crypt(v.getBytes()));
    }

    public static Iterable<String> split(String target, char separator) {
        return Splitter.on(separator).omitEmptyStrings().split(target);
    }

    private static Iterable<String> splitToList(String target, char separator) {
        return Splitter.on(separator).omitEmptyStrings().splitToList(target);
    }

    public Iterable<String> split(char separator) {
        return StringEx.split(this.builder.toString(), separator);
    }

    public Iterable<String> splitToList(char separator) {
        return StringEx.splitToList(this.builder.toString(), separator);
    }

    @Override
    public String toString() {
        return this.builder.toString();
    }

    public boolean isNullOrEmpty() {
        return Strings.isNullOrEmpty(this.builder.toString());
    }

    public int toInt() {
        return Integer.parseInt(this.builder.toString());
    }

    public int toInteger() {
        return this.toInt();
    }

    public IntegerEx toIntegerEx() {
        return new IntegerEx(this.toInteger());
    }

    public StringEx padEnd(int minLength, char padChar) {
        String s = Strings.padEnd(this.builder.toString(), minLength, padChar);
        return new StringEx(s);
    }

    public StringEx padStart(int minLength, char padChar) {
        String s = Strings.padStart(this.builder.toString(), minLength, padChar);
        return new StringEx(s);
    }

    public StringEx repeat(int count) {
        String s = Strings.repeat(this.builder.toString(), count);
        return new StringEx(s);
    }

    public StringEx append(String target) {
        this.builder.append(Optional.of(target).orElse(""));
        return new StringEx(this.builder.toString());
    }

    public StringEx appendFormat(String format, Object... args) {
        this.builder.append(StringEx.format(format, args).toString());
        return new StringEx(this.builder.toString());
    }

    public StringEx appendLenientFormat(@Nullable String template, @Nullable Object @Nullable ... args) {
        this.builder.append(StringEx.lenientFormat(template, args).toString());
        return new StringEx(this.builder.toString());
    }

    public boolean contains(String target) {
        if (StringEx.isNullOrEmpty(target)) {
            return false;
        }

        return this.builder.toString().contains(target);
    }

    public String serialize() {
        return this.builder.toString();
    }
}
