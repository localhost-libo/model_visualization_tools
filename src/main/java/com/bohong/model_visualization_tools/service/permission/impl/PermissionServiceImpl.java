package com.bohong.model_visualization_tools.service.permission.impl;


import com.alibaba.fastjson.JSONObject;
import com.bohong.model_visualization_tools.domain.permission.*;
import com.bohong.model_visualization_tools.mapper.pg.PermissionMapper;
import com.bohong.model_visualization_tools.mapper.pg.SystemSettingsMapper;
import com.bohong.model_visualization_tools.service.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.jdo.annotations.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    public Map verificationLogin(User user, HttpSession Session){
        Map map = new HashMap();
        map.put("result",true);


       List<User> users = permissionMapper.selectVerificationLogin(user);
       if (users.size() != 0){
           Session.setAttribute("loginUser",users.get(0).getReal_name());
           Session.setAttribute("user",users.get(0).getUser_name());
           Session.setAttribute("group",users.get(0).getGroup());
           map.put("result",true);
           return map;
       }
        map.put("info","登录失败");
        return map;
    }



}
