package com.slj.pg.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by yaoyl on 2018/12/23.
 */
@Configuration
@MapperScan(basePackages = "com.slj.pg.dao.mapper")
public class MyBatisConfig {
    private static final Logger LOG = LoggerFactory.getLogger(MyBatisConfig.class);
    /**
     * Mybatis SqlSessionFactory
     *
     * @param dataSource datasource
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        sessionFactory.setPlugins(new Interceptor[]{pageHelper});
        LOG.info("------------mybatis加载完毕");

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sessionFactory.setMapperLocations(resolver.getResources("classpath:mappers/*.xml"));
            return sessionFactory.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
