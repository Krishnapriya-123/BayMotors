package com.baymotors.dao;

import com.baymotors.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    
    private static final List<Vehicle> vehicles = new ArrayList<>();
    
    static {
        vehicles.add(new Vehicle(1, "AB123CD", "Toyota", "Corolla", 2020, "Blue", 1));
        vehicles.add(new Vehicle(2, "XY456EF", "Honda", "Civic", 2018, "Black", 2));
        vehicles.add(new Vehicle(3, "GH789IJ", "Ford", "Focus", 2022, "White", 3));
    }

    /**
     * Retrieve all vehicles from the list.
     *
     * @return List of vehicles.
     * @throws Exception If fetching vehicles fails.
     */
    public static List<Vehicle> getVehicles() throws Exception {
        try {
            return new ArrayList<>(vehicles); // Return a copy to prevent external modification
        } catch (Exception e) {
            System.err.println("Error while fetching vehicles: " + e.getMessage());
            throw new Exception("Unable to fetch vehicles.", e);
        }
    }

    /**
     * Add a new vehicle to the list.
     *
     * @param vehicle The vehicle to add.
     * @throws Exception If adding the vehicle fails.
     */
    public static void addVehicle(Vehicle vehicle) throws Exception {
        try {
            vehicles.add(vehicle);
        } catch (Exception e) {
            System.err.println("Error while adding vehicle: " + e.getMessage());
            throw new Exception("Unable to add vehicle.", e);
        }
    }
}
