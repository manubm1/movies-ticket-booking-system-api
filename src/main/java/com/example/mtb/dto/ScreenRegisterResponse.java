package com.example.mtb.dto;

import com.example.mtb.enums.ScreenType;

public record ScreenRegisterResponse (String screenId,ScreenType screenType, int capacity, int noOfRows){
}
