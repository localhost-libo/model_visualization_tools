package com.bohong.model_visualization_tools.action.resourceAllocation;

import com.bohong.model_visualization_tools.domain.resourceAllocation.SparkParameter;
import com.bohong.model_visualization_tools.service.resourceAllocation.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.spi.RegisterableService;
import java.util.Map;

@Controller
public class ResourceAllocationAction {


    @Autowired
    private ResourceAllocationService resourceAllocationService;


    /**
     * 跳转 spark 页面
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/resourceAllocation/toSparkParameter.do")
    public String toSparkParameter(ModelMap modelMap){
        resourceAllocationService.toSparkParameter(modelMap);
        return "resourceAllocation/sparkParameter";
    }

        @ResponseBody
        @RequestMapping(value = "/resourceAllocation/updateSparkParameter.do")
        public Map updateSparkParameter(SparkParameter sparkParameter){
        return resourceAllocationService.updateSparkParameter(sparkParameter);
        }

    @RequestMapping(value = "/hive.do")
    public String hive(ModelMap modelMap) throws Exception {
        resourceAllocationService.toSparkParameters(modelMap);
        return "resourceAllocation/TestHive";
    }
}
