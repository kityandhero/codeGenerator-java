package com.lzt.operate.extensions;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;

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

    public Iterable<String> split(char separator) {
        return Splitter.on(separator).split(this.source);
    }

    @Override
    public String toString() {
        return this.source;
    }

    public int toInt() {

        // com.google.common.base.Strings.
        return Integer.parseInt(this.source);
    }

    public int toInteger() {

        // com.google.common.base.Strings.
        return this.toInt();
    }


    public IntegerEx toIntegerEx() {
        // com.google.common.base.Strings.
        return new IntegerEx(this.toInteger());
    }

}
