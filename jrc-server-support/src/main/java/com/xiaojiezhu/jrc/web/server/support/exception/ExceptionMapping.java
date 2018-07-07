package com.xiaojiezhu.jrc.web.server.support.exception;

import com.xiaojiezhu.jrc.web.server.support.exception.ex.NoticeException;

import java.sql.SQLException;

/**
 * @author xiaojie.zhu
 */
public class ExceptionMapping {

    public static boolean isPrintError(Throwable t){
        if(t instanceof NoticeException){
            return false;
        }else {
            return true;
        }
    }

    public static int getErrorCode(Throwable t){
        return 500;
    }

    /**
     * filter sql exception
     * @param throwable
     * @return
     */
    public static String getErrorMsg(Throwable throwable) {
        if(throwable instanceof SQLException){
            return "jdbc sql error";
        }else{
            return throwable.getMessage();
        }
    }
}
