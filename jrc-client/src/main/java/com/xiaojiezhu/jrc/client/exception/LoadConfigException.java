package com.xiaojiezhu.jrc.client.exception;

/**
 * @author xiaojie.zhu
 */
public class LoadConfigException extends RuntimeException {
    public LoadConfigException() {
    }

    public LoadConfigException(String message) {
        super(message);
    }

    public LoadConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadConfigException(Throwable cause) {
        super(cause);
    }

    public LoadConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
