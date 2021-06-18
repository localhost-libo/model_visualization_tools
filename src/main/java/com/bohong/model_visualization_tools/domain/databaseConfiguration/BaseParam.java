package com.bohong.model_visualization_tools.domain.databaseConfiguration;

import lombok.Data;

@Data
public class BaseParam {

    private Integer id;
    private String category_id;
    private String param_name;
    private String param_value;
    private String param_desc;
    private String param_date;
    private String user_id;


}
