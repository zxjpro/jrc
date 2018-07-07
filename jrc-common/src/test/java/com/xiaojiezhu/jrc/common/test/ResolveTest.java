package com.xiaojiezhu.jrc.common.test;

import com.xiaojiezhu.jrc.common.exception.UnSupportConfigException;
import com.xiaojiezhu.jrc.common.resolve.DefaultConfigResolve;
import org.junit.Test;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.InputStreamResource;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * @author xiaojie.zhu
 */
public class ResolveTest {

    @Test
    public void test1() throws UnSupportConfigException {
        DefaultConfigResolve configResolve = new DefaultConfigResolve();

        File file = new File("D:\\tmp\\config");
        File[] files = file.listFiles();
        int i = 0 ;
        for(File f : files){
            String read = read(f);
            configResolve.addConfig(i++,read);
        }
        Map<String, String> resolve = configResolve.resolve();
        System.out.println("load over");

    }






    private String read(File file){
        try {
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
