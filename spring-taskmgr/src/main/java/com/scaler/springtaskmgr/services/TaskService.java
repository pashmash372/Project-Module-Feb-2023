package com.scaler.springtaskmgr.services;

import com.scaler.springtaskmgr.entities.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {
    private final List<Task> taskList;
    private final AtomicInteger taskId = new AtomicInteger(0);

    public TaskService() {
        taskList = new ArrayList<>();
        taskList.add(new Task(taskId.incrementAndGet(), "Task 1", "Description 1", "2021-01-01"));
        taskList.add(new Task(taskId.incrementAndGet(), "Task 2", "Description 2", "2021-01-01"));
        taskList.add(new Task(taskId.incrementAndGet(), "Task 3", "Description 3", "2021-01-01"));
    }


    public List<Task> getTasks() {
        return taskList;
    }

    public Task createTask(String title, String description, String dueDate ) {
        // TODO: ensure date is not before today
        var task = new Task(taskId.incrementAndGet(), title, description, dueDate);
        taskList.add(task);
        return task;
    }

    public Task getTaskById(Integer id) {
        return taskList.stream().filter(task -> task.getId().equals(id)).findFirst().orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask(Integer id, String title, String description, String dueDate) {
        var task = getTaskById(id);
        if (title != null) task.setTitle(title);
        if (description != null) task.setDescription(description);
        if (dueDate != null) task.setDueDate(dueDate);
        return task;
    }

    public Task deleteTask(Integer id) {
        var task = getTaskById(id);
        taskList.remove(task);
        return task;
    }

    public static class TaskNotFoundException extends IllegalArgumentException {
        public TaskNotFoundException(Integer id) {
            super("Task with id " + id + " not found");
        }
    }

}
