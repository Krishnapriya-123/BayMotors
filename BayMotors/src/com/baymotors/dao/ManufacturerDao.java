package com.baymotors.dao;

import com.baymotors.models.Manufacturer;

import java.util.ArrayList;
import java.util.List;

public class ManufacturerDao {
    private static final List<Manufacturer> manufacturers = new ArrayList<>();

    static {
        manufacturers.add(new Manufacturer(1, "Toyota", "Japan"));
        manufacturers.add(new Manufacturer(2, "Ford", "USA"));
    }

    /**
     * Fetch the list of manufacturers.
     *
     * @return List of manufacturers.
     * @throws Exception If fetching manufacturers fails.
     */
    public static List<Manufacturer> listManufacturers() throws Exception {
        try {
            return new ArrayList<>(manufacturers);
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
    public static void addManufacturer(Manufacturer manufacturer) throws Exception {
        try {
            manufacturers.add(manufacturer);
        } catch (Exception e) {
            System.err.println("Error while adding manufacturer: " + e.getMessage());
            throw new Exception("Unable to add manufacturer.", e);
        }
    }
}
