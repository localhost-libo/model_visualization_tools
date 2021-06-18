package com.bohong.model_visualization_tools.domain.resourceAllocation;

import lombok.Data;

@Data
public class PathConfiguration {

    private String model_path;
    private String hdfs_path;
    private String base_path;
    private String proj_path;
}
