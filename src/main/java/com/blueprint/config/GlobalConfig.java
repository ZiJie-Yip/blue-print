package com.blueprint.config;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author xiaoyetongxue
 * @data 2018-09-24 16:13
 * @version 1.0.0
 * @description 全局配置
 **/
@Configuration
@Slf4j
public class GlobalConfig {

    /**
     * mybatis PageHelper
     **/
    @Bean
    public PageHelper pageHelper() {
        log.debug("MyBatisConfiguration.pageHelper()");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "false");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
