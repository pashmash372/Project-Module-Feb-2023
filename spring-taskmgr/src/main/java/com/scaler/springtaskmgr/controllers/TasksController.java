package com.scaler.springtaskmgr.controllers;

import com.scaler.springtaskmgr.dtos.ErrorResponse;
import com.scaler.springtaskmgr.entities.Task;
import com.scaler.springtaskmgr.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TasksController {
    private final TaskService taskService;



    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * show all existing tasks
     * GET /tasks
     *
     * @return List of tasks
     */

    @GetMapping("/tasks")
    ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    /**
     * Create a new task
     * POST /tasks
     * Body:
     * <pre>
     *      {
     *          "title": "Task 4",
     *          "description": "Description 4",
     *          "dueDate": "2021-01-01"
     *      }
     *  </pre>
     *
     * @param task Task object sent by client
     * @return Task object created
     */

    @PostMapping("/tasks")
    ResponseEntity<Task> createTask(@RequestBody Task task) {
        var newTask = taskService.createTask(task.getTitle(), task.getDescription(), task.getDueDate());
        return ResponseEntity.created(URI.create("/tasks/" + newTask.getId())).body(newTask);
    }

    /**
     * Get a task by id
     *
     * @param id
     * @return Task object
     */

    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    /**
     * Delete a task by given id
     *
     * @param id Task id to delete
     * @return the deleted task
     */

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Task> deleteTask(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    /**
     * Updated a task by given id
     *
     * @param id   Task id to create
     * @param task Task object sent by client
     * @return the updated task
     */

    @PatchMapping("/tasks/{id}")
    ResponseEntity<Task> updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        var updatedTask = taskService.updateTask(id, task.getTitle(), task.getDescription(), task.getDueDate());
        return ResponseEntity.accepted().body(updatedTask);
    }


    @ExceptionHandler(TaskService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
