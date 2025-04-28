package com.example.mtb.service;

import com.example.mtb.dto.ScreenRegistrationRequest;
import com.example.mtb.dto.ScreenResponse;

public interface ScreenService {
    ScreenResponse screenRegistration(String theaterId, ScreenRegistrationRequest request);
}
