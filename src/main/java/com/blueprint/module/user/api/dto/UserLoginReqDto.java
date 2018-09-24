package com.blueprint.module.user.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xiaoyetongxue
 * @data 2018-09-24 16:23
 * @version 1.0.0
 * @description 用户登陆请求dto
 **/
@Data
public class UserLoginReqDto {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
