//
//
//package com.parking.SpotFinder_app.service;
//
//import com.parking.SpotFinder_app.model.ParkingSlot;
//import com.parking.SpotFinder_app.model.Reservation;
//import com.parking.SpotFinder_app.model.ReservationRequest;
//import com.parking.SpotFinder_app.model.User;
//import com.parking.SpotFinder_app.model.Vehicle;
//import com.parking.SpotFinder_app.repository.ParkingSlotRepository;
//import com.parking.SpotFinder_app.repository.ReservationRepository;
//import com.parking.SpotFinder_app.repository.UserRepository;
//import com.parking.SpotFinder_app.repository.VehicleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class ReservationService {
//
//    @Autowired
//    private ReservationRepository reservationRepo;
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Autowired
//    private ParkingSlotRepository slotRepo;
//
//    @Autowired
//    private VehicleRepository vehicleRepo;
//
//    public List<Reservation> getAllReservations() {
//        return reservationRepo.findAll();
//    }
//
//    @Transactional
//    public Reservation createReservation(ReservationRequest request) {
//        if (request.getUserId() == null || request.getSlotId() == null) {
//            throw new IllegalArgumentException("userId and slotId are required.");
//        }
//
//        User user = userRepo.findById(request.getUserId()).orElse(null);
//        ParkingSlot slot = slotRepo.findById(request.getSlotId()).orElse(null);
//
//        if (slot == null || user == null) {
//            throw new IllegalArgumentException("Both user and slot must be provided for a reservation.");
//        }
//
//        if (!slot.isAvailable() || "BOOKED".equalsIgnoreCase(slot.getStatus())) {
//            throw new IllegalStateException("Slot is already booked.");
//        }
//
//        slot.setAvailable(false);
//        slot.setStatus("BOOKED");
//        slotRepo.save(slot);
//
//        Reservation reservation = new Reservation();
//        reservation.setUser(user);
//        reservation.setSlot(slot);
//        reservation.setStartTime(request.getStartTime());
//        reservation.setEndTime(request.getEndTime());
//
//        Reservation savedReservation = reservationRepo.save(reservation);
//
//        if (vehicleRepo.findByParkingSlot(slot).isEmpty()) {
//            Vehicle vehicle = new Vehicle();
//            vehicle.setOwner(user);
//            vehicle.setOwnerName(user.getUsername() != null ? user.getUsername() : user.getEmail());
//            vehicle.setPlateNumber("BOOKED-" + slot.getId());
//            vehicle.setParkingSlot(slot);
//            vehicleRepo.save(vehicle);
//        }
//
//        return savedReservation;
//    }
//
//    public Reservation updateReservation(Integer id, Reservation reservation) {
//        reservation.setId(id);
//        return reservationRepo.save(reservation);
//    }
//
//    public void cancelReservation(Integer id) {
//        reservationRepo.deleteById(id);
//    }
//}

//
//package com.parking.SpotFinder_app.service;
//
//import com.parking.SpotFinder_app.model.ParkingSlot;
//import com.parking.SpotFinder_app.model.Reservation;
//import com.parking.SpotFinder_app.model.ReservationRequest;
//import com.parking.SpotFinder_app.model.User;
//import com.parking.SpotFinder_app.model.Vehicle;
//import com.parking.SpotFinder_app.repository.ParkingSlotRepository;
//import com.parking.SpotFinder_app.repository.ReservationRepository;
//import com.parking.SpotFinder_app.repository.UserRepository;
//import com.parking.SpotFinder_app.repository.VehicleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class ReservationService {
//
//    @Autowired
//    private ReservationRepository reservationRepo;
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Autowired
//    private ParkingSlotRepository slotRepo;
//
//    @Autowired
//    private VehicleRepository vehicleRepo;
//
//    public List<Reservation> getAllReservations() {
//        return reservationRepo.findAll();
//    }
//
//    @Transactional
//    public Reservation createReservation(ReservationRequest request) {
//        if (request.getUserId() == null || request.getSlotId() == null || request.getPlateNumber() == null || request.getPlateNumber().trim().isEmpty()) {
//            throw new IllegalArgumentException("userId, slotId and plateNumber are required.");
//        }
//
//        User user = userRepo.findById(request.getUserId()).orElse(null);
//        ParkingSlot slot = slotRepo.findById(request.getSlotId()).orElse(null);
//
//        if (slot == null || user == null) {
//            throw new IllegalArgumentException("Both user and slot must be provided for a reservation.");
//        }
//
//        if (!slot.isAvailable() || "BOOKED".equalsIgnoreCase(slot.getStatus())) {
//            throw new IllegalStateException("Slot is already booked.");
//        }
//
//        slot.setAvailable(false);
//        slot.setStatus("BOOKED");
//        slotRepo.save(slot);
//
//        Reservation reservation = new Reservation();
//        reservation.setUser(user);
//        reservation.setSlot(slot);
//        reservation.setPlateNumber(request.getPlateNumber().trim());
//        reservation.setStartTime(request.getStartTime());
//        reservation.setEndTime(request.getEndTime());
//
//        Reservation savedReservation = reservationRepo.save(reservation);
//
//        Vehicle vehicle = vehicleRepo.findByParkingSlot(slot).orElse(new Vehicle());
//        vehicle.setOwner(user);
//        vehicle.setOwnerName(user.getUsername() != null ? user.getUsername() : user.getEmail());
//        vehicle.setPlateNumber(request.getPlateNumber().trim());
//        vehicle.setParkingSlot(slot);
//        vehicleRepo.save(vehicle);
//
//        return savedReservation;
//    }
//
//    public Reservation updateReservation(Integer id, Reservation reservation) {
//        reservation.setId(id);
//        return reservationRepo.save(reservation);
//    }
//
//    @Transactional
//    public void cancelReservation(Integer id) {
//        Reservation reservation = reservationRepo.findById(id).orElse(null);
//        if (reservation == null) {
//            return;
//        }
//
//        ParkingSlot slot = reservation.getSlot();
//        if (slot != null) {
//            slot.setAvailable(true);
//            slot.setStatus("AVAILABLE");
//            slotRepo.save(slot);
//
//            vehicleRepo.findByParkingSlot(slot).ifPresent(vehicle -> {
//                vehicle.setParkingSlot(null);
//                vehicleRepo.save(vehicle);
//            });
//        }
//
//        reservationRepo.delete(reservation);
//    }
//}


package com.parking.SpotFinder_app.service;

import com.parking.SpotFinder_app.model.ParkingSlot;
import com.parking.SpotFinder_app.model.Reservation;
import com.parking.SpotFinder_app.model.ReservationRequest;
import com.parking.SpotFinder_app.model.User;
import com.parking.SpotFinder_app.model.Vehicle;
import com.parking.SpotFinder_app.repository.ParkingSlotRepository;
import com.parking.SpotFinder_app.repository.ReservationRepository;
import com.parking.SpotFinder_app.repository.UserRepository;
import com.parking.SpotFinder_app.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ParkingSlotRepository slotRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    public List<Reservation> getAllReservations() {
        cleanupExpiredReservations();
        return reservationRepo.findAll();
    }

    private boolean isReservationExpired(Reservation reservation) {
        if (reservation == null || reservation.getEndTime() == null) {
            return false;
        }
        return !reservation.getEndTime().isAfter(LocalDateTime.now());
    }

    private void cleanupExpiredReservations() {
        List<Reservation> allReservations = reservationRepo.findAll();
        for (Reservation reservation : allReservations) {
            if (isReservationExpired(reservation)) {
                ParkingSlot slot = reservation.getSlot();
                if (slot != null) {
                    slot.setAvailable(true);
                    slot.setStatus("AVAILABLE");
                    slotRepo.save(slot);
                    vehicleRepo.findByParkingSlot(slot).ifPresent(vehicleRepo::delete);
                }
                reservationRepo.delete(reservation);
            }
        }
    }

    private void cleanupExpiredReservationsForSlot(ParkingSlot slot) {
        if (slot == null) {
            return;
        }
        List<Reservation> slotReservations = reservationRepo.findBySlot(slot);
        for (Reservation reservation : slotReservations) {
            if (isReservationExpired(reservation)) {
                slot.setAvailable(true);
                slot.setStatus("AVAILABLE");
                slotRepo.save(slot);
                vehicleRepo.findByParkingSlot(slot).ifPresent(vehicleRepo::delete);
                reservationRepo.delete(reservation);
            }
        }
    }

    @Transactional
    public Reservation createReservation(ReservationRequest request) {
        if (request.getUserId() == null || request.getSlotId() == null || request.getPlateNumber() == null || request.getPlateNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("userId, slotId and plateNumber are required.");
        }

        User user = userRepo.findById(request.getUserId()).orElse(null);
        ParkingSlot slot = slotRepo.findById(request.getSlotId()).orElse(null);

        if (slot == null || user == null) {
            throw new IllegalArgumentException("Both user and slot must be provided for a reservation.");
        }

        cleanupExpiredReservationsForSlot(slot);
        slot = slotRepo.findById(request.getSlotId()).orElse(slot);

        if (!slot.isAvailable() || "BOOKED".equalsIgnoreCase(slot.getStatus())) {
            throw new IllegalStateException("Slot is already booked.");
        }

        slot.setAvailable(false);
        slot.setStatus("BOOKED");
        slotRepo.save(slot);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setSlot(slot);
        reservation.setPlateNumber(request.getPlateNumber().trim());
        reservation.setStartTime(request.getStartTime());
        reservation.setEndTime(request.getEndTime());

        Reservation savedReservation = reservationRepo.save(reservation);

        Vehicle vehicle = vehicleRepo.findByParkingSlot(slot).orElse(new Vehicle());
        vehicle.setOwner(user);
        vehicle.setOwnerName(user.getUsername() != null ? user.getUsername() : user.getEmail());
        vehicle.setPlateNumber(request.getPlateNumber().trim());
        vehicle.setParkingSlot(slot);
        vehicleRepo.save(vehicle);

        return savedReservation;
    }

    public Reservation updateReservation(Integer id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepo.save(reservation);
    }

    @Transactional
    public void cancelReservation(Integer id) {
        Reservation reservation = reservationRepo.findById(id).orElse(null);
        if (reservation == null) {
            return;
        }

        ParkingSlot slot = reservation.getSlot();
        if (slot != null) {
            slot.setAvailable(true);
            slot.setStatus("AVAILABLE");
            slotRepo.save(slot);

            vehicleRepo.findByParkingSlot(slot).ifPresent(vehicleRepo::delete);
        }

        reservationRepo.delete(reservation);
    }
}
