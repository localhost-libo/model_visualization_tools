package com.bohong.model_visualization_tools.service.resourceAllocation;

import com.bohong.model_visualization_tools.domain.resourceAllocation.HiveResourceAllocation;
import com.bohong.model_visualization_tools.domain.resourceAllocation.PathConfiguration;
import com.bohong.model_visualization_tools.domain.resourceAllocation.PythonResourceAllocation;
import com.bohong.model_visualization_tools.domain.resourceAllocation.SparkParameter;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ResourceAllocationService {

    void  toSparkParameter(ModelMap modelMap);

    /**
     * Hive参数配置 获取数据
     * @param request
     * @param response
     * @return
     */
    Map  queryHiveResourceAllocationData(HttpServletRequest request, HttpServletResponse response);


    Map querySparkResourceAllocationData(HttpServletRequest request, HttpServletResponse response);

    Map queryPythonResourceAllocationData(HttpServletRequest request, HttpServletResponse response);

    Map querypathConfigurationData(HttpServletRequest request, HttpServletResponse response);
    /**
     * 修改hive参数配置数据
     * @param request
     * @param response
     * @param hiveResourceAllocation
     */
    void updateHiveResourceAllocationData(HttpServletRequest request, HttpServletResponse response, HiveResourceAllocation hiveResourceAllocation);

    void updateSparkResourceAllocationData(HttpServletRequest request, HttpServletResponse response, SparkParameter sparkParameter );

    public void updatePythonResourceAllocationData(HttpServletRequest request, HttpServletResponse response, PythonResourceAllocation pythonResourceAllocation );

    public void updatePathResourceAllocationData(HttpServletRequest request, HttpServletResponse response, PathConfiguration pathConfiguration );

}
