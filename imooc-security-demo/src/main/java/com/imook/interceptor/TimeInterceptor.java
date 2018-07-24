package com.imook.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * spring mvc拦截器
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 中化能源科技有限公司</p>
 *
 * @author xuming
 * @since：2018年7月22日
 * @version v1.0
 *
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
            //controller 执行之前调用 handler 存储的是执行此次请求的controller的信息
        System.out.println("prehandler");
        String clazzName = ((HandlerMethod)(handler)).getBean().getClass().getName();
        String methodName = ((HandlerMethod)(handler)).getMethod().getName();
        System.out.println(clazzName+"."+methodName);
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
        
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        //controller 抛出异常时不调用此函数
        //没有抛出异常才调用次函数
        System.out.println("postHandle");
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //无论如何都调用此函数
        System.out.println("afterCompletion");
        Long s = Long.parseLong(request.getAttribute("startTime").toString());
        System.out.println("耗时："+(System.currentTimeMillis()-s));
        System.out.println(ex);
    }

}
