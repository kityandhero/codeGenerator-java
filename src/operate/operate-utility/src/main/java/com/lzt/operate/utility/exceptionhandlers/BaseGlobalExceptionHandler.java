package com.lzt.operate.utility.exceptionhandlers;

import com.lzt.operate.utility.assists.RequestAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.exceptions.AuthenticationException;
import com.lzt.operate.utility.exceptions.AuthorizationException;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.ReturnMessage;
import com.lzt.operate.utility.pojo.SerializableData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.util.Optional;

/**
 * 类描述: 全局异常拦截处理器
 * 1.处理自定义异常
 * 2.未知异常统一返回服务器错误
 * 3.已经catch到的异常不会被捕获
 * 4.异常的体系结构中，哪个异常与目标方法抛出的异常血缘关系越紧密，就会被哪个捕捉到。
 *
 * @author luzhitao
 * @version 1.0
 * @date 2020/02/11 16:13
 * @see ExceptionHandler：统一处理某一类异常，从而能够减少代码重复率和复杂度
 * @see ControllerAdvice ：异常集中处理，更好的使业务逻辑与异常处理剥离开
 * @see ResponseStatus：可以将某种异常映射为HTTP状态码 成功则Status Code: 200
 */
@Slf4j
public abstract class BaseGlobalExceptionHandler {

	/**
	 * 记录错误
	 *
	 * @param e 异常
	 */
	protected void recordErrorLog(Exception e) {
		this.recordErrorLog(e, null);
	}

	/**
	 * 记录错误
	 *
	 * @param e       异常
	 * @param request request
	 */
	protected abstract void recordErrorLog(Exception e, HttpServletRequest request);

	/**
	 * 记录错误
	 *
	 * @param e 异常
	 */
	protected void recordErrorLog(Throwable e) {
		this.recordErrorLog(e, null);
	}

	/**
	 * 记录错误
	 *
	 * @param e       异常
	 * @param request request
	 */
	protected abstract void recordErrorLog(Throwable e, HttpServletRequest request);

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, BindException.class,
			ServletRequestBindingException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
	public ResultSingleData handleHttpMessageNotReadableException(Exception e) {
		log.error("参数解析失败", e);

		Optional<HttpServletRequest> optional = RequestAssist.getCurrentHttpServletRequest();

		if (optional.isPresent()) {
			this.recordErrorLog(e, optional.get());
		} else {
			this.recordErrorLog(e);
		}

		if (e instanceof BindException) {
			ResultSingleData error = new ResultSingleData(ReturnDataCode.BAD_REQUEST.toMessage());

			error.setMessage(error.getMessage() + "," + e.getMessage());

			return error.transferToJsonResultData();
		} else {
			ResultSingleData error = new ResultSingleData(ReturnDataCode.BAD_REQUEST.toMessage());

			error.setMessage(error.getMessage() + "," + e.getMessage());

			return error.transferToJsonResultData();
		}

	}

	/**
	 * 405 - Method Not Allowed
	 * 带有@ResponseStatus注解的异常类会被ResponseStatusExceptionResolver 解析
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResultSingleData handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("不支持当前请求方法", e);

		Optional<HttpServletRequest> optional = RequestAssist.getCurrentHttpServletRequest();

		if (optional.isPresent()) {
			this.recordErrorLog(e, optional.get());
		} else {
			this.recordErrorLog(e);
		}

		return new ResultSingleData(ReturnDataCode.METHOD_NOT_ALLOWED.toMessage()).transferToJsonResultData();
	}

	/**
	 * 其他全局异常在此捕获
	 *
	 * @param e 异常
	 * @return json结果
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(Throwable.class)
	public ResultSingleData handleException(Throwable e) {
		Optional<HttpServletRequest> optional = RequestAssist.getCurrentHttpServletRequest();

		if (optional.isPresent()) {
			this.recordErrorLog(e, optional.get());
		} else {
			this.recordErrorLog(e);
		}

		if (e instanceof AuthenticationException) {

			ReturnMessage ex = ReturnDataCode.Authentication_FAIL.toMessage();

			String message = e.getMessage();

			if (!StringAssist.isNullOrEmpty(message)) {
				ex.toMessage(message);
			}

			return new ResultSingleData(ex).transferToJsonResultData();
		} else if (e instanceof AuthorizationException) {
			ReturnDataCode ex = ReturnDataCode.UNAUTHORIZED_ERROR;

			String message = e.getMessage();

			if (!StringAssist.isNullOrEmpty(message)) {
				ex.toMessage(message);
			}

			return new ResultSingleData(ex.toMessage()).transferToJsonResultData();
		} else if (e instanceof JedisConnectionException) {
			log.error("服务运行异常", e);

			//redis连接异常
			return new ResultSingleData(ReturnDataCode.REDIS_CONNECT_ERROR.toMessage()).transferToJsonResultData();
		} else if (e instanceof JedisException) {
			log.error("服务运行异常", e);

			//redis异常
			return new ResultSingleData(ReturnDataCode.REDIS_ERROR.toMessage()).transferToJsonResultData();
		}

		log.error("服务运行异常", e);

		SerializableData serializableData = new SerializableData();

		serializableData.append("message", e.getMessage());
		serializableData.append("localizedMessage", e.getLocalizedMessage());
		serializableData.append("stackTrace", e.getStackTrace());

		return new ResultSingleData(ReturnDataCode.SYSTEM_ERR.appendMessage(e.getMessage()), serializableData).transferToJsonResultData();
	}
}
