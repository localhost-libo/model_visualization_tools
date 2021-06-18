package com.bohong.model_visualization_tools.service.databaseConfiguration.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bohong.model_visualization_tools.domain.databaseConfiguration.BaseParam;
import com.bohong.model_visualization_tools.domain.databaseConfiguration.Database;
import com.bohong.model_visualization_tools.mapper.mysql.MysqlTestMapper;
import com.bohong.model_visualization_tools.service.databaseConfiguration.DatabaseConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.jdo.annotations.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@Slf4j
@Service
public class DatabaseConfigurationServiceImpl implements DatabaseConfigurationService {


    @Autowired
    private MysqlTestMapper mysqlTestMapper;
    /**
     * 跳转数据库管理页面
     * @param modelMap
     */
    public void toDatabase(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
        Integer sign = 1;
        modelMap.put("database",getDatabaseData(request,response,sign));
    }

    public Database changeData(HttpServletRequest request, HttpServletResponse response, Integer sign){
       return getDatabaseData(request,response,sign);
    }

    @Transactional
    public void updateDatabaseService(HttpServletRequest request, HttpServletResponse response,Database database){

                Map map = new HashMap();
                map.put("user_id",request.getSession().getAttribute("user").toString());
                map.put("category_id", "config");
                map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL%" : "HIVE%" );
                List<BaseParam> baseParams = mysqlTestMapper.selectBaseParamLike(map);
                if (baseParams.size() == 6){
                    if(database.getType().equals("1")){
                        for (int i = 0; i < baseParams.size(); i++) {
                            switch (baseParams.get(i).getParam_name()) {
                                case "MySQL-HOST":
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-HOST" : "HIVE-HOST");
                                    map.put("param_value",database.getHost());
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                                case "MySQL-PORT":
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-PORT" : "HIVE-PORT");
                                    map.put("param_value",database.getPort());
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                                case "MySQL-USER":
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-USER" : "HIVE-USER");
                                    map.put("param_value",database.getUser());
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                                case "MySQL-PSWD":
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-PSWD" : "HIVE-PSWD");
                                    map.put("param_value",database.getPswd());
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                                case "MySQL-DBNM":
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-DBNM" : "HIVE-DBNM");
                                    map.put("param_value",database.getDbnm());
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                                case "MySQL-INST":
                                    map.put("param_value",database.getInst());
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-INST" : "HIVE-INST");
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                            }
                        }

                    }
                    else {
                        for (int i = 0; i < baseParams.size(); i++) {
                            switch (baseParams.get(i).getParam_name()) {
                                case "HIVE-HOST":
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-HOST" : "HIVE-HOST");
                                    map.put("param_value",database.getHost());
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                                case "HIVE-PORT":
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-PORT" : "HIVE-PORT");
                                    map.put("param_value",database.getPort());
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                                case "HIVE-USER":
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-USER" : "HIVE-USER");
                                    map.put("param_value",database.getUser());
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                                case "HIVE-PSWD":
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-PSWD" : "HIVE-PSWD");
                                    map.put("param_value",database.getPswd());
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                                case "HIVE-DBNM":
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-DBNM" : "HIVE-DBNM");
                                    map.put("param_value",database.getDbnm());
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                                case "HIVE-INST":
                                    map.put("param_value",database.getInst());
                                    map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-INST" : "HIVE-INST");
                                    mysqlTestMapper.updateBaseParamData(map);
                                    break;
                            }
                        }
                    }

                }else {
                    for (int i = 0; i < 6; i++) {
                        switch (i) {
                            case 0:
                                map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-HOST" : "HIVE-HOST");
                                map.put("param_value",database.getHost());
                                mysqlTestMapper.insertBaseParamData(map);
                                break;
                            case 1:
                                map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-PORT" : "HIVE-PORT");
                                map.put("param_value",database.getPort());
                                mysqlTestMapper.insertBaseParamData(map);
                                break;
                            case 2:
                                map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-USER" : "HIVE-USER");
                                map.put("param_value",database.getUser());
                                mysqlTestMapper.insertBaseParamData(map);
                                break;
                            case 3:
                                map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-PSWD" : "HIVE-PSWD");
                                map.put("param_value",database.getPswd());
                                mysqlTestMapper.insertBaseParamData(map);
                                break;
                            case 4:
                                map.put("param_value",database.getDbnm());
                                map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-DBNM" : "HIVE-DBNM");
                                mysqlTestMapper.insertBaseParamData(map);
                                break;
                            case 5:
                                map.put("param_value",database.getInst());
                                map.put("param_name",Integer.valueOf(database.getType()) == 1 ? "MySQL-INST" : "HIVE-INST");
                                mysqlTestMapper.insertBaseParamData(map);
                                break;
                        }
                    }
                }
    }

    /**
     * 获取数据库管理初始化信息
     * @param sign
     * @return
     */
    private Database getDatabaseData(HttpServletRequest request, HttpServletResponse response,Integer sign)  {
        Database database = new Database();
        database.setType(sign.toString());
        Map map = new HashMap();
        map.put("user_id",request.getSession().getAttribute("user").toString());
        map.put("category_id", "config");
        map.put("param_name",sign == 1 ? "MySQL%" : "HIVE%" );
        List<BaseParam> baseParams = mysqlTestMapper.selectBaseParamLike(map);
        for (int i = 0; i < baseParams.size(); i++) {
            if (sign == 1){
                switch (baseParams.get(i).getParam_name()){
                case "MySQL-HOST":
                    database.setHost(baseParams.get(i).getParam_value());
                    break;
                case "MySQL-PORT":
                    database.setPort(baseParams.get(i).getParam_value());
                    break;
                case "MySQL-USER":
                    database.setUser(baseParams.get(i).getParam_value());
                    break;
                case "MySQL-PSWD":
                    database.setPswd(baseParams.get(i).getParam_value());
                    break;
                case "MySQL-DBNM":
                    database.setDbnm(baseParams.get(i).getParam_value());
                    break;
                case "MySQL-INST":
                    database.setInst(baseParams.get(i).getParam_value());
                    break;
            }}else {switch (baseParams.get(i).getParam_name()){
                case "HIVE-HOST":
                    database.setHost(baseParams.get(i).getParam_value());
                    break;
                case "HIVE-PORT":
                    database.setPort(baseParams.get(i).getParam_value());
                    break;
                case "HIVE-USER":
                    database.setUser(baseParams.get(i).getParam_value());
                    break;
                case "HIVE-PSWD":
                    database.setPswd(baseParams.get(i).getParam_value());
                    break;
                case "HIVE-DBNM":
                    database.setDbnm(baseParams.get(i).getParam_value());
                    break;
                case "HIVE-INST":
                    database.setInst(baseParams.get(i).getParam_value());
                    break;
            }}

        }
        return database;
    }


//    /**
//     * 获取文件内容公共方法
//     * @param filePath
//     * @return
//     */
//    public List<String[]> getFile(String filePath) {
//        log.info("txt文件路径:{}", filePath);
//        try {
//            List<String[]> result = new ArrayList<>();
//            InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);
//            BufferedReader br = null;
//            try {
//                br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
//                String lineTxt = null;
//                while ((lineTxt = br.readLine()) != null) {
//                    String[] dataStr = lineTxt.split("\t");
//                    result.add(dataStr);
//                }
//                br.close();
//            } catch (FileNotFoundException e) {
//                log.error("FileNotFoundException:" + e);
//            } catch (IOException e) {
//                log.error("IOException:" + e);
//            } finally {
//                if (br != null) {
//                    try {
//                        br.close();
//                    } catch (IOException e) {
//                        log.error("close br error:" + e);
//                    }
//                }
//            }
//            return result;
//        } catch (Exception e) {
//            System.out.println("文件读取错误!");
//        }
//        return null;
//    }

}
