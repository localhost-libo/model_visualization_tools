package com.bohong.model_visualization_tools.action.permission;

import com.bohong.model_visualization_tools.domain.permission.User;
import com.bohong.model_visualization_tools.service.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PermissionAction {

    @Autowired()
    private PermissionService permissionService;

    @ResponseBody
    @RequestMapping(value = "/permission/verificationLogin.do",method = RequestMethod.GET)
    public Map getVerificationLogin(User user, HttpSession Session){
        return permissionService.verificationLogin(user,Session);
    }

    @RequestMapping(value = "/TestHTML.do")
    public String test(ModelMap modelMap){
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUser_name("user1:success!");
        User user2 = new User();
        user2.setUser_name("user2:success!");
        userList.add(user1);
        userList.add(user2);
        modelMap.put("userList",userList);
        modelMap.put("user",user1);
        return "canvasTest";
    }




//    @ResponseBody
//    @RequestMapping(value = "/TestLinux.do")
//    public Map TESTlINUX(String command) throws Exception {
//        Map map = new HashMap();
//        String cmdstring = "mkdir "+command;
//        System.out.println(cmdstring);
//        Process proc = Runtime.getRuntime().exec(cmdstring);
//        proc.waitFor(); //阻塞，直到上述命令执行完
//        cmdstring = "bash test.sh"; //这里也可以是ksh等
//        proc = Runtime.getRuntime().exec(cmdstring);
//// 注意下面的操作
//        String ls_1;
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//        while ((ls_1 = bufferedReader.readLine()) != null) ;
//        System.out.println(ls_1);
//        bufferedReader.close();
//        proc.waitFor();
//        map.put("ls",ls_1);
//        return map;
//    }

}
