package com.imooc.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
/**
 * 手机验证码登录配置
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 中化能源科技有限公司</p>
 *
 * @author xuming
 * @since：2018年8月1日
 * @version v1.0
 *
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler imoocAuthenticationFailerHandler;
    @Autowired
    private UserDetailsService userDetailService;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsAuthenticationFilter    smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationFailureHandler(imoocAuthenticationFailerHandler);
        smsAuthenticationFilter.setAuthenticationSuccessHandler(imoocAuthenticationSuccessHandler);
        
        SmsAuthenticationProvider provider = new SmsAuthenticationProvider();
        provider.setUserDetailService(userDetailService);
        
        http
            .authenticationProvider(provider)
            .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
