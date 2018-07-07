package com.xiaojiezhu.jrc.web.server.test;

import com.alibaba.druid.DruidRuntimeException;
import com.xiaojiezhu.jrc.kit.IOUtil;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.UUID;

/**
 * @author xiaojie.zhu
 */
public class MetaInfTest {

    public static void main(String[] args) throws IOException {
        String path = DruidRuntimeException.class.getResource("").getPath();
        System.out.println(path);
        File file = new File(path);
        file = new File(file.getParentFile().getParentFile().getParentFile().toString() + "/META-INF/druid-filter.properties");

        FileInputStream fileInputStream = new FileInputStream(file);

        String s = IOUtil.toString(fileInputStream);
        System.out.println(s);
    }


    @Test
    public void test2() throws IOException {
        System.out.println(UUID.randomUUID());
        InputStream resourceAsStream = com.alibaba.druid.proxy.DruidDriver.class.getResourceAsStream("/META-INF/druid-filter.properties");
        System.out.println(IOUtil.toString(resourceAsStream));

        URL resource = MetaInfTest.class.getClassLoader().getResource("log4j2.xml");
        System.out.println(resource);
    }
}
