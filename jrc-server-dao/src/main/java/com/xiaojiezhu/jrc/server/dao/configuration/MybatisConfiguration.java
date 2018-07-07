package com.xiaojiezhu.jrc.server.dao.configuration;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;


/**
 * @author xiaojie.zhu
 */
@Configuration
@MapperScan(basePackages = {"com.xiaojiezhu.jrc.server.dao.mysql"},sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfiguration {

    @Value("${jdbc.url}")
    private String url;


    @Autowired
    private DataSource dataSource;


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return getSqlSessionFactory(dataSource);
    }



    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;
    }

    protected SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //适配驼峰
        configuration.setMapUnderscoreToCamelCase(true);
        //日志实现
        configuration.setLogImpl(Slf4jImpl.class);
        factory.setConfiguration(configuration);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resourceArray = resolver.getResources("classpath*:mapper/*.xml");
        factory.setMapperLocations(resourceArray);

        return factory.getObject();
    }
}
