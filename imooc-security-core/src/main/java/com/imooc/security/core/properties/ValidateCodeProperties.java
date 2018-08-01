package com.imooc.security.core.properties;

public class ValidateCodeProperties {
    //图形验证码
    private ImageCodeProperties image = new ImageCodeProperties();
    //手机验证码
    private SmsCodeProperties sms = new SmsCodeProperties();
    
    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }
    
    
}
