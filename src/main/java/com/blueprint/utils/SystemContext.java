package com.blueprint.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoyetongxue
 * @data 2018-09-24 16:13
 * @version 1.0.0
 * @description 系统上下文对象 用于在当前线程中保存或存储对象
 */
@Slf4j
public class SystemContext implements Serializable {

    private static final long serialVersionUID = 3338699016721773130L;

    private static ThreadLocal<Map<String,Object>> resources = new ThreadLocal<>();

    private SystemContext(){

    }

    /**
     * 获取所有资源
     */
    public static Map getResources(){
        if(resources.get() == null){
            return Collections.emptyMap();
        }else{
            return new HashMap(resources.get());
        }
    }

    /**
     * 添加资源
     */
    public static void setResources(Map<String,Object> newResources){
        if(CollectionUtils.isEmpty(newResources)) {
            return;
        }
        ensureResourcesInitialized();
        resources.get().clear();
        resources.get().putAll(newResources);
    }

    /**
     * 根据key获取对应的资源
     * @param key
     * @return
     */
    private static Object getValue(String key){
        Map<String, Object> threadResources = resources.get();
        return threadResources != null ? threadResources.get(key) : null;
    }

    /**
     * 获取默认对象
     */
    private static void ensureResourcesInitialized(){
        if (resources.get() == null){
            resources.set(new HashMap<String, Object>());
        }
    }

    /**
     * 根据key获取对应的资源
     * @param key
     * @return
     */
    public static Object get(String key){
        if(log.isTraceEnabled()){
            String msg = "get() - in thread [ " + Thread.currentThread().getName() + " ]";
            log.trace(msg);
        }
        Object value = get(key);
        if(value != null && log.isTraceEnabled()){
            String msg = "Retrieved value of type [" + value.getClass().getName() + "] for key [" +
                    key + "] " + "bound to thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
        return value;
    }

    /**
     * 添加指定资源
     * @param key
     * @param value
     */
    public static void put(String key,Object value){
        if(key == null){
            throw new IllegalArgumentException("key can't not be null");
        }
        if(value == null){
            remove(key);
            return;
        }

        ensureResourcesInitialized();
        resources.get().put(key,value);

        if (log.isTraceEnabled()) {
            String msg = "Bound value of type [" + value.getClass().getName() + "] for key [" +
                    key + "] to thread " + "[" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
    }

    /**
     * 根据 key 移除资源
     * @param key
     * @return
     */
    public static Object remove(String key){
        Map<String, Object> perThreadResources = resources.get();
        Object value = perThreadResources != null ? perThreadResources.remove(key) : null;

        if ((value != null) && log.isTraceEnabled()) {
            String msg = "Removed value of type [" + value.getClass().getName() + "] for key [" +
                    key + "]" + "from thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
        return value;
    }

    /**
     * 移除所有资源
     */
    public static void remove() {
        resources.remove();
    }

}
