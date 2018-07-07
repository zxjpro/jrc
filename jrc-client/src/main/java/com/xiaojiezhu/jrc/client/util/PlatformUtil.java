package com.xiaojiezhu.jrc.client.util;

import java.io.File;

/**
 * @author xiaojie.zhu
 */
public class PlatformUtil {

    /**
     * get the system version
     * @return true = linux, false = windows
     */
    public static boolean isLinux(){
        String property = System.getProperty("os.name");
        if(property.contains("win") || property.contains("Win")){
            return false;
        }else {
            return true;
        }
    }

    /**
     * get the path of jrc on disk
     * @return return a dir
     */
    public static String getDiskJrcPath(){
        if(isLinux()){
            return "/opt/jrc/";
        }else{
            return "c:/opt/jrc/";
        }
    }

    /**
     * get the config on disk path
     * @param group
     * @param unit
     * @param version
     * @param profile
     * @return
     */
    public static String getCoordDiskPath(String group,String unit,String version,String profile){
        String path = PlatformUtil.getDiskJrcPath() + group + File.separator + unit + File.separator + version + File.separator + profile + File.separator + "config.properties";
        return path;
    }
}
