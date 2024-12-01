package com.baymotors.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.baymotors.models.Customer;
import com.baymotors.constants.CustomerType;

public class CustomerDao {
	private static final List<Customer> customers = new ArrayList<>();
	
	static {
		customers.add(new Customer(1, "John", "Doe", "john.doe@example.com", "1234567890",
		        "123 Main Street, Springfield", CustomerType.REGISTERED, new Date()));
		
		customers.add(new Customer(2, "Jane", "Smith", "jane.smith@example.com", "9876543210",
		        "456 Elm Street, Shelbyville", CustomerType.WALKIN, null));
		
		customers.add(new Customer(3, "Bob", "Brown", "bob.brown@example.com", "5555555555",
		        "789 Oak Avenue, Ogdenville", CustomerType.REGISTERED, new Date()));
	}
	
	public static List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
	
	public static void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	/**
     * Find a customer by their ID.
     *
     * @param id The customer ID.
     * @return The customer object or null if not found.
     */
    public static Customer getCustomerById(int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Update an existing customer.
     *
     * @param updatedCustomer The updated customer object.
     */
    public static void updateCustomer(Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == updatedCustomer.getId()) {
                customers.set(i, updatedCustomer); // Replace with the updated customer
                break;
            }
        }
    }
	
	
}

