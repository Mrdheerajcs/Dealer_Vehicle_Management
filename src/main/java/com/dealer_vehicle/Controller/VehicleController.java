package com.dealer_vehicle.Controller;

import com.dealer_vehicle.Entity.Vehicle;
import com.dealer_vehicle.Response.ApiResponse;
import com.dealer_vehicle.Service.VehicleService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Vehicle>> saveVehicle(@RequestBody Vehicle vehicle) {
        ApiResponse<Vehicle> response = vehicleService.saveVehicle(vehicle);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<?>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.deleteVehicle(id));
    }

    @GetMapping("/premium")
    public ResponseEntity<ApiResponse<?>> getPremiumDealerVehicles() {
        return ResponseEntity.ok(vehicleService.getVehiclesOfPremiumDealers());
    }
}
