package com.example.mtb.dto;

import com.example.mtb.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record TheaterRegistrationRequest(
                                        @NotNull(message = "Theater name is Required")  @Pattern(regexp = "^[A-Za-z0-9 ]{3,15}$",message = "Must be 3–15 characters with only letters, numbers, _ and @")
                                        String name,
                                        @NotNull(message = "Address is Required")  @Pattern(regexp = "^[A-Za-z0-9 ]{2,15}$",message = "Must be 2–50 characters with only letters, numbers, _ and @")
                                        String address,
                                        @NotNull(message = "city is Required")  @Pattern(regexp = "^[A-Za-z0-9 ]{2,15}$",message = "Must be 2–50 characters with only letters, numbers, _ and @")
                                        String city,
                                        @NotNull(message = "Landmark is Required")  @Pattern(regexp = "^[A-Za-z0-9 ]{2,15}$",message = "Must be 2–50 characters with only letters, numbers, _ and @")
                                        String landmark,
                                        Long createAt,
                                        Long updatedAt,
                                        Long createdBy){
}
