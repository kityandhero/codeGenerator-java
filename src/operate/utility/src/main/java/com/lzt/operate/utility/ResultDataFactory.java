package com.lzt.operate.utility;

import java.util.ArrayList;

/**
 * 参数异常类
 * CreteTime:2019-04-29 22:06
 * UpdateTIme:2019-04-29 22:06
 *
 * @author lzt
 */
public class ResultDataFactory {

    public final static int CODE_ACCESS_SUCCESS = 200;
    public final static String MESSAGE_ACCESS_SUCCESS = "success";

    public static ResultSingleData failData(int code, String message) {
        return new ResultSingleData(code, false, message, new SerializableData(), new SerializableData());
    }

    public static ResultSingleData successSingleData() {
        return new ResultSingleData(CODE_ACCESS_SUCCESS, true, MESSAGE_ACCESS_SUCCESS, new SerializableData(), new SerializableData());
    }

    public static ResultListData successListData() {
        return new ResultListData(CODE_ACCESS_SUCCESS, true, MESSAGE_ACCESS_SUCCESS, new ArrayList(), new SerializableData());
    }

}
