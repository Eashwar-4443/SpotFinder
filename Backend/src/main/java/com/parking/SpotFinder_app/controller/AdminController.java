package com.parking.SpotFinder_app.controller;

import com.parking.SpotFinder_app.model.ParkingArea;
import com.parking.SpotFinder_app.repository.ParkingAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private ParkingAreaRepository areaRepo;

    @GetMapping("/areas")
    public List<ParkingArea> getAllAreas() {
        return areaRepo.findAll();
    }

    @PostMapping("/areas")
    public ParkingArea addArea(@RequestBody ParkingArea area) {
        return areaRepo.save(area);
    }

    @DeleteMapping("/areas/{id}")
    public String deleteArea(@PathVariable int id) {
        areaRepo.deleteById(id);
        return "Parking area deleted!";
    }
}
