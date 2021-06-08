package com.bohong.model_visualization_tools.mapper.pg;

import com.bohong.model_visualization_tools.domain.modelTraining.ModelParams;
import com.bohong.model_visualization_tools.domain.modelTraining.ModelTraining;

import java.util.List;

public interface ModelTrainingMapper {


    void insertFeatureProcessingData(ModelTraining modelTraining);


    List<ModelParams> selectModelparamsData(String alg_id);


    void insertSuperParameterSettingData(ModelParams modelParams);
}
