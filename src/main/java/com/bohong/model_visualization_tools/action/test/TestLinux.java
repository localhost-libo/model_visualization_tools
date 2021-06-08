package com.bohong.model_visualization_tools.action.test;

import com.bohong.model_visualization_tools.domain.test.TestUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestLinux {


    @RequestMapping(value = "/test/testXshell.do")
    public String a(){
        return "test/testXshell";
    }


    @ResponseBody
    @RequestMapping(value = "/test/testUrl.do",method = RequestMethod.GET)
    public String testUrlGet(TestUrl testUrl){
        return "success_get"+testUrl.toString();
    }
    @ResponseBody
    @RequestMapping(value = "/test/testUrl.do",method = RequestMethod.POST)
    public String testUrlPost(TestUrl testUrl){
        return "success_post"+testUrl.toString();
    }
    @ResponseBody
    @RequestMapping(value = "/test/testUrl.do",method = RequestMethod.PUT)
    public String testUrlPut(TestUrl testUrl){
        return "success_put"+testUrl.toString();
    }
    @ResponseBody
    @RequestMapping(value = "/test/testUrl.do/{name}",method = RequestMethod.DELETE)
    public String testUrlDelete(@PathVariable String name){
        return "success_delete";
    }
}
