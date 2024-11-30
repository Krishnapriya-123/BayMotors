package com.baymotors.services;

import com.baymotors.models.Vehicle;
import com.baymotors.dao.VehicleDao;

import java.util.List;


public class VehicleService {
	public static List<Vehicle> getVehicles(){
		return VehicleDao.getVehicles();
	}
	
	public static void logVehicle(Vehicle vehicle) {
		VehicleDao.addVehicle(vehicle);
	}
	
	public static boolean isVehicleExists(int vehicleId) {
		return VehicleDao.getVehicles().stream().anyMatch(vehicle -> vehicle.getId() == vehicleId);
	}

}
