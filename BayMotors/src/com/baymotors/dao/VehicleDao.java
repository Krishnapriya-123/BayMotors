package com.baymotors.dao;

import com.baymotors.models.Vehicle;

import java.util.ArrayList;
import java.util.List;


public class VehicleDao {
	
	private static final List<Vehicle> vehicles = new ArrayList<>();
	
	static {
        vehicles.add(new Vehicle(1, "AB123CD", "Toyota", "Corolla", 2020, "Blue", 101));
        vehicles.add(new Vehicle(2, "XY456EF", "Honda", "Civic", 2018, "Black", 102));
        vehicles.add(new Vehicle(3, "GH789IJ", "Ford", "Focus", 2022, "White", 103));
    }

	/**
     * Retrieve all vehicles from the list.
     *
     * @return List of vehicles.
     */
    public static List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles); // Return a copy to prevent external modification
    }

	
    /**
     * Add a new vehicle to the list.
     *
     * @param vehicle The vehicle to add.
     */
    public static void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
}
