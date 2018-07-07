package com.xiaojiezhu.jrc.web.server.support.exception.ex;

/**
 * @author xiaojie.zhu
 */
public class UnitExistException extends NoticeException {

    public UnitExistException() {
    }

    public UnitExistException(String message) {
        super(message);
    }

    public UnitExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnitExistException(Throwable cause) {
        super(cause);
    }

    public UnitExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
