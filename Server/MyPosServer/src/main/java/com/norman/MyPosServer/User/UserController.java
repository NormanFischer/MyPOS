package com.norman.MyPosServer.User;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping(path="/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/createUser")
    public ResponseEntity<?> createUser(@RequestParam String userName,
                                        @RequestParam String password) {
        System.out.println("Create user requested");
        User userToAdd = new User();
        userToAdd.setUserName(userName);
        userToAdd.setPassword(password);
        userService.saveUser(userToAdd);
        return ResponseEntity.ok("User created");
    }
}
