package com.imook.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component//面向切面的调用时间获取类
public class TimeAspect {

    @Around("execution(* com.imook.web.controller.UserController.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect");
        long  s = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        System.out.println("aspect time:"+(System.currentTimeMillis()-s));
        return proceed;
    }
}
