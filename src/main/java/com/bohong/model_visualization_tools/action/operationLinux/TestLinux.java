package com.bohong.model_visualization_tools.action.operationLinux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestLinux {

    public void Test1(){
        try {
            String[] cmd = new String[]{"/bin/sh","-c", "cmd"};
            Process ps = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);//输出返回值
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
