package com.example.mtb.dto;

import com.example.mtb.entity.Seat;
import com.example.mtb.enums.ScreenType;

import java.util.List;

public record ScreenResponse(ScreenType screenType, int capacity, int noOfRows, List<Seat> seats)  {
}
