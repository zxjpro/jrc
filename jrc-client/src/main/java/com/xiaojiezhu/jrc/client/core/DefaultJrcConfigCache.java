package com.xiaojiezhu.jrc.client.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiaojiezhu.jrc.client.JrcConfigCache;
import com.xiaojiezhu.jrc.client.MetaConfig;
import com.xiaojiezhu.jrc.client.exception.FlushCacheFailException;
import com.xiaojiezhu.jrc.client.exception.LoadConfigException;
import com.xiaojiezhu.jrc.client.util.HttpParamUtil;
import com.xiaojiezhu.jrc.kit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.TreeMap;

/**
 * @author xiaojie.zhu
 */
public class DefaultJrcConfigCache implements JrcConfigCache {
    public final static Logger LOG = LoggerFactory.getLogger(DefaultJrcConfigCache.class);

    @Override
    public void flushCache() throws FlushCacheFailException {
        LOG.debug("flush config cache");
        this.request();
        LOG.debug("flush config cache over");

    }

    protected void request()throws FlushCacheFailException{
        HttpRequest.CloseableResponse closeableResponse = null;
        String content = null;
        try {
            closeableResponse = HttpRequest.Builder.newBuilder().url(MetaConfig.getServerUrl() + "/config/flushCache").post().json().stream(getRequestParams().getBytes(Charsets.UTF8)).build().requestConnection();
            InputStream inputStream = closeableResponse.getInputStream();
            content = IOUtil.toString(inputStream);
        } catch (Exception e) {
            LOG.error("flush config cache error , " + e.getMessage());
            throw new LoadConfigException(e);
        } finally {
            CloseUtil.close(closeableResponse);
        }
        LOG.debug("jrc-server response:" + content);
        Result<?> result = JSON.parseObject(content, new TypeReference<Result<?>>() {
        });
        if(0 != result.getCode()){
            LOG.error("flush config cache fail , " + result.getMsg());
            throw new FlushCacheFailException(result.getMsg());
        }
    }

    private String getRequestParams(){
        TreeMap<String, Object> params = HttpParamUtil.getCommonParams();
        String sign = HttpParamUtil.getSign(params);
        params.put("sign",sign);
        return JSON.toJSONString(params);
    }
}
