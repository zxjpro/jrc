package com.xiaojiezhu.jrc.common.cache.counter.exception;

/**
 * @author zxj<br>
 * 时间 2017/11/29 20:28
 * 说明 计数器不能写的异常
 */
public class CounterCanNotWriteException extends RuntimeException {
    public CounterCanNotWriteException() {
    }

    public CounterCanNotWriteException(String message) {
        super(message);
    }

    public CounterCanNotWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CounterCanNotWriteException(Throwable cause) {
        super(cause);
    }

    public CounterCanNotWriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
