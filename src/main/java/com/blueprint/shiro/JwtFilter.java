package com.blueprint.shiro;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZiJie.Yip
 * @Description:Shiro JWT权限过滤器
 * @date: 2018/11/23 11:28
 */
@Slf4j
public class JwtFilter extends SecurityFilter {


    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");

        JwtToken jwtToken = new JwtToken(token);
        //提交给realm进行登入
        getSubject(request,response).login(jwtToken);
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("JwtFilter login is access allowed");
        if(isLoginAttempt(request,response)){
            try{
                executeLogin(request,response);
            }catch (Exception e){
                //TODO
            }
        }
        return true;
    }
}
