//package com.parking.SpotFinder_app.model;
//
//import java.time.LocalDateTime;
//
//public class ReservationRequest {
//
//    private Integer userId;
//    private Integer slotId;
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public Integer getSlotId() {
//        return slotId;
//    }
//
//    public void setSlotId(Integer slotId) {
//        this.slotId = slotId;
//    }
//
//    public LocalDateTime getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDateTime startTime) {
//        this.startTime = startTime;
//    }
//
//    public LocalDateTime getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(LocalDateTime endTime) {
//        this.endTime = endTime;
//    }
//}


//
//package com.parking.SpotFinder_app.model;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import java.time.LocalDateTime;
//
//public class ReservationRequest {
//
//    private Integer userId;
//    private Integer slotId;
//    private String plateNumber;
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public Integer getSlotId() {
//        return slotId;
//    }
//
//    public void setSlotId(Integer slotId) {
//        this.slotId = slotId;
//    }
//
//    @JsonProperty("plateNumber")
//    public String getPlateNumber() {
//        return plateNumber;
//    }
//
//    @JsonProperty("plateNumber")
//    public void setPlateNumber(String plateNumber) {
//        this.plateNumber = plateNumber;
//    }
//
//    public LocalDateTime getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDateTime startTime) {
//        this.startTime = startTime;
//    }
//
//    public LocalDateTime getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(LocalDateTime endTime) {
//        this.endTime = endTime;
//    }
//}


package com.parking.SpotFinder_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class ReservationRequest {

    private Integer userId;
    private Integer slotId;
    private String plateNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    @JsonProperty("plateNumber")
    public String getPlateNumber() {
        return plateNumber;
    }

    @JsonProperty("plateNumber")
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
