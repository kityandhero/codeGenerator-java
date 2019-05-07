package com.lzt.operate.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 空的可序列化类
 *
 * @author lzt
 */
public class SerializableData extends HashMap<String, Serializable> {
    static final String EMPTY_SERIALIZE_VALUE = "{}";

    public SerializableData() {
        super();
    }

    public SerializableData(Map<? extends String, ? extends Serializable> data) {
        this();
        this.appends(data);
    }

    public SerializableData append(String key, Serializable value) {
        this.put(key, value);

        return this;
    }

    public SerializableData appends(Map<? extends String, ? extends Serializable> data) {
        this.putAll(data);

        return this;
    }

    public SerializableData appends(SerializableData data) {
        this.putAll(data);

        return this;
    }

    public String serialize() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            return "{}";
        }
    }
}
