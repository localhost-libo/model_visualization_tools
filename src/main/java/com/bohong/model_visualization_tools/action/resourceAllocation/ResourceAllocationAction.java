package com.bohong.model_visualization_tools.action.resourceAllocation;

import com.bohong.model_visualization_tools.domain.resourceAllocation.HiveResourceAllocation;
import com.bohong.model_visualization_tools.domain.resourceAllocation.PythonResourceAllocation;
import com.bohong.model_visualization_tools.domain.resourceAllocation.SparkParameter;
import com.bohong.model_visualization_tools.service.resourceAllocation.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ResourceAllocationAction {


    @Autowired
    private ResourceAllocationService resourceAllocationService;





    @ResponseBody
    @RequestMapping(value = "/resourceAllocation/updateSparkResourceAllocationData.do")
    public Map updateSparkResourceAllocationData(HttpServletRequest request, HttpServletResponse response, SparkParameter sparkParameter){
        Map map = new HashMap();
        map.put("sign",true);
        map.put("info","保存成功");
        try {
            resourceAllocationService.updateSparkResourceAllocationData(request,response,sparkParameter);
        }catch (Exception e){
            map.put("sign",false);
            map.put("info","保存失败");
            e.printStackTrace();
        }
        return map;
    }
        @ResponseBody
        @RequestMapping(value = "/resourceAllocation/querySparkResourceAllocationData.do")
        public Map querySparkResourceAllocationData(HttpServletRequest request, HttpServletResponse response){
        return resourceAllocationService.querySparkResourceAllocationData(request,response);
        }


    /**
     * Hive参数配置 获取数据
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/resourceAllocation/queryHiveResourceAllocationData.do")
    public Map queryHiveResourceAllocationData(HttpServletRequest request, HttpServletResponse response)  {
        return resourceAllocationService.queryHiveResourceAllocationData(request,response);
    }


    /**
     * 修改hive参数配置数据
     * @param request
     * @param response
     * @param hiveResourceAllocation
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/resourceAllocation/updateHiveResourceAllocationData.do")
    public Map updateHiveResourceAllocationData(HttpServletRequest request, HttpServletResponse response, HiveResourceAllocation hiveResourceAllocation)  {
        Map map = new HashMap();
        map.put("sign",true);
        map.put("info","保存成功");
        try {
            resourceAllocationService.updateHiveResourceAllocationData(request,response,hiveResourceAllocation);
        }catch (Exception e){
            map.put("sign",false);
            map.put("info","保存失败");
            e.printStackTrace();
        }
        return map;
    }


    /**
     * python参数配置 获取数据
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/resourceAllocation/queryPythonResourceAllocationData.do")
    public Map queryPythonResourceAllocationData(HttpServletRequest request, HttpServletResponse response)  {
        return resourceAllocationService.queryPythonResourceAllocationData(request,response);
    }

    /**
     * 修改python参数配置数据
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/resourceAllocation/updatePythonResourceAllocationData.do")
    public Map updatePythonResourceAllocationData(HttpServletRequest request, HttpServletResponse response, PythonResourceAllocation pythonResourceAllocation)  {
        Map map = new HashMap();
        map.put("sign",true);
        map.put("info","保存成功");
        try {
            resourceAllocationService.updatePythonResourceAllocationData(request,response,pythonResourceAllocation);
        }catch (Exception e){
            map.put("sign",false);
            map.put("info","保存失败");
            e.printStackTrace();
        }
        return map;
    }
}
