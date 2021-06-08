package com.bohong.model_visualization_tools.domain.modelTraining;

import lombok.Data;

@Data
public class ModelTraining {
    private String data_sheet;//读入数据表
    private String database_name;//数据库名
    private String model_path;//模型路径
    private String algorithm;//算法
    private String model_id;//模型ID
    private String cross_validation;//K折交叉验证
    private String field;//字段
    private String describes;//描述
    private String field_type;//字段类型
    private String use;//是否使用
    private String setting_type;//设置类型
    private String handle;//特征处理方式
    private String handle_value;//取值
    private String features_model_id;//特征设置模ID

}
