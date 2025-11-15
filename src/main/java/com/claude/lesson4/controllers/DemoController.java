package com.claude.lesson4.controllers;

import com.claude.lesson4.aspects.AuditAspect;
import com.claude.lesson4.models.TaskStatus;
import com.claude.lesson4.services.TaskService;
import com.claude.lesson4.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class DemoController {
    private TaskService taskService;
    private UserService userService;
    private AuditAspect auditAspect;

    public DemoController(TaskService taskService, UserService userService, AuditAspect auditAspect) {
        this.taskService = taskService;
        this.userService = userService;
        this.auditAspect = auditAspect;
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
        taskService.assignTask(1L, 2L);
    }

    public void demo4() {
        printSection("Security - negative scenario");
        try {
            taskService.deleteTask(1L);
        } catch (SecurityException e) {
            throw new RuntimeException("Security exception");
        }
    }

    public void demo5() {
        printSection("Exception handling");
        try {
            taskService.updateTaskStatus(999L, TaskStatus.DONE);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void demo6() {
        printSection("Audit");
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
