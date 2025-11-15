package com.claude.lesson4.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class AuditAspect {
    private List<String> auditLog = new ArrayList<>();

    @Pointcut("@annotation(com.claude.lesson4.annotations.Auditable)")
    public void pointcut() {};

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        LocalDateTime timestamp = LocalDateTime.now();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String log = timestamp + " - " + methodName + "(" + Arrays.toString(args) + ")";
        auditLog.add(log);
        System.out.println("[AUDIT] Action logged: " + methodName + " at " + timestamp);
    }

    public void displayAuditLog() {
        System.out.println("======================================================================");
        System.out.println("\uD83D\uDCCA AUDIT LOG");
        System.out.println("======================================================================");
        for (String log : auditLog) {
            System.out.println(log);
            System.out.println("======================================================================");
        }
    }
}
