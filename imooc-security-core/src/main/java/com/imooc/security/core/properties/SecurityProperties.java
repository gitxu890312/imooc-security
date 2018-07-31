package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("imooc.security")
public class SecurityProperties {

    //浏览器配置
    private BrowserProperties browser = new BrowserProperties();

    //验证码配置
    private ValidateCodeProperties code = new ValidateCodeProperties();
    
    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
    
    
    
}
