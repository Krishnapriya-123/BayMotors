package com.baymotors.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.baymotors.dao.ManufacturerDao;
import com.baymotors.models.Manufacturer;
import com.baymotors.exceptions.ManufacturerAlreadyExistsException;

class ManufacturerServiceTest {

    @BeforeEach
    void setUp() {
        // Reset in-memory data before each test
        ManufacturerDao.resetManufacturers(Arrays.asList(
                new Manufacturer(1, "Toyota", "Japan"),
                new Manufacturer(2, "Ford", "USA")
        ));
    }

    @Test
    void testListManufacturers() throws Exception {
        // Test fetching all manufacturers
        List<Manufacturer> manufacturers = ManufacturerService.listManufacturers();
        assertEquals(2, manufacturers.size());
        assertEquals("Toyota", manufacturers.get(0).getName());
    }

    @Test
    void testAddManufacturerSuccess() throws Exception {
        // Test adding a new manufacturer
        Manufacturer newManufacturer = new Manufacturer(3, "Honda", "Japan");

        ManufacturerService.addManufacturer(newManufacturer);

        List<Manufacturer> manufacturers = ManufacturerService.listManufacturers();
        assertEquals(3, manufacturers.size());
        assertEquals("Honda", manufacturers.get(2).getName());
    }

    @Test
    void testAddManufacturerThrowsExceptionWhenDuplicate() {
        // Test adding a duplicate manufacturer
        Manufacturer duplicateManufacturer = new Manufacturer(3, "Toyota", "Japan");

        ManufacturerAlreadyExistsException exception = assertThrows(ManufacturerAlreadyExistsException.class, () -> {
            ManufacturerService.addManufacturer(duplicateManufacturer);
        });

        assertEquals("Manufacturer with this name already exists.", exception.getMessage());
    }

    @Test
    void testIsValidManufacturerIdValid() throws Exception {
        // Test validating a valid manufacturer ID
        assertTrue(ManufacturerService.isValidManufacturerId(1)); // Toyota exists
    }

    @Test
    void testIsValidManufacturerIdInvalid() throws Exception {
        // Test validating an invalid manufacturer ID
        assertFalse(ManufacturerService.isValidManufacturerId(99)); // Non-existent ID
    }
}
