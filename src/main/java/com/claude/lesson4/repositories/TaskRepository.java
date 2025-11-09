package com.claude.lesson4.repositories;

import com.claude.lesson4.models.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TaskRepository {
    private Map<Long, Task> tasks = new HashMap<>();
    Long id = 0L;

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
        if (id == null) {
            System.out.println("[TaskRepository] Cannot find task by id. Id is null");
        }
    }
}
