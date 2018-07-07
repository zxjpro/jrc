package com.xiaojiezhu.jrc.server.interceptor;

import com.alibaba.fastjson.JSON;
import com.xiaojiezhu.jrc.kit.CloseUtil;
import com.xiaojiezhu.jrc.kit.IOUtil;
import com.xiaojiezhu.jrc.kit.SignUtil;
import com.xiaojiezhu.jrc.server.util.RequestKit;
import com.xiaojiezhu.jrc.web.server.support.exception.ex.NoticeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @author xiaojie.zhu
 */
@Component
public class AccessFilter implements HandlerInterceptor {
    public final static Logger LOG = LoggerFactory.getLogger(AccessFilter.class);
    public final static String SIGN = "sign";

    private static String JRC_KEY;
    static {
        InputStream inputStream = null;
        try {
            inputStream = AccessFilter.class.getClassLoader().getResourceAsStream("jrc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            JRC_KEY = (String) properties.get("jrc.key");
        } catch (Exception e) {
            LOG.error("加载jrc.key失败，程序即将退出");
            System.exit(0);
        } finally {
            CloseUtil.close(inputStream);
        }
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        String content = IOUtil.toString(inputStream);
        RequestKit.set(content);

        Map<String,Object> map = null;
        try {
            map = JSON.parseObject(content, TreeMap.class);
        } catch (Exception e) {
            String errMsg = "请求接口错误，post数据不合法:" + request.getRequestURI();
            LOG.error(errMsg);
            throw new NoticeException(errMsg);
        }
        TreeMap<String, Object> treeMap = treeMap(map);
        String mySign = SignUtil.sign(treeMap, JRC_KEY, SIGN);
        request.getParameter(SIGN);
        if(mySign.equals(mySign)){
            return true;
        }else{
            String errMsg = "签名有误：" + request.getRequestURI();
            LOG.warn(errMsg);
            throw new NoticeException(errMsg);
        }

    }

    private static TreeMap<String,Object> treeMap(Map<String,Object> data){
        TreeMap<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(data);
        return treeMap;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
