package com.lzt.operate.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @param <String>
 * @param <Serializable>
 * @author lzt
 */
public class SerializableMap<String, Serializable> extends HashMap<String, Serializable> {

    SerializableMap() {
        super();
    }

    SerializableMap(Map<? extends String, Serializable> data) {
        this();
        this.appends(data);
    }

    public SerializableMap append(String key, Serializable value) {
        this.put(key, value);

        return this;
    }

    private SerializableMap appends(Map<? extends String, Serializable> data) {
        this.putAll(data);

        return this;
    }

    public java.lang.String serialize() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            return "{}";
        }
    }
}
