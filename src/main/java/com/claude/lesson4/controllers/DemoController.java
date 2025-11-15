package com.claude.lesson4.controllers;

import com.claude.lesson4.aspects.AuditAspect;
import com.claude.lesson4.aspects.SecurityAspect;
import com.claude.lesson4.models.TaskStatus;
import com.claude.lesson4.models.UserRole;
import com.claude.lesson4.services.TaskService;
import com.claude.lesson4.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class DemoController {
    private final TaskService taskService;
    private final UserService userService;
    private final AuditAspect auditAspect;
    private final SecurityAspect securityAspect;

    public DemoController(TaskService taskService, UserService userService, AuditAspect auditAspect, SecurityAspect securityAspect) {
        this.taskService = taskService;
        this.userService = userService;
        this.auditAspect = auditAspect;
        this.securityAspect = securityAspect;
    }

    public void demonstrateAOP() {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
        demo7();
    }

    public void demo1() {
        printSection("Logging");
        taskService.findAllTasks();

    }

    public void demo2() {
        printSection("Performance");
        try {
            taskService.getTasksByStatus(TaskStatus.TODO);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void demo3() {
        printSection("Security - positive scenario");
        securityAspect.setUserRole(UserRole.MANAGER);
        taskService.assignTask(1L, 2L);
    }

    public void demo4() {
        printSection("Security - negative scenario");
        securityAspect.setUserRole(UserRole.USER);
        try {
            taskService.deleteTask(1L);
        } catch (SecurityException e) {
            System.out.println("Caught SecurityException as expected: " + e.getMessage());;
        }
    }

    public void demo5() {
        printSection("Exception handling");
        try {
            taskService.updateTaskStatus(999L, TaskStatus.DONE);
        } catch (RuntimeException e) {
            System.out.println("Caught exception as expected: " + e.getMessage());
        }
    }

    public void demo6() {
        printSection("Audit");
        securityAspect.setUserRole(UserRole.MANAGER);
        taskService.createTask("Buy scissors", "Buy scissors in grocery", 1L);
        taskService.updateTaskStatus(2L, TaskStatus.IN_PROGRESS);
        taskService.assignTask(2L, 1L);
        auditAspect.displayAuditLog();
    }

    public void demo7() {
        printSection("Complex demonstration");
        taskService.createTask("Run marathon", "Run marathon description", 1L);
    }

    public void printSection(String title) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% " + title + " %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }
}
