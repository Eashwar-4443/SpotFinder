//
//package com.parking.SpotFinder_app.repository;
//
//import com.parking.SpotFinder_app.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Integer> {
//    User findByUsername(String username);
//    User findByEmail(String email);
//}


package com.parking.SpotFinder_app.repository;

import com.parking.SpotFinder_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
}
