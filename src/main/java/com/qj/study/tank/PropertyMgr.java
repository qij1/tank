package com.qj.study.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * 使用单例
 */
public class PropertyMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load((PropertyMgr.class.getClassLoader().getResourceAsStream("config")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if(props == null) return null;
        return props.get(key);
    }

    public static int getInt(String key) {
        if(props == null) return 0;
        return Integer.parseInt((String) props.get(key));
    }

    public static String getString(String key) {
        if(props == null) return null;
        return (String) props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(get("initTankCount"));
    }
}
