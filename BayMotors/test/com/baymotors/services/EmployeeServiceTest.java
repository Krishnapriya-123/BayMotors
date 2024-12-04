package com.baymotors.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.baymotors.dao.EmployeeDao;
import com.baymotors.models.Employee;
import com.baymotors.models.Manager;
import com.baymotors.models.Mechanic;


class EmployeeServiceTest {
			
	@BeforeEach
    void setUp() {
        // Replace EmployeeDao with a temporary in-memory list for testing
        EmployeeDao.resetEmployees(Arrays.asList(
            new Manager(1, "admin", "admin", "John", "Doe", "admin@example.com", "1234567890", "Manager", "123 Bay Street"),
            new Mechanic(2, "mechanic1", "pass1", "Jane", "Doe", "mechanic1@example.com", "0987654321", "Mechanic", "456 Bay Avenue")
        ));
    }
	
	@Test
    void testValidateEmployeeSuccess() throws Exception {
        Employee result = EmployeeService.validateEmployee("admin", "admin", "Manager");
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

	@Test
	void testValidateEmployeeFailure() {
	    Exception exception = assertThrows(Exception.class, () -> {
	        EmployeeService.validateEmployee("wronguser", "wrongpass", "Manager");
	    });
	    assertEquals("Invalid login credentials for username: wronguser", exception.getMessage());
	}

    @Test
    void testGetEmployees() throws Exception {
        List<Employee> employees = EmployeeService.getEmployees();
        assertEquals(2, employees.size());
    }

    @Test
    void testIsEmployeeExists() throws Exception {
        boolean exists = EmployeeService.isEmployeeExists(1);
        assertTrue(exists);

        boolean notExists = EmployeeService.isEmployeeExists(99);
        assertFalse(notExists);
    }

}
