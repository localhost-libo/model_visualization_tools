package com.bohong.model_visualization_tools.action.databaseConfiguration;

import com.bohong.model_visualization_tools.domain.databaseConfiguration.Database;
import com.bohong.model_visualization_tools.service.databaseConfiguration.DatabaseConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DatabaseConfigurationAction {

    @Autowired
    private DatabaseConfigurationService databaseConfigurationService;
    @Autowired
    private Environment environment;

    @RequestMapping(value = "/databaseConfiguration/topgDatabase.do")
    public String topgDatabase(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
        databaseConfigurationService.toDatabase(request,response,modelMap);
        return "databaseConfiguration/configuration";
    }

    @ResponseBody
    @RequestMapping(value = "/databaseConfiguration/updateDatabase.do")
    public Map updatePgDatabaseAction(HttpServletRequest request, HttpServletResponse response,Database Database){
        Map resultMap = new HashMap();
        resultMap.put("sign",true);
        resultMap.put("info","修改成功");
        try {
            databaseConfigurationService.updateDatabaseService(request,response,Database);
        }catch (Exception e){
            resultMap.put("sign",false);
            resultMap.put("info","修改失败");
            e.printStackTrace();
        }
        return resultMap;
    }


    @ResponseBody
    @RequestMapping(value = "/databaseConfiguration/changeData.do")
    public Database changeData(HttpServletRequest request, HttpServletResponse response, Integer sign){
       return databaseConfigurationService.changeData(request,response,sign);
    }
}
