package com.baymotors.services;

import com.baymotors.dao.ManufacturerDao;
import com.baymotors.models.Manufacturer;
import com.baymotors.exceptions.ManufacturerAlreadyExistsException;

import java.util.List;

public class ManufacturerService {
	/**
     * List all manufacturers.
     *
     * @return List of manufacturers.
     * @throws Exception If fetching manufacturers fails.
     */
    public static List<Manufacturer> listManufacturers() throws Exception {
        try {
            return ManufacturerDao.listManufacturers();
        } catch (Exception e) {
            System.err.println("Error while fetching manufacturers: " + e.getMessage());
            throw new Exception("Unable to fetch manufacturers.", e);
        }
    }

    /**
     * Add a new manufacturer.
     *
     * @param manufacturer The manufacturer to add.
     * @throws Exception If adding the manufacturer fails.
     */
    public static void addManufacturer(Manufacturer manufacturer) throws ManufacturerAlreadyExistsException, Exception {
        try {
            // Business logic: Check if the manufacturer already exists by name
            boolean exists = ManufacturerDao.listManufacturers().stream()
                    .anyMatch(m -> m.getName().equalsIgnoreCase(manufacturer.getName()));

            if (exists) {
                throw new ManufacturerAlreadyExistsException("Manufacturer with this name already exists.");
            }

            ManufacturerDao.addManufacturer(manufacturer);
        } catch (ManufacturerAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Unable to add manufacturer.", e);
        }
    }
    
    /**
     * Validate if a manufacturer ID exists.
     *
     * @param manufacturerId The ID of the manufacturer to validate.
     * @return true if the manufacturer ID is valid, false otherwise.
     * @throws Exception If validating manufacturer ID fails.
     */
    public static boolean isValidManufacturerId(int manufacturerId) throws Exception {
        try {
            return ManufacturerDao.listManufacturers().stream()
                    .anyMatch(m -> m.getId() == manufacturerId);
        } catch (Exception e) {
            System.err.println("Error while validating manufacturer ID: " + e.getMessage());
            throw new Exception("Unable to validate manufacturer ID.", e);
        }
    }
}
