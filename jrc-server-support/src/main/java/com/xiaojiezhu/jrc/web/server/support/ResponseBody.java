package com.xiaojiezhu.jrc.web.server.support;

import java.lang.annotation.*;

/**
 * my custom http response,response data in
 * @author xiaojie.zhu
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseBody {
}
