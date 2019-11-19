package com.lzt.operate.entity;

import com.lzt.operate.extensions.StringEx;
import lombok.var;

import java.io.Serializable;
import java.util.Map;

public class ParamData extends SerializableMap<String, Serializable> {

    public ParamData() {
        super();
    }

    public ParamData(Map<String, String> data) {
        super(data);
    }

    public StringEx getByKey(String key) {
        var v = this.get(key);

        return new StringEx((String) v);
    }

    public boolean isNullOrEmpty(String key) {
        var v = this.get(key);

        return StringEx.isNullOrEmpty((String) v);
    }
}