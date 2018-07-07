package com.xiaojiezhu.jrc.web.server.annotation;

import java.lang.annotation.*;

/**
 * this annotation in the method with @RequestMapping,
 * when call this http request,it will to be flush cache on http end
 *
 * such as :  add config ,delete config ,update config, add dependency , remote dependency
 * @author xiaojie.zhu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface FlushCache {
}
