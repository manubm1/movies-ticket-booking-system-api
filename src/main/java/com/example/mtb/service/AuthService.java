package com.example.mtb.service;

import com.example.mtb.dto.AuthResponse;
import com.example.mtb.dto.LoginRequest;
import com.example.mtb.security.jwt.AuthenticateTokenDetails;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refresh(AuthenticateTokenDetails details);
}
