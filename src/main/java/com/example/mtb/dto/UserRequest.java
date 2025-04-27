package com.example.mtb.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserRequest(@NotNull(message = "UserName is Required")  @Pattern(regexp = "^[A-Za-z0-9_@]{5,15}$",message = "Must be 5–15 characters with only letters, numbers, _ and @")
                          String username,
                          @NotNull(message = "Phone number is required")@Pattern(regexp = "^[0-9]{10}$" ,message=(" must be enter 10 digit only "))
                          String phoneNumber,
                          @Past(message = "Date of birth required")
                          LocalDate dateOfBirth) {
}
