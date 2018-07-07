package com.xiaojiezhu.jrc.client.exception;

/**
 * flush cache fail
 * @author xiaojie.zhu
 */
public class FlushCacheFailException extends RuntimeException {

    public FlushCacheFailException() {
    }

    public FlushCacheFailException(String message) {
        super(message);
    }

    public FlushCacheFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlushCacheFailException(Throwable cause) {
        super(cause);
    }

    public FlushCacheFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
