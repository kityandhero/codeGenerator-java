package com.lzt.operate.codetools.app.exceptionhandlers;

import com.lzt.operate.codetools.entities.ErrorLog;
import com.lzt.operate.custommessagequeue.custommessagequeue.errorlog.ErrorLogProducer;
import com.lzt.operate.custommessagequeue.custommessagequeue.errorlog.ErrorLogProducerFactory;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.exceptionhandlers.BaseGlobalExceptionHandler;
import com.lzt.operate.utility.pojo.SerializableData;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Optional;

/**
 * @author luzhitao
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {

	private String getRequestHeaderJson(@NonNull HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();

		SerializableData headers = new SerializableData();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();

			headers.append(headerName, request.getHeader(headerName));
		}

		return headers.serialize();
	}

	@Override
	protected void recordErrorLog(Throwable e, HttpServletRequest request) {
		ErrorLog errorLog = new ErrorLog();

		try {
			errorLog.setMessage(e.getMessage());
			errorLog.setStackTrace(ConvertAssist.serialize(e.getStackTrace()));

			if (Optional.ofNullable(request).isPresent()) {
				errorLog.setHeader(this.getRequestHeaderJson(request));
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			return;
		}

		ErrorLogProducer producer = ErrorLogProducerFactory.getInstance().getProducer();

		producer.push(errorLog);
	}

	@Override
	protected void recordErrorLog(Exception e, HttpServletRequest request) {
		ErrorLog errorLog = new ErrorLog();

		try {
			errorLog.setMessage(e.getMessage());
			errorLog.setStackTrace(ConvertAssist.serialize(e.getStackTrace()));

			if (Optional.ofNullable(request).isPresent()) {
				errorLog.setHeader(this.getRequestHeaderJson(request));
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			return;
		}

		ErrorLogProducer producer = ErrorLogProducerFactory.getInstance().getProducer();

		producer.push(errorLog);
	}

}
