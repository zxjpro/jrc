package com.xiaojiezhu.jrc.client.util;

import com.xiaojiezhu.jrc.client.MetaConfig;
import com.xiaojiezhu.jrc.kit.RandomUtil;
import com.xiaojiezhu.jrc.kit.SignUtil;

import java.util.TreeMap;

/**
 * @author xiaojie.zhu
 */
public class HttpParamUtil {

    public static TreeMap<String,Object> getCommonParams(){
        TreeMap<String,Object> params = new TreeMap<>();
        params.put("time",System.currentTimeMillis());
        params.put("random", RandomUtil.random(8));
        return params;
    }

    public static String getSign(TreeMap<String,Object> params){
        String sign = SignUtil.sign(params, MetaConfig.getJrcKey());
        return sign;
    }

}
