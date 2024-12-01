package com.baymotors.services;

import com.baymotors.dao.CustomerDao;
import com.baymotors.dao.TaskDao;
import com.baymotors.dao.VehicleDao;
import com.baymotors.models.Customer;
import com.baymotors.constants.CustomerType;
import com.baymotors.models.Task;
import com.baymotors.models.Vehicle;

import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;


public class CustomerService {
	public static List<Customer> listCustomers() {
		List<Customer> customers = CustomerDao.getAllCustomers();
		 
		return customers;
	}
	
	public static void addCustomer(Customer customer) {
		CustomerDao.addCustomer(customer);
	}
	
	// Check if a customer with the given ID exists
    public static boolean isCustomerExists(int customerId) {
        return CustomerDao.getAllCustomers().stream().anyMatch(customer -> customer.getId() == customerId);
    }
    
    /**
     * Fetch a list of all Registered customers.
     *
     * @return List of Registered customers.
     */
    public static List<Customer> getRegisteredCustomers() {
        return CustomerDao.getAllCustomers().stream()
                .filter(customer -> CustomerType.REGISTERED.equals(customer.getCustomerType()))
                .collect(Collectors.toList());
    }
    
    /**
     * Fetch a list of all Walk-In customers.
     *
     * @return List of Walk-In customers.
     */
    public static List<Customer> getWalkInCustomers() {
        return CustomerDao.getAllCustomers().stream()
                .filter(customer -> CustomerType.WALKIN.equals(customer.getCustomerType()))
                .collect(Collectors.toList());
    }
    
    /**
     * Register a Walk-In customer by their ID.
     *
     * @param customerId The ID of the customer to register.
     * @return true if the customer was registered successfully, false otherwise.
     */
    public static boolean registerCustomer(int customerId) {
        // Fetch the customer by ID
        Customer customer = CustomerDao.getCustomerById(customerId);

        if (customer != null && CustomerType.WALKIN.equals(customer.getCustomerType())) {
            // Mark the customer as Registered
            customer.setCustomerType(CustomerType.REGISTERED);
            customer.setRegistrationDate(new Date());
            CustomerDao.updateCustomer(customer); // Update the customer in the DAO
            return true;
        }
        return false; // Customer not found or already registered
    }
    
    public static Customer taskAssociatedCustomer(int taskId) {
    	Task currentTask = TaskDao.getTasks().stream().filter(task -> task.getId() == taskId).findFirst().orElse(null);
    	Vehicle currentVehicle = VehicleDao.getVehicles().stream().filter(vehicle -> vehicle.getId() == currentTask.getVehicleId()).findFirst().orElse(null);
    	Customer currentCustomer = CustomerDao.getCustomerById(currentVehicle.getCustomerId()); 
    	return currentCustomer;
    }
}
