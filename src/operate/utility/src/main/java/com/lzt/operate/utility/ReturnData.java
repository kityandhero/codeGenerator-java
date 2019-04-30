package com.lzt.operate.utility;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * 参数异常类
 * CreteTime:2019-04-29 22:06
 * UpdateTIme:2019-04-29 22:06
 *
 * @author lzt
 */
public class ReturnData implements java.io.Serializable {

    @Getter
    @Setter
    public int code;

    @Getter
    @Setter
    public boolean success;

    @Getter
    @Setter
    public String message;

    @Getter
    @Setter
    public Serializable data;

    @Getter
    @Setter
    public Serializable extraData;

    public ReturnData() {
        this(200, true, "success", null, null);
    }

    public ReturnData(Serializable data) {
        this();
        this.data = data;
    }

    public ReturnData(Serializable data, Serializable extraData) {
        this();
        this.data = data;
        this.extraData = extraData;
    }

    public ReturnData(int code, boolean success, String message, Serializable data, Serializable extraData) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
        this.extraData = extraData;
    }

    public HashMap<String, Serializable> toMap() {
        HashMap<String, Serializable> map = new LinkedHashMap<>();

        map.put("code", this.code);
        map.put("success", this.success);
        map.put("message", this.message);

        if (this.data != null) {
            Serializable v = Optional.of(this.data).orElse(new SerializableData());

            if (!Collection.class.isAssignableFrom(v.getClass())) {
                map.put("data", Optional.of(v).orElse(new SerializableData()));
            } else {
                map.put("list", Optional.of(v).orElse(new ArrayList<>()));
            }
        }

        if (this.extraData != null) {
            map.put("extra", Optional.of(this.extraData).orElse(new SerializableData()));
        }

        return map;
    }
}
