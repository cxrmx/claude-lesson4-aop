package com.claude.lesson4.services;

import com.claude.lesson4.annotations.Auditable;
import com.claude.lesson4.annotations.Loggable;
import com.claude.lesson4.annotations.Secured;
import com.claude.lesson4.annotations.Timed;
import com.claude.lesson4.models.Task;
import com.claude.lesson4.models.TaskStatus;
import com.claude.lesson4.models.User;
import com.claude.lesson4.models.UserRole;
import com.claude.lesson4.repositories.TaskRepository;
import com.claude.lesson4.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        System.out.println("[IoC] Bean TaskService created");
        this.taskRepository = taskRepository;
        System.out.println("[TaskService] Dependency Task Repository was injected.");
        this.userRepository = userRepository;
        System.out.println("[UserService] Dependency User Repository was injected");
    }

    @Loggable
    @Auditable
    @Timed
    public Task createTask(String title, String description, Long userId) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Title is empty");
        }
        if (userRepository.findById(userId) == null) {
            throw new IllegalArgumentException("Cannot find user by id: " + userId);
        }
        Task task = new Task(null, title, description, TaskStatus.TODO, userId);
        taskRepository.save(task);
        System.out.println("[TaskService] Task with title " + title + " was created and saved");
        return task;
    }

    @Secured(role = UserRole.MANAGER)
    @Auditable
    public void assignTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId);
        User user = userRepository.findById(userId);
        if (task != null && user != null) {
            task.setAssignedUserId(userId);
            System.out.println("[TaskService] Task with id: " + taskId + " was assigned to user with id: " + userId);
            taskRepository.save(task);
            return;
        }
        throw new IllegalArgumentException("[TaskService] Task and/or User was invalid, cannot assign task");
    }

    @Loggable
    @Auditable
    public void updateTaskStatus(Long taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Cannot find task by id: " + taskId + ". Task is null");
        }
        task.setStatus(newStatus);
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(task);
        System.out.println("[TaskService] Task's status was updated");
    }

    @Secured(role = UserRole.ADMIN)
    @Auditable
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Cannot find task by id: " + taskId + ". Task is null");
        }
        taskRepository.deleteById(taskId);
        System.out.println("[TaskService] Task with id: " + taskId + " was deleted");
    }

    @Loggable
    public List<Task> findAllTasks() {
        System.out.println("[TaskService] Returning all tasks from repository");
        return taskRepository.findAll();
    }

    @Timed
    public List<Task> getTasksByStatus(TaskStatus taskStatus) throws InterruptedException {
        List<Task> tasksByStatus = taskRepository.findByStatus(taskStatus);
        Thread.sleep(300);
        return tasksByStatus;
    }
}
