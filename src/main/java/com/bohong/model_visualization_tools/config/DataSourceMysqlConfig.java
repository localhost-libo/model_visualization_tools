package com.bohong.model_visualization_tools.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.bohong.model_visualization_tools.mapper.mysql", sqlSessionTemplateRef  = "mysqlSqlSessionTemplate")
public class DataSourceMysqlConfig {

    @Value("${spring.datasource.mysql.jdbc-url}")
    private String url;
    @Value("${spring.datasource.mysql.username}")
    private String username;
    @Value("${spring.datasource.mysql.password}")
    private String password;
    @Value("${spring.datasource.mysql.driver-class-name}")
    private String driverClassName;

    static final String MAPPER_LOCATION = "classpath:mybatis/mysql/*.xml";

    /**创建数据源*/
    @Bean(name = "mysqlDataSource")
    public DataSource getpgDataSource() {
        DataSource build =  DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
        return build;
    }

    /**创建SessionFactory*/
    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //设置mapper配置文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }
    /**创建事务管理器*/
    @Bean("mysqlTransactionManger")
    public DataSourceTransactionManager mysqlTransactionManger(@Qualifier("mysqlDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**创建SqlSessionTemplate*/
    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
