package com.lzt.operate.web.controllers;

import com.lzt.operate.entities.ParamData;
import com.lzt.operate.entities.ResultDataFactory;
import com.lzt.operate.entities.ResultListData;
import com.lzt.operate.entities.ResultSingleData;
import com.lzt.operate.entities.SerializableData;
import com.lzt.operate.enums.ReturnDataCode;
import io.swagger.annotations.Api;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author lzt
 */
@RestController
@Slf4j
@Api(tags = "错误接口")
public class BaseController implements ErrorController {

	/**
	 * 返回结构化的参数
	 *
	 * @param query json参数
	 * @return ParamData
	 */
	protected ParamData getParamData(Map<String, Serializable> query) {
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

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@GetMapping(value = "/error")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Object error(HttpServletRequest request, HttpServletResponse response) {

		log.error("response error,httpCode:" + response.getStatus());

		final int requestNotFound = 404;

		// 错误处理逻辑
		int status = response.getStatus();
		if (status == requestNotFound) {

			return new ResultSingleData(ReturnDataCode.REQUEST_NOT_FOUND);
		} else if (status == 500) {
			return new ResultSingleData(ReturnDataCode.SYSTEM_ERR);
		} else if (status >= 100 && status < 200) {
			return new ResultSingleData(ReturnDataCode.HTTP_ERROR_100);
		} else if (status >= 300 && status < 400) {
			return new ResultSingleData(ReturnDataCode.HTTP_ERROR_300);
		} else if (status >= 400 && status < 500) {
			return new ResultSingleData(ReturnDataCode.HTTP_ERROR_400);
		} else {
			return new ResultSingleData(ReturnDataCode.SYSTEM_ERR);
		}
	}

}
