package com.bohong.model_visualization_tools.mapper.pg;

import com.bohong.model_visualization_tools.domain.systemSettings.MenuUtil;
import com.bohong.model_visualization_tools.domain.systemSettings.SubMenu;
import com.bohong.model_visualization_tools.domain.systemSettings.UserData;

import java.util.List;

public interface SystemSettingsMapper {

    List<SubMenu> selectSudMenuData(MenuUtil menuUtil);

    List<String> selectMenuData(MenuUtil menuUtil);

    List<String> selectMenuUtilCategoryData();

    List<String> selectMenuUtilCategoryDatas();

    List<MenuUtil> selectMenuUtilNameData(MenuUtil menuUtil);

    List<MenuUtil> selectSudMenuAllData(MenuUtil menuUtil);

    SubMenu selectSubMenuInforMationData(MenuUtil menuUtil);

    void insertSubMenuData(MenuUtil menuUtil);

    void insertNewSubMenuData(MenuUtil menuUtil);

    void deleteSubMenuData(MenuUtil menuUtil);

    MenuUtil selectSubMenuDataById(MenuUtil menuUtil);

    MenuUtil selectMenuID(MenuUtil menuUtil);

    List<UserData> selectUserData();

    void insertUserDataDivision(UserData userData);

    void deleteUserDataDivision();
}
