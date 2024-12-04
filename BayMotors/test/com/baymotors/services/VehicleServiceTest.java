package com.baymotors.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.baymotors.dao.VehicleDao;
import com.baymotors.exceptions.VehicleNotFoundException;
import com.baymotors.models.Vehicle;

class VehicleServiceTest {

    @BeforeEach
    void setUp() {
        // Reset in-memory data before each test
        VehicleDao.resetVehicles(Arrays.asList(
                new Vehicle(1, "AB123CD", "Toyota", "Corolla", 2020, "Blue", 1),
                new Vehicle(2, "XY456EF", "Honda", "Civic", 2018, "Black", 2),
                new Vehicle(3, "GH789IJ", "Ford", "Focus", 2022, "White", 3)
        ));
    }

    @Test
    void testGetVehicles() throws Exception {
        // Test fetching all vehicles
        List<Vehicle> vehicles = VehicleService.getVehicles();
        assertEquals(3, vehicles.size());
        assertEquals("Toyota", vehicles.get(0).getMake());
    }

    @Test
    void testLogVehicleSuccess() throws Exception {
        // Test adding a new vehicle
        Vehicle newVehicle = new Vehicle(4, "LM012OP", "Mazda", "CX-5", 2021, "Red", 4);

        VehicleService.logVehicle(newVehicle);

        List<Vehicle> vehicles = VehicleService.getVehicles();
        assertEquals(4, vehicles.size());
        assertEquals("Mazda", vehicles.get(3).getMake());
    }

    @Test
    void testIsVehicleExistsSuccess() throws Exception {
        // Test checking existence of a valid vehicle
        assertTrue(VehicleService.isVehicleExists(1)); // Vehicle with ID 1 exists
    }

    @Test
    void testIsVehicleExistsFailure() {
        // Test checking existence of an invalid vehicle
        VehicleNotFoundException exception = assertThrows(VehicleNotFoundException.class, () -> {
            VehicleService.isVehicleExists(99); // Non-existent vehicle ID
        });

        assertEquals("Vehicle with ID 99 not found.", exception.getMessage());
    }
}
