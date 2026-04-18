//
//package com.parking.SpotFinder_app.repository;
//
//import com.parking.SpotFinder_app.model.Reservation;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
//}


package com.parking.SpotFinder_app.repository;

import com.parking.SpotFinder_app.model.ParkingSlot;
import com.parking.SpotFinder_app.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findBySlot(ParkingSlot slot);
    void deleteBySlot(ParkingSlot slot);
    void deleteBySlotId(Integer slotId);
}
