package com.example.mtb.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record FeedbackRequest(
                              @NotNull @DecimalMin(value = "1.0",inclusive = true)@DecimalMax(value="5.0",inclusive = true)
                              double rating,
                              @NotNull @Pattern(regexp = "^[A-Za-z0-9(-) ]{0,50}$",message = "Must be 0–50 characters with only numbers")
                              String reviews) {
}
