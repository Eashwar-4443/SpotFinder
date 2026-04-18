//
//
//package com.parking.SpotFinder_app.model;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "parking_slots")
//public class ParkingSlot {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "slot_number", unique = true)
//    private String slotNumber;
//
//    @Column(name = "is_available")
//    private boolean isAvailable = true;
//
//    @Column(name = "status")
//    private String status = "AVAILABLE";
//
//    @ManyToOne
//    @JoinColumn(name = "area_id")
//    private ParkingArea area;
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//
//    public String getSlotNumber() { return slotNumber; }
//    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }
//
//    @JsonProperty("isAvailable")
//    public boolean isAvailable() { return isAvailable; }
//
//    @JsonProperty("isAvailable")
//    public void setAvailable(boolean available) { isAvailable = available; }
//
//    @JsonProperty("available")
//    public boolean getAvailable() { return isAvailable; }
//
//    @JsonProperty("status")
//    public String getStatus() { return status; }
//
//    @JsonProperty("status")
//    public void setStatus(String status) { this.status = status; }
//
//    public ParkingArea getArea() { return area; }
//    public void setArea(ParkingArea area) { this.area = area; }
//}
//


//package com.parking.SpotFinder_app.model;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "parking_slots")
//public class ParkingSlot {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "slot_number", unique = true)
//    private String slotNumber;
//
//    @Column(name = "is_available")
//    private boolean isAvailable = true;
//
//    @Column(name = "status")
//    private String status = "AVAILABLE";
//
//    @ManyToOne
//    @JoinColumn(name = "area_id")
//    private ParkingArea area;
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//
//    public String getSlotNumber() { return slotNumber; }
//    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }
//
//    @JsonProperty("isAvailable")
//    public boolean isAvailable() { return isAvailable; }
//
//    @JsonProperty("isAvailable")
//    public void setAvailable(boolean available) { isAvailable = available; }
//
//    @JsonProperty("available")
//    public boolean getAvailable() { return isAvailable; }
//
//    @JsonProperty("status")
//    public String getStatus() { return status; }
//
//    @JsonProperty("status")
//    public void setStatus(String status) { this.status = status; }
//
//    public ParkingArea getArea() { return area; }
//    public void setArea(ParkingArea area) { this.area = area; }
//}
//


package com.parking.SpotFinder_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "parking_slots")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "slot_number", unique = true)
    private String slotNumber;

    @Column(name = "is_available")
    private boolean isAvailable = true;

    @Column(name = "status")
    private String status = "AVAILABLE";

    @ManyToOne
    @JoinColumn(name = "area_id")
    private ParkingArea area;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }

    @JsonProperty("isAvailable")
    public boolean isAvailable() { return isAvailable; }

    @JsonProperty("isAvailable")
    public void setAvailable(boolean available) { isAvailable = available; }

    @JsonProperty("available")
    public boolean getAvailable() { return isAvailable; }

    @JsonProperty("status")
    public String getStatus() { return status; }

    @JsonProperty("status")
    public void setStatus(String status) { this.status = status; }

    public ParkingArea getArea() { return area; }
    public void setArea(ParkingArea area) { this.area = area; }
}
