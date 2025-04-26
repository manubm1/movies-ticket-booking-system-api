package com.example.mtb.dto;

import com.example.mtb.enums.UserRole;

import java.time.LocalDate;

public record UserResponse(String userId,
                           String username,
                           String email,
                           String phoneNumber,
                           UserRole userRole,
                           LocalDate dateOfBirth) {
}
