package com.blueprint.module.user.serviceDal;

import com.blueprint.module.user.serviceDal.pojo.User;

public interface UserService {

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
