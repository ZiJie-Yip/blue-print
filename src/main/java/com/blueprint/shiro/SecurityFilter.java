package com.blueprint.shiro;

import com.blueprint.common.JsonData;
import com.blueprint.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ZiJie.Yip
 * @Description:系统安全过滤器
 * @date: 2018/11/23 13:38
 */
@Slf4j
public abstract class SecurityFilter extends BasicHttpAuthenticationFilter {

    /**
     * 添加跨域支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        if(log.isTraceEnabled()){
            String msg = "Enabled cross - in thread [" + Thread.currentThread().getName() +"]" ;
            log.trace(msg);
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*,Content-Type,token,wxid");
        if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 判断用户是否想要登录
     * 过滤器必须实现该方法
     * @param request
     * @param response
     * @return
     */
    @Override
    protected abstract boolean isLoginAttempt(ServletRequest request, ServletResponse response);

    /**
     * 执行登录方法，过滤器必须实现该方法
     * @param request
     * @param response
     * @return
     */
    @Override
    protected abstract boolean executeLogin(ServletRequest request, ServletResponse response);

    /**
     * 是否允许进入系统，过滤器比逊实现该方法
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected abstract boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue);

    public void writeResponse(HttpServletResponse response, int code, String msg, Object data){
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        JsonData obj = new JsonData(code, data, msg);
        try{
            response.getWriter().write(JSONUtil.toString(obj));
        }catch (Exception e){
            log.warn("response write error" + e.getMessage(),e);
        }
    }

}
