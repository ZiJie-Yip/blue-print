package com.blueprint.interceptor.annotations;

import java.lang.annotation.*;

/**
 * @author xiaoyetongxue
 * @date 2018-09-24 16:19
 * @version 1.0.0
 * @description 搭配拦截器使用 免登陆访问
 */
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotLogin {
    boolean validate() default true;
}
