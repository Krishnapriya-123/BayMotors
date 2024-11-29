package com.baymotors.services;

import java.util.List;

import com.baymotors.dao.TaskDao;
import com.baymotors.models.Task;

public class TaskService {
	public static List<Task> listTasks() {
		List<Task> tasks = TaskDao.getAllTasks();
		 
		return tasks;
	}
}
