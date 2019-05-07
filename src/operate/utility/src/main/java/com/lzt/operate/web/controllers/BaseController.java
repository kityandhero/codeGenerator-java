package com.lzt.operate.web.controllers;

import com.lzt.operate.utility.ReturnData;
import com.lzt.operate.utility.ReturnDataCore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author lzt
 */
public class BaseController {

    protected ReturnDataCore data(int code, boolean success, String message, Serializable data, Serializable extraData) {
        ReturnData returnData = new ReturnData(code, success, message, data, extraData);
        return returnData.toResult();
    }

    protected ReturnDataCore success() {
        ReturnData returnData = new ReturnData();
        return returnData.toResult();
    }

    protected ReturnDataCore success(Serializable data) {
        ReturnData returnData = new ReturnData(data);
        return returnData.toResult();
    }

    protected ReturnDataCore success(Serializable data, Serializable extraData) {
        ReturnData returnData = new ReturnData(data, extraData);
        return returnData.toResult();
    }

    protected ReturnDataCore fail(int code, String message, Serializable data, Serializable extraData) {
        ReturnData returnData = new ReturnData(code, false, message, data, extraData);
        return returnData.toResult();
    }

    protected ReturnDataCore pageData(ArrayList list) {
        ReturnData returnData = new ReturnData(list);
        return returnData.toResult();
    }

    protected ReturnDataCore pageData(ArrayList list, Serializable extraData) {
        ReturnData returnData = new ReturnData(list, extraData);
        return returnData.toResult();
    }

    protected ReturnDataCore pageData(ArrayList list, int pageNo, int pageSize, int total, Serializable other) {
        HashMap<String, Serializable> extraData = new LinkedHashMap<>();
        extraData.put("pageNo", pageNo);
        extraData.put("pageSize", pageSize);
        extraData.put("total", total);
        extraData.put("other", other);

        ReturnData returnData = new ReturnData(list, extraData);
        return returnData.toResult();
    }
}
