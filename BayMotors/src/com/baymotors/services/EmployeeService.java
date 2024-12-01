package com.baymotors.services;
import java.util.List;


import com.baymotors.dao.EmployeeDao;
import com.baymotors.models.Employee;

public class EmployeeService {
	public static Employee validateEmployee(String username, String password, String role) {
		boolean loginStatus = EmployeeDao.validateEmployee(username, password, role);
		Employee emp = null;
		if (loginStatus) {
			emp = EmployeeDao.getEmployeeByUsername(username);
		}
		return emp;
	}
	
	public static void addEmployee(Employee employee) {
		 EmployeeDao.addEmployee(employee);
	 }
	
	 public static List<Employee> getEmployees() {
		 return EmployeeDao.getEmployees();
	 }
	 
	 public static boolean isEmployeeExists(int employeeId) {
		 return EmployeeDao.getEmployees().stream().anyMatch(employee -> employee.getId() == employeeId);
	 }
	 
	 	 
}
