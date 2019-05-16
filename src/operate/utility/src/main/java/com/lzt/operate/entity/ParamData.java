package com.lzt.operate.entity;

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

    public String getByKey(String key) {
        var name = this.get("name");

        return (String) name;
    }
}