//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.Reservation;
//import com.parking.SpotFinder_app.model.ReservationRequest;
//import com.parking.SpotFinder_app.service.ReservationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking/reservations")
//public class ReservationController {
//
//    @Autowired
//    private ReservationService reservationService;
//
//    @GetMapping
//    public List<Reservation> getAllReservations() {
//        return reservationService.getAllReservations();
//    }
//
//    @GetMapping("/{id}")
//    public Reservation getReservationById(@PathVariable Integer id) {
//        return reservationService.getAllReservations().stream()
//                .filter(reservation -> reservation.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @PostMapping
//    public Reservation createReservation(@RequestBody ReservationRequest request) {
//        return reservationService.createReservation(request);
//    }
//
//    @PutMapping("/{id}")
//    public Reservation updateReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {
//        return reservationService.updateReservation(id, reservation);
//    }
//
//    @DeleteMapping("/{id}")
//    public String cancelReservation(@PathVariable Integer id) {
//        reservationService.cancelReservation(id);
//        return "Reservation cancelled!";
//    }
//}

//
//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.Reservation;
//import com.parking.SpotFinder_app.model.ReservationRequest;
//import com.parking.SpotFinder_app.service.ReservationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking/reservations")
//public class ReservationController {
//
//    @Autowired
//    private ReservationService reservationService;
//
//    @GetMapping
//    public List<Reservation> getAllReservations() {
//        return reservationService.getAllReservations();
//    }
//
//    @GetMapping("/{id}")
//    public Reservation getReservationById(@PathVariable Integer id) {
//        return reservationService.getAllReservations().stream()
//                .filter(reservation -> reservation.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @PostMapping
//    public Reservation createReservation(@RequestBody ReservationRequest request) {
//        return reservationService.createReservation(request);
//    }
//
//    @PutMapping("/{id}")
//    public Reservation updateReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {
//        return reservationService.updateReservation(id, reservation);
//    }
//
//    @DeleteMapping("/{id}")
//    public String cancelReservation(@PathVariable Integer id) {
//        reservationService.cancelReservation(id);
//        return "Reservation cancelled!";
//    }
//}



package com.parking.SpotFinder_app.controller;

import com.parking.SpotFinder_app.model.Reservation;
import com.parking.SpotFinder_app.model.ReservationRequest;
import com.parking.SpotFinder_app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/parking/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Integer id) {
        return reservationService.getAllReservations().stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationRequest request) {
        return reservationService.createReservation(request);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public String cancelReservation(@PathVariable Integer id) {
        reservationService.cancelReservation(id);
        return "Reservation cancelled!";
    }
}
