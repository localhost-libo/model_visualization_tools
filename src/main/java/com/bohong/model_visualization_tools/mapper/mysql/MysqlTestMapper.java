package com.bohong.model_visualization_tools.mapper.mysql;


import com.bohong.model_visualization_tools.domain.databaseConfiguration.BaseParam;
import com.bohong.model_visualization_tools.domain.modelTraining.ModelTraining;
import java.util.List;
import java.util.Map;

public interface MysqlTestMapper {

    List<ModelTraining> selectDataSheetInfo(ModelTraining modelTraining);

    List<BaseParam> selectBaseParamLike(Map map);


    void updateBaseParamData(Map map);

    void insertBaseParamData(Map map);
}
