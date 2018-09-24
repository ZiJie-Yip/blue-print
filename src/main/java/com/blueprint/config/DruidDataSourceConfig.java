package com.blueprint.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoyetongxue
 * @data 2018-09-24 16:11
 * @version 1.0.0
 * @description alibaba Druid连接池及SQL监控配置
**/
@Slf4j
@Configuration
public class DruidDataSourceConfig{

    private static final String DB_PREFIX = "spring.datasource";

    @ConfigurationProperties(prefix = DB_PREFIX)
    @Data
    class IDataSourceProperties {
       private String url;

       private String driverClassName;

       private String username;

       private String password;

       private String initialSize;

       private String minIdle;

       private String maxWait;

       private String maxActive;

       private String minEvictableIdleTimeMillis;

       @Bean
       @Primary
       public DataSource dataSource(){
           log.debug("create datasource");
           DruidDataSource datasource = new DruidDataSource();
           datasource.setUrl(url);
           datasource.setDriverClassName(driverClassName);
           datasource.setUsername(username);
           datasource.setPassword(password);
           datasource.setInitialSize(Integer.valueOf(initialSize));
           datasource.setMinIdle(Integer.valueOf(minIdle));
           datasource.setMaxWait(Long.valueOf(maxWait));
           datasource.setMaxActive(Integer.valueOf(maxActive));
           datasource.setMinEvictableIdleTimeMillis(
                   Long.valueOf(minEvictableIdleTimeMillis));
           try {
               datasource.setFilters("stat,wall");
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return datasource;
       }

    }


    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<String, String>();
         initParameters.put("loginUsername", "druid");// 用户名
        initParameters.put("loginPassword", "druid");// 密码
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        initParameters.put("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)
        // initParameters.put("deny", "192.168.20.38");// IP黑名单
        // (存在共同时，deny优先于allow)
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    // 按照BeanId来拦截配置 用来bean的监控
    @Bean(value = "druid-stat-interceptor")
    public DruidStatInterceptor DruidStatInterceptor() {
        DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
        return druidStatInterceptor;
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setProxyTargetClass(true);
        // 设置要监控的bean的id
        //beanNameAutoProxyCreator.setBeanNames("sysRoleMapper","loginController");
        beanNameAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");
        return beanNameAutoProxyCreator;
    }

}
