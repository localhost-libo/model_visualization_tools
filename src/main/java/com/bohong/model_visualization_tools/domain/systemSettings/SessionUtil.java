package com.bohong.model_visualization_tools.domain.systemSettings;

public class SessionUtil {

    /**
     * 本类内部创建对象实例
     */
    private static SessionUtil instance = null;

    /**
     *私有化构造方法 禁止外部new
     */
    private SessionUtil(){}


    public static SessionUtil getInstance(){
        if (instance == null){
            synchronized (SessionUtil.class){
                if (instance == null){
                    instance = new SessionUtil();
                }
            }
        }
        return instance;
    }

}
