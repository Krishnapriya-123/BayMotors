package com.baymotors.dao;


import com.baymotors.constants.Roles;
import com.baymotors.models.Employee;
import com.baymotors.models.Manager;
import com.baymotors.models.Mechanic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Data Access Object (DAO) for managing employee-related data.
 */

public class EmployeeDao {
	private static final List<Employee> employees = new ArrayList<>();

    static {
        // Static data for one manager and two mechanics
        employees.add(new Manager(
            1, "admin","admin", "Krishna", "Priya", "admin@gmail.com", "1234567890", 
            Roles.MANAGER, "123 Bay Street"));
        
        employees.add(new Mechanic(
            2, "mechanic1", "mechanic1", "Sai", "Vineeth", "mechanic1@baymotors.com", "9876543210", 
            Roles.MECHANIC, "456 Bay Avenue"));
        
        employees.add(new Mechanic(
            3, "mechanic2", "mechanic2","Kavya", "ShivaRedyy", "mechanic2@baymotors.com", "1122334455", 
            Roles.MECHANIC, "789 Bay Lane"));
    }

    public static List<Employee> getEmployees() {
        return employees;
    }
    
    public static Employee getEmployeeByUsername(String username) {
        return employees.stream()
                .filter(emp -> emp.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    
	public static boolean validateEmployee(String username, String password, String role) {
	    try {
	        // Fetch employee by username from EmployeeData
	        Employee employee = EmployeeDao.getEmployeeByUsername(username);

	        // Validate employee details
	        if (employee != null && employee.getUsername().equals(username) &&
	            employee.getPassword().equals(password) && // Replace with employee-specific password logic if needed
	            employee.getRole().equalsIgnoreCase(role)) {
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public static void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
}

