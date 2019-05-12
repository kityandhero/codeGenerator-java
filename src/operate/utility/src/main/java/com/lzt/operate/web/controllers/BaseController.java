package com.lzt.operate.web.controllers;

import com.lzt.operate.utility.ResultDataFactory;
import com.lzt.operate.utility.ResultListData;
import com.lzt.operate.utility.ResultSingleData;
import com.lzt.operate.utility.SerializableData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author lzt
 */
public class BaseController {

    protected ResultSingleData singleData(Serializable data) {
        ResultSingleData result = ResultDataFactory.successSingleData();

        result.data = data;
        result.extra = new SerializableData();

        return result;
    }

    protected ResultSingleData singleData(Serializable data, Serializable extra) {
        ResultSingleData result = ResultDataFactory.successSingleData();

        result.data = data;
        result.extra = extra;

        return result;
    }

    private ResultListData listData(ArrayList list) {
        ResultListData result = ResultDataFactory.successListData();

        result.list = list;
        result.extra = new SerializableData();

        return result;
    }

    private ResultListData listData(ArrayList list, Serializable extra) {
        ResultListData result = ResultDataFactory.successListData();

        result.list = list;
        result.extra = extra;

        return result;
    }

    protected ResultSingleData success() {
        return ResultDataFactory.successSingleData();
    }

    protected ResultSingleData fail(int code, String message) {
        return ResultDataFactory.failData(code, message);
    }

    protected ResultSingleData fail(int code, String message, SerializableData data) {
        ResultSingleData result = ResultDataFactory.failData(code, message);

        result.data = data;

        return result;
    }

    protected ResultSingleData fail(int code, String message, Serializable data, Serializable extra) {
        ResultSingleData result = ResultDataFactory.failData(code, message);

        result.data = data;
        result.extra = extra;

        return result;
    }

    protected ResultListData pageListData(ArrayList list) {
        return this.listData(list);
    }

    protected ResultListData pageListData(ArrayList list, Serializable extra) {
        return this.listData(list, extra);
    }

    protected ResultListData pageData(ArrayList list, int pageNo, int pageSize, int total, Serializable other) {
        ResultListData result = this.listData(list);

        SerializableData extra = new SerializableData();

        extra.put("pageNo", pageNo);
        extra.put("pageSize", pageSize);
        extra.put("total", total);
        extra.put("other", other);

        result.extra = extra;

        return result;
    }
}
