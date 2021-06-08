package com.bohong.model_visualization_tools.service.resourceAllocation.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.sql.visitor.functions.Char;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bohong.model_visualization_tools.domain.databaseConfiguration.Database;
import com.bohong.model_visualization_tools.domain.resourceAllocation.SparkParameter;
import com.bohong.model_visualization_tools.mapper.hive.TestHiveMapper;
import com.bohong.model_visualization_tools.service.resourceAllocation.ResourceAllocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;

import javax.swing.*;
import java.io.*;
import java.net.URLDecoder;
import java.sql.*;
import java.util.*;

@Slf4j
@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    @Autowired
    private TestHiveMapper testHiveMapper;

    @Value("${file.path}")
    private String path;
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";


    public void toSparkParameter(ModelMap modelMap){
        modelMap.put("sparkParameter",getSparkParameterData());
    }


    public List<String[]> readTxt(String filePath) {
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
    public void toSparkParameters(ModelMap modelMap) throws Exception {
//        ResourceAllocationServiceImpl resourceAllocationService = new ResourceAllocationServiceImpl();
//        List<String[]> S =  resourceAllocationService.readTxt("config/pg.json");
//        String a = null;
//        for (int i = 1; i<= S.size(); i++){
//            a = Arrays.toString(S.get(i-1));
//        }
//        System.out.println(a);
//        char prefix = '[';
//        char suffix = ']';
//        a =  StrUtil.unWrap(a,prefix,suffix);
//        Database database = JSONObject.parseObject(a, Database.class);
//        System.out.println(database.toString());
//        System.out.println(ResourceAllocationServiceImpl.class.getClassLoader());
//        File file = new File(ResourceAllocationServiceImpl.class.getClassLoader().getResource("config/pg.json").getFile());
//        System.out.println(file);
        try {
            Class.forName(driverName);
        }catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        Connection con = DriverManager.getConnection("jdbc:hive2://10.1.1.104:10000/test", "hadoop", "hadoop");//后两个参数是用户名密码
        if(con==null)
            System.out.println("连接失败");
        else {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM test limit 1";
            System.out.println("Running: " + sql);
            ResultSet res = stmt.executeQuery(sql);
            int a=0;
            while (res.next()) {
                System.out.println(res.getString(1));
                modelMap.put("s",res.getString(1));
            }
        }
    }
    private SparkParameter getSparkParameterData()  {
        String c = System.getProperty("user.dir");
        String filePath= null;
        if (c.equals("/home/modelVisual")){
            filePath = "/home/modelVisual/config/spark.json";
        }else {
            filePath= c+"/src/main/resources/config/spark.json";
        }
        SparkParameter sparkParameter = null;
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
                sparkParameter = JSONObject.parseObject(tempString, SparkParameter.class);
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
        return sparkParameter;
    }


    public Map updateSparkParameter(SparkParameter sparkParameter){
        Map resultMap = new HashMap();
        sparkParameter.setType("spark");
        resultMap.put("sign",true);
        resultMap.put("info","修改成功");
        String c = System.getProperty("user.dir");
        System.out.println(c);
        String filePath= null;
        if (c.equals("/home/modelVisual")){
            filePath = "/home/modelVisual/config/"+sparkParameter.getType()+".json";
        }else {
            filePath= c+"/src/main/resources/config/"+sparkParameter.getType()+".json";
        }
        File file=new File(filePath);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            String json= JSON.toJSONString(sparkParameter);
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

}
