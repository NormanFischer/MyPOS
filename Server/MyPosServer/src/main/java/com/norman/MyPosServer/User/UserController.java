package com.norman.MyPosServer.User;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/createUser")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO userDTO) {
        System.out.println("Username: " + userDTO.getUsername());
        System.out.println("Password: " + userDTO.getPassword());
        System.out.println("Authorities: " + userDTO.getAuths());
        userService.saveUser(userDTO);
        return ResponseEntity.ok("User created");
    }
}
