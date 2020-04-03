package com.lzt.operate.code.generator.custommessagequeue.errorlog;

import com.lzt.operate.code.generator.common.enums.ErrorLogDataType;
import com.lzt.operate.code.generator.entities.ErrorLog;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.RequestAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.custommessagequeue.concurrentlinkeddeque.BaseProducerAdapter;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author luzhitao
 */
public class ErrorLogProducer extends BaseProducerAdapter<ErrorLog, ConcurrentLinkedDeque<ErrorLog>> {

	protected ErrorLogProducer() {
		super(SingletonErrorLogQueue.getInstance().getQueue());
	}

	public void pushException(Exception ex) {
		pushException(ex, "", ErrorLogDataType.CommonValue);
	}

	public void pushException(Exception ex, String data, ErrorLogDataType dataType) {
		Optional<HttpServletRequest> optionalHttpServletRequest = RequestAssist.getCurrentHttpServletRequest();

		ErrorLog errorLog = new ErrorLog();

		errorLog.setMessage(ex.getMessage());

		try {
			errorLog.setStackTrace(ConvertAssist.serialize(ex.getStackTrace()));
		} catch (Exception e) {
			errorLog.setStackTrace("");

			ErrorLog errorLogCatch = new ErrorLog();

			errorLogCatch.setMessage(e.getMessage());
			errorLogCatch.setHeader(RequestAssist.getCurrentRequestHeaderJson());
			errorLogCatch.setExceptionTypeName(e.getClass().getName());

			this.push(errorLogCatch);
		}

		errorLog.setHeader(RequestAssist.getCurrentRequestHeaderJson());

		errorLog.setData(data);
		errorLog.setDataType(dataType);
		errorLog.setExceptionTypeName(ex.getClass().getName());

		if (optionalHttpServletRequest.isPresent()) {
			HttpServletRequest request = optionalHttpServletRequest.get();

			String uriString = RequestAssist.getRequestOriginalUrl(request);

			if (!StringAssist.isNullOrEmpty(uriString)) {
				try {
					URL url = new URL(uriString);

					errorLog.setHost(url.getHost());
					errorLog.setPort(url.getPort());
					errorLog.setOtherLog("字符串转换为URI出错");

					errorLog.setRequestQueryString(url.getQuery());
					errorLog.setRequestBody(RequestAssist.getRequestBody(request));
					errorLog.setRequestParams(RequestAssist.getRequestFormData(request).serialize());
				} catch (Exception e) {
					ErrorLog errorLogUri = new ErrorLog();

					errorLogUri.setMessage(e.getMessage());
					errorLogUri.setHeader(RequestAssist.getCurrentRequestHeaderJson());
					errorLogUri.setExceptionTypeName(e.getClass().getName());
					errorLogUri.setData(uriString);
					errorLogUri.setData(uriString);
					errorLogUri.setDataType(ErrorLogDataType.CommonValue);

					this.push(errorLogUri);
				}

				errorLog.setUri(uriString);
			}
		}

		this.push(errorLog);
	}

}
