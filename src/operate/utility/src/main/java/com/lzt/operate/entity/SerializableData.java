package com.lzt.operate.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * 空的可序列化类
 *
 * @author lzt
 */
public class SerializableData extends SerializableMap<String, Serializable> {
    static final String EMPTY_SERIALIZE_VALUE = "{}";

    public SerializableData() {
        super();
    }

    public SerializableData(Map<String, Serializable> data) {
        super(data);
    }

}
