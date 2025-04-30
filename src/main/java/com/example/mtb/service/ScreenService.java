package com.example.mtb.service;

import com.example.mtb.dto.ScreenRegisterResponse;
import com.example.mtb.dto.ScreenRegistrationRequest;
import com.example.mtb.dto.ScreenResponse;
import com.example.mtb.entity.Screen;

public interface ScreenService {
    ScreenRegisterResponse screenRegistration(String theaterId, ScreenRegistrationRequest request);

    ScreenResponse findScreen(String screenId);
}
