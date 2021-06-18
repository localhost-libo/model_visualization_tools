package com.bohong.model_visualization_tools.service.resourceAllocation.impl;

import com.alibaba.fastjson.JSONObject;
import com.bohong.model_visualization_tools.domain.databaseConfiguration.BaseParam;
import com.bohong.model_visualization_tools.domain.resourceAllocation.HiveResourceAllocation;
import com.bohong.model_visualization_tools.domain.resourceAllocation.PathConfiguration;
import com.bohong.model_visualization_tools.domain.resourceAllocation.PythonResourceAllocation;
import com.bohong.model_visualization_tools.domain.resourceAllocation.SparkParameter;
import com.bohong.model_visualization_tools.mapper.mysql.MysqlTestMapper;
import com.bohong.model_visualization_tools.service.resourceAllocation.ResourceAllocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.jdo.annotations.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Slf4j
@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    @Autowired
    private MysqlTestMapper mysqlTestMapper;


    /**
     * Hive参数配置 获取数据
     * @param request
     * @param response
     * @return
     */
    public Map queryHiveResourceAllocationData(HttpServletRequest request, HttpServletResponse response)  {
        Map resultMap = new HashMap();
        Map map = new HashMap();
        map.put("user_id",request.getSession().getAttribute("user").toString());
        map.put("category_id","hive");
        List<BaseParam> baseParams = mysqlTestMapper.selectBaseParamLike(map);
        HiveResourceAllocation hiveResourceAllocation = new HiveResourceAllocation();
        for (int i = 0; i < baseParams.size(); i++) {
            switch (baseParams.get(i).getParam_name()){
                case "mapreduce_map_memory_mb":
                    hiveResourceAllocation.setMapreduce_map_memory_mb(baseParams.get(i).getParam_value());
                    break;
                case "mapreduce_reduce_memory_mb":
                    hiveResourceAllocation.setMapreduce_reduce_memory_mb(baseParams.get(i).getParam_value());
                    break;
                case "hive_map_aggr":
                    hiveResourceAllocation.setHive_map_aggr(baseParams.get(i).getParam_value());
                    break;
                case "mapreduce_job_priority":
                    hiveResourceAllocation.setMapreduce_job_priority(baseParams.get(i).getParam_value());
                    break;
                case "hive_exec_compress_output":
                    hiveResourceAllocation.setHive_exec_compress_output(baseParams.get(i).getParam_value());
                    break;
                case "mapred_output_compression_codec":
                    hiveResourceAllocation.setMapred_output_compression_codec(baseParams.get(i).getParam_value());
                    break;
                case "mapred_output_compression_type":
                    hiveResourceAllocation.setMapred_output_compression_type(baseParams.get(i).getParam_value());
                    break;
                case "hive_exec_reducers_bytes_per_reducer":
                    hiveResourceAllocation.setHive_exec_reducers_bytes_per_reducer(baseParams.get(i).getParam_value());
                    break;
                case "mapred_max_split_size":
                    hiveResourceAllocation.setMapred_max_split_size(baseParams.get(i).getParam_value());
                    break;
                case "mapred_min_split_size_per_node":
                    hiveResourceAllocation.setMapred_min_split_size_per_node(baseParams.get(i).getParam_value());
                    break;
                case "mapred_min_split_size_per_rack":
                    hiveResourceAllocation.setMapred_min_split_size_per_rack(baseParams.get(i).getParam_value());
                    break;
                case "hive_hadoop_supports_splittable_combineinputformat":
                    hiveResourceAllocation.setHive_hadoop_supports_splittable_combineinputformat(baseParams.get(i).getParam_value());
                    break;
                case "hive_input_format":
                    hiveResourceAllocation.setHive_input_format(baseParams.get(i).getParam_value());
                    break;
                case "hive_merge_mapfiles":
                    hiveResourceAllocation.setHive_merge_mapfiles(baseParams.get(i).getParam_value());
                    break;
                case "hive_merge_mapredfiles":
                    hiveResourceAllocation.setHive_merge_mapredfiles(baseParams.get(i).getParam_value());
                    break;
                case "hive_merge_size_per_task":
                    hiveResourceAllocation.setHive_merge_size_per_task(baseParams.get(i).getParam_value());
                    break;
                case "hive_merge_smallfiles_avgsize":
                    hiveResourceAllocation.setHive_merge_smallfiles_avgsize(baseParams.get(i).getParam_value());
                    break;
            }
        }
        resultMap.put("hiveResourceAllocation",hiveResourceAllocation);
        return resultMap;
    }


    /**
     * 修改hive参数配置数据
     * @param request
     * @param response
     * @param hiveResourceAllocation
     */
    @Transactional
    public void updateHiveResourceAllocationData(HttpServletRequest request, HttpServletResponse response, HiveResourceAllocation hiveResourceAllocation){
        Map map = new HashMap();
        map.put("user_id",request.getSession().getAttribute("user").toString());
        map.put("category_id","hive");
        List<BaseParam> baseParams = mysqlTestMapper.selectBaseParamLike(map);
        if (baseParams.size() != 17){
            for (int i = 0; i < 17; i++) {
                switch (i){
                    case 0:
                        map.put("param_name","mapreduce_map_memory_mb");
                        map.put("param_value",hiveResourceAllocation.getMapreduce_map_memory_mb());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 1:
                        map.put("param_name","mapreduce_reduce_memory_mb");
                        map.put("param_value",hiveResourceAllocation.getMapreduce_reduce_memory_mb());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 2:
                        map.put("param_name","hive_map_aggr");
                        map.put("param_value",hiveResourceAllocation.getHive_map_aggr());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 3:
                        map.put("param_name","mapreduce_job_priority");
                        map.put("param_value",hiveResourceAllocation.getMapreduce_job_priority());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 4:
                        map.put("param_name","hive_exec_compress_output");
                        map.put("param_value",hiveResourceAllocation.getHive_exec_compress_output());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 5:
                        map.put("param_name","mapred_output_compression_codec");
                        map.put("param_value",hiveResourceAllocation.getMapred_output_compression_codec());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 6:
                        map.put("param_name","mapred_output_compression_type");
                        map.put("param_value",hiveResourceAllocation.getMapred_output_compression_type());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 7:
                        map.put("param_name","hive_exec_reducers_bytes_per_reducer");
                        map.put("param_value",hiveResourceAllocation.getHive_exec_reducers_bytes_per_reducer());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 8:
                        map.put("param_name","mapred_max_split_size");
                        map.put("param_value",hiveResourceAllocation.getMapred_max_split_size());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 9:
                        map.put("param_name","mapred_min_split_size_per_node");
                        map.put("param_value",hiveResourceAllocation.getMapred_min_split_size_per_node());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 10:
                        map.put("param_name","mapred_min_split_size_per_rack");
                        map.put("param_value",hiveResourceAllocation.getMapred_min_split_size_per_rack());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 11:
                        map.put("param_name","hive_hadoop_supports_splittable_combineinputformat");
                        map.put("param_value",hiveResourceAllocation.getHive_hadoop_supports_splittable_combineinputformat());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 12:
                        map.put("param_name","hive_input_format");
                        map.put("param_value",hiveResourceAllocation.getHive_input_format());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 13:
                        map.put("param_name","hive_merge_mapfiles");
                        map.put("param_value",hiveResourceAllocation.getHive_merge_mapfiles());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 14:
                        map.put("param_name","hive_merge_mapredfiles");
                        map.put("param_value",hiveResourceAllocation.getHive_merge_mapredfiles());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 15:
                        map.put("param_name","hive_merge_size_per_task");
                        map.put("param_value",hiveResourceAllocation.getHive_merge_size_per_task());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 16:
                        map.put("param_name","hive_merge_smallfiles_avgsize");
                        map.put("param_value",hiveResourceAllocation.getHive_merge_smallfiles_avgsize());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                }
            }
        }else {
            for (int i = 0; i < baseParams.size(); i++) {
                switch (i){
                    case 0:
                        map.put("param_name","mapreduce_map_memory_mb");
                        map.put("param_value",hiveResourceAllocation.getMapreduce_map_memory_mb());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 1:
                        map.put("param_name","mapreduce_reduce_memory_mb");
                        map.put("param_value",hiveResourceAllocation.getMapreduce_reduce_memory_mb());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 2:
                        map.put("param_name","hive_map_aggr");
                        map.put("param_value",hiveResourceAllocation.getHive_map_aggr());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 3:
                        map.put("param_name","mapreduce_job_priority");
                        map.put("param_value",hiveResourceAllocation.getMapreduce_job_priority());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 4:
                        map.put("param_name","hive_exec_compress_output");
                        map.put("param_value",hiveResourceAllocation.getHive_exec_compress_output());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 5:
                        map.put("param_name","mapred_output_compression_codec");
                        map.put("param_value",hiveResourceAllocation.getMapred_output_compression_codec());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 6:
                        map.put("param_name","mapred_output_compression_type");
                        map.put("param_value",hiveResourceAllocation.getMapred_output_compression_type());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 7:
                        map.put("param_name","hive_exec_reducers_bytes_per_reducer");
                        map.put("param_value",hiveResourceAllocation.getHive_exec_reducers_bytes_per_reducer());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 8:
                        map.put("param_name","mapred_max_split_size");
                        map.put("param_value",hiveResourceAllocation.getMapred_max_split_size());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 9:
                        map.put("param_name","mapred_min_split_size_per_node");
                        map.put("param_value",hiveResourceAllocation.getMapred_min_split_size_per_node());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 10:
                        map.put("param_name","mapred_min_split_size_per_rack");
                        map.put("param_value",hiveResourceAllocation.getMapred_min_split_size_per_rack());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 11:
                        map.put("param_name","hive_hadoop_supports_splittable_combineinputformat");
                        map.put("param_value",hiveResourceAllocation.getHive_hadoop_supports_splittable_combineinputformat());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 12:
                        map.put("param_name","hive_input_format");
                        map.put("param_value",hiveResourceAllocation.getHive_input_format());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 13:
                        map.put("param_name","hive_merge_mapfiles");
                        map.put("param_value",hiveResourceAllocation.getHive_merge_mapfiles());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 14:
                        map.put("param_name","hive_merge_mapredfiles");
                        map.put("param_value",hiveResourceAllocation.getHive_merge_mapredfiles());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 15:
                        map.put("param_name","hive_merge_size_per_task");
                        map.put("param_value",hiveResourceAllocation.getHive_merge_size_per_task());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case 16:
                        map.put("param_name","hive_merge_smallfiles_avgsize");
                        map.put("param_value",hiveResourceAllocation.getHive_merge_smallfiles_avgsize());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                }
            }
        }
    }

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


    public Map querySparkResourceAllocationData(HttpServletRequest request, HttpServletResponse response){
        Map resultMap = new HashMap();
        Map map = new HashMap();
        map.put("user_id",request.getSession().getAttribute("user").toString());
        map.put("category_id","spark");
        List<BaseParam> baseParams = mysqlTestMapper.selectBaseParamLike(map);
        SparkParameter sparkResourceAllocation = new SparkParameter();
        for (int i = 0; i < baseParams.size(); i++) {
            switch (baseParams.get(i).getParam_name()){
                case "num_executors":
                    sparkResourceAllocation.setNum_executors(baseParams.get(i).getParam_value());
                    break;
                case "executor_memory":
                    sparkResourceAllocation.setExecutor_memory(baseParams.get(i).getParam_value());
                    break;
                case "executor_cores":
                    sparkResourceAllocation.setExecutor_cores(baseParams.get(i).getParam_value());
                    break;
            }
        }
        resultMap.put("sparkResourceAllocation",sparkResourceAllocation);
        return resultMap;

    }

    /**
     * 修改spark 参数配置
     * @param request
     * @param response
     * @param sparkParameter
     */
    @Transactional
    public void updateSparkResourceAllocationData(HttpServletRequest request, HttpServletResponse response, SparkParameter sparkParameter ){

        Map map = new HashMap();
        map.put("user_id",request.getSession().getAttribute("user").toString());
        map.put("category_id", "spark");
        List<BaseParam> baseParams = mysqlTestMapper.selectBaseParamLike(map);
        if (baseParams.size() == 3){
            for (int i = 0; i < baseParams.size(); i++) {
                switch (baseParams.get(i).getParam_name()) {
                    case "num_executors":
                        map.put("param_name","num_executors");
                        map.put("param_value",sparkParameter.getNum_executors());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case "executor_memory":
                        map.put("param_name","executor_memory");
                        map.put("param_value",sparkParameter.getExecutor_memory());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case "executor_cores":
                        map.put("param_name","executor_cores");
                        map.put("param_value",sparkParameter.getExecutor_cores());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                }
            }
        }else {
            for (int i = 0; i < 3; i++) {
                switch (i) {
                    case 0:
                        map.put("param_name","num_executors");
                        map.put("param_value",sparkParameter.getNum_executors());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 1:
                        map.put("param_name","executor_memory");
                        map.put("param_value",sparkParameter.getExecutor_memory());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 2:
                        map.put("param_name","executor_cores");
                        map.put("param_value",sparkParameter.getExecutor_cores());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                }
            }
        }
    }
 public Map queryPythonResourceAllocationData(HttpServletRequest request, HttpServletResponse response){
     Map resultMap = new HashMap();
     Map map = new HashMap();
     map.put("user_id",request.getSession().getAttribute("user").toString());
     map.put("category_id","python");
     List<BaseParam> baseParams = mysqlTestMapper.selectBaseParamLike(map);
     PythonResourceAllocation pythonResourceAllocation = new PythonResourceAllocation();
     for (int i = 0; i < baseParams.size(); i++) {
         switch (baseParams.get(i).getParam_name()){
             case "pyspark_python":
                 pythonResourceAllocation.setPyspark_python(baseParams.get(i).getParam_value());
                 break;
             case "pyspark_driver_python":
                 pythonResourceAllocation.setPyspark_driver_python(baseParams.get(i).getParam_value());
                 break;
         }
     }
     resultMap.put("pythonResourceAllocation",pythonResourceAllocation);
     return resultMap;

 }

    public   Map querypathConfigurationData(HttpServletRequest request, HttpServletResponse response){
        Map resultMap = new HashMap();
        Map map = new HashMap();
        map.put("user_id",request.getSession().getAttribute("user").toString());
        map.put("category_id","path");
        List<BaseParam> baseParams = mysqlTestMapper.selectBaseParamLike(map);
        PathConfiguration pathResourceAllocation = new PathConfiguration();
        for (int i = 0; i < baseParams.size(); i++) {
            switch (baseParams.get(i).getParam_name()){
                case "MODEL_PATH":
                    pathResourceAllocation.setModel_path(baseParams.get(i).getParam_value());
                    break;
                case "HDFS_PATH":
                    pathResourceAllocation.setHdfs_path(baseParams.get(i).getParam_value());
                    break;
                case "BASE_PATH":
                    pathResourceAllocation.setBase_path(baseParams.get(i).getParam_value());
                    break;
                case "PROJ_PATH":
                    pathResourceAllocation.setProj_path(baseParams.get(i).getParam_value());
                    break;
            }
        }
        resultMap.put("pathResourceAllocation",pathResourceAllocation);
        return resultMap;
    }
    /**
     * 修改python 参数配置
     * @param request
     * @param response
     */
    @Transactional
    public void updatePythonResourceAllocationData(HttpServletRequest request, HttpServletResponse response, PythonResourceAllocation pythonResourceAllocation ){

        Map map = new HashMap();
        map.put("user_id",request.getSession().getAttribute("user").toString());
        map.put("category_id", "python");
        List<BaseParam> baseParams = mysqlTestMapper.selectBaseParamLike(map);
        if (baseParams.size() == 2){
            for (int i = 0; i < baseParams.size(); i++) {
                switch (baseParams.get(i).getParam_name()) {
                    case "num_executors":
                        map.put("param_name","pyspark_python");
                        map.put("param_value",pythonResourceAllocation.getPyspark_python());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case "executor_memory":
                        map.put("param_name","pyspark_driver_python");
                        map.put("param_value",pythonResourceAllocation.getPyspark_driver_python());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                }
            }
        }else {
            for (int i = 0; i < 2; i++) {
                switch (i) {
                    case 0:
                        map.put("param_name","pyspark_python");
                        map.put("param_value",pythonResourceAllocation.getPyspark_python());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 1:
                        map.put("param_name","pyspark_driver_python");
                        map.put("param_value",pythonResourceAllocation.getPyspark_driver_python());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                }
            }
        }
    }


    /**
     * 修改python 参数配置
     * @param request
     * @param response
     */
    @Transactional
    public void updatePathResourceAllocationData(HttpServletRequest request, HttpServletResponse response, PathConfiguration pathConfiguration  ){

        Map map = new HashMap();
        map.put("user_id",request.getSession().getAttribute("user").toString());
        map.put("category_id", "path");
        List<BaseParam> baseParams = mysqlTestMapper.selectBaseParamLike(map);
        if (baseParams.size() == 4){
            for (int i = 0; i < baseParams.size(); i++) {
                switch (baseParams.get(i).getParam_name()) {
                    case "MODEL_PATH":
                        map.put("param_name","MODEL_PATH");
                        map.put("param_value",pathConfiguration.getModel_path());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case "HDFS_PATH":
                        map.put("param_name","HDFS_PATH");
                        map.put("param_value",pathConfiguration.getHdfs_path());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case "BASE_PATH":
                        map.put("param_name","BASE_PATH");
                        map.put("param_value",pathConfiguration.getBase_path());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                    case "PROJ_PATH":
                        map.put("param_name","PROJ_PATH");
                        map.put("param_value",pathConfiguration.getProj_path());
                        mysqlTestMapper.updateBaseParamData(map);
                        break;
                }
            }
        }else {
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        map.put("param_name","MODEL_PATH");
                        map.put("param_value",pathConfiguration.getModel_path());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 1:
                        map.put("param_name","HDFS_PATH");
                        map.put("param_value",pathConfiguration.getHdfs_path());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 2:
                        map.put("param_name","BASE_PATH");
                        map.put("param_value",pathConfiguration.getBase_path());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                    case 3:
                        map.put("param_name","PROJ_PATH");
                        map.put("param_value",pathConfiguration.getProj_path());
                        mysqlTestMapper.insertBaseParamData(map);
                        break;
                }
            }
        }
    }
}
