//
//package com.parking.SpotFinder_app.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "vehicles")
//public class Vehicle {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "owner_name")
//    private String ownerName;
//
//    @Column(name = "plate_number", unique = true)
//    private String plateNumber;
//
//    private String make;
//    private String model;
//
//    @ManyToOne
//    @JoinColumn(name = "slot_id")
//    private ParkingSlot parkingSlot;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User owner;
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//
//    public String getOwnerName() { return ownerName; }
//    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
//
//    public String getPlateNumber() { return plateNumber; }
//    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
//
//    public String getMake() { return make; }
//    public void setMake(String make) { this.make = make; }
//
//    public String getModel() { return model; }
//    public void setModel(String model) { this.model = model; }
//
//    public ParkingSlot getParkingSlot() { return parkingSlot; }
//    public void setParkingSlot(ParkingSlot parkingSlot) { this.parkingSlot = parkingSlot; }
//
//    public User getOwner() { return owner; }
//    public void setOwner(User owner) { this.owner = owner; }
//}


//package com.parking.SpotFinder_app.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "vehicles")
//public class Vehicle {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "owner_name")
//    private String ownerName;
//
//    @Column(name = "plate_number", unique = true)
//    private String plateNumber;
//
//    private String make;
//    private String model;
//
//    @ManyToOne
//    @JoinColumn(name = "slot_id")
//    private ParkingSlot parkingSlot;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User owner;
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//
//    public String getOwnerName() { return ownerName; }
//    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
//
//    public String getPlateNumber() { return plateNumber; }
//    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
//
//    public String getMake() { return make; }
//    public void setMake(String make) { this.make = make; }
//
//    public String getModel() { return model; }
//    public void setModel(String model) { this.model = model; }
//
//    public ParkingSlot getParkingSlot() { return parkingSlot; }
//    public void setParkingSlot(ParkingSlot parkingSlot) { this.parkingSlot = parkingSlot; }
//
//    public User getOwner() { return owner; }
//    public void setOwner(User owner) { this.owner = owner; }
//}



package com.parking.SpotFinder_app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "plate_number", unique = true)
    private String plateNumber;

    private String make;
    private String model;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private ParkingSlot parkingSlot;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public ParkingSlot getParkingSlot() { return parkingSlot; }
    public void setParkingSlot(ParkingSlot parkingSlot) { this.parkingSlot = parkingSlot; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}
