package com.bohong.model_visualization_tools.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.ConstructorProperties;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid配置
 */
@Configuration
public class DruidConfig {


    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){

        return new DruidDataSource();
    }
    //配置Druid监控
    //1.配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,Object> initParameter = new HashMap<>();
        initParameter.put("loginUsername","supadmin");
        initParameter.put("loginPassword","123456");
        initParameter.put("allow","");//默认就是允许所有访问
        initParameter.put("deny","192.168.11.85");
        bean.setInitParameters(initParameter);

        return bean;
    }

    //2.配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParameters = new HashMap<>();
        initParameters.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
