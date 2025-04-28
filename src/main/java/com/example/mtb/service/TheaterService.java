package com.example.mtb.service;

import com.example.mtb.dto.TheaterRegistrationRequest;
import com.example.mtb.dto.TheaterRequest;
import com.example.mtb.dto.TheaterResponse;
import com.example.mtb.entity.Theater;

public interface TheaterService {
    TheaterResponse registration(String email, TheaterRegistrationRequest request);

    TheaterResponse findById(String theaterId);

    TheaterResponse updateTheater(String theaterId, TheaterRequest theater);
}
