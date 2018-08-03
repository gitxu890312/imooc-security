package com.imooc.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.filter.SmsValidateCodeFilter;
import com.imooc.security.core.validate.code.filter.ValidateCodeFilter;

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
    @Autowired
    private SmsValidateCodeFilter smsValidateCodeFilter;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .addFilterBefore(smsValidateCodeFilter, UsernamePasswordAuthenticationFilter.class)//短信验证码过滤器
        .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//图形验证码过滤器
            .formLogin()
           
    //        .loginPage("/imooc-signIn.html")//需要登录时跳转到指定页面
            .loginPage("/authentication/require")//需要认证时跳转到指定请求
            
            .loginProcessingUrl("/authentication/form")// 定义UsernamePasswordAuthenticationFilter处理的url
            .successHandler(imoocAuthenticationSuccessHandler)//登录成功处理器
            .failureHandler(imoocAuthenticationFailerHandler)//登录失败处理器
        .and() //记住我功能
            .rememberMe()
            .userDetailsService(userDetailsService)
            .tokenRepository(persistentTokenRepository())
            .tokenValiditySeconds(3600)
        .and()
            .authorizeRequests()
    //        .antMatchers("/imooc-signIn.html").permitAll()//登录页面权限设置
            .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(),"/code/*").permitAll()//登录页面权限设置
            
            .anyRequest().authenticated()
        
        .and()
            .csrf().disable()//关闭跨站请求伪造
            
            .apply(smsCodeAuthenticationSecurityConfig);
    }
    
    
    @Bean//记住我功能dao
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
        token.setDataSource(dataSource);
//        token.setCreateTableOnStartup(true);
        return token;
    }
}
