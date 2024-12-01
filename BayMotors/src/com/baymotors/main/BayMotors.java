package com.baymotors.main;

import com.baymotors.services.EmployeeService;
import com.baymotors.services.ManufacturerService;
import com.baymotors.services.SupplierService;
import com.baymotors.services.CustomerService;
import com.baymotors.services.TaskService;
import com.baymotors.services.VehicleService;

import com.baymotors.models.Employee;
import com.baymotors.models.Mechanic;
import com.baymotors.models.Customer;
import com.baymotors.models.Task;
import com.baymotors.models.Vehicle;
import com.baymotors.models.Manufacturer;
import com.baymotors.models.Supplier;

import com.baymotors.constants.Roles;
import com.baymotors.constants.Priority;
import com.baymotors.constants.Status;
import com.baymotors.constants.CustomerType;

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
            Employee employee = EmployeeService.validateEmployee(username, password, role);
          

            if (employee!= null) {
                System.out.println("Logged in successfully!");
                getDashboard(employee);
            } else {
                System.out.println("Incorrect Credentials. Please try again.");
            }
        }

        sc.close();
    }
    
    
    public static void getDashboard(Employee employee) {
        System.out.println("\n--- Dashboard ---");

        if (Roles.MANAGER.equals(employee.getRole())) {
            displayManagerOptions();
        } else if (Roles.MECHANIC.equals(employee.getRole())) {
            displayMechanicOptions(employee);
        } else {
            System.out.println("Invalid role. No dashboard available.");
        }
    }
    
    public static void displayManagerMenu() {
        String border = "+---------------------------------------------------------------+";
        System.out.println("\n" + border);
        System.out.println("|                   Manager Operations Menu                    |");
        System.out.println(border);
        System.out.println("| 1. List Mechanics                                             |");
        System.out.println("| 2. Add Mechanic                                               |");
        System.out.println("| 3. List Customers                                             |");
        System.out.println("| 4. Add Customer                                               |");
        System.out.println("| 5. Register Customer                                          |");
        System.out.println("| 6. List Vehicles                                              |");
        System.out.println("| 7. Log Vehicle                                                |");
        System.out.println("| 8. List Tasks                                                 |");
        System.out.println("| 9. Add Task                                                   |");
        System.out.println("| 10. Notify Customers                                          |");
        System.out.println("| 11. List Manufacturers                                        |");
        System.out.println("| 12. Add Manufacturer                                          |");
        System.out.println("| 13. List Suppliers                                            |");
        System.out.println("| 14. Add Supplier                                              |");
        System.out.println("| 15. LogOut                                                    |");
        System.out.println(border);
    }
    
    public static void displayMechanicMenu() {
    	String border = "+---------------------------------------------------------------+";
        System.out.println("\n" + border);
        System.out.println("|                   Mechanic Operations Menu                    |");
        System.out.println(border);
        System.out.println("| 1. List Tasks                                                |");
        System.out.println("| 2. Complete Task                                             |");
        System.out.println("| 3. List Manufacturers                                        |");
        System.out.println("| 4. Add Manufacturer                                          |");
        System.out.println("| 5. List Suppliers                                            |");
        System.out.println("| 6. Add Supplier                                              |");
        System.out.println("| 7. LogOut                                                    |");
        System.out.println(border);

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
	            	
	            	 System.out.print("Select Customer Type (Choose from options: 1 => Registered, 2 => Walk-In): ");
	            	 int customerTypeChoice = sc.nextInt();
	                 sc.nextLine(); 
	                 
	                 String customerType = CustomerType.WALKIN;
	                 
	                 Date registrationDate = null;
	                 if (customerTypeChoice == 1) {
	                	 customerType = CustomerType.REGISTERED;
	                	 registrationDate = new Date();
	                 }
	                 
	                 
	                
	            	// Dynamically generate the next ID
	            	int nextId2 = CustomerService.listCustomers().size() + 1;
	            	
	            	// Create a Mechanic object
	            	Customer cus = new Customer(nextId2, firstName2, lastName2, email2, mobileNumber2, address2, customerType, registrationDate);

	            	// Add the mechanic to the repository
	            	CustomerService.addCustomer(cus);
	                System.out.println("Customer added: " + cus);
	                break;
	                
	            case 5:
	            	// logic to register the customer
	            	System.out.println("\n--- Register a Walk-In Customer ---");
	            	
	            	// Fetch the list of Walk-In customers
	                List<Customer> walkInCustomers = CustomerService.getWalkInCustomers();
	            	
	                // Check if there are any Walk-In customers
	                if (walkInCustomers.isEmpty()) {
	                    System.out.println("No Walk-In customers available to register.");
	                    break;
	                }
	                
	                // Display the Walk-In customers
	                System.out.println("Walk-In Customers:");
	                walkInCustomers.forEach(customer -> 
	                    System.out.println("ID: " + customer.getId() + " | Name: " + customer.getFirstName() + " " + customer.getLastName())
	                );
	                
	                // Prompt the user to select a customer to register
	                System.out.print("\nEnter the ID of the customer to register: ");
	                int customerIdToRegister = sc.nextInt();
	                sc.nextLine(); // Consume newline

	                // Validate the selection and register the customer
	                boolean isRegistered = CustomerService.registerCustomer(customerIdToRegister);

	                if (isRegistered) {
	                    System.out.println("Customer registered successfully!");
	                } else {
	                    System.out.println("Invalid customer ID. Registration failed.");
	                }
	                break;
	            
	            case 6:
	            	System.out.println("\n--- List of Vehicles ---");
	            	List<Vehicle> vehicles = VehicleService.getVehicles();
	            	for(Vehicle vehicle : vehicles) {
	            		System.out.println(vehicle);
	            	}
	            	break;

	            case 7:
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

	            case 8:
	            	// Logic to list tasks
	                System.out.println("\nList of Tasks\n");
	                
	                List<Task> tasks = TaskService.getTasks();
	                System.out.println("\n--- Tasks ---");
	            	for(Task task:tasks) {
		                System.out.println(task);
		            }
	                break;

	            case 9:
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
	            case 10:
	            	// Logic to notify customers
	                System.out.println("Customers notified");
	                
	                System.out.println("\n--- Notify Customers ---");

	                // Ask the user to select the type of notification
	                System.out.println("Select Notification Type:");
	                System.out.println("1. Offers to Registered Customers");
	                System.out.println("2. Advertisement for Walk-In Customers");

	                int notificationType = -1;
	                boolean validChoice = false;
	                while (!validChoice) {
	                    System.out.print("Enter your choice (1 or 2): ");
	                    notificationType = sc.nextInt();
	                    sc.nextLine(); // Consume newline

	                    if (notificationType == 1 || notificationType == 2) {
	                        validChoice = true;
	                    } else {
	                        System.out.println("Invalid choice. Please enter 1 or 2.");
	                    }
	                }

	                // Prompt the user to enter the notification message
	                System.out.print("Enter the notification message: ");
	                String notificationMessage = sc.nextLine();

	                // Fetch the list of customers based on the selected type
	                List<Customer> targetCustomers;
	                if (notificationType == 1) {
	                    System.out.println("\n--- Sending Offers to Registered Customers ---");
	                    targetCustomers = CustomerService.getRegisteredCustomers();
	                } else {
	                    System.out.println("\n--- Sending Advertisement to Walk-In Customers ---");
	                    targetCustomers = CustomerService.getWalkInCustomers();
	                }

	                // Check if there are any customers to notify
	                if (targetCustomers.isEmpty()) {
	                    System.out.println("No customers found for the selected category.");
	                    break;
	                }

	                // Print notifications for each customer
	                targetCustomers.forEach(customer -> {
	                    System.out.println("Notification sent to " + customer.getFirstName() + " " + customer.getLastName() +
	                            " (" + customer.getEmail() + "): " + notificationMessage);
	                });

	                System.out.println("\nAll notifications have been sent.");
	                break;

	            case 11:
	            	// Logic to list of Manufacturers
	           
	            	System.out.println("\n--- List of Manufacturers ---");
	                ManufacturerService.listManufacturers().forEach(System.out::println);
	                break;

	            case 12:
	            	// Logic to add a Manufacturer
	            	System.out.println("\n--- Add a New Manufacturer ---");

	                System.out.print("Enter Manufacturer Name: ");
	                String manufacturerName = sc.nextLine();

	                System.out.print("Enter Country: ");
	                String country = sc.nextLine();

	                int nextManufacturerId = ManufacturerService.listManufacturers().size() + 1;

	                Manufacturer newManufacturer = new Manufacturer(nextManufacturerId, manufacturerName, country);

	                try {
	                    ManufacturerService.addManufacturer(newManufacturer);
	                    System.out.println("Manufacturer added successfully!");
	                } catch (IllegalArgumentException e) {
	                    System.out.println("Error: " + e.getMessage());
	                }
	                break;

	            case 13:
	            	// Logic to list Suppliers
	            	System.out.println("\n--- List of Suppliers ---");
	                SupplierService.listSuppliers().forEach(System.out::println);
	                break;

	            case 14:
	            	// Logic to add a supplier
	            	System.out.println("\n--- Add a New Supplier ---");

	                // Display the list of existing manufacturers
	                List<Manufacturer> manufacturers = ManufacturerService.listManufacturers();
	                if (manufacturers.isEmpty()) {
	                    System.out.println("No manufacturers found. Please add a manufacturer first.");
	                    break;
	                }

	                System.out.println("Available Manufacturers:");
	                manufacturers.forEach(manufacturer -> 
	                    System.out.println("ID: " + manufacturer.getId() + " | Name: " + manufacturer.getName())
	                );

	                // Ask the user to select a manufacturer
	                int manufacturerId = -1;
	                boolean validManufacturer = false;
	                while (!validManufacturer) {
	                    System.out.print("Enter Manufacturer ID from the list above: ");
	                    manufacturerId = sc.nextInt();
	                    sc.nextLine(); // Consume newline

	                    // Validate if the entered manufacturer ID exists
	                    validManufacturer = ManufacturerService.isValidManufacturerId(manufacturerId);
	                    if (!validManufacturer) {
	                        System.out.println("Invalid Manufacturer ID. Please try again.");
	                    }
	                }

	                // Collect remaining supplier details
	                System.out.print("Enter Supplier Name: ");
	                String supplierName = sc.nextLine();

	                System.out.print("Enter Contact Person: ");
	                String contactPerson = sc.nextLine();

	                System.out.print("Enter Contact Number: ");
	                String contactNumber = sc.nextLine();

	                System.out.print("Enter Email: ");
	                String supplierEmail = sc.nextLine();

	                System.out.print("Enter Address: ");
	                String supplierAddress = sc.nextLine();

	                // Generate the next Supplier ID dynamically
	                int nextSupplierId = SupplierService.listSuppliers().size() + 1;

	                Supplier newSupplier = new Supplier(nextSupplierId, supplierName, contactPerson, contactNumber, supplierEmail, manufacturerId, supplierAddress);

	                try {
	                    SupplierService.addSupplier(newSupplier);
	                    System.out.println("Supplier added successfully!");
	                } catch (IllegalArgumentException e) {
	                    System.out.println("Error: " + e.getMessage());
	                }
	                break;
	            case 15:
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
	   
    public static void displayMechanicOptions(Employee employee) {
    	
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		displayMechanicMenu();
    		
    		int mechanicOption = -1;
    		boolean validInput = false;
    		
    		while(!validInput) {
    			try {
    				System.out.print("Enter your Choice: ");
    				mechanicOption = sc.nextInt();
    				sc.nextLine();
    				validInput = true;
    			}
    			catch(Exception e){
    				System.out.println("Invalid Input. Please enter a valid choice.");
    				sc.nextLine();
    				
    			}
    		}
    		
    		switch(mechanicOption) {
    			case 1:
    				//List Tasks
    				System.out.println("\n--- List of Tasks ---");
    			    TaskService.getTasksByMechanic(employee.getId()).forEach(System.out::println);
    				break;
    				
    			case 2:
    				//Complete Task
    				
    				System.out.println("\n--- Complete an Existing Task ---");
    				List<Task> tasks = TaskService.getTasksByMechanic(employee.getId());
    				if (tasks.isEmpty()) {
    			        System.out.println("No Pending tasks available to complete.");
    			        break;
    			    }
    				// Display the tasks
    			    System.out.println("Tasks Available to Complete:");
    			    tasks.forEach(task -> 
    			        System.out.println("ID: " + task.getId() + " | Description: " + task.getDescription() +
    			                           " | Status: " + task.getStatus() + " | Priority: " + task.getPriority())
    			    );
    			    
    			    
    			    
    			 // Ask the user to select a task
    			    int taskId = -1;
    			    boolean selectedValidTask = false;
    			    while (!selectedValidTask) {
    			        System.out.print("Enter the ID of the task to mark as complete: ");
    			        taskId = sc.nextInt();
    			        sc.nextLine(); // Consume newline
    			        selectedValidTask = TaskService.isValidTaskId(taskId);

    			        if (!selectedValidTask) {
    			            System.out.println("Invalid task ID. Please try again.");
    			        }
    			    }
    			    
    			    boolean isCompleted = TaskService.endTask(taskId);
    			    if(isCompleted) {
    			    	System.out.println("Task marked as COMPLETED.");
    			    	Customer customer = CustomerService.taskAssociatedCustomer(taskId);
    			    	String notificationMessage = "Your Vehicle Service is Completed.";
    		            System.out.println("Notification sent to " + customer.getFirstName() + " " + customer.getLastName() +
    		                            " (" + customer.getEmail() + "): " + notificationMessage);
    		               
    			    }
    			    else {
    			    	System.out.println("Task not found.");
    			    }
    			    
    			    
    				break;
    				
    			case 3:
    				//List Manufacturers
	            	System.out.println("\n--- List of Manufacturers ---");
	                ManufacturerService.listManufacturers().forEach(System.out::println);
    				break;
    				
    			case 4:
    				//Add Manufacturers
    				System.out.println("\n--- Add a New Manufacturer ---");

	                System.out.print("Enter Manufacturer Name: ");
	                String manufacturerName = sc.nextLine();

	                System.out.print("Enter Country: ");
	                String country = sc.nextLine();

	                int nextManufacturerId = ManufacturerService.listManufacturers().size() + 1;

	                Manufacturer newManufacturer = new Manufacturer(nextManufacturerId, manufacturerName, country);

	                try {
	                    ManufacturerService.addManufacturer(newManufacturer);
	                    System.out.println("Manufacturer added successfully!");
	                } catch (IllegalArgumentException e) {
	                    System.out.println("Error: " + e.getMessage());
	                }
    				break;
    			
    			case 5:
    				//List Suppliers
    				System.out.println("\n--- List of Suppliers ---");
	                SupplierService.listSuppliers().forEach(System.out::println);
    				break;
    				
    			case 6:
    				//Add Suppliers
    				System.out.println("\n--- Add a New Supplier ---");

	                // Display the list of existing manufacturers
	                List<Manufacturer> manufacturers = ManufacturerService.listManufacturers();
	                if (manufacturers.isEmpty()) {
	                    System.out.println("No manufacturers found. Please add a manufacturer first.");
	                    break;
	                }

	                System.out.println("Available Manufacturers:");
	                manufacturers.forEach(manufacturer -> 
	                    System.out.println("ID: " + manufacturer.getId() + " | Name: " + manufacturer.getName())
	                );

	                // Ask the user to select a manufacturer
	                int manufacturerId = -1;
	                boolean validManufacturer = false;
	                while (!validManufacturer) {
	                    System.out.print("Enter Manufacturer ID from the list above: ");
	                    manufacturerId = sc.nextInt();
	                    sc.nextLine(); // Consume newline

	                    // Validate if the entered manufacturer ID exists
	                    validManufacturer = ManufacturerService.isValidManufacturerId(manufacturerId);
	                    if (!validManufacturer) {
	                        System.out.println("Invalid Manufacturer ID. Please try again.");
	                    }
	                }

	                // Collect remaining supplier details
	                System.out.print("Enter Supplier Name: ");
	                String supplierName = sc.nextLine();

	                System.out.print("Enter Contact Person: ");
	                String contactPerson = sc.nextLine();

	                System.out.print("Enter Contact Number: ");
	                String contactNumber = sc.nextLine();

	                System.out.print("Enter Email: ");
	                String supplierEmail = sc.nextLine();

	                System.out.print("Enter Address: ");
	                String supplierAddress = sc.nextLine();

	                // Generate the next Supplier ID dynamically
	                int nextSupplierId = SupplierService.listSuppliers().size() + 1;

	                Supplier newSupplier = new Supplier(nextSupplierId, supplierName, contactPerson, contactNumber, supplierEmail, manufacturerId, supplierAddress);

	                try {
	                    SupplierService.addSupplier(newSupplier);
	                    System.out.println("Supplier added successfully!");
	                } catch (IllegalArgumentException e) {
	                    System.out.println("Error: " + e.getMessage());
	                }
    				break;
    				
    			case 7:
    				//Logging out
    				break;
    				
    			default:
    				System.out.println("Invalid Choice. Please enter valid choice.");
    				break; 			
    		}
    		System.out.println("\nPress Enter to return to the Main Menu.....");
    		sc.nextLine();
    	}
		
	}

}
