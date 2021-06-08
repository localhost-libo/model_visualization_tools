package com.bohong.model_visualization_tools.mapper.mysql;


import com.bohong.model_visualization_tools.domain.modelTraining.ModelTraining;
import java.util.List;
public interface MysqlTestMapper {

    List<ModelTraining> selectDataSheetInfo(ModelTraining modelTraining);
}
