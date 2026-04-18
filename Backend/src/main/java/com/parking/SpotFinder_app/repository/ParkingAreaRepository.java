//
//
//package com.parking.SpotFinder_app.repository;
//
//import com.parking.SpotFinder_app.model.ParkingArea;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface ParkingAreaRepository extends JpaRepository<ParkingArea, Integer> {
//}


package com.parking.SpotFinder_app.repository;

import com.parking.SpotFinder_app.model.ParkingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingAreaRepository extends JpaRepository<ParkingArea, Integer> {
}
