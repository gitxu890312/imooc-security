package com.imooc.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    //应用id
    private String appId;
    //qq号的唯一id
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;
        
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);
        //通过token获取openid
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        //通过appid和openid获取用户信息
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);

        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }

}
