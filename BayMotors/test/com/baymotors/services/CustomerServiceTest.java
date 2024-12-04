package com.baymotors.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.baymotors.constants.CustomerType;
import com.baymotors.dao.CustomerDao;
import com.baymotors.dao.TaskDao;
import com.baymotors.dao.VehicleDao;
import com.baymotors.models.Customer;
import com.baymotors.models.Task;
import com.baymotors.models.Vehicle;

class CustomerServiceTest {

    @BeforeEach
    void setUp() {
        // Reset in-memory data before each test
        CustomerDao.resetCustomers(Arrays.asList(
                new Customer(1, "John", "Doe", "john.doe@example.com", "1234567890", "123 Main St", CustomerType.REGISTERED, new Date()),
                new Customer(2, "Jane", "Doe", "jane.doe@example.com", "9876543210", "456 Elm St", CustomerType.WALKIN, null)
        ));

        TaskDao.resetTasks(Arrays.asList(
                new Task(1, 1, 1, "Oil Change", "Pending", "High", null, 0, null)
        ));

        VehicleDao.resetVehicles(Arrays.asList(
                new Vehicle(1, "AB123CD", "Toyota", "Corolla", 2020, "Blue", 1)
        ));
    }

    @Test
    void testListCustomers() throws Exception {
        List<Customer> customers = CustomerService.listCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    void testAddCustomer() throws Exception {
        Customer newCustomer = new Customer(3, "Alice", "Smith", "alice@example.com", "5555555555", "789 Oak St", CustomerType.WALKIN, null);

        CustomerService.addCustomer(newCustomer);

        List<Customer> customers = CustomerService.listCustomers();
        assertEquals(3, customers.size());
        assertEquals("Alice", customers.get(2).getFirstName());
    }

    @Test
    void testIsCustomerExists() throws Exception {
        assertTrue(CustomerService.isCustomerExists(1));
        assertFalse(CustomerService.isCustomerExists(99));
    }

    @Test
    void testGetRegisteredCustomers() throws Exception {
        List<Customer> registeredCustomers = CustomerService.getRegisteredCustomers();
        assertEquals(1, registeredCustomers.size());
        assertEquals("John", registeredCustomers.get(0).getFirstName());
    }

    @Test
    void testGetWalkInCustomers() throws Exception {
        List<Customer> walkInCustomers = CustomerService.getWalkInCustomers();
        assertEquals(1, walkInCustomers.size());
        assertEquals("Jane", walkInCustomers.get(0).getFirstName());
    }

    @Test
    void testRegisterCustomerSuccess() throws Exception {
        boolean result = CustomerService.registerCustomer(2); // Jane is a Walk-In customer
        assertTrue(result);

        List<Customer> registeredCustomers = CustomerService.getRegisteredCustomers();
        assertEquals(2, registeredCustomers.size());
    }

    @Test
    void testRegisterCustomerFailure() {
        Exception exception = assertThrows(Exception.class, () -> {
        	CustomerService.registerCustomer(99); // Non-existent customer
        });
        assertEquals("Unable to register customer.", exception.getMessage());
    }

    @Test
    void testTaskAssociatedCustomerSuccess() throws Exception {
        Customer customer = CustomerService.taskAssociatedCustomer(1); // Task 1 belongs to Vehicle 1, owned by John
        assertNotNull(customer);
        assertEquals("John", customer.getFirstName());
    }

    @Test
    void testTaskAssociatedCustomerFailure() {
        Exception exception = assertThrows(Exception.class, () -> {
        	CustomerService.taskAssociatedCustomer(99); // Non-existent task
        });
        assertTrue(exception.getMessage().contains("Task with ID 99 not found."));
    }
}
