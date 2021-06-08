package com.bohong.model_visualization_tools.service.modelTraining.impl;

import com.alibaba.fastjson.JSON;
import com.bohong.model_visualization_tools.domain.modelTraining.ModelParams;
import com.bohong.model_visualization_tools.domain.modelTraining.ModelTraining;
import com.bohong.model_visualization_tools.mapper.mysql.MysqlTestMapper;
import com.bohong.model_visualization_tools.mapper.pg.ModelTrainingMapper;
import com.bohong.model_visualization_tools.mapper.pg.PermissionMapper;
import com.bohong.model_visualization_tools.service.modelTraining.ModelTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModelTrainingServiceImpl implements ModelTrainingService {

    @Autowired
    private MysqlTestMapper mysqlTestMapper;

    @Autowired
    private ModelTrainingMapper modelTrainingMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    @Value("${custom.yml_sign}")
    private String yml_sign;


    public Map getDataSheetInfo(ModelTraining modelTraining){
        Map paramMap = new HashMap();
        modelTraining.setFeatures_model_id(modelTraining.getAlgorithm() + modelTraining.getModel_id());
        if (yml_sign.equals("1")){
            List<ModelTraining> ModelTrainingList =  mysqlTestMapper.selectDataSheetInfo(modelTraining);
            paramMap.put("ModelTrainingListJsonArray",JSON.toJSON(ModelTrainingList).toString());
            paramMap.put("ModelTrainingList",ModelTrainingList);
        }else {
            List<ModelTraining> ModelTrainingList = permissionMapper.selectDataSheetInfos(modelTraining);
            for (ModelTraining m : ModelTrainingList){
                if(m.getDescribes()==null){
                    m.setDescribes("无");
                }
            }
            paramMap.put("ModelTrainingListJsonArray",JSON.toJSON(ModelTrainingList).toString());
            paramMap.put("ModelTrainingList",ModelTrainingList);
        }

        return paramMap;
    }

    public Map getFeatureProcessingData(ModelTraining modelTraining){
        Map paramMap = new HashMap();
        paramMap.put("ModelTrainingList", divisionString(modelTraining));
        return paramMap;
    }

    public Map getSuperParameterSettingData(ModelTraining modelTraining){
        Map paramMap = new HashMap();
        paramMap.put("sign", true);
        paramMap.put("info", "特征处理保存成功");
        List<ModelTraining> modelTrainingList = divisionStrings(modelTraining);
        for (ModelTraining m : modelTrainingList){
            try {
                modelTrainingMapper.insertFeatureProcessingData(m);
                paramMap.put("ModelTrainingList", modelTrainingList);
            }catch (Exception e){
                paramMap.put("sign", false);
                paramMap.put("info", "特征处理保存失败");
                e.printStackTrace();
            }
        }
        List<ModelParams> modelParamsList = modelTrainingMapper.selectModelparamsData(modelTraining.getAlgorithm());
        for (ModelParams m : modelParamsList){
            m.setAlg_id(modelTrainingList.get(0).getFeatures_model_id());
        }
        paramMap.put("modelParamsList",modelParamsList);
        return paramMap;
    }
        public List<ModelTraining> divisionString(ModelTraining modelTraining){
            List<ModelTraining> modelTrainingsList = new ArrayList<>();
            String features_model_id = modelTraining.getFeatures_model_id();
            String field = modelTraining.getField();
            String describes = modelTraining.getDescribes();
            String field_type = modelTraining.getField_type();
            String use = modelTraining.getUse();
            String[] setting_types = null;
            if (modelTraining.getSetting_type() != null){
                String setting_type = modelTraining.getSetting_type();
                setting_types = setting_type.split(",");
            }

            String[] features_model_ids = features_model_id.split(",");
            String[] fields = field.split(",");
            String[] describess = describes.split(",");
            String[] field_types = field_type.split(",");
            String[] uses = use.split(",");
            int a = 0;
            for (int i = 0; i < features_model_ids.length; ++i){
                if(!uses[i].equals("1")){
                    if (fields.length != setting_types.length){
                        try {
                            if (setting_types[i] == null ){
                                continue;
                            }
                        }catch (Exception e){
                        }
                    }
                    ModelTraining modelTraining1 = new ModelTraining();
                    modelTraining1.setFeatures_model_id(features_model_ids[i]);
                    modelTraining1.setField(fields[i]);
                    try {
                        modelTraining1.setData_sheet(describess[i]);
                    }catch (Exception e){
                        modelTraining1.setData_sheet("无");
                    }
                    modelTraining1.setField_type(field_types[i]);
                    modelTraining1.setUse(uses[i]);
                    modelTraining1.setSetting_type(setting_types[a]);
                    modelTrainingsList.add(modelTraining1);
                    a++;
                }
            }
            return modelTrainingsList;
        }

    public List<ModelTraining> divisionStrings(ModelTraining modelTraining){
        List<ModelTraining> modelTrainingsList = new ArrayList<>();
        List<String> ss = new ArrayList<>();
        String features_model_id = modelTraining.getFeatures_model_id();
        String field = modelTraining.getField();
        String describes = modelTraining.getDescribes();
        String field_type = modelTraining.getField_type();
        String setting_type = modelTraining.getSetting_type();
        String handle = modelTraining.getHandle();
        String[] handle_values = null;
        if (modelTraining.getHandle_value() != null){
            String handle_value = modelTraining.getHandle_value();
            ss.add(handle_value);
            handle_values = handle_value.split(",");
        }
        ss.add(features_model_id);
        ss.add(field);
        ss.add(describes);
        ss.add(field_type);
        ss.add(setting_type);
        ss.add(handle);
        String[] features_model_ids = features_model_id.split(",");
        String[] fields = field.split(",");
        String[] describess = describes.split(",");
        String[] field_types = field_type.split(",");
        String[] setting_types = setting_type.split(",");
        String[] handles = handle.split(",");

        for (int i = 0; i < features_model_ids.length; ++i){
            ModelTraining modelTraining1 = new ModelTraining();
            modelTraining1.setFeatures_model_id(features_model_ids[i]);
            modelTraining1.setField(fields[i]);
            modelTraining1.setDescribes(describess[i]);
            modelTraining1.setField_type(field_types[i]);
            modelTraining1.setSetting_type(setting_types[i]);
            modelTraining1.setHandle(handles[i]);
            try {
                if (handle_values[i] != null){
                    modelTraining1.setHandle_value(handle_values[i]);
                }
            }catch (Exception e){
                modelTraining1.setHandle_value("");
            }

            modelTrainingsList.add(modelTraining1);
        }
        return modelTrainingsList;
    }

    public Map getYanzheng(ModelTraining modelTraining){
        Map paramMap = new HashMap();
        paramMap.put("sign", 1);
        List<String> strList = new ArrayList<>();
        List<ModelTraining> list = divisionStrings(modelTraining);
        for (ModelTraining m : list){
            if (m.getHandle().equals("pca") ){
                if (m.getHandle_value().equals("") ){
                strList.add(m.getField()+"_handle_value");
                    paramMap.put("sign", 2);
                }
            }
            if (m.getHandle().equals("qd")){
                if (m.getHandle_value() == "" ){
                    strList.add(m.getField()+"_handle_value");
                    paramMap.put("sign", 2);
                }

            }
        }
        paramMap.put("strList",strList);
        //如果有一个对象中特征处理方式为pca 或 minmax 但 取值为空 则 返回 改条 id（定以id）  取值框变红
        return paramMap;
    }


    public Map preservation(ModelParams modelParams){
        Map paramMap = new HashMap();
        paramMap.put("info","超参设置保存成功");
        List<ModelParams> modelParamsList = preservations(modelParams);
        for (ModelParams m : modelParamsList){
            try {
                //缺事务
                modelTrainingMapper.insertSuperParameterSettingData(m);
            }catch (Exception e){
                paramMap.put("info","超参设置保存失败");
            }
        }
        return paramMap;
    }

    public List<ModelParams> preservations(ModelParams modelParams){
        List<ModelParams> ModelParamsList = new ArrayList<>();
        List<String> ss = new ArrayList<>();
        String alg_id = modelParams.getAlg_id();
        String param_name = modelParams.getParam_name();
        String use = modelParams.getUse();
        String handle_value = modelParams.getHandle_value();
        ss.add(alg_id);
        ss.add(param_name);
        ss.add(use);
        ss.add(handle_value);
        String[] alg_ids = alg_id.split(",");
        String[] param_names = param_name.split(",");
        String[] uses = use.split(",");
        String[] handle_values = handle_value.split(",");
        for (int i = 0; i < param_names.length; ++i){
            ModelParams modelParams1 = new ModelParams();
            modelParams1.setAlg_id(alg_ids[i]);
            modelParams1.setParam_name(param_names[i]);
            modelParams1.setUse(uses[i]);
            try {
                modelParams1.setHandle_value(handle_values[i]);
            }catch (Exception e){
                modelParams1.setHandle_value("");
            }
            ModelParamsList.add(modelParams1);
        }
        return ModelParamsList;
    }
}
