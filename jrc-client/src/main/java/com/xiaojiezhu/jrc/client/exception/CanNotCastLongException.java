package com.xiaojiezhu.jrc.client.exception;

/**
 * @author xiaojie.zhu
 */
public class CanNotCastLongException extends CastException {

    public CanNotCastLongException() {
    }

    public CanNotCastLongException(String message) {
        super(message);
    }

    public CanNotCastLongException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotCastLongException(Throwable cause) {
        super(cause);
    }

    public CanNotCastLongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
