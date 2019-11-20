package com.lzt.operate.entities;

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

    public SerializableData(String key, Serializable v) {
        super();
        this.append(key, v);
    }

    public SerializableData(Map<String, ? extends Serializable> data) {
        super(data);
    }

    public SerializableData(Exception e) {
        super();

        this.append("message", e.getMessage());
        this.append("stackTrace", e.getStackTrace());
    }
}
