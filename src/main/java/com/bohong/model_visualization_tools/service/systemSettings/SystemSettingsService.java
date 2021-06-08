package com.bohong.model_visualization_tools.service.systemSettings;

import com.bohong.model_visualization_tools.domain.systemSettings.MenuUtil;
import com.bohong.model_visualization_tools.domain.systemSettings.UserData;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface SystemSettingsService {



    Map signOut(HttpSession session);

    Map toIndex(ModelMap modelMap, HttpServletRequest request);

    void toMenuInformation(ModelMap modelMap, HttpServletRequest request);

    Map gettestData(MenuUtil menuUtil);

//    Map preservationMenuData(MenuUtil menuUtil);

    Map getEditSubMenuInforMationData(MenuUtil menuUtil);

    Map getSelectNameData(MenuUtil menuUtil);

    void updateSupMenuData(MenuUtil menuUtil);

    Map getUserData();

    /**
     * 用户信息 增删改查 操作
     * @return
     */
    void userDataOperation(UserData userData);

    String testLinux();
}
