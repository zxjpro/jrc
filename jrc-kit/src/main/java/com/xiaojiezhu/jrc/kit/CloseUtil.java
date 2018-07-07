package com.xiaojiezhu.jrc.kit;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author xiaojie.zhu
 */
public class CloseUtil {
    public static void close(Closeable closeable){
        if(closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeable = null;
            }
        }
    }
}
