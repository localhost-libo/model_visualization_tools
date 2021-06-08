package com.bohong.model_visualization_tools.action.modelTraining;


import com.bohong.model_visualization_tools.domain.modelTraining.ModelParams;
import com.bohong.model_visualization_tools.domain.modelTraining.ModelTraining;
import com.bohong.model_visualization_tools.service.modelTraining.ModelTrainingService;
import com.bohong.model_visualization_tools.service.modelTraining.impl.ModelTrainingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ModelTrainingAction {

    @Autowired
    private ModelTrainingService modelTrainingService;

    @RequestMapping(value = "/modelTraining/toModelTraining.do")
    public String toModelTraining(){
        return "modelTraining/modelTraining";
    }


    @ResponseBody
    @RequestMapping(value = "/getDataSheetInfo.do")
    public Map testMysql(ModelTraining modelTraining){
        return modelTrainingService.getDataSheetInfo(modelTraining);
    }

    @ResponseBody
    @RequestMapping(value = "/getFeatureProcessingData.do")
    public Map getFeatureProcessingData(ModelTraining modelTraining){
        return  modelTrainingService.getFeatureProcessingData(modelTraining);
    }



    @ResponseBody
    @RequestMapping(value = "/getSuperParameterSettingData.do")
    public Map getSuperParameterSettingData(ModelTraining modelTraining){
        return   modelTrainingService.getSuperParameterSettingData(modelTraining);
    }


    @ResponseBody
    @RequestMapping(value = "/yanzheng.do")
    public Map yanzheng(ModelTraining modelTraining){
        return   modelTrainingService.getYanzheng(modelTraining);
    }



    @ResponseBody
    @RequestMapping(value = "/preservation.do")
    public Map preservation(ModelParams modelParams){

        return modelTrainingService.preservation(modelParams);
    }
}
