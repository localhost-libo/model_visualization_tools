package com.bohong.model_visualization_tools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;
import java.util.Map;

@SpringBootTest
class ModelVisualizationToolsApplicationTests {

    @Test
    void contextLoads() {
        try {
            URL url = Test.class.getClassLoader().getResource("application.yml");
            DumperOptions dumperOptions = new DumperOptions();
            dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
            dumperOptions.setPrettyFlow(false);
            Yaml yaml = new Yaml(dumperOptions);
            Map map =(Map)yaml.load(new FileInputStream(url.getFile()));
            System.out.println("这是修改前："+map.get("pgdatabase"));
            map.put("pgdatabase","XXX");
            yaml.dump(map, new OutputStreamWriter(new FileOutputStream(url.getFile())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getIsOne(){
        try {
            Yaml yaml = new Yaml();
            URL url = Test.class.getClassLoader().getResource("application.yml");
            if (url != null) {
                //获取test.yaml文件中的配置数据，然后转换为obj，
                Object obj =yaml.load(new FileInputStream(url.getFile()));
                System.out.println(obj);
                //也可以将值转换为Map
                Map map =(Map)yaml.load(new FileInputStream(url.getFile()));
                System.out.println(map.get("pgdatabase"));
                //通过map我们取值就可以了.

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
