package com.devMeet.DeveloperMeetWeb.service.impl;

import com.devMeet.DeveloperMeetWeb.dto.LoginRequest;
import com.devMeet.DeveloperMeetWeb.dto.LoginResponse;
import com.devMeet.DeveloperMeetWeb.dto.RegisterRequest;   // 👈 new
import com.devMeet.DeveloperMeetWeb.model.User;
import com.devMeet.DeveloperMeetWeb.repository.UserRepository;
import com.devMeet.DeveloperMeetWeb.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null) {
            return new LoginResponse(false, "User not found");
        }

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponse(false, "Invalid password");
        }

        return new LoginResponse(true, "Login successful");
    }

    @Override
    public LoginResponse register(RegisterRequest request) {
        // check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new LoginResponse(false, "Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword())); // hash password

        userRepository.save(user);

        return new LoginResponse(true, "User registered successfully");
    }
}
