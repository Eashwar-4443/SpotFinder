//
//
//package com.parking.SpotFinder_app.model;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "reservations")
//public class Reservation {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "slot_id")
//    private ParkingSlot slot;
//
//    @Column(name = "start_time")
//    private LocalDateTime startTime;
//
//    @Column(name = "end_time")
//    private LocalDateTime endTime;
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//
//    public User getUser() { return user; }
//    public void setUser(User user) { this.user = user; }
//
//    public ParkingSlot getSlot() { return slot; }
//    public void setSlot(ParkingSlot slot) { this.slot = slot; }
//
//    public LocalDateTime getStartTime() { return startTime; }
//    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
//
//    public LocalDateTime getEndTime() { return endTime; }
//    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
//}

//
//package com.parking.SpotFinder_app.model;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "reservations")
//public class Reservation {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "slot_id")
//    private ParkingSlot slot;
//
//    @Column(name = "start_time")
//    private LocalDateTime startTime;
//
//    @Column(name = "end_time")
//    private LocalDateTime endTime;
//
//    @Column(name = "plate_number")
//    private String plateNumber;
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//
//    public User getUser() { return user; }
//    public void setUser(User user) { this.user = user; }
//
//    public ParkingSlot getSlot() { return slot; }
//    public void setSlot(ParkingSlot slot) { this.slot = slot; }
//
//    public LocalDateTime getStartTime() { return startTime; }
//    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
//
//    public LocalDateTime getEndTime() { return endTime; }
//    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
//
//    @JsonProperty("plateNumber")
//    public String getPlateNumber() { return plateNumber; }
//
//    @JsonProperty("plateNumber")
//    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
//}
//


package com.parking.SpotFinder_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private ParkingSlot slot;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "plate_number")
    private String plateNumber;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public ParkingSlot getSlot() { return slot; }
    public void setSlot(ParkingSlot slot) { this.slot = slot; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    @JsonProperty("plateNumber")
    public String getPlateNumber() { return plateNumber; }

    @JsonProperty("plateNumber")
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
}
