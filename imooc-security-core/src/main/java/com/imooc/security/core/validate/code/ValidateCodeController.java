package com.imooc.security.core.validate.code;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.bean.ImageCode;
import com.imooc.security.core.validate.code.bean.ValidateCode;
import com.imooc.security.core.validate.code.generator.ImageCodeGenerator;
import com.imooc.security.core.validate.code.generator.SmsCodeGenerator;
import com.imooc.security.core.validate.code.sender.SmsCodeSender;

@RestController
public class ValidateCodeController {
    //图形验证码key
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    //短信验证码key
    public static final String SESSION_SMS_KEY = "SESSION_KEY_SMS_CODE";
    
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private ImageCodeGenerator imageCodeGenerator;
    @Autowired
    private SmsCodeGenerator smsCodeGenerator;
    
    @Autowired
    private SmsCodeSender smsCodeSender;
    //生成图形验证码
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = imageCodeGenerator.createCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }
    //生成短信验证码
    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        ValidateCode createCode = smsCodeGenerator.createCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_SMS_KEY, createCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsCodeSender.sender(mobile, createCode);
    }
}
