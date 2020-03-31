package com.lzt.operate.utility.web.controllers;

import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultDataFactory;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.ReturnMessage;
import com.lzt.operate.utility.pojo.SerializableData;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
import com.lzt.operate.utility.pojo.results.ListResult;
import com.lzt.operate.utility.pojo.results.PageListResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luzhitao
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
	protected ParamData getParamData(Map<String, ? extends Serializable> query) {
		return new ParamData(query);
	}

	protected ResultSingleData singleData(Object data) {
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

	protected ResultListData listDataEmpty() {
		return this.listData(new ArrayList<>(), new SerializableData());
	}

	protected ResultListData listData(ListResult<Object> listResult) {
		return this.listData(listResult.getList(), new SerializableData());
	}

	protected ResultListData listData(ListResult<Object> listResult, Object extra) {
		if (listResult.getSuccess()) {
			return this.listData(listResult.getList(), extra);
		}

		return ResultDataFactory.failListData(listResult.getCode(), extra);
	}

	protected ResultListData listData(List<Object> list) {
		ResultListData result = ResultDataFactory.successListData();

		result.list = list;
		result.extra = new SerializableData();

		return result;
	}

	protected ResultListData listData(List<Object> list, Object extra) {
		ResultListData result = ResultDataFactory.successListData();

		result.list = list;
		result.extra = extra;

		return result;
	}

	protected ResultSingleData success() {
		return ResultDataFactory.successSingleData();
	}

	protected ResultSingleData fail(@NonNull ExecutiveSimpleResult result) {
		ReturnMessage returnMessage = result.getCode();

		returnMessage.toMessage(result.getMessage());

		return fail(returnMessage);
	}

	protected ResultSingleData fail(@NonNull ReturnMessage returnMessage) {
		return ResultDataFactory.failData(returnMessage);
	}

	protected ResultSingleData fail(@NonNull ReturnMessage returnMessage, SerializableData data) {
		ResultSingleData result = ResultDataFactory.failData(returnMessage);

		result.data = data;

		return result;
	}

	protected ResultSingleData fail(@NonNull ReturnMessage returnMessage, Serializable data, Serializable extra) {
		ResultSingleData result = ResultDataFactory.failData(returnMessage);

		result.data = data;
		result.extra = extra;

		return result;
	}

	protected ResultSingleData noChange(String description) {
		SerializableData data = new SerializableData();

		data.append("description", description);

		return ResultDataFactory.failData(ReturnDataCode.NoChange.toMessage(description), data);
	}

	protected ResultSingleData paramError(String paramName, String description) {
		SerializableData data = new SerializableData();

		data.append("paramName", paramName);
		data.append("description", description);

		return ResultDataFactory.failData(ReturnDataCode.ParamError.toMessage(StringAssist.merge("参数：", paramName, "【", description, "】")), data);
	}

	protected ResultSingleData noDataError() {
		return this.noDataError("指定的数据不存在！");
	}

	protected ResultSingleData noDataError(String description) {
		SerializableData data = new SerializableData();

		data.append("description", description);

		return ResultDataFactory.failData(ReturnDataCode.NoData.toMessage(description));
	}

	protected ResultSingleData exceptionError(Exception e) {
		return ResultDataFactory.failData(ReturnDataCode.EXCEPTION_ERROR.toMessage());
	}

	protected ResultSingleData customError(int code, boolean success, String message) {
		return ResultDataFactory.failData(ReturnDataCode.EXCEPTION_ERROR.toMessage());
	}

	protected ResultListData pageDataEmpty() {
		return this.pageDataEmpty(new SerializableData());
	}

	protected ResultListData pageDataEmpty(int pageSize) {
		return this.pageDataEmpty(pageSize, new SerializableData());
	}

	protected ResultListData pageDataEmpty(SerializableData extra) {
		return this.pageDataEmpty(10, extra);
	}

	protected ResultListData pageDataEmpty(int pageSize, SerializableData extra) {
		return this.pageData(new ArrayList<>(), 1, pageSize, 0, extra);
	}

	protected ResultListData pageData(Page<Object> pageListResult) {
		return this.pageData(pageListResult, new SerializableData());
	}

	protected ResultListData pageData(Page<Object> pageListResult, SerializableData extra) {
		return this.pageData(pageListResult.getContent(), pageListResult.getNumber(), pageListResult.getSize(), pageListResult
				.getTotalPages(), extra);
	}

	protected ResultListData pageData(PageListResult<Object> pageListResult) {
		return this.pageData(pageListResult, new SerializableData());
	}

	protected ResultListData pageData(PageListResult<Object> pageListResult, SerializableData extra) {
		if (pageListResult.getSuccess()) {
			return this.pageData(pageListResult.getList(), pageListResult.getPageIndex(), pageListResult.getPageSize(), pageListResult
					.getTotalSize(), extra);
		}

		return ResultDataFactory.failListData(pageListResult.getCode(), extra);
	}

	protected ResultListData pageData(List<SerializableData> list, int pageNo, int pageSize, long totalPage) {
		return pageData(list, pageNo, pageSize, totalPage, new SerializableData());
	}

	protected ResultListData pageData(List<Object> list, int pageNo, int pageSize, long totalPage) {
		return pageData(list, pageNo, pageSize, totalPage, new SerializableData());
	}

	protected ResultListData pageData(List<Object> list, int pageNo, int pageSize, long totalPage, SerializableData other) {
		ResultListData result = this.listData(list);

		SerializableData extra = new SerializableData();

		extra.getMultimap().put("pageNo", pageNo);
		extra.getMultimap().put("pageSize", pageSize);

		extra.getMultimap().put("total", totalPage);
		extra.getMultimap().put("other", other.getMultimap());

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
