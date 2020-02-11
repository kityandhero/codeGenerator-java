package com.lzt.operate.utility.extensions;

/**
 * 整型扩展类
 * CreteTime:2019-04-28 23:14
 * UpdateTIme:2019-04-28 23:14
 *
 * @author luzhitao
 */
public class IntegerEx {
    private Integer source;

    IntegerEx(Integer source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return this.source.toString();
    }

    public StringEx toStringEx() {
        return new StringEx(this.toString());
    }
}
