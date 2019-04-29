package com.lzt.operate.utility;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
    public Object data;

    @Getter
    @Setter
    public Object extraData;

    public ReturnData() {
        this(200, true, "success", new Object(), new Object());
    }

    private ReturnData(int code, boolean success, String message, Object data, Object extraData) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
        this.extraData = extraData;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("code", this.code);
        map.put("success", this.success);
        map.put("message", this.message);

        if (this.data != null) {
            Object v = Optional.of(this.data).orElse(new Object());

            if (!Collection.class.isAssignableFrom(v.getClass())) {
                map.put("data", Optional.of(this.data).orElse(new Object()));
            } else {
                map.put("list", Optional.of(this.data).orElse(new ArrayList<>()));
            }
        }

        if (this.extraData != null) {
            map.put("extra", Optional.of(this.extraData).orElse(new Object()));
        }

        return map;
    }
}
