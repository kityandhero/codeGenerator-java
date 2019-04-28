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

    public Iterable<String> split(char separator) {
        return Splitter.on(separator).split(this.source);
    }

    @Override
    public String toString() {
        return this.source;
    }
}
