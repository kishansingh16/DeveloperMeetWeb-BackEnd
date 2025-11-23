package com.devMeet.DeveloperMeetWeb.service;

import com.devMeet.DeveloperMeetWeb.dto.LoginRequest;
import com.devMeet.DeveloperMeetWeb.dto.LoginResponse;
import com.devMeet.DeveloperMeetWeb.dto.RegisterRequest;


public interface AuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse register(RegisterRequest request);

}
