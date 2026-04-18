//
//
//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.User;
//import com.parking.SpotFinder_app.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Integer id) {
//        return userService.getAllUsers().stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }
//
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
//        return userService.updateUser(id, user);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable Integer id) {
//        userService.deleteUser(id);
//        return "User deleted successfully!";
//    }
//}

//
//package com.parking.SpotFinder_app.controller;
//
//import com.parking.SpotFinder_app.model.User;
//import com.parking.SpotFinder_app.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/parking/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Integer id) {
//        return userService.getAllUsers().stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        if (user.getEmail() == null || !user.getEmail().toLowerCase().endsWith("@gmail.com")) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists or invalid data.");
//        }
//        try {
//            return userService.createUser(user);
//        } catch (DataIntegrityViolationException ex) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists or invalid data.");
//        }
//    }
//
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
//        if (user.getEmail() != null && !user.getEmail().toLowerCase().endsWith("@gmail.com")) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists or invalid data.");
//        }
//        try {
//            return userService.updateUser(id, user);
//        } catch (DataIntegrityViolationException ex) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists or invalid data.");
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable Integer id) {
//        userService.deleteUser(id);
//        return "User deleted successfully!";
//    }
//}



package com.parking.SpotFinder_app.controller;

import com.parking.SpotFinder_app.model.User;
import com.parking.SpotFinder_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/parking/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        if (user.getEmail() == null || !user.getEmail().toLowerCase().endsWith("@gmail.com")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists or invalid data.");
        }
        try {
            return userService.createUser(user);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists or invalid data.");
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        if (user.getEmail() != null && !user.getEmail().toLowerCase().endsWith("@gmail.com")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists or invalid data.");
        }
        try {
            return userService.updateUser(id, user);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists or invalid data.");
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }
}
