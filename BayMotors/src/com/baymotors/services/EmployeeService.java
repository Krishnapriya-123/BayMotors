package com.baymotors.services;
import java.util.List;


import com.baymotors.dao.EmployeeDao;
import com.baymotors.models.Employee;

public class EmployeeService {
	public static boolean validateEmployee(String username, String password, String role) {
		return EmployeeDao.validateEmployee(username, password, role);
	}
	
	public static void addEmployee(Employee employee) {
		 EmployeeDao.addEmployee(employee);
	 }
	
	 public static List<Employee> getEmployees() {
		 List<Employee> employees = EmployeeDao.getEmployees();
		 
		 return employees;
	 }
	 
	 public static boolean isEmployeeExists(int employeeId) {
		 return EmployeeDao.getEmployees().stream().anyMatch(employee -> employee.getId() == employeeId);
	 }
	 
	 	 
}
