package com.blueprint;

import com.blueprint.module.user.api.dto.UserLoginReqDto;
import com.blueprint.module.user.serviceBll.UserServiceBll;
import com.blueprint.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xiaoyetongxue
 * @date 2018-09-24 16:01
 * @version 1.0.0
 * @description 用户相关控制器
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceBll userServiceBll;

    @PostMapping("/login")
    public JsonData login(@RequestBody @Valid UserLoginReqDto userLoginReqDto){
        log.info("UserController.login()");
        return userServiceBll.login(userLoginReqDto);
    }


}
