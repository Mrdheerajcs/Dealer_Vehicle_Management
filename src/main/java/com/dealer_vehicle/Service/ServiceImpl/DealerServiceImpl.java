package com.dealer_vehicle.Service.ServiceImpl;

import com.dealer_vehicle.Entity.Dealer;
import com.dealer_vehicle.Repository.DealerRepository;
import com.dealer_vehicle.Response.ApiResponse;
import com.dealer_vehicle.Service.DealerService;
import com.dealer_vehicle.Util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealerServiceImpl implements DealerService {

    private final DealerRepository dealerRepository;

    @Override
    public ApiResponse<Dealer> saveDealer(Dealer dealer) {
        try {
            if (dealer.getName() == null || dealer.getName().trim().isEmpty()) {
                return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                        "Dealer name is required", HttpStatus.BAD_REQUEST.value());
            }

            if (dealer.getEmail() == null || dealer.getEmail().trim().isEmpty()) {
                return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                        "Dealer email is required", HttpStatus.BAD_REQUEST.value());
            }

            if (dealer.getSubscriptionType() == null) {
                return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                        "Subscription type is required (BASIC or PREMIUM)", HttpStatus.BAD_REQUEST.value());
            }

            boolean exists = dealerRepository.existsByEmailIgnoreCase(dealer.getEmail());
            if (exists) {
                return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                        "Dealer with this email already exists", HttpStatus.CONFLICT.value());
            }

            Dealer saved = dealerRepository.save(dealer);
            return ResponseUtils.createSuccessResponse(saved, new TypeReference<>() {});

        } catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    "Error saving dealer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<Dealer>> getAllDealers() {
        List<Dealer> list = dealerRepository.findAll();
        return ResponseUtils.createSuccessResponse(list, new TypeReference<>() {});
    }

    @Override
    public ApiResponse<Dealer> getDealerById(Long id) {
        Dealer dealer = dealerRepository.findById(id).orElse(null);
        if (dealer != null)
            return ResponseUtils.createSuccessResponse(dealer, new TypeReference<>() {});
        else
            return ResponseUtils.createNotFoundResponse("Dealer not found", HttpStatus.NOT_FOUND.value());
    }

    @Override
    public ApiResponse<Dealer> updateDealer(Long id, Dealer updated) {
        Dealer existing = dealerRepository.findById(id).orElse(null);
        if (existing == null)
            return ResponseUtils.createNotFoundResponse("Dealer not found", HttpStatus.NOT_FOUND.value());

        if (!existing.getEmail().equalsIgnoreCase(updated.getEmail()) &&
                dealerRepository.existsByEmailIgnoreCase(updated.getEmail())) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    "Another dealer with this email already exists", HttpStatus.CONFLICT.value());
        }

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setSubscriptionType(updated.getSubscriptionType());

        Dealer saved = dealerRepository.save(existing);
        return ResponseUtils.createSuccessResponse(saved, new TypeReference<>() {});
    }

    @Override
    public ApiResponse<String> deleteDealer(Long id) {
        if (!dealerRepository.existsById(id))
            return ResponseUtils.createNotFoundResponse("Dealer not found", HttpStatus.NOT_FOUND.value());

        dealerRepository.deleteById(id);
        return ResponseUtils.createSuccessResponse("Dealer deleted successfully", new TypeReference<>() {});
    }
}
