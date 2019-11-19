package com.lzt.operate.web.controllers;

import com.lzt.operate.entity.ErrorMessage;
import com.lzt.operate.entity.ParamData;
import com.lzt.operate.entity.ResultDataFactory;
import com.lzt.operate.entity.ResultListData;
import com.lzt.operate.entity.ResultSingleData;
import com.lzt.operate.entity.SerializableData;
import lombok.var;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author lzt
 */
public class BaseController {

    /**
     * 返回结构化的参数
     *
     * @param query
     * @return
     */
    protected ParamData getParamData(Map<String, String> query) {
        return new ParamData(query);
    }

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

    protected ResultListData listData(List list) {
        ResultListData result = ResultDataFactory.successListData();

        result.list = list;
        result.extra = new SerializableData();

        return result;
    }

    private ResultListData listData(List list, Serializable extra) {
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

    protected ResultSingleData paramError(String paramName, String description) {
        var code = ErrorMessage.ParamError.getCode();

        var message = ErrorMessage.ParamError.getMessage();

        var data = new SerializableData();
        data.append("paramName", paramName);
        data.append("description", description);

        return ResultDataFactory.failData(code, message, data);
    }

    protected ResultSingleData exceptionError(Exception e) {
        var code = ErrorMessage.exceptionError.getCode();
        var message = ErrorMessage.ParamError.getMessage();
        var data = new SerializableData(e);

        return ResultDataFactory.failData(code, message, data);
    }

    protected ResultListData pageListData(List list) {
        return this.listData(list);
    }

    protected ResultListData pageListData(List list, Serializable extra) {
        return this.listData(list, extra);
    }

    protected ResultListData pageData(List list, int pageNo, int pageSize, int total) {
        return pageData(list, pageNo, pageSize, total, new SerializableData());
    }

    protected ResultListData pageData(List list, int pageNo, int pageSize, int total, Serializable other) {
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
