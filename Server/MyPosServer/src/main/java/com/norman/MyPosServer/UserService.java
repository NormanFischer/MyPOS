//package com.norman.MyPosServer;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Service
//public class UserService {
//    private final UserRepository userRepo;
//    private final PasswordEncoder encoder;
//    @Autowired
//    public UserService(UserRepository userRepo, PasswordEncoder encoder) {
//        this.userRepo = userRepo;
//        this.encoder = encoder;
//    }
//    public User createUser(String userName, String password) {
//        User userToAdd = new User();
//        userToAdd.setUserName(userName);
//        userToAdd.setHash(encoder.encode(password));
//        userToAdd.setIsAdmin(true);
//        return userRepo.save(userToAdd);
//    }
//
//    public void deleteUser(String userID) {
//
//    }
//}
