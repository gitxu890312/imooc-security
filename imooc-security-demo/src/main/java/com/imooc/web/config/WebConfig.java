package com.imooc.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.filter.TimeFilter;
import com.imooc.interceptor.TimeInterceptor;

@Configurable
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private TimeInterceptor timeInterceptor;
    
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).addPathPatterns("/*");
        super.addInterceptors(registry);
    }
    
    @Bean//自定义过滤器
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        bean.setFilter(timeFilter);
        //定义要拦截的url
        bean.addUrlPatterns("/**");
        return bean;
    }
}
