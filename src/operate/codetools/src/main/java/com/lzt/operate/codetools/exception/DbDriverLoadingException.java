package com.lzt.operate.codetools.exception;

/**
 * 数据库驱动加载异常
 *
 * @author luzhitao
 * @date 2017/8/15 21:46
 */
public class DbDriverLoadingException extends RuntimeException {

    private static final long serialVersionUID = 2648802179469546947L;

    public DbDriverLoadingException(String message) {
        super(message);
    }
}
