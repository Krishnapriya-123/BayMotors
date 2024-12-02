package com.baymotors.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.baymotors.models.Customer;
import com.baymotors.constants.CustomerType;
import com.baymotors.exceptions.CustomerNotFoundException;
import com.baymotors.exceptions.DuplicateCustomerException;


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
	
	/**
     * Get all customers.
     *
     * @return A list of customers.
     * @throws Exception If an error occurs while fetching customers.
     */
    public static List<Customer> getAllCustomers() throws Exception {
        try {
            return new ArrayList<>(customers);
        } catch (Exception e) {
            System.err.println("Error while fetching customers: " + e.getMessage());
            throw new Exception("Unable to fetch customers.", e);
        }
    }
	
    /**
     * Add a new customer.
     *
     * @param customer The customer to add.
     * @throws DuplicateCustomerException If a customer with the same ID or email already exists.
     */
    public static void addCustomer(Customer customer) throws DuplicateCustomerException, Exception {
        try {
            boolean exists = customers.stream()
                    .anyMatch(existingCustomer -> existingCustomer.getEmail().equalsIgnoreCase(customer.getEmail()));

            if (exists) {
                throw new DuplicateCustomerException("Customer with the email " + customer.getEmail() + " already exists.");
            }

            customers.add(customer);
        } catch (DuplicateCustomerException e) {
            System.err.println("Validation error: " + e.getMessage());
            throw e; 
        } catch (Exception e) {
            System.err.println("Error while adding customer: " + e.getMessage());
            throw new Exception("Unable to add customer.", e);
        }
    }
	
    /**
     * Find a customer by their ID.
     *
     * @param id The customer ID.
     * @return The customer object or null if not found.
     * @throws CustomerNotFoundException If the customer with the given ID is not found.
     */
    public static Customer getCustomerById(int id) throws CustomerNotFoundException, Exception {
        try {
            return customers.stream()
                    .filter(customer -> customer.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));
        } catch (CustomerNotFoundException e) {
            System.err.println("Error while finding customer: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error while fetching customer: " + e.getMessage());
            throw new Exception("Unable to fetch customer by ID " + id, e);
        }
    }
    
    /**
     * Update an existing customer.
     *
     * @param updatedCustomer The updated customer object.
     * @throws CustomerNotFoundException If the customer to be updated is not found.
     */
    public static void updateCustomer(Customer updatedCustomer) throws CustomerNotFoundException, Exception {
        try {
            boolean customerFound = false;
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getId() == updatedCustomer.getId()) {
                    customers.set(i, updatedCustomer); // Replace with updated customer
                    customerFound = true;
                    break;
                }
            }

            if (!customerFound) {
                throw new CustomerNotFoundException("Customer with ID " + updatedCustomer.getId() + " not found.");
            }
        } catch (CustomerNotFoundException e) {
            System.err.println("Error while updating customer: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error while updating customer: " + e.getMessage());
            throw new Exception("Unable to update customer.", e);
        }
    }
	
	
}

