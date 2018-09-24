package com.blueprint.module.user.serviceDal.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userId;

    private String username;

    private String password;

    private String phone;

    private Byte delFlag;

    private Date createTime;
}