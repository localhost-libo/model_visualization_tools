package com.bohong.model_visualization_tools.service.databaseConfiguration;

import com.bohong.model_visualization_tools.domain.databaseConfiguration.Database;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface DatabaseConfigurationService {


    void toDatabase(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap);

    void updateDatabaseService(HttpServletRequest request, HttpServletResponse response,Database Database);

    Database changeData(HttpServletRequest request, HttpServletResponse response, Integer sign);

}
