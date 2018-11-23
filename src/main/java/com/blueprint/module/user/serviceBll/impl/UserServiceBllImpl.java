package com.blueprint.module.user.serviceBll.impl;

import com.blueprint.common.JsonData;
import com.blueprint.module.user.api.dto.UserLoginReqDto;
import com.blueprint.module.user.serviceBll.UserServiceBll;
import com.blueprint.module.user.serviceDal.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceBll")
public class UserServiceBllImpl implements UserServiceBll {

    @Autowired
    private UserService userService;

    @Override
    public JsonData login(UserLoginReqDto userLoginReqDto) {
        return JsonData.buildSuccess();
    }
}
