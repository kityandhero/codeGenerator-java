package com.lzt.operate.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * 空的可序列化类
 *
 * @author lzt
 */
public class SerializableData implements Serializable {
    public static final String EMPTY_SERIALIZE_VALU = "{}";

    public String serialize() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            return "{}";
        }
    }
}
