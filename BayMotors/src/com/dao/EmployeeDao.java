package com.dao;
import java.util.Scanner;

public class EmployeeDao {
	public static boolean validateEmployee(String username, String password, String role) {
		try {
			if("Krishna".equals(username) & "Krishna@27".equals(password) & "Manager".equals(role)) {
            	return true;
            }
		}
		catch(Exception e){
            e.printStackTrace();
        }

        return false;
	}
	
	public static void getDashboard(String role) {
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

}
