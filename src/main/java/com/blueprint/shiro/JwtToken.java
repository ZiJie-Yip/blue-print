package com.blueprint.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: ZiJie.Yip
 * @Description: shiro jwt token
 * @date: 2018/11/23 11:23
 */
public class JwtToken implements AuthenticationToken {

    /**
     * 身份秘钥
     */
    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
