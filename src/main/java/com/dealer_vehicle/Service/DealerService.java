package com.dealer_vehicle.Service;

import com.dealer_vehicle.Entity.Dealer;
import com.dealer_vehicle.Response.ApiResponse;

import java.util.List;

public interface DealerService {
    ApiResponse<Dealer> saveDealer(Dealer dealer);

    ApiResponse<List<Dealer>> getAllDealers();

    ApiResponse<Dealer> getDealerById(Long id);

    ApiResponse<Dealer> updateDealer(Long id, Dealer updated);

    ApiResponse<String> deleteDealer(Long id);
}
