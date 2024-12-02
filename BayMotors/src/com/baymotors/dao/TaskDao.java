package com.baymotors.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.baymotors.models.Task;
import com.baymotors.constants.Priority;
import com.baymotors.constants.Status;

public class TaskDao {
    private static final List<Task> tasks = new ArrayList<>();

    static {
        tasks.add(new Task(1, 1, 2, "Oil change for vehicle 1", 
                Status.PENDING, Priority.LOW, null, 0, null));
        tasks.add(new Task(2, 1, 2, "Engine repair for vehicle 1", 
                Status.IN_PROGRESS, Priority.HIGH, null, 0, null));
        tasks.add(new Task(3, 1, 2, "Brake check for vehicle 1", 
                Status.COMPLETED, Priority.MEDIUM, new Date(), 8, "Good work"));
        tasks.add(new Task(4, 2, 3, "Tire replacement for vehicle 2", 
                Status.CANCELLED, Priority.LOW, null, 0, "Customer canceled"));
        tasks.add(new Task(5, 3, 3, "Battery replacement for vehicle 3", 
                Status.PENDING, Priority.MEDIUM, null, 0, null));
    }

    /**
     * Retrieve all tasks.
     *
     * @return List of tasks.
     * @throws Exception If fetching tasks fails.
     */
    public static List<Task> getTasks() throws Exception {
        try {
            return new ArrayList<>(tasks); // Return a copy to prevent external modification
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
            tasks.add(task);
        } catch (Exception e) {
            System.err.println("Error while adding task: " + e.getMessage());
            throw new Exception("Unable to add task.", e);
        }
    }

    /**
     * Mark a task as completed.
     *
     * @param taskId The ID of the task to complete.
     * @return true if the task was successfully marked as completed, false otherwise.
     * @throws Exception If updating the task fails.
     */
    public static boolean markTaskAsCompleted(int taskId) throws Exception {
        try {
            Task currentTask = tasks.stream().filter(task -> task.getId() == taskId).findFirst().orElse(null);
            if (currentTask != null) {
                currentTask.setStatus(Status.COMPLETED);
                currentTask.setCompletedAt(new Date());
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error while marking task as completed: " + e.getMessage());
            throw new Exception("Unable to mark task as completed.", e);
        }
    }
}
