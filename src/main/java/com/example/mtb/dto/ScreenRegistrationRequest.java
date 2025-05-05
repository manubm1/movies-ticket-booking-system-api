package com.example.mtb.dto;

import com.example.mtb.enums.ScreenType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ScreenRegistrationRequest(
                                        @NotNull(message = " scree type require")
                                        ScreenType screenType,
                                        @NotNull @Pattern(regexp = "^[0-9 ]{0,5}$",message = "Must be 0–5 characters with only numbers")
                                        int capacity,
                                        @NotNull @Pattern(regexp = "^[0-9 ]{0,5}$",message = "Must be 0–5 characters with only numbers")
                                        int noOfRows) {
}
