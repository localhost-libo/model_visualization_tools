package com.bohong.model_visualization_tools.service.systemSettings.impl;

import com.alibaba.fastjson.JSONObject;
import com.bohong.model_visualization_tools.domain.systemSettings.*;
import com.bohong.model_visualization_tools.mapper.pg.SystemSettingsMapper;
import com.bohong.model_visualization_tools.service.systemSettings.SystemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import parquet.schema.Type;

import javax.jdo.annotations.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemSettingsServiceImpl implements SystemSettingsService {

    @Autowired
    private SystemSettingsMapper systemSettingsMapper;


    public Map signOut(HttpSession session){
        Map map = new HashMap();
        session.invalidate();
        return map;
    }

    public Map toIndex(ModelMap modelMap, HttpServletRequest request){
        Map map = new HashMap();
        MenuUtil menuUtil = new MenuUtil();
        menuUtil.setGroup(request.getSession().getAttribute("group").toString());
        List<MenuUtilOne> menuUtilOneList = new ArrayList<>();
        List<String> categoryList = null;
        if (request.getSession().getAttribute("loginUser").equals("管理员")){
            categoryList = systemSettingsMapper.selectSubmenuUtilCategoryData();
        }else{
            categoryList = systemSettingsMapper.selectSubmenuUtilCategoryDatas();
        }
        for (int i = 0; i < categoryList.size();i++){
            MenuUtilOne menuUtilOne = new MenuUtilOne();
            menuUtilOne.setId(categoryList.get(i));
            menuUtil.setCategory(categoryList.get(i));
            List<String> nameList = systemSettingsMapper.selectSubmenuData(menuUtil);
            List<MenuUtilTwo> menuUtilTwoList = new ArrayList<>();
            for (int a = 0; a < nameList.size(); a++ ){
                MenuUtilTwo menuUtilTwo = new MenuUtilTwo();
                menuUtilTwo.setText(nameList.get(a));
                menuUtil.setName(nameList.get(a));
                List<SubMenu> sudMenuDataList = systemSettingsMapper.selectSudMenuData(menuUtil);
                List<SubMenu> sudMenuList = new ArrayList<>();
                for (int s = 0;s < sudMenuDataList.size(); s++){
                    SubMenu sudMenu = new SubMenu();
                    sudMenu.setId(sudMenuDataList.get(s).getId());
                    sudMenu.setText(sudMenuDataList.get(s).getText());
                    sudMenu.setHref(sudMenuDataList.get(s).getHref());
                    sudMenuList.add(sudMenu);
                }
                menuUtilTwo.setItems(sudMenuList);
                menuUtilTwoList.add(menuUtilTwo);
            }
            menuUtilOne.setMenu(menuUtilTwoList);
            menuUtilOneList.add(menuUtilOne);
        }
        map.put("menuListJson", JSONObject.toJSON(menuUtilOneList).toString());
        map.put("categoryList",categoryList);
        return map;
    }

    public void toSubmenuInformation(ModelMap modelMap, HttpServletRequest request){
        List<String> categoryList = systemSettingsMapper.selectSubmenuUtilCategoryData();

        modelMap.put("categoryList",categoryList);
    }

    public Map  gettestData(MenuUtil menuUtil){
        Map map = new HashMap();
        List<MenuUtil> menuUtilList = systemSettingsMapper.selectSudMenuAllData(menuUtil);
        map.put("menuUtilList",JSONObject.toJSON(menuUtilList).toString());
        return map;
    }

//    @Transactional
//    public Map preservationMenuData(MenuUtil menuUtil){
//        Map map = new HashMap();
//        map.put("sign",true);
//        try {
//            systemSettingsMapper.insertSubMenuData(menuUtil);
//        }catch (Exception e){
//            map.put("sign",false);
//            e.printStackTrace();
//        }
//        return map;
//    }

    public Map getEditSubMenuInforMationData(MenuUtil menuUtil){
        Map map = new HashMap();
        SubMenu subMenu = systemSettingsMapper.selectSubMenuInforMationData(menuUtil);
        map.put("subMenu",subMenu);
        return map;
    }

    public Map getSelectNameData(MenuUtil menuUtil){
        Map map = new HashMap();
        map.put("nameList",systemSettingsMapper.selectMenuUtilNameData(menuUtil));
        return map;
    }

    @Transactional
    public void updateSupMenuData(MenuUtil menuUtil){
        List<MenuUtil> newMenuUtilList = divisionMenuStrings(menuUtil);

        List<MenuUtil> newSubMenuData = new ArrayList<>();

        for (MenuUtil m : newMenuUtilList){
            MenuUtil menuUtil1 = systemSettingsMapper.selectSubMenuDataById(m);
            if (menuUtil1 == null){
                m.setManag("0");
                m.setOperation("0");
                m.setCloseable("true");
                m.setSuperior(systemSettingsMapper.selectMenuID(menuUtil).getId());
                newSubMenuData.add(m);
            }else {
                newSubMenuData.add(menuUtil1);
            }

        }

        systemSettingsMapper.deleteSubMenuData(menuUtil);

        for (MenuUtil s : newSubMenuData){
            systemSettingsMapper.insertSubMenuData(s);
        }
    }

    public List<MenuUtil> divisionMenuStrings(MenuUtil menuUtil){
        List<MenuUtil> menuUtilList = new ArrayList<>();
        String[] id = menuUtil.getId().split(",");
        String[] text = menuUtil.getText().split(",");
        String[] href = menuUtil.getHref().split(",");
        String[] sequence = menuUtil.getSequence().split(",");
        for (int i = 0; i < id.length; i++){
            MenuUtil menuUtil1 = new MenuUtil();
            menuUtil1.setName(menuUtil.getName());
            menuUtil1.setCategory(menuUtil.getCategory());
            menuUtil1.setId(id[i]);
            menuUtil1.setText(text[i]);
            menuUtil1.setHref(href[i]);
            menuUtil1.setSequence(sequence[i]);
            menuUtilList.add(menuUtil1);
        }
        return menuUtilList;
    }

    public Map getUserData(){
        Map map = new HashMap();
        map.put("userDataList",JSONObject.toJSON(systemSettingsMapper.selectUserData()).toString());
        return map;
    }


    @Transactional
    public void userDataOperation(UserData userData){
        List<UserData> userDataList = userDataDivision(userData);
        systemSettingsMapper.deleteUserDataDivision();
        for (UserData u : userDataList){
            if (u.getUser_name().equals("supadmin")){
                u.setGroup("0");
            }
        systemSettingsMapper.insertUserDataDivision(u);
        }
    }

    public List<UserData> userDataDivision(UserData userData){
        List<UserData> userDataList = new ArrayList<>();
        String[] usernames = userData.getUser_name().split(",");
        String[] real_names = userData.getReal_name().split(",");
        String[] names = userData.getName().split(",");
        String[] phones = userData.getPhone().split(",");
        String[] genders = userData.getGender().split(",");
        for (int i = 0; i < usernames.length;i++){
            userDataList.add(new UserData(usernames[i],"123456",names[i].equals("管理人员")?"1":"2",real_names[i],genders[i],phones[i],names[i]));
        }
        return userDataList;
    }

    public String testLinux(){
        String result = execCmd("cat /home/modelVisual/config/test01.txt", null);
        System.out.println(result);
        return result;
    }

    //此方法用来执行脚本或命令
    //String cmd：脚本（绝对路径）位置或命令内容
    //File dir：执行命令的子进程的工作目录（null则表示和当前主进程工作目录相同）
    public static String execCmd(String cmd, File dir){
        StringBuilder result = new StringBuilder();
        Process process = null;
        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;
        try {
            //执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = Runtime.getRuntime().exec(cmd, null, dir);
            //方法阻塞,等待命令执行完成
            process.waitFor();
            //获取命令执行结果, 有两个结果: 正常的输出和错误的输出（PS: 子进程的输出就是主进程的输入）
            bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));//正常的输出
            bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));//错误的输出
            //读取输出
            String line = null;
            while ((line = bufrIn.readLine()) != null){
                result.append(line).append('\n');
            }
            while ((line = bufrError.readLine()) != null){
                result.append(line).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeStream(bufrIn);
            closeStream(bufrError);
            //销毁子进程
            if (process != null){
                process.destroy();
            }
        }
        //返回执行结果
        return result.toString();

    }

    //关流方法
    private static void closeStream(Closeable stream){
        if (stream != null){
            try {
                stream.close();
            } catch (Exception e) {

            }
        }
    }

    /**
     * 获取一级菜单信息
     * @return
     */
    public  Map getMenuData(){
        Map map = new HashMap();
        map.put("menuDataList",JSONObject.toJSON(systemSettingsMapper.selectMenuData()).toString());
        return map;
    }
}
