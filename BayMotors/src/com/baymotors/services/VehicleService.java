package com.baymotors.services;

import com.baymotors.models.Vehicle;
import com.baymotors.dao.VehicleDao;
import com.baymotors.exceptions.VehicleNotFoundException;

import java.util.List;

public class VehicleService {

    /**
     * Fetch all vehicles.
     *
     * @return List of vehicles.
     * @throws Exception If fetching vehicles fails.
     */
    public static List<Vehicle> getVehicles() throws Exception {
        try {
            return VehicleDao.getVehicles();
        } catch (Exception e) {
            System.err.println("Error while fetching vehicles: " + e.getMessage());
            throw new Exception("Unable to fetch vehicles.", e);
        }
    }

    /**
     * Log a new vehicle.
     *
     * @param vehicle The vehicle to add.
     * @throws Exception If adding the vehicle fails.
     */
    public static void logVehicle(Vehicle vehicle) throws Exception {
        try {
            VehicleDao.addVehicle(vehicle);
        } catch (Exception e) {
            System.err.println("Error while logging vehicle: " + e.getMessage());
            throw new Exception("Unable to log vehicle.", e);
        }
    }

    /**
     * Check if a vehicle exists by ID.
     *
     * @param vehicleId The ID of the vehicle to check.
     * @return true if the vehicle exists, false otherwise.
     * @throws Exception If validating vehicle existence fails.
     */
    public static boolean isVehicleExists(int vehicleId) throws VehicleNotFoundException, Exception {
        try {
            boolean exists = VehicleDao.getVehicles().stream()
                    .anyMatch(vehicle -> vehicle.getId() == vehicleId);
            if (!exists) {
                throw new VehicleNotFoundException("Vehicle with ID " + vehicleId + " not found.");
            }
            return exists;
        } catch (VehicleNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new VehicleNotFoundException("Unable to check if vehicle exists for ID " + vehicleId);
        }
    }
}
