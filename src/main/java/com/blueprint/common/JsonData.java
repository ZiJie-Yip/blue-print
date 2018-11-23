package com.blueprint.common;

import java.io.Serializable;

/**
 * @Author: ZiJie.Yip
 * @Description:Json返回数据模型
 * @date: 2018/11/23 14:45
 */
public class JsonData implements Serializable {

    private static final long serialVersionUID = 3124966182067804251L;

    public static int SUCCESS = 0;

    public static int FAIL = -1;

    public static String SUCCESS_MSG = "操作成功！";

    public static String FAIL_MSG = "操作失败！";

    public static String ERROR_MSG = "系统异常，请稍后再试！";

    /***
     * 状态码 0 成功 -1 失败 -2 异常 返回时必填
     */
    private int code = JsonData.SUCCESS;
    /**
     * 返回信息描述，根据业务需求返回业务描述信息
     */
    private String msg = JsonData.SUCCESS_MSG;

    /**
     * 返回数据详细参数，不需要返回参数时，该字段返回空刮括号 {}
     */
    private Object data = new Object();

    /**
     * 私有化构造方法
     */
    private JsonData() {
    }

    public JsonData(int code, Object data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     *
     * @return
     */
    public static JsonData buildSuccess() {
        return new JsonData(JsonData.SUCCESS, null, JsonData.SUCCESS_MSG);
    }

    public static JsonData success() {
        return buildSuccess();
    }

    /**
     * 成功
     *
     * @param data
     *            数据对象
     * @return
     */
    public static JsonData buildSuccess(Object data) {
        return new JsonData(JsonData.SUCCESS, data, JsonData.SUCCESS_MSG);
    }

    public static JsonData success(Object data) {
        return buildSuccess(data);
    }

    /**
     * /成功，传入数据,及描述信息
     *
     * @param data
     *            数据对象
     * @param msg
     *            描述信息
     * @return
     */
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(JsonData.SUCCESS, data, msg);
    }

    public static JsonData success(Object data, String msg) {
        return buildSuccess(data, msg);
    }

    /**
     * 失败
     *
     * @param msg
     *            失败信息
     * @return
     */
    public static JsonData buildError(String msg) {
        return new JsonData(JsonData.FAIL, null, msg);
    }

    public static JsonData error(String msg) {
        return buildError(msg);
    }

    /**
     * 失败，传入描述信息,状态码，-2表示公众号无相关权限
     *
     * @param msg
     *            失败信息
     * @param code
     *            状态码
     * @return
     */
    public static JsonData buildError(String msg, int code) {
        return new JsonData(code, null, msg);
    }

    public static JsonData error(String msg, int code) {
        return buildError(msg, code);

    }

    /**
     * 失败，程序错误
     *
     * @param throwable
     *            错误对象
     * @return
     */
    public static JsonData bulidError(Throwable throwable) {
        return new JsonData(JsonData.FAIL, null, throwable.getMessage());
    }

    public static JsonData error(Throwable throwable) {
        return bulidError(throwable);
    }

    public static JsonData bulidError(Exception e) {
        return new JsonData(JsonData.FAIL, null, e.getMessage());
    }

    public static JsonData error(Exception e) {
        return bulidError(e);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonData{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }
}
