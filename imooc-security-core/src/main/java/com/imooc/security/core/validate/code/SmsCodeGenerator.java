package com.imooc.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imooc.security.core.properties.SecurityProperties;
/**
 * 
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 中化能源科技有限公司</p>
 *
 * @author xuming
 * @since：2018年8月1日
 * @version v1.0
 * 手机验证码生成器
 */
@Component
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;
    
    @Override
    public ValidateCode createCode(HttpServletRequest request)  {
        String rands = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
      
        return new ValidateCode(rands, securityProperties.getCode().getSms().getExpireIn());
    }

}
