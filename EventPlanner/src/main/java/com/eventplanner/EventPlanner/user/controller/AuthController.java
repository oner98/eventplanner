package com.eventplanner.EventPlanner.user.controller;

import com.eventplanner.EventPlanner.user.model.User;
import com.eventplanner.EventPlanner.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "User registered";
    }
}
