package com.example.mtb.dto;

import com.example.mtb.enums.ScreenType;

public record ScreenResponse(ScreenType screenType, int capacity, int noOfRows)  {
}
