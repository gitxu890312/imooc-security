package com.imooc.security.browser;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.security.browser.support.SimpleResponse;
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
 * @since：2018年7月28日
 * @version v1.0
 * 当需要身份认证时跳转到这里
 * g根据请求的类型返回不同的数据
 */
@RestController
public class BrowerSecurityController {

    private Logger log = LoggerFactory.getLogger(BrowerSecurityController.class);
    //获取引发跳转的请求
    private RequestCache requestCache = new HttpSessionRequestCache();
    
    private RedirectStrategy redirectStratery = new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityProperties;
   
    /**
     * 当需要身份认证时跳转到这里
     * @throws IOException 
     */
    @RequestMapping("/authentication/require")
    public SimpleResponse requireAuthenticatoin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取引发跳转的请求
        SavedRequest saveReq = requestCache.getRequest(request, response);
        if(saveReq!=null) {
            String redirectUrl = saveReq.getRedirectUrl();
            log.info("请求地址为："+redirectUrl);
            if(StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                redirectStratery.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }

        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页面");
    }
}
