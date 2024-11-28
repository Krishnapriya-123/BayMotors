package com.baymotors.services;
import java.util.List;


import com.baymotors.dao.EmployeeDao;
import com.baymotors.models.Employee;

public class EmployeeService {
	 public static List<Employee> listEmployees() {
		 List<Employee> employees = EmployeeDao.getEmployees();
		 
		 return employees;
	 }
	 
	 public static void addEmployee(Employee employee) {
		 EmployeeDao.addEmployee(employee);
	 }
	 
}
