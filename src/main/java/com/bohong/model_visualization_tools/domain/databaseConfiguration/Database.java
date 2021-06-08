package com.bohong.model_visualization_tools.domain.databaseConfiguration;


public class Database {

    private String type;
    private String host;
    private String port;
    private String user;
    private String pswd;
    private String dbnm;
    private String inst;

    @Override
    public String toString() {
        return "Database{" +
                "type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", user='" + user + '\'' +
                ", pswd='" + pswd + '\'' +
                ", dbnm='" + dbnm + '\'' +
                ", inst='" + inst + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getDbnm() {
        return dbnm;
    }

    public void setDbnm(String dbnm) {
        this.dbnm = dbnm;
    }

    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }
}
