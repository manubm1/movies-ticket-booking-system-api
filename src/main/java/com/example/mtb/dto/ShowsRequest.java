package com.example.mtb.dto;

import com.example.mtb.enums.ScreenType;

import java.time.LocalDate;
import java.util.Date;

public record ShowsRequest(LocalDate date,
                           String zoneId,
                           ScreenType screenType,
                           int size,
                           int number) {
}
