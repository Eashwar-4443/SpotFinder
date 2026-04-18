//
//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.ParkingArea;
//import com.parking.SpotFinder_app.service.ParkingAreaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking/areas")
//public class ParkingAreaController {
//
//    @Autowired
//    private ParkingAreaService areaService;
//
//    @GetMapping
//    public List<ParkingArea> getAllAreas() {
//        return areaService.getAllAreas();
//    }
//
//    @PostMapping
//    public ParkingArea createArea(@RequestBody ParkingArea area) {
//        return areaService.createArea(area);
//    }
//
//    @PutMapping("/{id}")
//    public ParkingArea updateArea(@PathVariable Integer id, @RequestBody ParkingArea area) {
//        return areaService.updateArea(id, area);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteArea(@PathVariable Integer id) {
//        areaService.deleteArea(id);
//        return "Parking area deleted!";
//    }
//}

//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.ParkingArea;
//import com.parking.SpotFinder_app.service.ParkingAreaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking/areas")
//public class ParkingAreaController {
//
//    @Autowired
//    private ParkingAreaService areaService;
//
//    @GetMapping
//    public List<ParkingArea> getAllAreas() {
//        return areaService.getAllAreas();
//    }
//
//    @PostMapping
//    public ParkingArea createArea(
//            @RequestHeader(value = "X-User-Role", required = false) String role,
//            @RequestBody ParkingArea area) {
//        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admin users can create parking areas.");
//        }
//        return areaService.createArea(area);
//    }
//
//    @PutMapping("/{id}")
//    public ParkingArea updateArea(@PathVariable Integer id, @RequestBody ParkingArea area) {
//        return areaService.updateArea(id, area);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteArea(@PathVariable Integer id) {
//        areaService.deleteArea(id);
//        return "Parking area deleted!";
//    }
//}


package com.parking.SpotFinder_app.controller;

import com.parking.SpotFinder_app.model.ParkingArea;
import com.parking.SpotFinder_app.service.ParkingAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/parking/areas")
public class ParkingAreaController {

    @Autowired
    private ParkingAreaService areaService;

    @GetMapping
    public List<ParkingArea> getAllAreas() {
        return areaService.getAllAreas();
    }

    @PostMapping
    public ParkingArea createArea(
            @RequestHeader(value = "X-User-Role", required = false) String role,
            @RequestBody ParkingArea area) {
        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admin users can create parking areas.");
        }
        return areaService.createArea(area);
    }

    @PutMapping("/{id}")
    public ParkingArea updateArea(@PathVariable Integer id, @RequestBody ParkingArea area) {
        return areaService.updateArea(id, area);
    }

    @DeleteMapping("/{id}")
    public String deleteArea(@PathVariable Integer id) {
        areaService.deleteArea(id);
        return "Parking area deleted!";
    }
}

