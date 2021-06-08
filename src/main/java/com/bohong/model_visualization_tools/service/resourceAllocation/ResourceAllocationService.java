package com.bohong.model_visualization_tools.service.resourceAllocation;

import com.bohong.model_visualization_tools.domain.resourceAllocation.SparkParameter;
import com.jcraft.jsch.MAC;
import org.springframework.ui.ModelMap;

import java.util.Map;

public interface ResourceAllocationService {

    void  toSparkParameter(ModelMap modelMap);
    void  toSparkParameters(ModelMap modelMap) throws Exception;
    Map updateSparkParameter(SparkParameter sparkParameter);
}
