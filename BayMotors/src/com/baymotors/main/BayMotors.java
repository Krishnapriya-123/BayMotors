package com.baymotors.main;

import com.baymotors.services.EmployeeService;
import com.baymotors.services.CustomerService;
import com.baymotors.services.TaskService;
import com.baymotors.services.VehicleService;

import com.baymotors.models.Employee;
import com.baymotors.models.Mechanic;
import com.baymotors.models.Customer;
import com.baymotors.models.Task;
import com.baymotors.models.Vehicle;

import com.baymotors.constants.Roles;
import com.baymotors.constants.Priority;
import com.baymotors.constants.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

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
            loginStatus = EmployeeService.validateEmployee(username, password, role);

            if (loginStatus) {
                System.out.println("Logged in successfully!");
                getDashboard(role);
            } else {
                System.out.println("Incorrect Credentials. Please try again.");
            }
        }

        sc.close();
    }
    
    
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
    
    public static void displayManagerMenu() {
    	System.out.println("\nSelect an Operation to perform ");
	    System.out.println("1. List Mechanics ");
	    System.out.println("2. Add Mechanic ");
	    System.out.println("3. List Customers ");
	    System.out.println("4. Add Customer ");
	    System.out.println("5. List Vehicles");
	    System.out.println("6. Log Vehicle ");
	    System.out.println("7. List Tasks ");
	    System.out.println("8. Add Task ");
	    System.out.println("9. Notify Customers ");
	    System.out.println("10. List Suppliers ");
	    System.out.println("11. Add Supplier ");
	    System.out.println("12. List Manufacturers ");
	    System.out.println("13. Add Manufacturer ");
	    System.out.println("14. LogOut \n");
    }
    
    public static void displayManagerOptions() {
	    Scanner sc = new Scanner(System.in);

	    while (true) { // Loop to allow multiple operations until logout
	    	displayManagerMenu();
	    	
	    	int userOption = -1; // Initialize with an invalid option
	    	boolean validInput = false; // Flag to track if input is valid

	    	while (!validInput) {
	    	    try {
	    	        System.out.print("Enter your choice: ");
	    	        userOption = sc.nextInt(); // Attempt to read an integer
	    	        sc.nextLine(); // Consume newline character
	    	        validInput = true; // Set flag to true if input is valid
	    	    } catch (Exception e) {
	    	        System.out.println("Invalid input. Please enter a valid number.");
	    	        sc.nextLine(); // Consume invalid input to avoid infinite loop
	    	    }
	    	}
	        
	        
	        switch (userOption) {
	            case 1:
	                System.out.println("\nList of Mechanics\n");
	                // Logic to list mechanics
	                List<Employee> employees = EmployeeService.getEmployees();
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
	            	int nextId = EmployeeService.getEmployees().size() + 1;

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
	            	int nextId2 = EmployeeService.getEmployees().size() + 1;
	            	
	            	// Create a Mechanic object
	            	Customer cus = new Customer(nextId2, firstName2, lastName2, email2, mobileNumber2, address2, isRegistered, registrationDate);

	            	// Add the mechanic to the repository
	            	CustomerService.addCustomer(cus);
	                System.out.println("Customer added: " + cus);
	                break;
	            
	            case 5:
	            	System.out.println("\n--- List of Vehicles ---");
	            	List<Vehicle> vehicles = VehicleService.getVehicles();
	            	for(Vehicle vehicle : vehicles) {
	            		System.out.println(vehicle);
	            	}
	            	break;

	            case 6:
	            	System.out.println("\n--- Log a Vehicle ---");

	                // Take user inputs for vehicle details
	            	int customerId = -1;

	                // Keep prompting for a valid customerId until one is provided
	                while (true) {
	                    // Display a list of existing customers
	                    System.out.println("Select a Customer ID from the following list:");
	                    CustomerService.listCustomers().forEach(customer -> {
	                        System.out.println("Customer ID: " + customer.getId() + ", Name: " + customer.getFirstName() + " " + customer.getLastName());
	                    });

	                    System.out.print("\nEnter Customer ID: ");
	                    customerId = sc.nextInt();
	                    sc.nextLine(); // Consume newline

	                    // Validate the entered customerId
	                    if (CustomerService.isCustomerExists(customerId)) {
	                        break; // Exit the loop if the customerId is valid
	                    } else {
	                        System.out.println("Invalid Customer ID. Please try again.\n");
	                    }
	                }

	                System.out.print("Enter Registration Number: ");
	                String registrationNumber = sc.nextLine();

	                System.out.print("Enter Make: ");
	                String make = sc.nextLine();

	                System.out.print("Enter Model: ");
	                String model = sc.nextLine();

	                System.out.print("Enter Year: ");
	                int year = sc.nextInt();
	                sc.nextLine(); // Consume newline

	                System.out.print("Enter Color: ");
	                String color = sc.nextLine();

	                // Generate the next ID dynamically
	                int nextVehicleId = VehicleService.getVehicles().size() + 1;

	                // Create a new Vehicle object
	                Vehicle newVehicle = new Vehicle(nextVehicleId, registrationNumber, make, model, year, color, customerId);

	                // Call the service method to add the vehicle
	                VehicleService.logVehicle(newVehicle);

	                System.out.println("Vehicle logged successfully!");
	                break;

	            case 7:
	            	// Logic to list tasks
	                System.out.println("\nList of Tasks\n");
	                
	                List<Task> tasks = TaskService.getTasks();
	                System.out.println("\n--- Tasks ---");
	            	for(Task task:tasks) {
		                System.out.println(task);
		            }
	                break;

	            case 8:
	            	// Logic to add a task
	            	System.out.println("\n--- Add a New Task ---");
	            	// Select a valid vehicleId
	                int vehicleId = -1;
	                while (true) {
	                    System.out.println("\nSelect a Vehicle ID from the following list:");
	                    VehicleService.getVehicles().forEach(vehicle -> {
	                        System.out.println("Vehicle ID: " + vehicle.getId() + ", Registration: " + vehicle.getRegistrationNumber());
	                    });

	                    System.out.print("Enter Vehicle ID: ");
	                    vehicleId = sc.nextInt();
	                    sc.nextLine(); // Consume newline

	                    if (VehicleService.isVehicleExists(vehicleId)) {
	                        break; // Valid vehicleId
	                    } else {
	                        System.out.println("Invalid Vehicle ID. Please try again.\n");
	                    }
	                }
	                
	             // Select a valid mechanicId
	                int mechanicId = -1;
	                while (true) {
	                    System.out.println("\nSelect a Mechanic ID from the following list:");
	                    EmployeeService.getEmployees().forEach(mechanic -> {
	                        System.out.println("Mechanic ID: " + mechanic.getId() + ", Name: " + mechanic.getFirstName() + " " + mechanic.getLastName());
	                    });

	                    System.out.print("Enter Mechanic ID: ");
	                    mechanicId = sc.nextInt();
	                    sc.nextLine(); // Consume newline

	                    if (EmployeeService.isEmployeeExists(mechanicId)) {
	                        break; // Valid mechanicId
	                    } else {
	                        System.out.println("Invalid Mechanic ID. Please try again.\n");
	                    }
	                }
	                
	             // Collect remaining task details
	                System.out.print("Enter Task Description: ");
	                String description = sc.nextLine();
	                
	                String priority = null;

	                while (true) {
	                    System.out.println("Select Task Priority: 1-Low, 2-Medium, 3-High");
	                    System.out.print("Enter your choice (1-3): ");

	                    int choice = sc.nextInt();
	                    sc.nextLine(); // Consume newline

	                    switch (choice) {
	                        case 1:
	                            priority = Priority.LOW;
	                            break;
	                        case 2:
	                        	priority = Priority.MEDIUM;
	                        	break;
	                        case 3:
	                        	priority = Priority.HIGH;
	                        	break;
	                        default:
	                            System.out.println("Invalid choice. Please select a valid priority (1-3).\n");
	                    }
	                    if (priority != null) {
	                    	break;
	                    }
	                }


	                // Generate the next Task ID
	                int taskId = TaskService.getTasks().size() + 1;

	                // Create a new Task object
	                Task newTask = new Task(taskId, vehicleId, mechanicId, description, Status.PENDING, priority, null, 0, null);

	                // Add the task
	                TaskService.addTask(newTask);

	                System.out.println("Task created successfully!");
	                break;
	            case 9:
	            	// Logic to notify customers
	                System.out.println("Customers notified");
	                
	                break;

	            case 10:
	            	// Logic to list suppliers
	            	System.out.println("List of Suppliers");
	                
	                break;

	            case 11:
	            	// Logic to add a supplier
	                System.out.println("Supplier added");
	                
	                break;

	            case 12:
	            	// Logic to list manufacturers
	                System.out.println("List of Manufacturers");
	                
	                break;

	            case 13:
	            	// Logic to add a manufacturer
	                System.out.println("Manufacturer added");
	                
	                break;

	            case 14:
	                System.out.println("Logging out...");
	                return; // Exit the method to simulate logout

	            default:
	                System.out.println("Invalid choice. Please try again.");
	                break;
	        }
	        
	        System.out.println("\nPress Enter to return to the main menu...");
	        sc.nextLine(); // Wait for Enter
	        
	    }
	    
    }    
	   
    public static void displayMechanicOptions() {
		System.out.println("Mechanic Options:");
        System.out.println("1. View Tasks");
        System.out.println("2. Update Task Status");
        System.out.println("3. Log Out");
	}

}
