package com.lzt.operate.utility;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * 参数异常类
 * CreteTime:2019-04-29 22:06
 * UpdateTIme:2019-04-29 22:06
 *
 * @author lzt
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReturnData extends ReturnDataCore {

    public final static int CODE_ACCESS_SUCCESS = 200;
    public final static String MESSAGE_ACCESS_SUCCESS = "success";

    public Serializable data;

    public ReturnData() {
        this(200, true, "success", null, null);
    }

    public ReturnData(Serializable data) {
        this();
        this.data = data;
    }

    public ReturnData(Serializable data, HashMap<String, Serializable> extra) {
        this();
        this.data = data;
        this.extra = extra;
    }

    private ReturnData(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public ReturnData(int code, boolean success, String message, Serializable data, HashMap<String, Serializable> extra) {
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

    public ReturnDataCore toResult() {
        Serializable v = Optional.of((ArrayList) this.data).orElse((new ArrayList()));

        if (ArrayList.class.isAssignableFrom(v.getClass())) {
            return toReturnList((ArrayList) v);
        }

        HashMap single = Optional.of((HashMap) this.data).orElse((new HashMap<String, Serializable>(0)));

        if (HashMap.class.isAssignableFrom(v.getClass())) {
            return toReturnSingle((HashMap<String, Serializable>) v);
        }


    }

    private ReturnSingle toReturnSingle(HashMap<String, Serializable> v) {
        return new ReturnSingle(this.code, this.success, this.message, Optional.of(v)
                                                                               .orElse(new HashMap<>(0)), Optional
                .of(this.extra)
                .orElse(new HashMap<>(0)));
    }

    private ReturnList toReturnList(ArrayList v) {
        return new ReturnList(this.code, this.success, this.message, Optional.of(v)
                                                                             .orElse(new ArrayList<>()), Optional
                .of(this.extra)
                .orElse(new HashMap<>(0)));
    }
}
