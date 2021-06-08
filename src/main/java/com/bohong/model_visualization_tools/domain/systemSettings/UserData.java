package com.bohong.model_visualization_tools.domain.systemSettings;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class UserData {
    private String user_name;
    private String password;
    private String group;
    private String real_name;
    private String gender;
    private String phone;
    private String name;

    public UserData(String user_name, String password, String group, String real_name, String gender, String phone, String name) {
        this.user_name = user_name;
        this.password = password;
        this.group = group;
        this.real_name = real_name;
        this.gender = gender;
        this.phone = phone;
        this.name = name;
    }

    public UserData() {
    }
}
