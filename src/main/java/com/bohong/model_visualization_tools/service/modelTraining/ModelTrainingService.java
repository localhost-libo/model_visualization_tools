package com.bohong.model_visualization_tools.service.modelTraining;

import com.bohong.model_visualization_tools.domain.modelTraining.ModelParams;
import com.bohong.model_visualization_tools.domain.modelTraining.ModelTraining;

import java.util.Map;

public interface ModelTrainingService {

    Map getDataSheetInfo(ModelTraining modelTraining);

    Map getFeatureProcessingData(ModelTraining modelTraining);

    Map getSuperParameterSettingData(ModelTraining modelTraining);


    Map getYanzheng(ModelTraining modelTraining);

    Map preservation(ModelParams modelParams);
}
