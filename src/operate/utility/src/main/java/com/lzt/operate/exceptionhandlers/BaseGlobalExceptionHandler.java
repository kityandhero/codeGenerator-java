package com.lzt.operate.exceptionhandlers;

import com.lzt.operate.entities.ResultSingleData;
import com.lzt.operate.entities.SerializableData;
import com.lzt.operate.enums.ReturnDataCode;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.shiro.ShiroException;
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

import javax.validation.ConstraintViolationException;
import java.net.BindException;

/**
 * 类描述: 全局异常拦截处理器
 * 1.处理自定义异常
 * 2.未知异常统一返回服务器错误
 * 3.已经catch到的异常不会被捕获
 * 4.异常的体系结构中，哪个异常与目标方法抛出的异常血缘关系越紧密，就会被哪个捕捉到。
 *
 * @author licanfeng
 * @version 1.0
 * @date 2019/3/11 16:13
 * @see ExceptionHandler：统一处理某一类异常，从而能够减少代码重复率和复杂度
 * @see ControllerAdvice ：异常集中处理，更好的使业务逻辑与异常处理剥离开
 * @see ResponseStatus：可以将某种异常映射为HTTP状态码 成功则Status Code: 200
 */
@Slf4j
public abstract class BaseGlobalExceptionHandler {
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, BindException.class,
            ServletRequestBindingException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResultSingleData handleHttpMessageNotReadableException(Exception e) {
        log.error("参数解析失败", e);
        if (e instanceof BindException) {
            var error = new ResultSingleData(ReturnDataCode.BAD_REQUEST);

            error.setMessage(error.getMessage() + "," + e.getMessage());

            return error;
        } else {
            var error = new ResultSingleData(ReturnDataCode.BAD_REQUEST);

            error.setMessage(error.getMessage() + "," + e.getMessage());

            return error;
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
        return new ResultSingleData(ReturnDataCode.METHOD_NOT_ALLOWED);
    }

    /**
     * 其他全局异常在此捕获
     *
     * @param e 异常
     * @return json结果
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResultSingleData handleException(Throwable e) {
        log.error("服务运行异常", e);
        if (e instanceof ShiroException) {
            return new ResultSingleData(ReturnDataCode.UNAUTHORIZED_ERROR);
        } else if (e instanceof JedisConnectionException) {
            //redis连接异常
            return new ResultSingleData(ReturnDataCode.REDIS_CONNECT_ERROR);
        } else if (e instanceof JedisException) {
            //redis异常
            return new ResultSingleData(ReturnDataCode.REDIS_ERROR);
        }

        var serializableData = new SerializableData();

        serializableData.append("message", e.getMessage());
        serializableData.append("localizedMessage", e.getLocalizedMessage());
        serializableData.append("stackTrace", e.getStackTrace());

        return new ResultSingleData(ReturnDataCode.SYSTEM_ERR, serializableData);
    }
}
