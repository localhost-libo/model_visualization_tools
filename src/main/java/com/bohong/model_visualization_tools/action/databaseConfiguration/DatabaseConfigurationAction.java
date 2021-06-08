package com.bohong.model_visualization_tools.action.databaseConfiguration;

import com.bohong.model_visualization_tools.domain.databaseConfiguration.Database;
import com.bohong.model_visualization_tools.service.databaseConfiguration.DatabaseConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DatabaseConfigurationAction {

    @Autowired
    private DatabaseConfigurationService databaseConfigurationService;
    @Autowired
    private Environment environment;

    @RequestMapping(value = "/databaseConfiguration/topgDatabase.do")
    public String topgDatabase(ModelMap modelMap){
        databaseConfigurationService.toDatabase(modelMap);
        return "databaseConfiguration/configuration";
    }

    @ResponseBody
    @RequestMapping(value = "/databaseConfiguration/updateDatabase.do")
    public Map updatePgDatabaseAction(Database Database){
        return databaseConfigurationService.updateDatabaseService(Database);
    }


    @ResponseBody
    @RequestMapping(value = "/changeData.do")
    public Database changeData(Integer sign){
       return databaseConfigurationService.changeData(sign);
    }
}
