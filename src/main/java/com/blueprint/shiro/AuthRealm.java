package com.blueprint.shiro;

import com.blueprint.common.LoginUser;
import com.blueprint.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

/**
 * @Author: ZiJie.Yip
 * @Description:Shiro获取安全数据
 * @date: 2018/11/23 11:27
 */
@Slf4j
@Service
public class AuthRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "authRealm";
    }

    /**
     * 自定义token必须重写supports方法
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        if(token == null){
            throw new AuthenticationException("token invalid");
        }
        String username = JwtUtils.getAppUsername(token);
        return new SimpleAuthenticationInfo(packLoginUser(username), token, getName());
    }

    private LoginUser packLoginUser(String username){
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(1);
        loginUser.setUsername(username);
        return loginUser;
    }


}
