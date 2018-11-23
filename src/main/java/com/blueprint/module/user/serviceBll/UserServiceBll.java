package com.blueprint.module.user.serviceBll;

import com.blueprint.common.JsonData;
import com.blueprint.module.user.api.dto.UserLoginReqDto;

public interface UserServiceBll {

    JsonData login(UserLoginReqDto userLoginReqDto);

}
