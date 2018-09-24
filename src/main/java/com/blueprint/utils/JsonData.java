package com.blueprint.utils;

import java.io.Serializable;

/**
 * @author xiaoyetongxue
 * @date 2018-09-24 16:03
 * @version 1.0.0
 * @description 统一返回
 */
public class JsonData implements Serializable {

    private static final long serialVersionUID = -6888655881168730149L;

    public static int SUCCESS = 0;
    public static int FAIL = -1;
    public static String SUCCESS_MSG = "操作成功！";
    public static String FAIL_MSG = "操作失败！";
    public static String ERROR_MSG = "系统异常，请稍后再试！";
    private int code;
    private String msg;
    private Object data;

    private JsonData() {
        this.code = SUCCESS;
        this.msg = SUCCESS_MSG;
        this.data = new Object();
    }

    public JsonData(int code, Object data, String msg) {
        this.code = SUCCESS;
        this.msg = SUCCESS_MSG;
        this.data = new Object();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonData buildSuccess() {
        return new JsonData(SUCCESS, (Object)null, SUCCESS_MSG);
    }

    public static JsonData success() {
        return buildSuccess();
    }

    public static JsonData buildSuccess(Object data) {
        return new JsonData(SUCCESS, data, SUCCESS_MSG);
    }

    public static JsonData success(Object data) {
        return buildSuccess(data);
    }

    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(SUCCESS, data, msg);
    }

    public static JsonData success(Object data, String msg) {
        return buildSuccess(data, msg);
    }

    public static JsonData buildError(String msg) {
        return new JsonData(FAIL, (Object)null, msg);
    }

    public static JsonData error(String msg) {
        return buildError(msg);
    }

    public static JsonData buildError(String msg, int code) {
        return new JsonData(code, (Object)null, msg);
    }

    public static JsonData error(String msg, int code) {
        return buildError(msg, code);
    }

    public static JsonData bulidError(Throwable throwable) {
        return new JsonData(FAIL, (Object)null, throwable.getMessage());
    }

    public static JsonData error(Throwable throwable) {
        return bulidError(throwable);
    }

    public static JsonData bulidError(Exception e) {
        return new JsonData(FAIL, (Object)null, e.getMessage());
    }

    public static JsonData error(Exception e) {
        return bulidError(e);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "JsonData{code=" + this.code + ", msg='" + this.msg + '\'' + ", data=" + this.data + '}';
    }

}
