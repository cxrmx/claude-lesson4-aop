package com.claude.lesson4.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    @Before("@annotation(com.claude.lesson4.annotations.Loggable)")
    public void beforeExecution(JoinPoint joinPoint) {
        System.out.println("[LOG] Method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "@annotation(com.claude.lesson4.annotations.Loggable)",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[LOG] Method completed: " + joinPoint.getSignature().getName());
        System.out.println("Result: " + result);
    }

    @After("@annotation(com.claude.lesson4.annotations.Loggable)")
    public void after(JoinPoint joinPoint) {
        System.out.println("[LOG] Method finished: " + joinPoint.getSignature().getName());
    }
}
