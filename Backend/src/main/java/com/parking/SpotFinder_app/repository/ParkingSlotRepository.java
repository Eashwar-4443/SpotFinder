//
//
//package com.parking.SpotFinder_app.repository;
//
//import com.parking.SpotFinder_app.model.ParkingSlot;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer> {
//}
//

package com.parking.SpotFinder_app.repository;

import com.parking.SpotFinder_app.model.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer> {
}

