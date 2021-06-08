package com.bohong.model_visualization_tools.action.sampleDivision;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class TestHttp {

        public static void main(String[] args) throws Exception {
            URL yahoo = new URL("http://www.baidu.com/query.jsp?param1=value2¶m2=value2");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yahoo.openStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();
    }
}
