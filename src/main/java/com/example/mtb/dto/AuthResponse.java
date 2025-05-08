package com.example.mtb.dto;

import com.example.mtb.enums.UserRole;

import java.time.Instant;

public record AuthResponse(String userID,
                           String username,
                           String email,
                           UserRole userRole,
                           Long accessExpiration,
                           Long refreshExpiration,
                           String accessToken,
                           String refreshToken) {
}
