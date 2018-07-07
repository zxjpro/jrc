package com.xiaojiezhu.jrc.client.exception;

/**
 * @author xiaojie.zhu
 */
public class CanNotCastIntegerException extends CastException {

    public CanNotCastIntegerException() {
    }

    public CanNotCastIntegerException(String message) {
        super(message);
    }

    public CanNotCastIntegerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotCastIntegerException(Throwable cause) {
        super(cause);
    }

    public CanNotCastIntegerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
