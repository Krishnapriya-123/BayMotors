package com.baymotors.services;


import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.baymotors.dao.TaskDao;
import com.baymotors.models.Task;
import com.baymotors.constants.Priority;
import com.baymotors.constants.Status;


public class TaskService {
	public static List<Task> getTasks() {
		List<Task> tasks = TaskDao.getTasks();
		return tasks;
	}
	
	public static void addTask(Task task) {
        TaskDao.addTask(task);
    }
	
	/**
     * Fetch tasks assigned to a specific mechanic by mechanic ID, sorted by priority.
     *
     * @param mechanicId The ID of the mechanic.
     * @return List of tasks assigned to the mechanic, sorted by priority.
     */
    public static List<Task> getTasksByMechanic(int mechanicId) {
        return TaskDao.getTasks().stream()
                .filter(task -> task.getMechanicId() == mechanicId)
                .filter(task -> Status.PENDING.equals(task.getStatus()) || Status.IN_PROGRESS.equals(task.getStatus()))
                .sorted(Comparator.comparing(TaskService::priorityOrder))
                .collect(Collectors.toList());
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
    
    public static boolean isValidTaskId(int taskId) {
    	return TaskDao.getTasks().stream().anyMatch(task -> task.getId() == taskId);
    }
    
    public static boolean endTask(int taskId) {
    	return TaskDao.markTaskAsCompleted(taskId);
    }
}
