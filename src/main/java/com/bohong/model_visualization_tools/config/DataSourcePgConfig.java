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
@MapperScan(basePackages = "com.bohong.model_visualization_tools.mapper.pg", sqlSessionTemplateRef  = "pgSqlSessionTemplate")
public class DataSourcePgConfig {
    @Value("${spring.datasource.pg.jdbc-url}")
    private String url;
    @Value("${spring.datasource.pg.username}")
    private String username;
    @Value("${spring.datasource.pg.password}")
    private String password;
    @Value("${spring.datasource.pg.driver-class-name}")
    private String driverClassName;

    static final String MAPPER_LOCATION = "classpath:mybatis/pg/*.xml";

    /**创建数据源*/
    @Bean(name = "pgDataSource")
    @Primary
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
    @Bean(name = "pgSqlSessionFactory")
    @Primary
    public SqlSessionFactory pgSqlSessionFactory(@Qualifier("pgDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean  bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //设置mapper配置文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }
    /**创建事务管理器*/
    @Bean("pgTransactionManger")
    @Primary
    public DataSourceTransactionManager pgTransactionManger(@Qualifier("pgDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**创建SqlSessionTemplate*/
    @Bean(name = "pgSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate pgSqlSessionTemplate(@Qualifier("pgSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
