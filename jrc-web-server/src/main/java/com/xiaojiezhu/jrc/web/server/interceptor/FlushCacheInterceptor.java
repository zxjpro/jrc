package com.xiaojiezhu.jrc.web.server.interceptor;

import com.xiaojiezhu.jrc.client.util.FlushUtil;
import com.xiaojiezhu.jrc.web.server.annotation.FlushCache;
import com.xiaojiezhu.jrc.web.server.component.FlushCacheUrlStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * this interceptor will be control flush config cache
 * @author xiaojie.zhu
 */
@Component
public class FlushCacheInterceptor implements HandlerInterceptor{
    @Autowired
    private FlushCacheUrlStore flushCacheUrlStore;

    public final static Logger LOG = LoggerFactory.getLogger(FlushCacheInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURL = request.getRequestURI();
        if(handler instanceof HandlerMethod){
            boolean needFlush = isNeedFlushGlobal(requestURL, (HandlerMethod) handler);
            if(needFlush){
                FlushUtil.flushCache();
            }
        }
    }



    private boolean isNeedFlushGlobal(String requestURI,HandlerMethod handlerMethod){
        if(isNeedFlush(requestURI)){
           return true;
        }else {
            return isNeedFlush(requestURI,handlerMethod);
        }
    }



    private boolean isNeedFlush(String requestURI){
        boolean exists = flushCacheUrlStore.exists(requestURI);
        return exists;
    }

    private boolean isNeedFlush(String requestURI,HandlerMethod handlerMethod){
        FlushCache flushCache = handlerMethod.getMethodAnnotation(FlushCache.class);
        if(flushCache != null){
            flushCacheUrlStore.put(requestURI);
            return true;
        }else {
            return false;
        }
    }
}
