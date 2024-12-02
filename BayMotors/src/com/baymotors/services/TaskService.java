package com.baymotors.services;


import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.baymotors.dao.TaskDao;
import com.baymotors.models.Task;
import com.baymotors.constants.Priority;
import com.baymotors.constants.Status;


public class TaskService {
	/**
     * Fetch all tasks.
     *
     * @return List of tasks.
     * @throws Exception If fetching tasks fails.
     */
    public static List<Task> getTasks() throws Exception {
        try {
            return TaskDao.getTasks();
        } catch (Exception e) {
            System.err.println("Error while fetching tasks: " + e.getMessage());
            throw new Exception("Unable to fetch tasks.", e);
        }
    }
	
    /**
     * Add a new task.
     *
     * @param task The task to add.
     * @throws Exception If adding the task fails.
     */
    public static void addTask(Task task) throws Exception {
        try {
            TaskDao.addTask(task);
        } catch (Exception e) {
            System.err.println("Error while adding task: " + e.getMessage());
            throw new Exception("Unable to add task.", e);
        }
    }
	
    /**
     * Fetch tasks assigned to a specific mechanic by mechanic ID, sorted by priority.
     *
     * @param mechanicId The ID of the mechanic.
     * @return List of tasks assigned to the mechanic, sorted by priority.
     * @throws Exception If fetching or sorting tasks fails.
     */
    public static List<Task> getTasksByMechanic(int mechanicId) throws Exception {
        try {
            return TaskDao.getTasks().stream()
                    .filter(task -> task.getMechanicId() == mechanicId)
                    .filter(task -> Status.PENDING.equals(task.getStatus()) || Status.IN_PROGRESS.equals(task.getStatus()))
                    .sorted(Comparator.comparing(TaskService::priorityOrder))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error while fetching tasks for mechanic ID " + mechanicId + ": " + e.getMessage());
            throw new Exception("Unable to fetch tasks for mechanic ID " + mechanicId, e);
        }
    }

    /**
     * Helper method to define the priority order for sorting.
     *
     * @param task The task object.
     * @return An integer value representing the priority order.
     */
    private static int priorityOrder(Task task) {
        switch (task.getPriority()) {
            case Priority.HIGH:
                return 1;
            case Priority.MEDIUM:
                return 2;
            case Priority.LOW:
                return 3;
            default:
                return Integer.MAX_VALUE; // For tasks with undefined priority
        }
    }
    
    /**
     * Check if a task ID is valid.
     *
     * @param taskId The ID of the task.
     * @return true if the task ID is valid, false otherwise.
     * @throws Exception If validating task ID fails.
     */
    public static boolean isValidTaskId(int taskId) throws Exception {
        try {
            return TaskDao.getTasks().stream()
                    .anyMatch(task -> task.getId() == taskId);
        } catch (Exception e) {
            System.err.println("Error while validating task ID " + taskId + ": " + e.getMessage());
            throw new Exception("Unable to validate task ID " + taskId, e);
        }
    }
    
    /**
     * Mark a task as completed.
     *
     * @param taskId The ID of the task to complete.
     * @return true if the task was successfully marked as completed, false otherwise.
     * @throws Exception If marking the task as completed fails.
     */
    public static boolean endTask(int taskId) throws Exception {
        try {
            return TaskDao.markTaskAsCompleted(taskId);
        } catch (Exception e) {
            System.err.println("Error while marking task ID " + taskId + " as completed: " + e.getMessage());
            throw new Exception("Unable to mark task ID " + taskId + " as completed.", e);
        }
    }
}
