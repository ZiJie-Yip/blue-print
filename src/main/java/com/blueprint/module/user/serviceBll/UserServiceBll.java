package com.blueprint.module.user.serviceBll;

import com.blueprint.module.user.api.dto.UserLoginReqDto;
import com.blueprint.utils.JsonData;

public interface UserServiceBll {

    JsonData login(UserLoginReqDto userLoginReqDto);

}
