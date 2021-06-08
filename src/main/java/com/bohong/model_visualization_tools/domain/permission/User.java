package com.bohong.model_visualization_tools.domain.permission;

import lombok.Data;

@Data
public class User {
    private String user_name;
    private String password;
    private Integer group;
    private String real_name;
}
