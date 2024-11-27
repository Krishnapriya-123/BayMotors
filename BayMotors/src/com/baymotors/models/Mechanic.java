package com.baymotors.models;

import java.util.List;

//import com.dao.TaskDao;

public class Mechanic extends Employee {
	public Mechanic() {
		super();
	}
	public Mechanic(int id, String username, String password, String firstName, String lastName, String email, String mobileNumber,
            String role, String address) {
		super(id, username, password, firstName, lastName, email, mobileNumber, role, address);
	}

	@Override
    public String performRoleSpecificTask() {
        return "Mechanic task: Repairing vehicles and completing assigned tasks.";
    }
	
//	@Override
//    public List<Task> viewTasks() {
//        return TaskDao.getTasksAssignedToMechanic(this.getId()); // Retrieve tasks specific to this mechanic
//    }
//	
	// Mechanic-specific method
//    public boolean completeTask(int taskId) {
//        return TaskDao.markTaskAsCompleted(taskId, this.getId());
//    }
	
}


