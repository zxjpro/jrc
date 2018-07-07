package com.xiaojiezhu.jrc.web.server.test;

import com.xiaojiezhu.jrc.common.exception.UnSupportConfigException;
import com.xiaojiezhu.jrc.common.resolve.*;
import com.xiaojiezhu.jrc.kit.CloseUtil;
import com.xiaojiezhu.jrc.kit.IOUtil;
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
    public void test2() throws FileNotFoundException {
        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(new InputStreamResource(new FileInputStream("D:\\tmp\\config\\a")));

        Properties prop = bean.getObject();
        System.out.println(prop);
    }

    @Test
    public void test3() throws IOException, UnSupportConfigException {
        FileInputStream inputStream = new FileInputStream("D:\\tmp\\config\\a");
        ConfigLoader loader = new SimpleConfigLoader();
        String s = IOUtil.toString(inputStream);
        loader.loader(null,new ConfigWeight(s));
    }

    @Test
    public void test4() throws IOException, UnSupportConfigException {
        DefaultConfigResolve configResolve = new DefaultConfigResolve();
        File file = new File("D:\\tmp\\config");
        File[] files = file.listFiles();
        int i = 0;
        for(File f : files){
            InputStream in = new FileInputStream(f);
            String s = IOUtil.toString(in);
            configResolve.addConfig(i++,s);
            CloseUtil.close(in);
        }

        Map<String, String> resolve = configResolve.resolve();

        System.out.println(resolve);

    }

}
