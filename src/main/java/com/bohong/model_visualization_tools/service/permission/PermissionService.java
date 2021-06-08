package com.bohong.model_visualization_tools.service.permission;


import com.bohong.model_visualization_tools.domain.permission.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface PermissionService {


    /**
     * 用户登录
     * @param user
     * @param Session
     * @return
     */
    Map verificationLogin(User user, HttpSession Session);


}
