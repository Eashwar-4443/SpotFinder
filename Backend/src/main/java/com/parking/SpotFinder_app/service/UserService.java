//
//
//package com.parking.SpotFinder_app.service;
//
//import com.parking.SpotFinder_app.model.User;
//import com.parking.SpotFinder_app.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    public List<User> getAllUsers() {
//        return userRepo.findAll();
//    }
//
//    public User createUser(User user) {
//        return userRepo.save(user);
//    }
//
//    public User updateUser(Integer id, User user) {
//        user.setId(id);
//        return userRepo.save(user);
//    }
//
//    public void deleteUser(Integer id) {
//        userRepo.deleteById(id);
//    }
//}



//
//package com.parking.SpotFinder_app.service;
//
//import com.parking.SpotFinder_app.model.User;
//import com.parking.SpotFinder_app.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    public List<User> getAllUsers() {
//        return userRepo.findAll();
//    }
//
//    public User createUser(User user) {
//        return userRepo.save(user);
//    }
//
//    public User updateUser(Integer id, User user) {
//        user.setId(id);
//        return userRepo.save(user);
//    }
//
//    public void deleteUser(Integer id) {
//        userRepo.deleteById(id);
//    }
//}


package com.parking.SpotFinder_app.service;

import com.parking.SpotFinder_app.model.User;
import com.parking.SpotFinder_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(Integer id, User user) {
        user.setId(id);
        return userRepo.save(user);
    }

    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }
}

