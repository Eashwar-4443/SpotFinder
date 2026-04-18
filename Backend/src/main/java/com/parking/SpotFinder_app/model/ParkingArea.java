//
//package com.parking.SpotFinder_app.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "parking_areas")
//public class ParkingArea {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String name;
//    private String location;
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getLocation() { return location; }
//    public void setLocation(String location) { this.location = location; }
//}
//
//
//


//package com.parking.SpotFinder_app.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "parking_areas")
//public class ParkingArea {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String name;
//    private String location;
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getLocation() { return location; }
//    public void setLocation(String location) { this.location = location; }
//}


package com.parking.SpotFinder_app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_areas")
public class ParkingArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String location;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}

