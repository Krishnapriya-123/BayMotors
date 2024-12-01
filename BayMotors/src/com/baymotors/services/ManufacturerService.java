package com.baymotors.services;

import com.baymotors.dao.ManufacturerDao;
import com.baymotors.models.Manufacturer;

import java.util.List;

public class ManufacturerService {
    /**
     * List all manufacturers.
     *
     * @return List of manufacturers.
     */
    public static List<Manufacturer> listManufacturers() {
        return ManufacturerDao.listManufacturers();
    }

    /**
     * Add a new manufacturer.
     *
     * @param manufacturer The manufacturer to add.
     */
    public static void addManufacturer(Manufacturer manufacturer) {
        // Business logic: Check if the manufacturer already exists by name
        boolean exists = ManufacturerDao.listManufacturers().stream()
                .anyMatch(m -> m.getName().equalsIgnoreCase(manufacturer.getName()));

        if (exists) {
            throw new IllegalArgumentException("Manufacturer with this name already exists.");
        }

        ManufacturerDao.addManufacturer(manufacturer);
    }
    
    public static boolean isValidManufacturerId(int manufacturerId) {
    	return ManufacturerDao.listManufacturers().stream().anyMatch(m -> m.getId() == manufacturerId);

    }
}
