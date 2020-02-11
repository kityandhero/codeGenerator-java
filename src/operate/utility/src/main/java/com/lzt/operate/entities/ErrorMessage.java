package com.lzt.operate.entities;

import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * 错误集合
 *
 * @author luzhitao
 */
@Data
public class ErrorMessage {

    /**
     * 参数错误
     */
    public static ErrorMessage ParamError = new ErrorMessage(40001, "参数错误");

    /**
     * 参数错误
     */
    public static ErrorMessage exceptionError = new ErrorMessage(40002, "执行异常");

    /**
     * 参数错误
     */
    public static ErrorMessage noDataError = new ErrorMessage(40003, "无数据");

    /**
     * 代码
     */
    private int code;
    /**
     * 描述
     */
    private String message;

    private ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorMessage createFromExisting(@NonNull ErrorMessage errorMessage, String otherMessage) {

        return new ErrorMessage(errorMessage.code, otherMessage + errorMessage.message);
    }
}
