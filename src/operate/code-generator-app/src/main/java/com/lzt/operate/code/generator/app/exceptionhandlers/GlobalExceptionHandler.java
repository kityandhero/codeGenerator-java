package com.lzt.operate.code.generator.app.exceptionhandlers;

import com.lzt.operate.code.generator.custommessagequeue.errorlog.ErrorLogProducerFactory;
import com.lzt.operate.utility.exceptionhandlers.BaseGlobalExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luzhitao
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {

	@Override
	protected void recordErrorLog(Throwable e, HttpServletRequest request) {
		ErrorLogProducerFactory.getInstance().getProducer().pushException(e, "统一异常处理");
	}

	@Override
	protected void recordErrorLog(Exception e, HttpServletRequest request) {
		ErrorLogProducerFactory.getInstance().getProducer().pushException(e, "统一异常处理");
	}

}
