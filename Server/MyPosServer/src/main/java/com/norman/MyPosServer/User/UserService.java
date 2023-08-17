package com.norman.MyPosServer.User;

import com.norman.MyPosServer.Security.Authority;
import com.norman.MyPosServer.Security.UserSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserSecurity(user);
    }

    public void saveUser(CreateUserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUsername());

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);

        for(String authStr: userDTO.getAuths()) {
            //TODO: Validate authStr to prevent unknown authorities from being posted
            Authority authority = new Authority();
            authority.setAuthority(authStr);
            authority.setUser(user);
            user.getAuthorities().add(authority);
        }

        userRepo.save(user);
    }
}
