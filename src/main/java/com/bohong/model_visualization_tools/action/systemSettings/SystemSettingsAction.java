package com.bohong.model_visualization_tools.action.systemSettings;


import com.bohong.model_visualization_tools.domain.systemSettings.MenuUtil;
import com.bohong.model_visualization_tools.domain.systemSettings.UserData;
import com.bohong.model_visualization_tools.service.systemSettings.SystemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SystemSettingsAction {


    @Autowired
    private SystemSettingsService systemSettingsService;


    @ResponseBody
    @RequestMapping(value = "/signOut.do")
    public Map signOut(HttpSession session){
        return systemSettingsService.signOut(session);
    }
    @ResponseBody
    @RequestMapping("/getindex.do")
    public Map index(ModelMap modelMap, HttpServletRequest request){
        return  systemSettingsService.toIndex(modelMap,request);
    }

    /**
     * 跳转至权限配置
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/systemSettings/toPermissionConfiguration.do")
    public String toPermissionConfiguration(ModelMap modelMap, HttpServletRequest request){

        return  "systemSettings/permissionConfiguration";
    }

    /**
     * 跳转至用户信息
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/systemSettings/toUserInformation.do")
    public String s(ModelMap modelMap, HttpServletRequest request){
        return  "systemSettings/userInformation";
    }


    /**
     * 跳转至菜单信息
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/systemSettings/toMenuInformation.do")
    public String toMenuInformation(ModelMap modelMap, HttpServletRequest request){
        systemSettingsService.toMenuInformation(modelMap,request);
        return  "systemSettings/menuInformation";
    }


    @ResponseBody
    @RequestMapping("/systemSettings/getSupMenuData.do")
    public  Map gettestData(MenuUtil menuUtil){
        return systemSettingsService.gettestData(menuUtil);
    }

//    @ResponseBody
//    @RequestMapping(value = "/systemSettings/preservationMenuData.do")
//    public Map preservationMenuData(MenuUtil menuUtil){
//        return systemSettingsService.preservationMenuData(menuUtil);
//    }



    @ResponseBody
    @RequestMapping(value = "/systemSettings/getEditSubMenuInforMationData.do")
    public Map getEditSubMenuInforMationData(MenuUtil menuUtil){
        return systemSettingsService.getEditSubMenuInforMationData(menuUtil);
    }

    @ResponseBody
    @RequestMapping(value = "/systemSettings/getSelectNameData.do")
    public Map getSelectNameData(MenuUtil menuUtil){
        return systemSettingsService.getSelectNameData(menuUtil);
    }

    @ResponseBody
    @RequestMapping(value = "/systemSettings/updateSupMenuData.do")
    public Map updateSupMenuData(MenuUtil menuUtil){
        Map map = new HashMap();
        map.put("sign",true);
        try {
            systemSettingsService.updateSupMenuData(menuUtil);
        }catch (Exception e){
            e.printStackTrace();
            map.put("sign",false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/systemSettings/getUserData.do")
    public Map getUserData(){

        return systemSettingsService.getUserData();
    }
    @ResponseBody
    @RequestMapping(value = "/systemSettings/userDataOperation.do")
    public Map userDataOperation(UserData userData){
        Map map = new HashMap();
        map.put("sign",true);
        map.put("info","保存成功");
        try {
            systemSettingsService.userDataOperation(userData);
        }catch (Exception e){
            map.put("sign",false);
            map.put("info","保存失败");
        }
        return  map;
    }

    @ResponseBody
    @RequestMapping(value = "/systemSettings/testLinux.do")
    public String testLinux(){
        return  systemSettingsService.testLinux();
    }
}
