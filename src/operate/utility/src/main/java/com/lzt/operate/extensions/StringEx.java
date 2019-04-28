package com.lzt.operate.extensions;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * 字符串扩展类
 * CreteTime:2019-04-28 23:14
 * UpdateTIme:2019-04-28 23:14
 *
 * @author lzt
 */
public class StringEx {
    private String source;

    public StringEx(String source) {
        this.source = Optional.of(source).or("");
    }

    public static StringEx join(String separator, Iterable<?> parts) {
        String s = com.google.common.base.Joiner.on(separator).join(parts);
        return new StringEx(s);
    }

    public static boolean isNullOrEmpty(@Nullable String target) {
        return com.google.common.base.Strings.isNullOrEmpty(target);
    }

    public Iterable<String> split(char separator) {
        return Splitter.on(separator).split(this.source);
    }

    @Override
    public String toString() {
        return this.source;
    }

    public int toInt() {
        return Integer.parseInt(this.source);
    }

    public int toInteger() {
        return this.toInt();
    }

    public IntegerEx toIntegerEx() {
        return new IntegerEx(this.toInteger());
    }

    public StringEx padEnd(int minLength, char padChar) {
        String s = com.google.common.base.Strings.padEnd(this.source, minLength, padChar);
        return new StringEx(s);
    }

    public StringEx padStart(int minLength, char padChar) {
        String s = com.google.common.base.Strings.padStart(this.source, minLength, padChar);
        return new StringEx(s);
    }

    public StringEx repeat(int count) {
        String s = com.google.common.base.Strings.repeat(this.source, count);
        return new StringEx(s);
    }
}
