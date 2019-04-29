package com.lzt.operate.extensions;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Optional;

/**
 * 字符串扩展类
 * CreteTime:2019-04-28 23:14
 * UpdateTIme:2019-04-28 23:14
 *
 * @author lzt
 */
public class StringEx {
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


    public Iterable<String> split(char separator) {
        return Splitter.on(separator).split(this.builder);
    }

    @Override
    public String toString() {
        return this.builder.toString();
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
        this.builder.append(java.util.Optional.of(target).orElse(""));
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
}
