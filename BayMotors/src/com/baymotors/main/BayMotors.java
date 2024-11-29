package com.baymotors.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

import com.baymotors.constants.Roles;
import com.baymotors.dao.EmployeeDao;
import com.baymotors.services.EmployeeService;
import com.baymotors.models.Employee;
import com.baymotors.models.Mechanic;
import com.baymotors.dao.CustomerDao;
import com.baymotors.services.CustomerService;
import com.baymotors.models.Customer;
import com.baymotors.models.Task;
import com.baymotors.dao.TaskDao;
import com.baymotors.services.TaskService;

public class BayMotors {

    public static void main(String[] args) {
        String username;
        String password;
        String role = null; // 1 - manager, 2 - mechanic
        int roleId;
        boolean loginStatus = false;

        Scanner sc = new Scanner(System.in);

        while (!loginStatus) { // Loop until login is successful
            System.out.println("Welcome to Bay Motors");
            System.out.println("Please Login");

            System.out.print("Enter Username: ");
            username = sc.nextLine();

            System.out.print("Enter Password: ");
            password = sc.nextLine();

            System.out.print("Select Role: 1 - Manager, 2 - Mechanic: ");
            roleId = sc.nextInt();
            sc.nextLine(); 

            if (roleId != 1 && roleId != 2) {
                System.out.println("Invalid Role. Please select from the given Roles.");
                continue; // Skip the rest of the loop and re-prompt
            }

            role = roleId == 1 ? "Manager" : "Mechanic";
            loginStatus = EmployeeDao.validateEmployee(username, password, role);

            if (loginStatus) {
                System.out.println("Logged in successfully!");
                getDashboard(role);
            } else {
                System.out.println("Incorrect Credentials. Please try again.");
            }
        }

        sc.close();
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
	    Scanner sc = new Scanner(System.in);

	    while (true) { // Loop to allow multiple operations until logout
	    	System.out.println("\nSelect an Operation to perform ");
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
		    System.out.println("13. LogOut \n");

	        System.out.print("Enter your choice: ");
	        int userOption = sc.nextInt();
	        
	        // Flush the leftover newline
	        sc.nextLine(); // Consume the leftover newline
	        
	        switch (userOption) {
	            case 1:
	                System.out.println("\nList of Mechanics\n");
	                // Logic to list mechanics
	                List<Employee> employees = EmployeeService.listEmployees();
	                System.out.println("\n--- Mechanics ---");
	                for(Employee employee:employees) {
	                	System.out.println(employee);
	                }
	                break;

	            case 2:
	            	// Logic to add a mechanic
	            	System.out.println("Enter Mechanic Details: ");
	            	System.out.print("FirstName: ");
	            	String firstName = sc.nextLine();
	            	System.out.print("LastName: ");
	            	String lastName = sc.nextLine();
	            	System.out.print("UserName: ");
	            	String userName = sc.nextLine();
	            	System.out.print("Password: ");
	            	String passWord = sc.nextLine();
	            	System.out.print("Email: ");
	            	String email = sc.nextLine();
	            	System.out.print("MobileNumber: ");
	            	String mobileNumber = sc.nextLine();
	            	System.out.print("Address: ");
	            	String address = sc.nextLine();

	            	// Dynamically generate the next ID
	            	int nextId = EmployeeService.listEmployees().size() + 1;

	            	// Create a Mechanic object
	            	Employee emp = new Mechanic(nextId, userName, passWord, firstName, lastName, email, mobileNumber, Roles.MECHANIC, address);

	            	// Add the mechanic to the repository
	            	EmployeeService.addEmployee(emp);

	            	System.out.println("Mechanic added successfully\n\n");
	                break;

	            case 3:
	            	// Logic to list customers
	            	 System.out.println("\nList of Customers\n");
	            	 List<Customer> customers = CustomerService.listCustomers();
	            	 System.out.println("--- Customers ---");
	            	 for(Customer customer:customers) {
		                	System.out.println(customer);
		                }
	                
	                break;

	            case 4:
	            	// Logic to add a Customer
	            	System.out.println("Enter Customer Details: ");
	            	System.out.print("FirstName: ");
	            	String firstName2 = sc.nextLine();
	            	System.out.print("LastName: ");
	            	String lastName2 = sc.nextLine();
	            	System.out.print("Email: ");
	            	String email2 = sc.nextLine();
	            	System.out.print("MobileNumber: ");
	            	String mobileNumber2 = sc.nextLine();
	            	System.out.print("Address: ");
	            	String address2 = sc.nextLine();
	            	
	            	 System.out.print("IsRegistered (true/false): ");
	                 boolean isRegistered = Boolean.parseBoolean(sc.nextLine()); // Parse string to boolean
	                 
	                 System.out.print("RegistrationDate (yyyy-MM-dd): ");
	                 String registrationDateStr = sc.nextLine();
	                 
	                 // Parse the registration date string to a Date object
	                 Date registrationDate = null;
	                 try {
	                     registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse(registrationDateStr);
	                 } catch (ParseException e) {
	                     System.out.println("Invalid date format. Please use yyyy-MM-dd.");
	                     e.printStackTrace();
	                     return; // Exit the process in case of invalid date input
	                 }
	            	// Dynamically generate the next ID
	            	int nextId2 = EmployeeService.listEmployees().size() + 1;
	            	
	            	// Create a Mechanic object
	            	Customer cus = new Customer(nextId2, firstName2, lastName2, email2, mobileNumber2, address2, isRegistered, registrationDate);

	            	// Add the mechanic to the repository
	            	CustomerService.addCustomer(cus);
	                System.out.println("Customer added: " + cus);
	                break;

	            case 5:
	                System.out.println("Vehicle logged");
	                // Logic to log a vehicle
	                
	                break;

	            case 6:
	            	// Logic to list tasks
	                System.out.println("\nList of Tasks\n");
	                
	                List<Task> tasks = TaskService.listTasks();
	                System.out.println("\n--- Tasks ---");
	            	for(Task task:tasks) {
		                System.out.println(task);
		                }
	                break;

	            case 7:
	            	// Logic to add a task
	                System.out.println("Task added");
	                
	                
	                break;

	            case 8:
	            	// Logic to notify customers
	                System.out.println("Customers notified");
	                
	                break;

	            case 9:
	            	// Logic to list suppliers
	            	System.out.println("List of Suppliers");
	                
	                break;

	            case 10:
	            	// Logic to add a supplier
	                System.out.println("Supplier added");
	                
	                break;

	            case 11:
	            	// Logic to list manufacturers
	                System.out.println("List of Manufacturers");
	                
	                break;

	            case 12:
	            	// Logic to add a manufacturer
	                System.out.println("Manufacturer added");
	                
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
