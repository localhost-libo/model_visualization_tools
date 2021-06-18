package com.bohong.model_visualization_tools.domain.databaseConfiguration;


import lombok.Data;

@Data
public class Database {

    private String type;
    private String host;
    private String port;
    private String user;
    private String pswd;
    private String dbnm;
    private String inst;


}
