package com.claude.lesson4.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class PerfomanceAspect {
    @Pointcut("@annotation(com.claude.lesson4.annotations.Timed)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException("Something went wrong with proceeding method "
                    + proceedingJoinPoint.getSignature().getName()
                    + " in PerfomanceAspect");
        }
        long endTime = System.currentTimeMillis();
        long proceedingTime = endTime - startTime;
        System.out.println("[PERFORMANCE] Method "
                + proceedingJoinPoint.getSignature().getName()
                + " took " + proceedingTime
                + " ms");
        if (proceedingTime > 250L) {
            System.out.println("⚠️Slow method detected");
        }
        return result;
    }
}
