package com.lzt.operate.entity;

import java.util.Map;

public class ParamData extends SerializableMap<String, String> {

    public ParamData() {
        super();
    }

    public ParamData(Map<String, String> data) {
        super(data);
    }

}