package com.norman.MyPosServer.Security;

import com.norman.MyPosServer.User.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController
public class UserAuthenticationController {
    @GetMapping(path="/auth/getUserProfile")
    public String getUserProfileDTO(Authentication authentication) {
        if(authentication != null && authentication.isAuthenticated()) {
            System.out.println(authentication.toString());
            //User authenticatedUser = (User) authentication.getPrincipal();
            return "User connected: ";
        } else {
            return "Could not get authentication from user";
        }
    }
}
