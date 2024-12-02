package com.baymotors.services;

import com.baymotors.dao.CustomerDao;
import com.baymotors.dao.TaskDao;
import com.baymotors.dao.VehicleDao;
import com.baymotors.models.Customer;
import com.baymotors.constants.CustomerType;
import com.baymotors.models.Task;
import com.baymotors.models.Vehicle;
import com.baymotors.exceptions.CustomerNotFoundException;
import com.baymotors.exceptions.DuplicateCustomerException;
import com.baymotors.exceptions.InvalidCustomerException;

import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;


public class CustomerService {
	public static List<Customer> listCustomers() throws Exception {
        try {
            return CustomerDao.getAllCustomers();
        } catch (Exception e) {
            System.err.println("Error while fetching customers: " + e.getMessage());
            throw new Exception("Unable to fetch customers.", e);
        }
    }
	public static void addCustomer(Customer customer) throws Exception {
        try {
            CustomerDao.addCustomer(customer);
        } catch (DuplicateCustomerException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error while adding customer: " + e.getMessage());
            throw new Exception("Unable to add customer.", e);
        }
    }
	
	// Check if a customer with the given ID exists
	public static boolean isCustomerExists(int customerId) throws Exception {
        try {
            return CustomerDao.getAllCustomers().stream()
                    .anyMatch(customer -> customer.getId() == customerId);
        } catch(InvalidCustomerException e) {
        	System.err.println("Customer not found: " + e.getMessage());
        	throw e;
        }
        catch (Exception e) {
            System.err.println("Error while checking customer existence: " + e.getMessage());
            throw new Exception("Unable to check if customer exists.", e);
        }
    }
    
    /**
     * Fetch a list of all Registered customers.
     *
     * @return List of Registered customers.
     */
	public static List<Customer> getRegisteredCustomers() throws Exception {
        try {
            return CustomerDao.getAllCustomers().stream()
                    .filter(customer -> CustomerType.REGISTERED.equals(customer.getCustomerType()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error while fetching registered customers: " + e.getMessage());
            throw new Exception("Unable to fetch registered customers.", e);
        }
    }
    
    /**
     * Fetch a list of all Walk-In customers.
     *
     * @return List of Walk-In customers.
     */
	public static List<Customer> getWalkInCustomers() throws Exception {
        try {
            return CustomerDao.getAllCustomers().stream()
                    .filter(customer -> CustomerType.WALKIN.equals(customer.getCustomerType()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error while fetching walk-in customers: " + e.getMessage());
            throw new Exception("Unable to fetch walk-in customers.", e);
        }
    }
    
    /**
     * Register a Walk-In customer by their ID.
     *
     * @param customerId The ID of the customer to register.
     * @return true if the customer was registered successfully, false otherwise.
     */
	public static boolean registerCustomer(int customerId) throws Exception {
        try {
            Customer customer = CustomerDao.getCustomerById(customerId);

            if (customer != null && CustomerType.WALKIN.equals(customer.getCustomerType())) {
                customer.setCustomerType(CustomerType.REGISTERED);
                customer.setRegistrationDate(new Date());
                CustomerDao.updateCustomer(customer);
                return true;
            }
        } catch (CustomerNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error while registering customer: " + e.getMessage());
            throw new Exception("Unable to register customer.", e);
        }
        return false; // Customer not found or already registered
    }
    
	public static Customer taskAssociatedCustomer(int taskId) throws Exception {
        try {
            Task currentTask = TaskDao.getTasks().stream()
                    .filter(task -> task.getId() == taskId)
                    .findFirst()
                    .orElseThrow(() -> new Exception("Task with ID " + taskId + " not found."));

            Vehicle currentVehicle = VehicleDao.getVehicles().stream()
                    .filter(vehicle -> vehicle.getId() == currentTask.getVehicleId())
                    .findFirst()
                    .orElseThrow(() -> new Exception("Vehicle associated with task ID " + taskId + " not found."));

            return CustomerDao.getCustomerById(currentVehicle.getCustomerId());
        } catch (Exception e) {
            System.err.println("Error while fetching customer associated with task: " + e.getMessage());
            throw new Exception("Unable to fetch customer associated with task.", e);
        }
    }
}
