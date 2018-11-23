package com.blueprint.interceptor.annotation;

import java.lang.annotation.*;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/11/23 17:48
 */
@Documented
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotLogin {
    boolean validate() default true;
}
