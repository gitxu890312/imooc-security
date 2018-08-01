package com.imooc.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
/**
 * 短信登录验证提供者
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
public class SmsAuthenticationProvider implements AuthenticationProvider {
    
    private UserDetailsService userDetailService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        SmsAuthenticationToken token  = (SmsAuthenticationToken)authentication;
        UserDetails user = userDetailService.loadUserByUsername((String)token.getPrincipal());
        if(user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsAuthenticationToken result = new SmsAuthenticationToken((String)token.getPrincipal(), user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailService() {
        return userDetailService;
    }

    public void setUserDetailService(UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }
    
}
