package com.imooc.security.core.validate.code.sender;

import com.imooc.security.core.validate.code.bean.ValidateCode;

public interface SmsCodeSender {

    public void sender(String mobile,ValidateCode code);
}
