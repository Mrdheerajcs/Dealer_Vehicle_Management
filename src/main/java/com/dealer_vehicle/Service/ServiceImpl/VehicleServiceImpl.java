package com.dealer_vehicle.Service.ServiceImpl;

import com.dealer_vehicle.Entity.Dealer;
import com.dealer_vehicle.Entity.Vehicle;
import com.dealer_vehicle.Repository.VehicleRepository;
import com.dealer_vehicle.Response.ApiResponse;
import com.dealer_vehicle.Service.VehicleService;
import com.dealer_vehicle.Util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public ApiResponse<Vehicle> saveVehicle(Vehicle vehicle) {
        try {
            Vehicle saved = vehicleRepository.save(vehicle);
            return ResponseUtils.createSuccessResponse(saved, new TypeReference<>() {});
        } catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {}, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
    @Override
    public ApiResponse<List<Vehicle>> getAllVehicles() {
        List<Vehicle> list = vehicleRepository.findAll();
        return ResponseUtils.createSuccessResponse(list, new TypeReference<>() {});
    }
    @Override
    public ApiResponse<Vehicle> getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        if (vehicle != null)
            return ResponseUtils.createSuccessResponse(vehicle, new TypeReference<>() {});
        else
            return ResponseUtils.createNotFoundResponse("Vehicle not found", HttpStatus.NOT_FOUND.value());
    }
    @Override
    public ApiResponse<Vehicle> updateVehicle(Long id, Vehicle updated) {
        Vehicle existing = vehicleRepository.findById(id).orElse(null);
        if (existing == null)
            return ResponseUtils.createNotFoundResponse("Vehicle not found", HttpStatus.NOT_FOUND.value());

        existing.setModel(updated.getModel());
        existing.setPrice(updated.getPrice());
        existing.setStatus(updated.getStatus());
        existing.setDealer(updated.getDealer());
        Vehicle saved = vehicleRepository.save(existing);
        return ResponseUtils.createSuccessResponse(saved, new TypeReference<>() {});
    }
    @Override
    public ApiResponse<String> deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id))
            return ResponseUtils.createNotFoundResponse("Vehicle not found", HttpStatus.NOT_FOUND.value());

        vehicleRepository.deleteById(id);
        return ResponseUtils.createSuccessResponse("Vehicle deleted successfully", new TypeReference<>() {});
    }
    @Override
    public ApiResponse<List<Vehicle>> getVehiclesOfPremiumDealers() {
        List<Vehicle> list = vehicleRepository.findAllByDealerSubscriptionType(Dealer.SubscriptionType.PREMIUM);
        return ResponseUtils.createSuccessResponse(list, new TypeReference<>() {});
    }
}
