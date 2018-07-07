package com.xiaojiezhu.jrc.kit;

import java.util.Random;

/**
 * @author xiaojie.zhu
 */
public class RandomUtil {

    public static String random(int len){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < len ; i ++){
            sb.append(r.nextInt(9));
        }
        return sb.toString();
    }
}
