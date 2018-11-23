package com.blueprint.common;

import lombok.Data;

/**
 * @Author: ZiJie.Yip
 * @Description:登录用户实体
 * @date: 2018/11/23 15:22
 */
@Data
public class LoginUser {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;

}
