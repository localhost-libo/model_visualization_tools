package com.bohong.model_visualization_tools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

//@MapperScan({"com.bohong.model_visualization_tools.mapper"})
@SpringBootApplication
public class ModelVisualizationToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelVisualizationToolsApplication.class, args);
    }

}
