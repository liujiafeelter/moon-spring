package com.moon.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogAopAspect2 {

    @Pointcut("execution(* com.moon..*.*(..))")
    public void pointCut(){};

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

}
