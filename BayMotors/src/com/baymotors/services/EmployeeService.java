package com.baymotors.services;
import java.util.List;


import com.baymotors.dao.EmployeeDao;
import com.baymotors.models.Employee;

public class EmployeeService {
	/**
     * Validates the employee's login credentials and returns the employee object if valid.
     *
     * @param username The username of the employee.
     * @param password The password of the employee.
     * @param role The role of the employee (e.g., "Manager" or "Mechanic").
     * @return The Employee object if login is successful.
     * @throws Exception If validation or fetching employee fails.
     */
	public static Employee validateEmployee(String username, String password, String role) throws Exception {
		try {
			boolean loginStatus = EmployeeDao.validateEmployee(username, password, role);
	        if (loginStatus) {
	        	return EmployeeDao.getEmployeeByUsername(username);
	        } else {
	        	throw new Exception("Invalid login credentials for username: " + username);
	        }
		} catch (Exception e) {
			System.err.println("Error while validating employee: " + e.getMessage());
			throw new Exception("Unable to validate employee.", e);
		}
	}
    
    /**
     * Adds a new employee to the system.
     *
     * @param employee The Employee object to add.
     * @throws Exception If adding the employee fails.
     */
    public static void addEmployee(Employee employee) throws Exception {
        try {
            EmployeeDao.addEmployee(employee);
        } catch (Exception e) {
            System.err.println("Error while adding employee: " + e.getMessage());
            throw new Exception("Unable to add employee.", e);
        }
    }
    
    /**
     * Fetches the list of all employees.
     *
     * @return List of Employee objects.
     * @throws Exception If fetching employees fails.
     */
	public static List<Employee> getEmployees() throws Exception {
		try {
			return EmployeeDao.getEmployees();
		} catch (Exception e) {
			System.err.println("Error while fetching employees: " + e.getMessage());
			throw new Exception("Unable to fetch employees.", e);
		}
	}
	
	/**
     * Checks if an employee exists with the given employee ID.
     *
     * @param employeeId The ID of the employee to check.
     * @return true if the employee exists, false otherwise.
     * @throws Exception If checking employee existence fails.
     */
    public static boolean isEmployeeExists(int employeeId) throws Exception {
        try {
            return EmployeeDao.getEmployees().stream().anyMatch(employee -> employee.getId() == employeeId);
        } catch (Exception e) {
            System.err.println("Error while checking employee existence: " + e.getMessage());
            throw new Exception("Unable to check if employee exists.", e);
        }
    }	 
}
