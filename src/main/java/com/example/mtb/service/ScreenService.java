package com.example.mtb.service;

import com.example.mtb.dto.ScreenRegistrationRequest;
import com.example.mtb.dto.ScreenResponse;
import com.example.mtb.entity.Screen;

public interface ScreenService {
    Screen screenRegistration(String theaterId, ScreenRegistrationRequest request);

    ScreenResponse findScreen(String screenId);
}
