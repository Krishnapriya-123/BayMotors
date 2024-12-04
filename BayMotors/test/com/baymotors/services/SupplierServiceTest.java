package com.baymotors.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.baymotors.dao.SupplierDao;
import com.baymotors.models.Supplier;

class SupplierServiceTest {

    @BeforeEach
    void setUp() {
        // Reset in-memory data before each test
        SupplierDao.resetSuppliers(Arrays.asList(
                new Supplier(1, "ABC Supplies", "John Doe", "1234567890", "abc@example.com", 1, "123 Main Street"),
                new Supplier(2, "XYZ Parts", "Jane Smith", "9876543210", "xyz@example.com", 2, "456 Elm Street")
        ));
    }

    @Test
    void testListSuppliers() throws Exception {
        // Test fetching all suppliers
        List<Supplier> suppliers = SupplierService.listSuppliers();
        assertEquals(2, suppliers.size());
        assertEquals("ABC Supplies", suppliers.get(0).getName());
    }

    @Test
    void testAddSupplierSuccess() throws Exception {
        // Test adding a new supplier
        Supplier newSupplier = new Supplier(3, "PQR Components", "Alice Brown", "5555555555", "pqr@example.com", 3, "789 Oak Avenue");

        SupplierService.addSupplier(newSupplier);

        List<Supplier> suppliers = SupplierService.listSuppliers();
        assertEquals(3, suppliers.size());
        assertEquals("PQR Components", suppliers.get(2).getName());
    }

    @Test
    void testAddSupplierThrowsExceptionWhenEmailExists() {
        // Test adding a supplier with a duplicate email
        Supplier duplicateEmailSupplier = new Supplier(3, "New Supplier", "Bob White", "6666666666", "abc@example.com", 3, "111 Birch Street");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            SupplierService.addSupplier(duplicateEmailSupplier);
        });

        assertEquals("Supplier with this email already exists.", exception.getMessage());
    }
}
