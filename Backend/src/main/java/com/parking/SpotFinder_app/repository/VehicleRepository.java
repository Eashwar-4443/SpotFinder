//
//package com.parking.SpotFinder_app.repository;
//
//import com.parking.SpotFinder_app.model.ParkingSlot;
//import com.parking.SpotFinder_app.model.Vehicle;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
//    Optional<Vehicle> findByParkingSlot(ParkingSlot parkingSlot);
//}


package com.parking.SpotFinder_app.repository;

import com.parking.SpotFinder_app.model.ParkingSlot;
import com.parking.SpotFinder_app.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findByParkingSlot(ParkingSlot parkingSlot);
}
