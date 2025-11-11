package com.dealer_vehicle.Repository;

import com.dealer_vehicle.Entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
    boolean existsByEmailIgnoreCase(String email);
}
