package com.claude.lesson4.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ExceptionHandleAspect {
    @Pointcut("execution(* com.claude.lesson4.services.*.*(..))")
    public void pointcut() {}

    @AfterThrowing(
            pointcut = "pointcut()",
            throwing = "exception"
    )
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        System.out.println("❌ [ERROR HANDLER] Exception caught!");
        System.out.println("    Method: "
                + joinPoint.getTarget().getClass()
                + " "
                + joinPoint.getSignature().getName());
        System.out.println("    Exception: " + exception.getClass());
        System.out.println("    Message: " + exception.getMessage());
        System.out.println("    Arguments: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("    \uD83D\uDCE7 [Notification sent to admin]");
        System.out.println("    \uD83D\uDCCA [Error logged to monitoring system]");
    }
}
