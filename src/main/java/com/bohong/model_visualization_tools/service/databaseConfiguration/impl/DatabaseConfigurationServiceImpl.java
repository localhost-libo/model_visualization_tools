package com.bohong.model_visualization_tools.service.databaseConfiguration.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bohong.model_visualization_tools.domain.databaseConfiguration.Database;
import com.bohong.model_visualization_tools.service.databaseConfiguration.DatabaseConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class DatabaseConfigurationServiceImpl implements DatabaseConfigurationService {

    /**
     * 跳转数据库管理页面
     * @param modelMap
     */
    public void toDatabase(ModelMap modelMap){
        Integer sign = 1;
        modelMap.put("database",getDatabaseData(sign));
    }

    public Database changeData(Integer sign){
       return getDatabaseData(sign);
    }

    public Map updateDatabaseService(Database database){
        return upadtaPgFile(database);
    }

    /**
     * 获取数据库管理初始化信息
     * @param sign
     * @return
     */
    private Database getDatabaseData(Integer sign)  {
        Database databases = new Database();
        if (sign == 1){
            databases.setType("pg");
        }else {
            databases.setType("hive");
        }
        String c = System.getProperty("user.dir");
        String filePath= null;
        if (c.equals("/home/modelVisual")){
            filePath = "/home/modelVisual/config/"+databases.getType()+".json";
        }else {
            filePath= c+"/src/main/resources/config/"+databases.getType()+".json";
        }
        Database database = null;
        File file=new File(filePath);
        BufferedReader reader = null;
        String tempString = null;
        int line =1;
        try {
            // System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
            while ((tempString = reader.readLine()) != null) {
                database = JSONObject.parseObject(tempString, Database.class);
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
        return database;
    }

    private  Map upadtaPgFile(Database database){
       Map resultMap = new HashMap();
       resultMap.put("sign",true);
       resultMap.put("info","修改成功");
       String c = System.getProperty("user.dir");
        System.out.println(c);
       String filePath= null;
        if (c.equals("/home/modelVisual")){
            filePath = "/home/modelVisual/config/"+database.getType()+".json";
        }else {
           filePath= c+"/src/main/resources/config/"+database.getType()+".json";
       }
        File file=new File(filePath);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            String json= JSON.toJSONString(database);
            fw.write(json);
            System.out.println("修改数据成功");
        } catch (IOException e) {
            resultMap.put("sign",false);
            resultMap.put("info","修改失败");
            e.printStackTrace();
        }finally {
            if (fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }


    /**
     * 获取文件内容公共方法
     * @param filePath
     * @return
     */
    public List<String[]> getFile(String filePath) {
        log.info("txt文件路径:{}", filePath);
        try {
            List<String[]> result = new ArrayList<>();
            InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    String[] dataStr = lineTxt.split("\t");
                    result.add(dataStr);
                }
                br.close();
            } catch (FileNotFoundException e) {
                log.error("FileNotFoundException:" + e);
            } catch (IOException e) {
                log.error("IOException:" + e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        log.error("close br error:" + e);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
        return null;
    }

}
