package com.bohong.model_visualization_tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
public class TestLog4j {


    @Test
    public void test1(){
        String result = "{a:b,c:d}";
        System.out.println(JSON.parseObject(result, Map.class).get("a"));
    }

}
