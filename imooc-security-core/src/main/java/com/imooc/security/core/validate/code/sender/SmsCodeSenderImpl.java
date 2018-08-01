package com.imooc.security.core.validate.code.sender;

import org.springframework.stereotype.Component;

import com.imooc.security.core.validate.code.ValidateCode;
@Component
public class SmsCodeSenderImpl implements SmsCodeSender {

    @Override
    public void sender(String mobile, ValidateCode code) {
        System.out.println("向手机："+mobile+",发送验证码："+code.getCode());
    }

}
