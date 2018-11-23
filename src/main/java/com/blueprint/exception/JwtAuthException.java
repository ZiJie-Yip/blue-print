package com.blueprint.exception;

/**
 * @Author: ZiJie.Yip
 * @Description:Jwt校验异常类
 * @date: 2018/11/23 16:46
 */
public class JwtAuthException extends SysException {

    private final static String AUTH_ERROR_MESSAGE = "Jwt auth fail";

    public JwtAuthException(Exception e){
        super(e);
    }

    public JwtAuthException(){
        this(AUTH_ERROR_MESSAGE);
    }

    public JwtAuthException(String message){
        super(message);
    }

    public JwtAuthException(String message, Exception e){
        super(message,e);
    }


}
