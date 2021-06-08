package com.bohong.model_visualization_tools.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用WebMvcConfigurer可以来扩展SpringMVC的功能
@Configuration

public class MyMvcConfig implements WebMvcConfigurer {

    //所有的WebMvcConfirurer组件都会一起起作用
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                /**
                 *
                 */
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/error.html").setViewName("login");
                registry.addViewController("/index.do").setViewName("index");
                registry.addViewController("/sampleDivision/trainingSetPartition.do").setViewName("sampleDivision/trainingSetPartition");
                registry.addViewController("/visualAnalysis/toVisualAnalysis.do").setViewName("visualAnalysis/visualAnalysis");
                registry.addViewController("/sampleDivision/trainingAndVerification.do").setViewName("sampleDivision/trainingAndVerification");
                registry.addViewController("/test/toTestUrl.do").setViewName("test/testUrl");
            }
            //配置拦截器
//            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/","/user/login","/static/**","/assets/**","/permission/verificationLogin.do",
                                "/webjars/**","/resources/**","/css/**","/img/**","/vendor/**","/js/**", "/public/**");
            }
        };
        return configurer;
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


}
