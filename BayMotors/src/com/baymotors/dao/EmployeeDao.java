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
	
	/**
     * Displays the dashboard based on the role of the employee.
     *
     * @param role The role of the logged-in employee.
     */
    public static void getDashboard(String role) {
        System.out.println("\n--- Dashboard ---");

        if (Roles.MANAGER.equals(role)) {
            displayManagerOptions();
        } else if (Roles.MECHANIC.equals(role)) {
            displayMechanicOptions();
        } else {
            System.out.println("Invalid role. No dashboard available.");
        }
    }

    
	public static void displayManagerOptions() {
	    System.out.println("Select an Operation to perform ");
	    System.out.println("1. List Mechanics ");
	    System.out.println("2. Add Mechanic ");
	    System.out.println("3. List Customers ");
	    System.out.println("4. Add Customer ");
	    System.out.println("5. Log Vehicle ");
	    System.out.println("6. List Tasks ");
	    System.out.println("7. Add Task ");
	    System.out.println("8. Notify Customers ");
	    System.out.println("9. List Suppliers ");
	    System.out.println("10. Add Supplier ");
	    System.out.println("11. List Manufacturers ");
	    System.out.println("12. Add Manufacturer ");
	    System.out.println("13. LogOut");

	    Scanner sc = new Scanner(System.in);

	    while (true) { // Loop to allow multiple operations until logout
	        System.out.print("Enter your choice: ");
	        int userOption = sc.nextInt();

	        switch (userOption) {
	            case 1:
	                System.out.println("List of Mechanics");
	                // Logic to list mechanics
	                break;

	            case 2:
	                System.out.println("Mechanic added");
	                // Logic to add a mechanic
	                break;

	            case 3:
	                System.out.println("List of Customers");
	                // Logic to list customers
	                break;

	            case 4:
	                System.out.println("Customer added");
	                // Logic to add a customer
	                break;

	            case 5:
	                System.out.println("Vehicle logged");
	                // Logic to log a vehicle
	                break;

	            case 6:
	                System.out.println("List of Tasks");
	                // Logic to list tasks
	                break;

	            case 7:
	                System.out.println("Task added");
	                // Logic to add a task
	                break;

	            case 8:
	                System.out.println("Customers notified");
	                // Logic to notify customers
	                break;

	            case 9:
	                System.out.println("List of Suppliers");
	                // Logic to list suppliers
	                break;

	            case 10:
	                System.out.println("Supplier added");
	                // Logic to add a supplier
	                break;

	            case 11:
	                System.out.println("List of Manufacturers");
	                // Logic to list manufacturers
	                break;

	            case 12:
	                System.out.println("Manufacturer added");
	                // Logic to add a manufacturer
	                break;

	            case 13:
	                System.out.println("Logging out...");
	                return; // Exit the method to simulate logout

	            default:
	                System.out.println("Invalid choice. Please try again.");
	                break;
	        }
	    }
	}
	
	public static void displayMechanicOptions() {
		System.out.println("Mechanic Options:");
        System.out.println("1. View Tasks");
        System.out.println("2. Update Task Status");
        System.out.println("3. Log Out");
	}

}

