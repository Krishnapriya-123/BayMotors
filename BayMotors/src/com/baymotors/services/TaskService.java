package com.baymotors.services;

import java.util.List;

import com.baymotors.dao.TaskDao;
import com.baymotors.models.Task;

public class TaskService {
	public static List<Task> getTasks() {
		List<Task> tasks = TaskDao.getTasks();
		return tasks;
	}
	
	public static void addTask(Task task) {
        TaskDao.addTask(task);
    }

}
