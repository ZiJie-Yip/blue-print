package com.blueprint.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ZiJie.Yip
 * @Description:JSON工具类
 * @date: 2018/11/23 14:57
 */
@Slf4j
public class JSONUtil {

    private JSONUtil(){

    }

    /**
     * 对象转json
     * @param obj
     * @return
     */
    public static String toString(Object obj){
        return JSON.toJSONString(obj);
    }

    /**
     * json转对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }

    /**
     * json字符串转json对象
     * @param json
     * @return
     */
    public static JSONObject parseJson(String json){
        return JSON.parseObject(json);
    }



}
