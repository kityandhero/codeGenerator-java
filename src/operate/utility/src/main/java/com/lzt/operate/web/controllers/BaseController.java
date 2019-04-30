package com.lzt.operate.web.controllers;

import com.lzt.operate.utility.ReturnData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzt
 */
public class BaseController {

    protected Map<String, Object> data(int code, boolean success, String message, Object data, Object extraData) {
        ReturnData returnData = new ReturnData(code, success, message, data, extraData);
        return returnData.toMap();
    }

    protected Map<String, Object> success(Object data, Object extraData) {
        ReturnData returnData = new ReturnData(data, extraData);
        return returnData.toMap();
    }

    protected Map<String, Object> fail(int code, String message, Object data, Object extraData) {
        ReturnData returnData = new ReturnData(code, false, message, data, extraData);
        return returnData.toMap();
    }

    protected Map<String, Object> pageData(Iterable<Object> list, Object extraData) {
        ReturnData returnData = new ReturnData(list, extraData);
        return returnData.toMap();
    }

    protected Map<String, Object> pageData(Iterable<Object> list, int pageNo, int pageSize, int total, Object other) {
        Map<String, Object> extraData = new HashMap<>(3);
        extraData.put("pageNo", pageNo);
        extraData.put("pageSize", pageSize);
        extraData.put("total", total);
        extraData.put("other", other);

        ReturnData returnData = new ReturnData(list, extraData);
        return returnData.toMap();
    }
}
