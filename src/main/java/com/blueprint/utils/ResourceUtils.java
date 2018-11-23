package com.blueprint.utils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Author: ZiJie.Yip
 * @Description:资源文件工具类
 * @date: 2018/11/23 15:55
 */
public class ResourceUtils {

    private ResourceBundle resourceBundle;

    private ResourceUtils(String resource){
        resourceBundle = ResourceBundle.getBundle(resource);
    }


    public static ResourceUtils getResource(String resource){
        return new ResourceUtils(resource);
    }

    /**
     * 根据key取得value
     * @param key 键值
     * @param args value中参数序列，参数:{0},{1}...,{n}
     * @return
     */
    public String getValue(String key, Object... args) {
        String temp = resourceBundle.getString(key);
        return MessageFormat.format(temp, args);
    }

    /**
     * 获取所有资源的Map表示
     * @return 资源Map
     */
    public Map<String, String> getMap(){
        Map<String, String> map = new HashMap<>(16);
        for(String key : resourceBundle.keySet()){
            map.put(key, resourceBundle.getString(key));
        }
        return map;
    }
}
