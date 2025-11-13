package com.claude.lesson4.models;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Long assignedUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(Long id, String title, String description, TaskStatus status, Long assignedUserId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedUserId = assignedUserId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
        setUpdatedAt(LocalDateTime.now());
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(Long userId) {
        this.assignedUserId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", assignedUserId=" + assignedUserId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
