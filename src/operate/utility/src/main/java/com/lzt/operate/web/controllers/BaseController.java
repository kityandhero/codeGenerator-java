package com.lzt.operate.web.controllers;

import com.lzt.operate.entities.ParamData;
import com.lzt.operate.entities.ResultDataFactory;
import com.lzt.operate.entities.ResultListData;
import com.lzt.operate.entities.ResultSingleData;
import com.lzt.operate.entities.SerializableData;
import com.lzt.operate.enums.ReturnDataCode;
import lombok.NonNull;
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
     * @param query json参数
     * @return ParamData
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

    protected ResultSingleData fail(@NonNull ReturnDataCode returnDataCode) {
        return ResultDataFactory.failData(returnDataCode);
    }

    protected ResultSingleData fail(@NonNull ReturnDataCode returnDataCode, SerializableData data) {
        ResultSingleData result = ResultDataFactory.failData(returnDataCode);

        result.data = data;

        return result;
    }

    protected ResultSingleData fail(@NonNull ReturnDataCode returnDataCode, Serializable data, Serializable extra) {
        ResultSingleData result = ResultDataFactory.failData(returnDataCode);

        result.data = data;
        result.extra = extra;

        return result;
    }

    protected ResultSingleData paramError(String paramName, String description) {
        var data = new SerializableData();
        data.append("paramName", paramName);
        data.append("description", description);

        return ResultDataFactory.failData(ReturnDataCode.PARAM_ERROR);
    }

    protected ResultSingleData noDataError() {

        return this.noDataError("指定的数据不存在！");
    }

    private ResultSingleData noDataError(String description) {
        var data = new SerializableData();
        data.append("description", description);

        return ResultDataFactory.failData(ReturnDataCode.NODATA);
    }

    protected ResultSingleData exceptionError(Exception e) {
        return ResultDataFactory.failData(ReturnDataCode.EXCEPTION_ERROR);
    }

    protected ResultSingleData customError(int code, boolean success, String message) {
        return ResultDataFactory.failData(ReturnDataCode.EXCEPTION_ERROR);
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
