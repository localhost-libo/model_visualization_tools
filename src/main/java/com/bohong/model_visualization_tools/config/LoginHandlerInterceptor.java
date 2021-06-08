package com.bohong.model_visualization_tools.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
//        String ip = request.getRemoteAddr();//返回发出请求的IP地址
//        String host=request.getRemoteHost();//返回发出请求的客户机的主机名
//        int port =request.getRemotePort();//返回发出请求的客户机的端口号。
//        System.out.println("IP==>"+ip);
//        System.out.println("主机名==>"+host);
//        System.out.println("端口号==>"+port);
        if(user == null){
            //未登录，返回登录页
            request.setAttribute("message","没有权限，请先登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else{
            return true;
        }
    }
}
