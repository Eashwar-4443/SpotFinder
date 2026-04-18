//
//package com.parking.SpotFinder_app.service;
//
//import com.parking.SpotFinder_app.model.Vehicle;
//import com.parking.SpotFinder_app.repository.VehicleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class VehicleService {
//
//    @Autowired
//    private VehicleRepository vehicleRepo;
//
//    public List<Vehicle> getAllVehicles() {
//        return vehicleRepo.findAll();
//    }
//
//    public Vehicle createVehicle(Vehicle vehicle) {
//        return vehicleRepo.save(vehicle);
//    }
//
//    public Vehicle updateVehicle(Integer id, Vehicle vehicle) {
//        vehicle.setId(id);
//        return vehicleRepo.save(vehicle);
//    }
//
//    public void deleteVehicle(Integer id) {
//        vehicleRepo.deleteById(id);
//    }
//}


//
//package com.parking.SpotFinder_app.service;
//
//import com.parking.SpotFinder_app.model.Vehicle;
//import com.parking.SpotFinder_app.repository.VehicleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class VehicleService {
//
//    @Autowired
//    private VehicleRepository vehicleRepo;
//
//    public List<Vehicle> getAllVehicles() {
//        return vehicleRepo.findAll();
//    }
//
//    public Vehicle createVehicle(Vehicle vehicle) {
//        return vehicleRepo.save(vehicle);
//    }
//
//    public Vehicle updateVehicle(Integer id, Vehicle vehicle) {
//        vehicle.setId(id);
//        return vehicleRepo.save(vehicle);
//    }
//
//    public void deleteVehicle(Integer id) {
//        vehicleRepo.deleteById(id);
//    }
//}


package com.parking.SpotFinder_app.service;

import com.parking.SpotFinder_app.model.Vehicle;
import com.parking.SpotFinder_app.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepo;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    public Vehicle updateVehicle(Integer id, Vehicle vehicle) {
        vehicle.setId(id);
        return vehicleRepo.save(vehicle);
    }

    public void deleteVehicle(Integer id) {
        vehicleRepo.deleteById(id);
    }
}
