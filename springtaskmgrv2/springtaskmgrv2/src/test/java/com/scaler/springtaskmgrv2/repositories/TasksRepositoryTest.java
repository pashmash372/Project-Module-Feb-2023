package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class TasksRepositoryTest {

    @Autowired
    TasksRepository tasksRepository;

    @Test
    public void testCreateTask() {
        TaskEntity task = new TaskEntity();
        task.setTitle("Test Task");
        task.setCompleted(false);
        var savedTask = tasksRepository.save(task);
        assertNotNull(savedTask);
    }

    @Test
    public void readTasksWorks() {
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("Test Task 1");
        task1.setCompleted(false);
        TaskEntity task2 = new TaskEntity();
        task2.setTitle("Test Task 2");
        task2.setCompleted(false);
        tasksRepository.save(task1);
        tasksRepository.save(task2);
        var tasks = tasksRepository.findAll();
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }

    @Test
    public void findByCompleteWorks() {
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("Test Task 1");
        task1.setCompleted(false);
        TaskEntity task2 = new TaskEntity();
        task2.setTitle("Test Task 2");
        task2.setCompleted(false);
        tasksRepository.save(task1);
        tasksRepository.save(task2);

        tasksRepository.findAllByCompleted(true);
        var completedTasks = tasksRepository.findAllByCompleted(true);
        var incompleteTasks = tasksRepository.findAllByCompleted(false);

        assertEquals(0, completedTasks.size());
        assertEquals(2, incompleteTasks.size());

    }
}