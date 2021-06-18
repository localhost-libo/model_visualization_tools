package com.bohong.model_visualization_tools.domain.resourceAllocation;


import lombok.Data;
import org.checkerframework.checker.units.qual.min;

@Data
public class HiveResourceAllocation {

    private String mapreduce_map_memory_mb;
    private String mapreduce_reduce_memory_mb;
    private String hive_map_aggr;
    private String mapreduce_job_priority;
    private String hive_exec_compress_output;
    private String mapred_output_compression_codec;
    private String mapred_output_compression_type;
    private String hive_exec_reducers_bytes_per_reducer;
    private String mapred_max_split_size;
    private String mapred_min_split_size_per_node;
    private String mapred_min_split_size_per_rack;
    private String hive_hadoop_supports_splittable_combineinputformat;
    private String hive_input_format;
    private String hive_merge_mapfiles;
    private String hive_merge_mapredfiles;
    private String hive_merge_size_per_task;
    private String hive_merge_smallfiles_avgsize;

}
