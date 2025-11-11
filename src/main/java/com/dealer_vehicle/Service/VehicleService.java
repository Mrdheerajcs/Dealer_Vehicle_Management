package com.dealer_vehicle.Service;

import com.dealer_vehicle.Entity.Vehicle;
import com.dealer_vehicle.Response.ApiResponse;

import java.util.List;

public interface VehicleService {
    ApiResponse<Vehicle> saveVehicle(Vehicle vehicle);

    ApiResponse<List<Vehicle>> getAllVehicles();

    ApiResponse<Vehicle> getVehicleById(Long id);

    ApiResponse<Vehicle> updateVehicle(Long id, Vehicle updated);

    ApiResponse<String> deleteVehicle(Long id);

    ApiResponse<List<Vehicle>> getVehiclesOfPremiumDealers();
}
