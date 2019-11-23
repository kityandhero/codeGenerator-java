package com.lzt.operate.entities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzt
 */
public class SerializableMap<String, T extends Serializable> extends HashMap<String, Serializable> {

    SerializableMap() {
        super();
    }

    SerializableMap(Map<? extends String, ? extends Serializable> data) {
        this();
        this.appends(data);
    }

    public SerializableMap append(String key, Serializable value) {
        this.put(key, value);

        return this;
    }

    public SerializableMap appends(Map<? extends String, ? extends Serializable> data) {
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
