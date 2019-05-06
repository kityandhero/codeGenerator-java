package com.lzt.operate.utility;

import lombok.Data;

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
@Data
public class ReturnData implements java.io.Serializable {

    public final static int CODE_ACCESS_SUCCESS = 200;
    public final static String MESSAGE_ACCESS_SUCCESS = "success";

    public int code;

    public boolean success;

    public String message;

    public Serializable data;

    public Serializable extra;

    public ReturnData() {
        this(200, true, "success", null, null);
    }

    public ReturnData(Serializable data) {
        this();
        this.data = data;
    }

    public ReturnData(Serializable data, Serializable extra) {
        this();
        this.data = data;
        this.extra = extra;
    }

    private ReturnData(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public ReturnData(int code, boolean success, String message, Serializable data, Serializable extra) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
        this.extra = extra;
    }

    public static ReturnData createSuccessData() {
        return new ReturnData();
    }

    public static ReturnData createParamErrorData(String paramName, String description) {
        return new ReturnData(40001, false, "参数" + Optional.of(paramName)
                                                           .orElse("参数名") + "错误:" + Optional.of(description)
                                                                                            .orElse("错误描述"));
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

        if (this.extra != null) {
            map.put("extra", Optional.of(this.extra).orElse(new SerializableData()));
        }

        return map;
    }
}
