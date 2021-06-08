package com.bohong.model_visualization_tools.service.databaseConfiguration;

import com.bohong.model_visualization_tools.domain.databaseConfiguration.Database;
import org.springframework.ui.ModelMap;

import java.util.Map;

public interface DatabaseConfigurationService {


    void toDatabase(ModelMap modelMap);

    Map updateDatabaseService(Database Database);

    Database changeData(Integer sign);

}
