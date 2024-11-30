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
        tasks.add(new Task(1, 101, 201, "Oil change for vehicle 101", 
                Status.PENDING, Priority.LOW, null, 0, null));
        tasks.add(new Task(2, 102, 202, "Engine repair for vehicle 102", 
                Status.IN_PROGRESS, Priority.HIGH, null, 0, null));
        tasks.add(new Task(3, 103, 201, "Brake check for vehicle 103", 
                Status.COMPLETED, Priority.MEDIUM, new Date(), 8, "Good work"));
        tasks.add(new Task(4, 104, 203, "Tire replacement for vehicle 104", 
                Status.CANCELLED, Priority.LOW, null, 0, "Customer canceled"));
        tasks.add(new Task(5, 105, 202, "Battery replacement for vehicle 105", 
                Status.PENDING, Priority.MEDIUM, null, 0, null));
    }

	/**
     * Retrieve all tasks.
     *
     * @return List of tasks.
     */
    public static List<Task> getTasks() {
        return new ArrayList<>(tasks); // Return a copy to prevent external modification
    }
    
    /**
     * Add a new task to the list.
     *
     * @param task The task to add.
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }
}
