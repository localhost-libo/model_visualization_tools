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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
@Configuration
@MapperScan(basePackages = "com.bohong.model_visualization_tools.mapper.hive",sqlSessionTemplateRef ="hiveSqlSessionTemplate")
public class DataSourceHiveConfig {
    @Value("${spring.datasource.hive.jdbc-url}")
    private String url;
    @Value("${spring.datasource.hive.username}")
    private String username;
    @Value("${spring.datasource.hive.password}")
    private String password;
    @Value("${spring.datasource.hive.driver-class-name}")
    private String driverClassName;
    /**本数据源扫描的mapper路径*/
    static final String MAPPER_LOCATION = "classpath:mybatis/hive/*.xml";

    /**创建数据源*/
    @Bean(name = "hiveDataSource")
    public DataSource getHiveDataSource() {
        DataSource build =  DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
        return build;
    }

    /**创建SessionFactory*/
    @Bean(name = "hiveSqlSessionFactory")
    public SqlSessionFactory hiveSqlSessionFactory(@Qualifier("hiveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //设置mapper配置文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }
    /**创建事务管理器*/
    @Bean("secondTransactionManger")
    public DataSourceTransactionManager hiveTransactionManger(@Qualifier("hiveDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    /**创建SqlSessionTemplate*/
    @Bean(name = "hiveSqlSessionTemplate")
    public SqlSessionTemplate hiveSqlSessionTemplate(@Qualifier("hiveSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
