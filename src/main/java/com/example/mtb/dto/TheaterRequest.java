package com.example.mtb.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TheaterRequest(@NotNull(message = "Theater name is Required")  @Pattern(regexp = "^[A-Za-z0-9 ]{3,15}+$",message = "Must be 3–15 characters with only letters, numbers")
                             String name,
                             @NotNull(message = "Address is Required")  @Pattern(regexp = "^[A-Za-z0-9 ]{2,50}+$",message = "Must be 2–50 characters with only letters, numbers")
                             String address,
                             @NotNull(message = "city is Required")  @Pattern(regexp = "^[A-Za-z0-9 ]{2,50}+$",message = "Must be 2–50 characters with only letters, numbers")
                             String city,
                             @NotNull(message = "Landmark is Required")  @Pattern(regexp = "^[A-Za-z0-9 ]{2,50}+$",message = "Must be 2–50 characters with only letters, numbers")
                             String landmark) {
}
