package com.baymotors.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.baymotors.models.Customer;

public class CustomerDao {
	private static final List<Customer> customers = new ArrayList<>();
	
	static {
		customers.add(new Customer(1, "John", "Doe", "john.doe@example.com", "1234567890",
		        "123 Main Street, Springfield", true, new Date()));
		
		customers.add(new Customer(2, "Jane", "Smith", "jane.smith@example.com", "9876543210",
		        "456 Elm Street, Shelbyville", false, null));
		
		customers.add(new Customer(3, "Bob", "Brown", "bob.brown@example.com", "5555555555",
		        "789 Oak Avenue, Ogdenville", true, new Date()));
	}
	
	public static List<Customer> getAllCustomers() {
        return customers;
    }
	
}

