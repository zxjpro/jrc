package com.xiaojiezhu.jrc.common.exception;

/**
 * @author xiaojie.zhu
 */
public class UnSupportConfigException extends Exception {

    public UnSupportConfigException() {
    }

    public UnSupportConfigException(String message) {
        super(message);
    }

    public UnSupportConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnSupportConfigException(Throwable cause) {
        super(cause);
    }

    public UnSupportConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
