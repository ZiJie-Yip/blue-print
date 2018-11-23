package com.blueprint.module.user.api;

import com.blueprint.common.JsonData;
import com.blueprint.interceptor.annotation.NotLogin;
import com.blueprint.module.user.api.dto.UserLoginReqDto;
import com.blueprint.module.user.serviceBll.UserServiceBll;
import com.blueprint.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @NotLogin
    @PostMapping("/login")
    public JsonData login(@RequestBody @Valid UserLoginReqDto userLoginReqDto){
        log.info("UserController.login()");
        String yezijie = JwtUtils.createToken("yezijie");
        return JsonData.buildSuccess(yezijie);
    }

    @GetMapping("/getUserInfo")
    public JsonData getUserInfo(){
        return JsonData.buildSuccess("goods login");
    }


}
