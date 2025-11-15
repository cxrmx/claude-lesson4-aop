package com.claude.lesson4.aspects;

import com.claude.lesson4.annotations.Secured;
import com.claude.lesson4.models.UserRole;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Aspect
@Component
public class SecurityAspect {
    private UserRole userRole = UserRole.USER;

    @Pointcut("within(com.claude.lesson4.annotations.Secured)")
    public void pointcut() {}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        Secured annotation = joinPoint.getSignature().getClass().getAnnotation(Secured.class);
        UserRole role = annotation.role();
        System.out.println("[SECURITY] Checking access for method: "
                + joinPoint.getSignature().getName());
        System.out.println("Required role: " + role);
        System.out.println("Current user role: " + userRole);
        if (role != userRole) {
            System.out.println("❌ ACCESS DENIED!");
            throw new SecurityException("Access denied. Required role: " + role);
        } else {
            System.out.println("✅ ACCESS GRANTED");
        }
    }
}
