//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.Vehicle;
//import com.parking.SpotFinder_app.service.VehicleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking/vehicles")
//public class VehicleController {
//
//    @Autowired
//    private VehicleService vehicleService;
//
//    @GetMapping
//    public List<Vehicle> getAllVehicles() {
//        return vehicleService.getAllVehicles();
//    }
//
//    @GetMapping("/{id}")
//    public Vehicle getVehicleById(@PathVariable Integer id) {
//        return vehicleService.getAllVehicles().stream()
//                .filter(vehicle -> vehicle.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @PostMapping
//    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
//        return vehicleService.createVehicle(vehicle);
//    }
//
//    @PutMapping("/{id}")
//    public Vehicle updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
//        return vehicleService.updateVehicle(id, vehicle);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteVehicle(@PathVariable Integer id) {
//        vehicleService.deleteVehicle(id);
//        return "Vehicle deleted successfully!";
//    }
//}

//
//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.Vehicle;
//import com.parking.SpotFinder_app.service.VehicleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking/vehicles")
//public class VehicleController {
//
//    @Autowired
//    private VehicleService vehicleService;
//
//    @GetMapping
//    public List<Vehicle> getAllVehicles() {
//        return vehicleService.getAllVehicles();
//    }
//
//    @GetMapping("/{id}")
//    public Vehicle getVehicleById(@PathVariable Integer id) {
//        return vehicleService.getAllVehicles().stream()
//                .filter(vehicle -> vehicle.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @PostMapping
//    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
//        return vehicleService.createVehicle(vehicle);
//    }
//
//    @PutMapping("/{id}")
//    public Vehicle updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
//        return vehicleService.updateVehicle(id, vehicle);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteVehicle(@PathVariable Integer id) {
//        vehicleService.deleteVehicle(id);
//        return "Vehicle deleted successfully!";
//    }
//}



package com.parking.SpotFinder_app.controller;

import com.parking.SpotFinder_app.model.Vehicle;
import com.parking.SpotFinder_app.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/parking/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Integer id) {
        return vehicleService.getAllVehicles().stream()
                .filter(vehicle -> vehicle.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Integer id) {
        vehicleService.deleteVehicle(id);
        return "Vehicle deleted successfully!";
    }
}
