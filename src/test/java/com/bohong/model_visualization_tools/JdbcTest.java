package com.bohong.model_visualization_tools;


import com.alibaba.fastjson.JSONObject;
import com.bohong.model_visualization_tools.domain.databaseConfiguration.Database;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootTest
public class JdbcTest {

    @Test
    void IOtest1(){
        Database pgDatabase = new Database();
        String c = System.getProperty("user.dir");
        String filePath= null;
        if (c.equals("/root")){
            filePath = "/root/testlibo/pg.json";
        }else {
            filePath= c+"/src/main/resources/config/pg.json";
        }
////        String filePath= c+"/src/main/resources/config/pg.txt";
//        String filePath= "/root/testlibo/pg.txt";
        File file=new File(filePath);
        BufferedReader reader = null;
        String tempString = null;
        int line =1;
        try {
            // System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
            while ((tempString = reader.readLine()) != null) {
                Database pgDatabase1 = JSONObject.parseObject(tempString, Database.class);
//                String json= JSON.toJSONString(pgDatabase1);
//                System.out.println(json);
                System.out.println(pgDatabase1);
                line ++ ;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    }
