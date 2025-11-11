package com.dealer_vehicle.Repository;


import com.dealer_vehicle.Entity.Dealer;
import com.dealer_vehicle.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE v.dealer.subscriptionType = :type")
    List<Vehicle> findAllByDealerSubscriptionType(Dealer.SubscriptionType type);
}
