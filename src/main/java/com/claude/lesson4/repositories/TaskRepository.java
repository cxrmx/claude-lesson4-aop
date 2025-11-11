package com.claude.lesson4.repositories;

import com.claude.lesson4.models.Task;
import com.claude.lesson4.models.TaskStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepository {
    private Map<Long, Task> tasks = new HashMap<>();
    Long id = 0L;

    public TaskRepository() {
        System.out.println("[IoC] Bean TaskRepository created");
        this.initializeTasks();
    }

    @PostConstruct
    void postConstruct() {
        System.out.println("[TaskRepository] Initialized tasks: " + tasks.size());
    }

    private void initializeTasks() {
        Map<Long, Task> newTasks = new HashMap<>();
        Task task1 = new Task(null, "Brush teeth", "Brushing teeth at 8 o'clock", TaskStatus.TODO, 2L);
        Task task2 = new Task(null, "Morning train", "Train at 10 o'clock", TaskStatus.IN_PROGRESS, 1L);
        Task task3 = new Task(null, "Learn Java", "Learning Java for 2 hours", TaskStatus.TODO, 1L);
        Task task4 = new Task(null, "Meditate", "Do meditation for 10 minutes", TaskStatus.CANCELLED, 3L);
        Task task5 = new Task(null, "Stretch", "Do stretching for 20 minutes", TaskStatus.DONE, 2L);
        this.save(task1);
        this.save(task2);
        this.save(task3);
        this.save(task4);
        this.save(task5);
    }

    public void save(Task task) {
        if (task == null) {
            System.out.println("[TaskRepository] Cannot save task. Task is null");
            return;
        }
        if (task.getId() == null) {
            task = new Task(id++, task.getTitle(), task.getDescription(), task.getStatus(), task.getAssignedUserId());
        }
        tasks.put(task.getId(), task);
        System.out.println("[TaskRepository] Task was successfully saved");
    }

    public Task findById(Long id) {
        Task task = tasks.get(id);
        if (task == null) {
            System.out.println("[TaskRepository] Cannot find task by id. Task with id " + id + " doesn't exist in repository");
            return null;
        }
        System.out.println("[TaskRepository] Task was found by id " + id + ". Returning task");
        return task;
    }

    public List<Task> findAll() {
        if (tasks.isEmpty()) {
            System.out.println("[TaskRepository] Returning empty list of tasks. Repository is empty");
            return List.of();
        }
        System.out.println("[TaskRepository] Returning list of tasks");
        return new ArrayList<>(tasks.values());
    }

    public List<Task> findByStatus(TaskStatus status) {
        if (status == null) {
            System.out.println("[TaskRepository] Cannot return list of tasks. Status is null. Returning empty list");
            return List.of();
        }
        List<Task> list = new ArrayList<>(tasks.values().stream().filter(t->t.getStatus()==status).toList());
        if (list.isEmpty()) {
            System.out.println("[TaskRepository] Found 0 tasks with status " + status + ". Returning empty list");
            return list;
        }
        System.out.println("[TaskRepository] Returning list of tasks with status " + status);
        return list;
    }

    public boolean deleteById(Long id) {
        Task task = tasks.get(id);
        if (task == null) {
            System.out.println("[TaskRepository] Task with id " + id + " was not found. Cannot delete task");
            return false;
        }
        System.out.println("[TaskRepository] Task with id " + id + " was found. Deleting task");
        tasks.remove(id);
        return true;
    }
}
