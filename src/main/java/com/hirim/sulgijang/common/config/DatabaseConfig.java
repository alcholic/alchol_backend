package com.hirim.sulgijang.common.config;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.ArrayList;
import java.util.List;

@MapperScan(basePackages = "com.hirim.sulgijang.repositories")
@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.mapperLocation}")
    private String mapperLocation;


    @Bean
    @Primary
    public BasicDataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        final DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.hirim.sulgijang.models");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperLocation));

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean;
    }
}
