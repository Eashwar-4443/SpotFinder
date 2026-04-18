//
//
//package com.parking.SpotFinder_app.service;
//
//import com.parking.SpotFinder_app.model.ParkingArea;
//import com.parking.SpotFinder_app.model.ParkingSlot;
//import com.parking.SpotFinder_app.repository.ParkingAreaRepository;
//import com.parking.SpotFinder_app.repository.ParkingSlotRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ParkingSlotService {
//
//    @Autowired
//    private ParkingSlotRepository slotRepo;
//
//    @Autowired
//    private ParkingAreaRepository areaRepo;
//
//    public List<ParkingSlot> getAllSlots() {
//        return slotRepo.findAll();
//    }
//
//    public ParkingSlot createSlot(ParkingSlot slot) {
//        if (slot.getArea() != null && slot.getArea().getId() != null) {
//            Optional<ParkingArea> areaOptional = areaRepo.findById(slot.getArea().getId());
//            areaOptional.ifPresent(slot::setArea);
//        }
//        if (slot.getStatus() == null) {
//            slot.setStatus("AVAILABLE");
//        }
//        if (slot.isAvailable() && !"BOOKED".equalsIgnoreCase(slot.getStatus())) {
//            slot.setStatus("AVAILABLE");
//        }
//        return slotRepo.save(slot);
//    }
//
//    public ParkingSlot updateSlot(Integer id, ParkingSlot slot) {
//        if (slot.getArea() != null && slot.getArea().getId() != null) {
//            Optional<ParkingArea> areaOptional = areaRepo.findById(slot.getArea().getId());
//            areaOptional.ifPresent(slot::setArea);
//        }
//        slot.setId(id);
//        if (slot.getStatus() == null) {
//            slot.setStatus(slot.isAvailable() ? "AVAILABLE" : "BOOKED");
//        }
//        return slotRepo.save(slot);
//    }
//
//    public void deleteSlot(Integer id) {
//        slotRepo.deleteById(id);
//    }
//
//    public String bookSlot(Integer id) {
//        Optional<ParkingSlot> optionalSlot = slotRepo.findById(id);
//        if (optionalSlot.isEmpty()) {
//            return "Slot not found!";
//        }
//        ParkingSlot slot = optionalSlot.get();
//        if (!slot.isAvailable() || "BOOKED".equalsIgnoreCase(slot.getStatus())) {
//            return "Slot already booked!";
//        }
//        slot.setAvailable(false);
//        slot.setStatus("BOOKED");
//        slotRepo.save(slot);
//        return "Slot booked successfully!";
//    }
//
//    public String releaseSlot(Integer id) {
//        Optional<ParkingSlot> optionalSlot = slotRepo.findById(id);
//        if (optionalSlot.isEmpty()) {
//            return "Slot not found!";
//        }
//        ParkingSlot slot = optionalSlot.get();
//        slot.setAvailable(true);
//        slot.setStatus("AVAILABLE");
//        slotRepo.save(slot);
//        return "Slot released successfully!";
//    }
//}
//

//
//package com.parking.SpotFinder_app.service;
//
//import com.parking.SpotFinder_app.model.ParkingArea;
//import com.parking.SpotFinder_app.model.ParkingSlot;
//import com.parking.SpotFinder_app.model.Vehicle;
//import com.parking.SpotFinder_app.repository.ParkingAreaRepository;
//import com.parking.SpotFinder_app.repository.ParkingSlotRepository;
//import com.parking.SpotFinder_app.repository.ReservationRepository;
//import com.parking.SpotFinder_app.repository.VehicleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//public class ParkingSlotService {
//
//    @Autowired
//    private ParkingSlotRepository slotRepo;
//
//    @Autowired
//    private ParkingAreaRepository areaRepo;
//
//    @Autowired
//    private ReservationRepository reservationRepo;
//
//    @Autowired
//    private VehicleRepository vehicleRepo;
//
//    public List<ParkingSlot> getAllSlots() {
//        return slotRepo.findAll();
//    }
//
//    public ParkingSlot createSlot(ParkingSlot slot) {
//        if (slot.getArea() != null && slot.getArea().getId() != null) {
//            Optional<ParkingArea> areaOptional = areaRepo.findById(slot.getArea().getId());
//            areaOptional.ifPresent(slot::setArea);
//        }
//        if (slot.getStatus() == null) {
//            slot.setStatus("AVAILABLE");
//        }
//        if (slot.isAvailable() && !"BOOKED".equalsIgnoreCase(slot.getStatus())) {
//            slot.setStatus("AVAILABLE");
//        }
//        return slotRepo.save(slot);
//    }
//
//    public ParkingSlot updateSlot(Integer id, ParkingSlot slot) {
//        Optional<ParkingSlot> existingSlotOpt = slotRepo.findById(id);
//        if (existingSlotOpt.isEmpty()) {
//            throw new IllegalArgumentException("Slot not found.");
//        }
//
//        ParkingSlot existingSlot = existingSlotOpt.get();
//
//        if (slot.getSlotNumber() != null) {
//            existingSlot.setSlotNumber(slot.getSlotNumber());
//        }
//
//        existingSlot.setAvailable(slot.isAvailable());
//
//        if (slot.getStatus() != null && !slot.getStatus().isBlank()) {
//            existingSlot.setStatus(slot.getStatus());
//        } else {
//            existingSlot.setStatus(existingSlot.isAvailable() ? "AVAILABLE" : "BOOKED");
//        }
//
//        if (slot.getArea() != null && slot.getArea().getId() != null) {
//            Optional<ParkingArea> areaOptional = areaRepo.findById(slot.getArea().getId());
//            areaOptional.ifPresent(existingSlot::setArea);
//        }
//
//        return slotRepo.save(existingSlot);
//    }
//
//    public void deleteSlot(Integer id) {
//        Optional<ParkingSlot> slotOptional = slotRepo.findById(id);
//        if (slotOptional.isPresent()) {
//            ParkingSlot slot = slotOptional.get();
//            reservationRepo.deleteBySlotId(slot.getId());
//            Optional<Vehicle> vehicleOptional = vehicleRepo.findByParkingSlot(slot);
//            vehicleOptional.ifPresent(vehicle -> {
//                vehicle.setParkingSlot(null);
//                vehicleRepo.save(vehicle);
//            });
//        }
//        slotRepo.deleteById(id);
//    }
//
//    public String bookSlot(Integer id) {
//        Optional<ParkingSlot> optionalSlot = slotRepo.findById(id);
//        if (optionalSlot.isEmpty()) {
//            return "Slot not found!";
//        }
//        ParkingSlot slot = optionalSlot.get();
//        if (!slot.isAvailable() || "BOOKED".equalsIgnoreCase(slot.getStatus())) {
//            return "Slot already booked!";
//        }
//        slot.setAvailable(false);
//        slot.setStatus("BOOKED");
//        slotRepo.save(slot);
//        return "Slot booked successfully!";
//    }
//
//    public String releaseSlot(Integer id) {
//        Optional<ParkingSlot> optionalSlot = slotRepo.findById(id);
//        if (optionalSlot.isEmpty()) {
//            return "Slot not found!";
//        }
//        ParkingSlot slot = optionalSlot.get();
//        slot.setAvailable(true);
//        slot.setStatus("AVAILABLE");
//        slotRepo.save(slot);
//
//        reservationRepo.deleteBySlotId(slot.getId());
//        Optional<Vehicle> vehicleOptional = vehicleRepo.findByParkingSlot(slot);
//        vehicleOptional.ifPresent(vehicle -> {
//            vehicle.setParkingSlot(null);
//            vehicleRepo.save(vehicle);
//        });
//
//        return "Slot released successfully!";
//    }
//}
//
//
//

package com.parking.SpotFinder_app.service;

import com.parking.SpotFinder_app.model.ParkingArea;
import com.parking.SpotFinder_app.model.ParkingSlot;
import com.parking.SpotFinder_app.model.Reservation;
import com.parking.SpotFinder_app.model.Vehicle;
import com.parking.SpotFinder_app.repository.ParkingAreaRepository;
import com.parking.SpotFinder_app.repository.ParkingSlotRepository;
import com.parking.SpotFinder_app.repository.ReservationRepository;
import com.parking.SpotFinder_app.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepository slotRepo;

    @Autowired
    private ParkingAreaRepository areaRepo;

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    public List<ParkingSlot> getAllSlots() {
        return slotRepo.findAll();
    }

    public ParkingSlot createSlot(ParkingSlot slot) {
        if (slot.getArea() != null && slot.getArea().getId() != null) {
            Optional<ParkingArea> areaOptional = areaRepo.findById(slot.getArea().getId());
            areaOptional.ifPresent(slot::setArea);
        }
        if (slot.getStatus() == null) {
            slot.setStatus("AVAILABLE");
        }
        if (slot.isAvailable() && !"BOOKED".equalsIgnoreCase(slot.getStatus())) {
            slot.setStatus("AVAILABLE");
        }
        return slotRepo.save(slot);
    }

    public ParkingSlot updateSlot(Integer id, ParkingSlot slot) {
        Optional<ParkingSlot> existingSlotOpt = slotRepo.findById(id);
        if (existingSlotOpt.isEmpty()) {
            throw new IllegalArgumentException("Slot not found.");
        }

        ParkingSlot existingSlot = existingSlotOpt.get();

        if (slot.getSlotNumber() != null) {
            existingSlot.setSlotNumber(slot.getSlotNumber());
        }

        existingSlot.setAvailable(slot.isAvailable());

        if (slot.getStatus() != null && !slot.getStatus().isBlank()) {
            existingSlot.setStatus(slot.getStatus());
        } else {
            existingSlot.setStatus(existingSlot.isAvailable() ? "AVAILABLE" : "BOOKED");
        }

        if (slot.getArea() != null && slot.getArea().getId() != null) {
            Optional<ParkingArea> areaOptional = areaRepo.findById(slot.getArea().getId());
            areaOptional.ifPresent(existingSlot::setArea);
        }

        if (existingSlot.isAvailable() && "AVAILABLE".equalsIgnoreCase(existingSlot.getStatus())) {
            reservationRepo.deleteBySlotId(existingSlot.getId());
            Optional<Vehicle> vehicleOptional = vehicleRepo.findByParkingSlot(existingSlot);
            vehicleOptional.ifPresent(vehicle -> {
                vehicle.setParkingSlot(null);
                vehicleRepo.save(vehicle);
            });
        }

        return slotRepo.save(existingSlot);
    }

    public void deleteSlot(Integer id) {
        Optional<ParkingSlot> slotOptional = slotRepo.findById(id);
        if (slotOptional.isPresent()) {
            ParkingSlot slot = slotOptional.get();
            reservationRepo.deleteBySlotId(slot.getId());
            Optional<Vehicle> vehicleOptional = vehicleRepo.findByParkingSlot(slot);
            vehicleOptional.ifPresent(vehicle -> {
                vehicle.setParkingSlot(null);
                vehicleRepo.save(vehicle);
            });
        }
        slotRepo.deleteById(id);
    }

    private boolean isReservationExpired(Reservation reservation) {
        if (reservation == null || reservation.getEndTime() == null) {
            return false;
        }
        return !reservation.getEndTime().isAfter(LocalDateTime.now());
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

    public String bookSlot(Integer id) {
        Optional<ParkingSlot> optionalSlot = slotRepo.findById(id);
        if (optionalSlot.isEmpty()) {
            return "Slot not found!";
        }
        ParkingSlot slot = optionalSlot.get();
        cleanupExpiredReservationsForSlot(slot);
        if (!slot.isAvailable() || "BOOKED".equalsIgnoreCase(slot.getStatus())) {
            return "Slot already booked!";
        }
        slot.setAvailable(false);
        slot.setStatus("BOOKED");
        slotRepo.save(slot);
        return "Slot booked successfully!";
    }

    public String releaseSlot(Integer id) {
        Optional<ParkingSlot> optionalSlot = slotRepo.findById(id);
        if (optionalSlot.isEmpty()) {
            return "Slot not found!";
        }
        ParkingSlot slot = optionalSlot.get();
        slot.setAvailable(true);
        slot.setStatus("AVAILABLE");
        slotRepo.save(slot);

        reservationRepo.deleteBySlotId(slot.getId());
        vehicleRepo.findByParkingSlot(slot).ifPresent(vehicleRepo::delete);

        return "Slot released successfully!";
    }
}
