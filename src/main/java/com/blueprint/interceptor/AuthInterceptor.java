package com.blueprint.interceptor;

import com.blueprint.interceptor.annotation.NotLogin;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ZiJie.Yip
 * @Description:权限验证拦截器
 * @date: 2018/11/23 17:45
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!validateLogin(handler)){
            //Shiro默认不拦截未登录用户，使用拦截器进行拦截
            if(!SecurityUtils.getSubject().isAuthenticated()){
                throw new AuthenticationException("not permission access");
            }
        }
        return super.preHandle(request, response, handler);
    }

    public boolean validateLogin(Object handler){
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            HandlerMethod method = (HandlerMethod) handler;
            NotLogin notLogin = method.getMethodAnnotation(NotLogin.class);
            if(notLogin != null && notLogin.validate()){
                return true;
            }
            Class<?> clazz = method.getMethod().getDeclaringClass();
            if(clazz != null){
                notLogin = clazz.getAnnotation(NotLogin.class);
                if(notLogin != null && notLogin.validate()){
                    return true;
                }
            }
        }
        return false;
    }
}
