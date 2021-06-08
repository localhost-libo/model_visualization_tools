package com.bohong.model_visualization_tools.mapper.pg;

import com.bohong.model_visualization_tools.domain.modelTraining.ModelTraining;
import com.bohong.model_visualization_tools.domain.permission.User;

import java.util.List;

public interface PermissionMapper {
    List<User> selectVerificationLogin(User user);

    List<ModelTraining> selectDataSheetInfos(ModelTraining modelTraining);
}
