package com.bohong.model_visualization_tools.domain.sampleDivision;

public class BasicDataGeneration {


    private String sh = "model_data_process.sh";
    private String data_source;//数据源
    private String prefix;//前缀
    private String startDate;//开始时间
    private String endDate;//结束时间
    private String basics;//基础
    private String htfs;
    private String hive_database;//hive数据库
    private String user;
    private String password;


    public String getCommand() {
        return "sh "+this.sh+" "+getData_source()+" "+getPrefix()+" "+getStartDate()+" "+getEndDate();
    }


    public String getBasics() {
        return basics;
    }

    public void setBasics(String basics) {
        this.basics = basics;
    }

    public String getHtfs() {
        return htfs;
    }

    public void setHtfs(String htfs) {
        this.htfs = htfs;
    }

    public String getHive_database() {
        return hive_database;
    }

    public void setHive_database(String hive_database) {
        this.hive_database = hive_database;
    }

    @Override
    public String toString() {
        return "BasicDataGeneration{" +
                "sh='" + sh + '\'' +
                ", data_source='" + data_source + '\'' +
                ", prefix='" + prefix + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", basics='" + basics + '\'' +
                ", htfs='" + htfs + '\'' +
                ", hive_database='" + hive_database + '\'' +
                ", command='" + command + '\'' +
                '}';
    }

    private String command;//命令


    public void setCommand(String command) {
        this.command = command;
    }

    public String getSh() {
        return sh;
    }

    public void setSh(String sh) {
        this.sh = sh;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
