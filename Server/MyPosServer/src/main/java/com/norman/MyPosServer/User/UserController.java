package com.norman.MyPosServer.User;

import org.springframework.security.core.Authentication;
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
        userService.saveUser(userDTO);
        return ResponseEntity.ok("User created");
    }

    @GetMapping(path="/getUserProfile")
    public String getUserProfileDTO(Authentication authentication) {
        if(authentication != null && authentication.isAuthenticated()) {
            System.out.println(authentication.toString());
            User authenticatedUser = (User) authentication.getPrincipal();
            return "User connected: " + authenticatedUser.getUsername();
        } else {
            return "Could not get authentication from user";
        }
    }
}
