package com.xiaojiezhu.jrc.web.server.support.exception;

import com.xiaojiezhu.jrc.kit.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Global exception handler
 *
 * @author xiaojie.zhu
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public final static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler
    public Result<?> handler(Throwable throwable){
        if(ExceptionMapping.isPrintError(throwable)){
            LOG.error(throwable.getMessage(),throwable);
        }else{
            LOG.info(throwable.getMessage());
        }

        int errorCode = ExceptionMapping.getErrorCode(throwable);
        String errMsg = ExceptionMapping.getErrorMsg(throwable);
        Result<?> result = new Result<>(errorCode,errMsg);
        return result;
    }

}
