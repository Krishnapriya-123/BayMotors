package com.baymotors.dao;


import com.baymotors.constants.Roles;
import com.baymotors.models.Employee;
import com.baymotors.models.Manager;
import com.baymotors.models.Mechanic;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * Fetch all employees.
     *
     * @return List of employees.
     * @throws Exception If fetching employees fails.
     */
    public static List<Employee> getEmployees() throws Exception {
        try {
            return new ArrayList<>(employees);
        } catch (Exception e) {
            System.err.println("Error while fetching employees: " + e.getMessage());
            throw new Exception("Unable to fetch employees.", e);
        }
    }
    
    /**
     * Fetch an employee by their username.
     *
     * @param username The username of the employee.
     * @return The employee object or null if not found.
     * @throws Exception If fetching the employee fails.
     */
    public static Employee getEmployeeByUsername(String username) throws Exception {
        try {
            return employees.stream()
                    .filter(emp -> emp.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            System.err.println("Error while finding employee by username: " + e.getMessage());
            throw new Exception("Unable to fetch employee by username: " + username, e);
        }
    }
    
    /**
     * Validate an employee by username, password, and role.
     *
     * @param username The username of the employee.
     * @param password The password of the employee.
     * @param role     The role of the employee.
     * @return true if valid, false otherwise.
     * @throws Exception If validating the employee fails.
     */
    public static boolean validateEmployee(String username, String password, String role) throws Exception {
        try {
            Employee employee = getEmployeeByUsername(username);
            // Validate employee details
            if (employee != null && employee.getPassword().equals(password) && employee.getRole().equalsIgnoreCase(role)) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error while validating employee: " + e.getMessage());
            throw new Exception("Unable to validate employee.", e);
        }
        return false;
    }
	
    /**
     * Add a new employee.
     *
     * @param employee The employee to add.
     * @throws Exception If adding the employee fails.
     */
    public static void addEmployee(Employee employee) throws Exception {
        try {
            employees.add(employee);
        } catch (Exception e) {
            System.err.println("Error while adding employee: " + e.getMessage());
            throw new Exception("Unable to add employee.", e);
        }
    }
	
}

