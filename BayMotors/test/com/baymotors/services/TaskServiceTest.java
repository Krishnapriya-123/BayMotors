package com.baymotors.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.baymotors.constants.Priority;
import com.baymotors.constants.Status;
import com.baymotors.dao.TaskDao;
import com.baymotors.models.Task;

class TaskServiceTest {

    @BeforeEach
    void setUp() {
        // Reset in-memory data before each test
        TaskDao.resetTasks(Arrays.asList(
                new Task(1, 1, 2, "Oil Change", Status.PENDING, Priority.HIGH, null, 0, null),
                new Task(2, 1, 2, "Brake Replacement", Status.IN_PROGRESS, Priority.MEDIUM, null, 0, null),
                new Task(3, 2, 3, "Battery Replacement", Status.COMPLETED, Priority.LOW, new Date(), 8, "Good work")
        ));
    }

    @Test
    void testGetTasks() throws Exception {
        // Test fetching all tasks
        List<Task> tasks = TaskService.getTasks();
        assertEquals(3, tasks.size());
    }

    @Test
    void testAddTask() throws Exception {
        // Test adding a new task
        Task newTask = new Task(4, 2, 3, "Tire Replacement", Status.PENDING, Priority.HIGH, null, 0, null);

        TaskService.addTask(newTask);

        List<Task> tasks = TaskService.getTasks();
        assertEquals(4, tasks.size());
        assertEquals("Tire Replacement", tasks.get(3).getDescription());
    }

    @Test
    void testGetTasksByMechanic() throws Exception {
        // Test fetching tasks assigned to a specific mechanic
        List<Task> tasksForMechanic = TaskService.getTasksByMechanic(2); // Mechanic ID 2
        assertEquals(2, tasksForMechanic.size()); // Only pending and in-progress tasks are fetched
        assertEquals("Oil Change", tasksForMechanic.get(0).getDescription()); // Sorted by priority
    }

    @Test
    void testIsValidTaskId() throws Exception {
        // Test validating a valid task ID
        assertTrue(TaskService.isValidTaskId(1)); // Task ID 1 exists

        // Test validating an invalid task ID
        assertFalse(TaskService.isValidTaskId(99)); // Non-existent Task ID
    }

    @Test
    void testEndTaskSuccess() throws Exception {
        // Test marking a task as completed
        boolean success = TaskService.endTask(1); // Mark Task ID 1 as completed
        assertTrue(success);

        // Verify the task's status
        Task task = TaskDao.getTasks().stream()
                .filter(t -> t.getId() == 1)
                .findFirst()
                .orElse(null);

        assertNotNull(task);
        assertEquals(Status.COMPLETED, task.getStatus());
    }

    @Test
    void testEndTaskFailure() {
        // Test marking a non-existent task as completed
        Exception exception = assertThrows(Exception.class, () -> {
            TaskService.endTask(99); // Non-existent Task ID
        });

        assertTrue(exception.getMessage().contains("Unable to mark task ID 99 as completed"));
    }
}
