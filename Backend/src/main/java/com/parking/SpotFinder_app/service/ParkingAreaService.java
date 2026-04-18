//
//
//package com.parking.SpotFinder_app.service;
//
//import com.parking.SpotFinder_app.model.ParkingArea;
//import com.parking.SpotFinder_app.repository.ParkingAreaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ParkingAreaService {
//
//    @Autowired
//    private ParkingAreaRepository areaRepo;
//
//    public List<ParkingArea> getAllAreas() {
//        return areaRepo.findAll();
//    }
//
//    public ParkingArea createArea(ParkingArea area) {
//        return areaRepo.save(area);
//    }
//
//    public ParkingArea updateArea(Integer id, ParkingArea area) {
//        area.setId(id);
//        return areaRepo.save(area);
//    }
//
//    public void deleteArea(Integer id) {
//        areaRepo.deleteById(id);
//    }
//}


//
//package com.parking.SpotFinder_app.service;
//
//import com.parking.SpotFinder_app.model.ParkingArea;
//import com.parking.SpotFinder_app.repository.ParkingAreaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ParkingAreaService {
//
//    @Autowired
//    private ParkingAreaRepository areaRepo;
//
//    public List<ParkingArea> getAllAreas() {
//        return areaRepo.findAll();
//    }
//
//    public ParkingArea createArea(ParkingArea area) {
//        return areaRepo.save(area);
//    }
//
//    public ParkingArea updateArea(Integer id, ParkingArea area) {
//        area.setId(id);
//        return areaRepo.save(area);
//    }
//
//    public void deleteArea(Integer id) {
//        areaRepo.deleteById(id);
//    }
//}


package com.parking.SpotFinder_app.service;

import com.parking.SpotFinder_app.model.ParkingArea;
import com.parking.SpotFinder_app.repository.ParkingAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingAreaService {

    @Autowired
    private ParkingAreaRepository areaRepo;

    public List<ParkingArea> getAllAreas() {
        return areaRepo.findAll();
    }

    public ParkingArea createArea(ParkingArea area) {
        return areaRepo.save(area);
    }

    public ParkingArea updateArea(Integer id, ParkingArea area) {
        area.setId(id);
        return areaRepo.save(area);
    }

    public void deleteArea(Integer id) {
        areaRepo.deleteById(id);
    }
}
