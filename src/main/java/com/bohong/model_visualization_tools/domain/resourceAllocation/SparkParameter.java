package com.bohong.model_visualization_tools.domain.resourceAllocation;

public class SparkParameter {
    private String num_executors;
    private String executor_memory;
    private String executor_cores;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum_executors() {
        return num_executors;
    }

    public void setNum_executors(String num_executors) {
        this.num_executors = num_executors;
    }

    public String getExecutor_memory() {
        return executor_memory;
    }

    public void setExecutor_memory(String executor_memory) {
        this.executor_memory = executor_memory;
    }

    public String getExecutor_cores() {
        return executor_cores;
    }

    public void setExecutor_cores(String executor_cores) {
        this.executor_cores = executor_cores;
    }
}
