package com.slj.pg.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
@Aspect
@Component
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.slj.pg.controller.*.*(..))")
    public void webLog() {

    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL:{},请求开始", request.getRequestURL());
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        logger.info("URL:{},Cost Time : {}ms", request.getRequestURL(), (System.currentTimeMillis() - startTime));
        return result;
    }

}