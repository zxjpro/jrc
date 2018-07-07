package com.xiaojiezhu.jrc.client.exception;

/**
 * @author xiaojie.zhu
 */
public class CanNotCastDoubleException extends CastException {

    public CanNotCastDoubleException() {
    }

    public CanNotCastDoubleException(String message) {
        super(message);
    }

    public CanNotCastDoubleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotCastDoubleException(Throwable cause) {
        super(cause);
    }

    public CanNotCastDoubleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
