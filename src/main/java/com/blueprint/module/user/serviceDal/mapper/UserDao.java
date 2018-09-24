package com.blueprint.module.user.serviceDal.mapper;

import com.blueprint.module.user.serviceDal.pojo.User;


public interface UserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}