package com.moon.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

@Service
public class LogAopAspect {

    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("around start");
        Object result = null;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around end");
        return result;
    }


}
