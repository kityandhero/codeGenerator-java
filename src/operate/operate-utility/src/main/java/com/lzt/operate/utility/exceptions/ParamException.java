package com.lzt.operate.utility.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 参数异常类
 * CreteTime:2019-04-29 22:06
 * UpdateTIme:2019-04-29 22:06
 *
 * @author luzhitao
 */
public class ParamException extends Exception {

    @Getter
    @Setter
    public String param;

    @Getter
    @Setter
    public List<String> errors;

    @Getter
    @Setter
    public boolean showError;

    private ParamException(String param, String... errors) {
        this.param = param;
        this.errors = Arrays.asList(errors);
    }

    private ParamException(String param, boolean showError, String... errors) {
        this(param, errors);
        this.showError = showError;
    }

    public ParamException(String param, String error, boolean showError) {
        this(param, new ArrayList<String>().add(error));
        this.showError = showError;
    }
}
