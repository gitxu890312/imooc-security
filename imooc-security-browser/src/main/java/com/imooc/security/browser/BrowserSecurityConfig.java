package com.imooc.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private SecurityProperties securityProperties;
    
    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler imoocAuthenticationFailerHandler;
    @Autowired//图形验证码过滤器
    private ValidateCodeFilter validateCodeFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin()
       
//        .loginPage("/imooc-signIn.html")//需要登录时跳转到指定页面
        .loginPage("/authentication/require")//需要认证时跳转到指定请求
        
        .loginProcessingUrl("/authentication/form")// 定义UsernamePasswordAuthenticationFilter出来的url
        .successHandler(imoocAuthenticationSuccessHandler)//登录成功处理器
        .failureHandler(imoocAuthenticationFailerHandler)//登录失败处理器
        .and()
        .authorizeRequests()
//        .antMatchers("/imooc-signIn.html").permitAll()//登录页面权限设置
        .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(),"/code/image").permitAll()//登录页面权限设置
        
        .anyRequest().authenticated()
        .and().csrf().disable();//关闭跨站请求伪造
    }
    
}
