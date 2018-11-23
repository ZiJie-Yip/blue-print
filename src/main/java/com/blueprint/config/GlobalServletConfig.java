package com.blueprint.config;

import com.blueprint.BlueprintApplication;
import com.blueprint.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/11/23 18:08
 */
@Configuration
@ComponentScan(basePackageClasses = BlueprintApplication.class, useDefaultFilters = true)
public class GlobalServletConfig implements WebMvcConfigurer{

    private static final String FAVICON_URL = "/favicon.ico";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns(FAVICON_URL);
    }

    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }


}
