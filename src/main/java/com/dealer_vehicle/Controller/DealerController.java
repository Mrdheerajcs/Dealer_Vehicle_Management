package com.dealer_vehicle.Controller;


import com.dealer_vehicle.Entity.Dealer;
import com.dealer_vehicle.Response.ApiResponse;
import com.dealer_vehicle.Service.DealerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dealers")
@CrossOrigin
public class DealerController {

    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Dealer>> saveDealer(@RequestBody Dealer dealer) {
        ApiResponse<Dealer> response = dealerService.saveDealer(dealer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<?>> getAllDealers() {
        return ResponseEntity.ok(dealerService.getAllDealers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getDealerById(@PathVariable Long id) {
        return ResponseEntity.ok(dealerService.getDealerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateDealer(@PathVariable Long id, @RequestBody Dealer dealer) {
        return ResponseEntity.ok(dealerService.updateDealer(id, dealer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteDealer(@PathVariable Long id) {
        return ResponseEntity.ok(dealerService.deleteDealer(id));
    }
}
