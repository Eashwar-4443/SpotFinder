//
//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.ParkingSlot;
//import com.parking.SpotFinder_app.service.ParkingSlotService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking/slots")
//public class ParkingSlotController {
//
//    @Autowired
//    private ParkingSlotService slotService;
//
//    @GetMapping
//    public List<ParkingSlot> getAllSlots() {
//        return slotService.getAllSlots();
//    }
//
//    @GetMapping("/{id}")
//    public ParkingSlot getSlotById(@PathVariable Integer id) {
//        return slotService.getAllSlots().stream()
//                .filter(slot -> slot.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @PostMapping
//    public ParkingSlot createSlot(@RequestBody ParkingSlot slot) {
//        return slotService.createSlot(slot);
//    }
//
//    @PutMapping("/{id}")
//    public ParkingSlot updateSlot(@PathVariable Integer id, @RequestBody ParkingSlot slot) {
//        return slotService.updateSlot(id, slot);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteSlot(@PathVariable Integer id) {
//        slotService.deleteSlot(id);
//        return "Slot deleted successfully!";
//    }
//
//    @PostMapping("/book/{id}")
//    public String bookSlot(@PathVariable Integer id) {
//        return slotService.bookSlot(id);
//    }
//
//    @PostMapping("/release/{id}")
//    public String releaseSlot(@PathVariable Integer id) {
//        return slotService.releaseSlot(id);
//    }
//}


//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.ParkingSlot;
//import com.parking.SpotFinder_app.service.ParkingSlotService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking/slots")
//public class ParkingSlotController {
//
//    @Autowired
//    private ParkingSlotService slotService;
//
//    @GetMapping
//    public List<ParkingSlot> getAllSlots() {
//        return slotService.getAllSlots();
//    }
//
//    @GetMapping("/{id}")
//    public ParkingSlot getSlotById(@PathVariable Integer id) {
//        return slotService.getAllSlots().stream()
//                .filter(slot -> slot.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @PostMapping
//    public ParkingSlot createSlot(@RequestBody ParkingSlot slot) {
//        return slotService.createSlot(slot);
//    }
//
//    @PutMapping("/{id}")
//    public ParkingSlot updateSlot(@PathVariable Integer id, @RequestBody ParkingSlot slot) {
//        return slotService.updateSlot(id, slot);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteSlot(@PathVariable Integer id) {
//        slotService.deleteSlot(id);
//        return "Slot deleted successfully!";
//    }
//
//    @PostMapping("/book/{id}")
//    public String bookSlot(@PathVariable Integer id) {
//        return slotService.bookSlot(id);
//    }
//
//    @PostMapping("/release/{id}")
//    public String releaseSlot(@PathVariable Integer id) {
//        return slotService.releaseSlot(id);
//    }
//}



package com.parking.SpotFinder_app.controller;

import com.parking.SpotFinder_app.model.ParkingSlot;
import com.parking.SpotFinder_app.service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/parking/slots")
public class ParkingSlotController {

    @Autowired
    private ParkingSlotService slotService;

    @GetMapping
    public List<ParkingSlot> getAllSlots() {
        return slotService.getAllSlots();
    }

    @GetMapping("/{id}")
    public ParkingSlot getSlotById(@PathVariable Integer id) {
        return slotService.getAllSlots().stream()
                .filter(slot -> slot.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public ParkingSlot createSlot(@RequestBody ParkingSlot slot) {
        return slotService.createSlot(slot);
    }

    @PutMapping("/{id}")
    public ParkingSlot updateSlot(@PathVariable Integer id, @RequestBody ParkingSlot slot) {
        return slotService.updateSlot(id, slot);
    }

    @DeleteMapping("/{id}")
    public String deleteSlot(@PathVariable Integer id) {
        slotService.deleteSlot(id);
        return "Slot deleted successfully!";
    }

    @PostMapping("/book/{id}")
    public String bookSlot(@PathVariable Integer id) {
        return slotService.bookSlot(id);
    }

    @PostMapping("/release/{id}")
    public String releaseSlot(@PathVariable Integer id) {
        return slotService.releaseSlot(id);
    }
}
