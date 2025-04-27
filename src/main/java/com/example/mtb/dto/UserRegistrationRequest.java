package com.example.mtb.dto;

import com.example.mtb.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserRegistrationRequest (@NotNull(message = "UserName is Required")  @Pattern(regexp = "^[A-Za-z0-9_@]{5,15}$",message = "Must be 5–15 characters with only letters, numbers, _ and @")
                                       String username,
                                       @NotNull(message = "email is required") @Email(message = "Email is required")
                                       String email,
                                       @NotNull(message = "Phone number is required")@Pattern(regexp = "^[0-9]{10}$" ,message=(" must be enter 10 digit only "))
                                       String phoneNumber,
                                       @NotNull(message = "Password is Required") @Pattern(regexp = "^[A-Za-z0-9_@]{8,15}$",message = "Must be 5–12 characters with only letters, numbers, _ and @")
                                       String password,
                                       @NotNull(message = "user role is required")
                                       UserRole userRole,
                                       @Past(message = "Date of birth required")
                                       LocalDate dateOfBirth){
}
