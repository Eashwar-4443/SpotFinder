
//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.LoginRequest;
//import com.parking.SpotFinder_app.model.LoginResponse;
//import com.parking.SpotFinder_app.model.User;
//import com.parking.SpotFinder_app.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking")
//public class AuthController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest request) {
//        User user = userRepository.findByUsername(request.getUsername());
//        if (user == null) {
//            user = userRepository.findByEmail(request.getUsername());
//        }
//        if (user == null || !user.getPassword().equals(request.getPassword())) {
//            return new LoginResponse(null, null, null, null, "Invalid username or password");
//        }
//        String token = "fake-jwt-token-for-" + user.getUsername();
//        return new LoginResponse(token, user.getRole(), user.getId(), user.getUsername());
//    }
//}
//


//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.LoginRequest;
//import com.parking.SpotFinder_app.model.LoginResponse;
//import com.parking.SpotFinder_app.model.User;
//import com.parking.SpotFinder_app.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking")
//public class AuthController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest request) {
//        User user = userRepository.findByUsername(request.getUsername());
//        if (user == null) {
//            user = userRepository.findByEmail(request.getUsername());
//        }
//        if (user == null || !user.getPassword().equals(request.getPassword())) {
//            return new LoginResponse(null, null, null, null, "Invalid username or password");
//        }
//        String token = "fake-jwt-token-for-" + user.getUsername();
//        return new LoginResponse(token, user.getRole(), user.getId(), user.getUsername());
//    }
//}


package com.parking.SpotFinder_app.controller;

import com.parking.SpotFinder_app.model.LoginRequest;
import com.parking.SpotFinder_app.model.LoginResponse;
import com.parking.SpotFinder_app.model.User;
import com.parking.SpotFinder_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/parking")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            user = userRepository.findByEmail(request.getUsername());
        }
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return new LoginResponse(null, null, null, null, "Invalid username or password");
        }
        String token = "fake-jwt-token-for-" + user.getUsername();
        return new LoginResponse(token, user.getRole(), user.getId(), user.getUsername());
    }
}
