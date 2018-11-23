package com.blueprint.config;

import com.blueprint.common.JsonData;
import com.blueprint.exception.JwtAuthException;
import com.blueprint.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZiJie.Yip
 * @Description:全局错误处理
 * @date: 2018/11/23 16:56
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = JwtAuthException.class)
    @ResponseBody
    public JsonData handlerJwtAuthException(HttpServletRequest request, JwtAuthException e){
        String exception = e.getMsgDes();
        log.error("[JwtAuthException] --> [RequestUrl:{}] {}", getRequestUrl(request),exception);
        return JsonData.buildError(exception);
    }

    @ExceptionHandler(value = SysException.class)
    @ResponseBody
    public JsonData handlerSysException(HttpServletRequest request, SysException e){
        String exception = e.getMsgDes();
        log.error("[SysException] --> [RequestUrl:{}] {}", getRequestUrl(request), exception);
        return JsonData.buildError(exception);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData globalException(HttpServletRequest request, Exception e){
        String exception = ExceptionUtils.getStackTrace(e);
        log.error("[Exception] --> [RequestUrl:{}] {}", getRequestUrl(request), exception);
        return JsonData.buildError(ExceptionUtils.getStackTrace(e));
    }

    private String getRequestUrl(HttpServletRequest request){
        return request.getRequestURL().toString();
    }



}
