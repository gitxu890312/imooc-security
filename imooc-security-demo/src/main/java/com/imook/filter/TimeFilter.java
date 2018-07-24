package com.imook.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;
@Component //直接加此注解会过滤所有的url
public class TimeFilter implements Filter {

    @Override
    public void destroy() {
           System.out.println("filter destory");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        long s = System.currentTimeMillis();
        System.out.println("dofilter start");
        chain.doFilter(req, rsp);
        long end = System.currentTimeMillis()-s;
        System.out.println("请求耗时："+end);
        System.out.println("dofilter end");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("filter init");
    }

}
