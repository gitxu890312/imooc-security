package com.imooc.security.core.validate.code.bean;

import java.time.LocalDateTime;
/**
 * 验证码
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
public class ValidateCode {
    private String code;
    
    private LocalDateTime expireTime;
    public ValidateCode( String code, int expireSeconds) {
        super();
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireSeconds);
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
    
    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
