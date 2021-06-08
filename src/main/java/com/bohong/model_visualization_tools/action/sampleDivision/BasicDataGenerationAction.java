package com.bohong.model_visualization_tools.action.sampleDivision;


import com.bohong.model_visualization_tools.domain.sampleDivision.BasicDataGeneration;
import com.bohong.model_visualization_tools.service.sampleDivision.BasicDataGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class BasicDataGenerationAction {

    @Autowired
    private BasicDataGenerationService trainingSetPartitionService;

    @ResponseBody
    @RequestMapping(value = "/operationLinux/TestLinux.do")
    public Map TestLinuxAction(BasicDataGeneration trainingSetPartition){
        return trainingSetPartitionService.TestLinuxService(trainingSetPartition);
    }

}
